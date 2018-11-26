package com.fyh.touchhelperapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author by nate_fu on 2018/11/20.
 * @version vision 1.0
 * @Email: fuyonghui@zjhcsoft.com
 */
public class MainActivity extends AppCompatActivity implements InterfaceOnCheckOrClickListener{

    private List<KeyValueBean> datas;
    private RecyclerView recyclerView;
    private TextView btnAll,btnDelete;
    private MyItemCallback callback;
    private MyAdapter myAdapter;
    private MyGestureListener gestureListener ;
    private boolean isChooseAll;
    private int checkedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    /**
     * 造点测试数据
     */
    private void initData(){
        datas = new ArrayList<>();
        for (int i = 0; i < 20 ; i++) {
            KeyValueBean bean = new KeyValueBean(i+"","第"+i+"个item");
            datas.add(bean);
        }
    }

    private void initView(){
        btnAll =(TextView)findViewById(R.id.tv_all);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAll();
            }
        });

        btnDelete = (TextView)findViewById(R.id.tv_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=myAdapter&&checkedItem>0){
                    datas=myAdapter.getData();
                    if (null!=datas) {
                        //新建一个数组对象存放已经选中的对象
                        List<KeyValueBean> checkedList = new ArrayList<>();
                        for (int i = 0; i < datas.size(); i++) {
                            if (datas.get(i).getChecked()){
                                checkedList.add(datas.get(i));
                            }
                        }
                        //将整个数组移除
                        datas.removeAll(checkedList);
                        //更新下界面展示
                        updataUI();
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        myAdapter = new MyAdapter(MainActivity.this,datas);
        myAdapter.setCheckOrClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        callback = new MyItemCallback(myAdapter);
        callback.setDrag(false);
        MyItemTouchHelper helper = new MyItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
        gestureListener= new MyGestureListener(helper);
        gestureListener.setDoDrag(false);
    }

    /**
     * 允许拖动
     */
    @Override
    public void onViewDragStart(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            if (null != gestureListener && null != callback) {
                callback.setDrag(true);
                gestureListener.setDoDrag(true);
            }
            break;
        }


    }

    /**
     * 关闭拖动
     */
    @Override
    public void onViewDragStop() {
        if (null != gestureListener&&null!=callback) {
            callback.setDrag(false);
            gestureListener.setDoDrag(false);
        }
    }

    @Override
    public void onViewClick(View view, int position) {
        for (int i = position; i > 0; i--) {
            Collections.swap(myAdapter.getData(), i, i - 1);
        }
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckBoxSelected(View view, int position) {
        //获取当前列表的数据
        datas= myAdapter.getData();
        setItemChecked(position,!myAdapter.getData().get(position).getChecked());
        updataUI();
        myAdapter.notifyDataSetChanged();

    }

    /**
     * 在绑定的数据上操作checkbox的状态
     * @param position
     * @param isChecked
     */
    private void setItemChecked(int position ,boolean isChecked){
        datas.get(position).setChecked(isChecked);
    }

    /**
     * 更新选中的条数
     */
    private void updataUI(){
        checkedItem = 0;
        for (int i = 0; i <datas.size() ; i++) {
            if (datas.get(i).getChecked()){
                checkedItem++;
            }
        }
        if (checkedItem>0) {
            btnDelete.setText("刪除("+checkedItem+")");
            btnDelete.setTextColor(getResources().getColor(R.color.colorPrimary));

        }else{
            btnDelete.setText("刪除");
            btnDelete.setTextColor(getResources().getColor(R.color.gray));

        }
        if (checkedItem==datas.size()){
            isChooseAll=true;
            btnAll.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else{
            isChooseAll=false;
            btnAll.setTextColor(getResources().getColor(R.color.gray));
        }
    }

    /**
     * 全選
     */
    private void chooseAll(){
        if (null!=myAdapter) {
            datas=myAdapter.getData();
            if (!datas.isEmpty()) {
                for (int i = 0; i < datas.size(); i++) {
                    datas.get(i).setChecked(!isChooseAll);
                }
                isChooseAll = !isChooseAll;
                updataUI();
                myAdapter.notifyDataSetChanged();

            }
        }
    }

}

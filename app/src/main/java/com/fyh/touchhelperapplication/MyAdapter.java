package com.fyh.touchhelperapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author by nate_fu on 2018/11/20.
 * @version vision 1.0
 * @Email: fuyonghui@zjhcsoft.com
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements InterfaceTouchHelper {

    private List<KeyValueBean> data = new ArrayList<>();
    private LayoutInflater inflater;
    private InterfaceOnCheckOrClickListener checkOrClickListener;

    private Context context;

    public MyAdapter(Context context, @NonNull List<KeyValueBean> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        }
    }

    public void setCheckOrClickListener(InterfaceOnCheckOrClickListener checkOrClickListener) {
        this.checkOrClickListener = checkOrClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_touchhelp, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final KeyValueBean bean = data.get(position);
        holder.text.setText(bean.getValue());
        holder.cb.setChecked(bean.getChecked());
        holder.ivDrag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                checkOrClickListener.onViewTouch(v,event);
                return false;
            }
        });
        holder.ivStick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOrClickListener.onViewClick(v,position);
            }
        });

        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOrClickListener.onCheckBoxSelected(v,position);
            }
        });

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onItemMove(RecyclerView.ViewHolder holder, int fromPosition, int toPosition) {

        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        //刷新列表
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSelect(RecyclerView.ViewHolder holder) {
//        holder.itemView.setScaleX(0.8f);
//        holder.itemView.setScaleY(0.8f);
    }

    @Override
    public void onItemClear(RecyclerView.ViewHolder holder) {
//        holder.itemView.setScaleX(1.0f);
//        holder.itemView.setScaleY(1.0f);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivDrag, ivStick;
        private TextView text;
        private CheckBox cb;

        public ViewHolder(View itemView) {
            super(itemView);
            ivDrag = (ImageView) itemView.findViewById(R.id.iv_drag);
            ivStick = (ImageView) itemView.findViewById(R.id.iv_stick);
            text = (TextView) itemView.findViewById(R.id.tv_info);
            cb= (CheckBox) itemView.findViewById(R.id.cb);
        }
    }

    /**
     * 获取操作后的数据
     */
    public List<KeyValueBean> getData(){
        return  data;
    }
}

package com.fyh.touchhelperapplication;

import android.view.MotionEvent;
import android.view.View;

/**
 * @author by nate_fu on 2018/11/21.
 * @version vision 1.0
 * @Email: fuyonghui@zjhcsoft.com
 */
public interface InterfaceOnCheckOrClickListener {
    /**
     * 点击拖动
     */
    void onViewDragStart(MotionEvent event);

    /**
     * 停止拖动
     */
    void onViewDragStop();

    /**
     * 点击事件
     * @param view
     * @param position
     */
    void onViewClick(View view,int position);

    /**
     * 选中的CheckBox
     * @param view
     * @param position
     */
    void onCheckBoxSelected(View view ,int position);
}

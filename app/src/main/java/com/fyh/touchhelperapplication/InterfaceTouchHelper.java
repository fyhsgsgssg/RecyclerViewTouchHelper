package com.fyh.touchhelperapplication;

import android.support.v7.widget.RecyclerView;

/**
 * @author by nate_fu on 2018/11/21.
 * @version vision 1.0
 * @Email: fuyonghui@zjhcsoft.com
 */
public interface InterfaceTouchHelper {
    /**
     * 移动item
     * @param holder
     * @param fromPosition
     * @param toPosition
     */
    void onItemMove(RecyclerView.ViewHolder holder, int fromPosition, int toPosition);

    /**
     *选中的 item
     * @param holder
     */
    void onItemSelect(RecyclerView.ViewHolder holder);

    /**
     * 移动后的更新
     * @param holder
     */
    void onItemClear(RecyclerView.ViewHolder holder);

}

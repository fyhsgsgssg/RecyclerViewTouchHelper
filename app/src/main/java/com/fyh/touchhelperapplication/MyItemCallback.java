package com.fyh.touchhelperapplication;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * @author by nate_fu on 2018/11/20.
 * @version vision 1.0
 * @Email: fuyonghui@zjhcsoft.com
 */
public class MyItemCallback extends ItemTouchHelper.Callback {

    private InterfaceTouchHelper interfaceTouchHelper;
    /**
     * 动态设置能够拖动
     */
    private boolean  isDrag=false;

    public MyItemCallback(InterfaceTouchHelper interfaceTouchHelper) {
        this.interfaceTouchHelper = interfaceTouchHelper;
    }

    public void setDrag(boolean isDrag) {
        this.isDrag = isDrag;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN ;
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        interfaceTouchHelper.onItemMove(viewHolder, viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            interfaceTouchHelper.onItemSelect(viewHolder);
        }
        super.onSelectedChanged(viewHolder,actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (!recyclerView.isComputingLayout()) {
            interfaceTouchHelper.onItemClear(viewHolder);
        }
        super.clearView(recyclerView,viewHolder);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return isDrag;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }
}

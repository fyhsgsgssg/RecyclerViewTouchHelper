package com.fyh.touchhelperapplication;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * @author by nate_fu on 2018/11/20.
 * @version vision 1.0
 * @Email: fuyonghui@zjhcsoft.com
 */
public class MyItemTouchHelper extends ItemTouchHelper {
    /**
     * Creates an ItemTouchHelper that will work with the given Callback.
     * <p>
     * You can attach ItemTouchHelper to a RecyclerView via
     * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
     *
     * @param callback The Callback which controls the behavior of this touch helper.
     */
    public MyItemTouchHelper(Callback callback) {
        super(callback);
    }



}

package com.fyh.touchhelperapplication;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.lang.reflect.Field;

/**
 * @author by nate_fu on 2018/11/21.
 * @version vision 1.0
 * @Email: fuyonghui@zjhcsoft.com
 */
public class MyGestureListener {
    private GestureDetector.OnGestureListener mGestureListener;
    private MySimpleOnGestureListener mySimpleOnGestureListener;
    private boolean mDoDrag = true;

    public MyGestureListener(ItemTouchHelper helper) {
        mySimpleOnGestureListener = new MySimpleOnGestureListener();
        try {
            //反射得到 ItemTouchHelper 对象 中的GestureDetectorCompat
            Field fGesDetector = ItemTouchHelper.class.getDeclaredField("mGestureDetector");
            fGesDetector.setAccessible(true);
            Object objGesDetector = fGesDetector.get(helper);
            // 反射 得到 GestureDetectorCompat 中的GestureDetectorCompatImpl
            Field fImpl = GestureDetectorCompat.class.getDeclaredField("mImpl");
            fImpl.setAccessible(true);
            Object objImpl = fImpl.get(objGesDetector);


            Field fLis = null;
            try {
                fLis = objImpl.getClass().getDeclaredField("mListener");
            }catch (Exception e){e.printStackTrace();}

            Object oDet = null;
            if (fLis == null){
                Field fDet = objImpl.getClass().getDeclaredField("mDetector");
                fDet.setAccessible(true);

                oDet = fDet.get(objImpl);
                fLis = oDet.getClass().getDeclaredField("mListener");
            }
            fLis.setAccessible(true);

            mGestureListener = (GestureDetector.OnGestureListener) fLis.get(oDet);

            fLis.set(oDet, mySimpleOnGestureListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDoDrag(boolean drag){
        mDoDrag = drag;
    }

    public class MySimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onShowPress(MotionEvent e) {
            if (mDoDrag) {
                mGestureListener.onLongPress(e);
            }
        }
    }
}

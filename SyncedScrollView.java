package com.alibaba_inc.helloworld.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

/**
 * Created by helloworld on 16-4-14.
 */
public class SyncedScrollView extends ScrollView {

    /**
     * custom ontouch listener
     */
    private OnTouchListener mTouchListener;

    /**
     * touch sync listener
     */
    private OnTouchListener mTouchSyncListener;

    /**
     * touch listener for intercept all touch event
     */
    private class SyncedTouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            boolean result = false;
            if (mTouchListener != null) {
                result = mTouchListener.onTouch(v, event);
            }

            if (!result && mTouchSyncListener != null && mTouchSyncListener.onTouch(v, event)) {
                result = true;
            }

            return result;
        }
    }

    public SyncedScrollView(Context context) {
        super(context);
        setOnTouchListener(new SyncedTouchListener());
    }

    public SyncedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(new SyncedTouchListener());
    }

    public SyncedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(new SyncedTouchListener());
    }

    public SyncedScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOnTouchListener(new SyncedTouchListener());
    }

    /**
     * on touch listener for sync
     * @param l
     */
    protected void setOnTouchListenerForSync(OnTouchListener l) {
        this.mTouchSyncListener = l;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        if (l instanceof SyncedTouchListener) {
            super.setOnTouchListener(l);
        } else {
            this.mTouchListener = l;
        }
    }
}

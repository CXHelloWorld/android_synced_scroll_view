package com.alibaba_inc.helloworld.myapplication;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by helloworld on 16-4-13.
 * group for scroll view, in one group scroll view will scroll in the same way
 */
public class SyncedScrollGroup {

    /**
     * initial size for array list
     */
    private static final int INIT_SIZE = 16;

    /**
     * list for SyncedHorizontalScrollView
     */
    ArrayList<SyncedHorizontalScrollView> horizontalScrollViews;

    /**
     * list for SyncedHorizontalScrollView
     */
    ArrayList<SyncedScrollView> VerticalScrollViews;

    /**
     * add scroll view
     * @param v horizontal scroll view
     */
    public void addScrollView(SyncedHorizontalScrollView v) {
        if (horizontalScrollViews == null) {
            horizontalScrollViews = new ArrayList<>(INIT_SIZE);
        }

        horizontalScrollViews.add(v);
        v.setOnTouchListenerForSync(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                for (SyncedHorizontalScrollView view : horizontalScrollViews) {
                    view.onTouchEvent(event);
                }

                return true;
            }
        });
    }

    /**
     * add scroll view
     * @param v vertical scroll view
     */
    public void addScrollView(SyncedScrollView v) {
        if (VerticalScrollViews == null) {
            VerticalScrollViews = new ArrayList<>(INIT_SIZE);
        }

        VerticalScrollViews.add(v);
        v.setOnTouchListenerForSync(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                for (SyncedScrollView view : VerticalScrollViews) {
                    view.onTouchEvent(event);
                }

                return true;
            }
        });
    }

    /**
     * remove scroll view
     * @param v horizontal scroll view
     */
    public void removeView(SyncedHorizontalScrollView v) {
        v.setOnTouchListenerForSync(null);
        horizontalScrollViews.remove(v);
    }

    /**
     * remove scroll view
     * @param v vertical scroll view
     */
    public void removeView(SyncedScrollView v) {
        v.setOnTouchListenerForSync(null);
        VerticalScrollViews.remove(v);
    }

    /**
     * sync scroll to align the scrollx and scrolly
     * actually, views not attached to the window can process the touch
     * event in the right way and the scroll x and y will be right.
     * But fling may not effected when one view's parent is null, so when it
     * was added back to the parent, sync may be necessary, specially in ListView
     * @param v view to sync
     */
    public void syncScroll(SyncedHorizontalScrollView v) {
        for (SyncedHorizontalScrollView view : horizontalScrollViews) {
            if (view != v && view.getParent() != null) {
                v.scrollTo(view.getScrollX(), view.getScrollY());
            }
        }
    }

    /**
     * sync scroll to align the scrollx and scrolly
     * actually, views not attached to the window can process the touch
     * event in the right way and the scroll x and y will be right.
     * But fling may not effected when one view's parent is null, so when it
     * was added back to the parent, sync may be necessary, specially in ListView
     * @param v view to sync
     */
    public void syncScroll(SyncedScrollView v) {
        for (SyncedScrollView view : VerticalScrollViews) {
            if (view != v && view.getParent() != null) {
                v.scrollTo(view.getScrollX(), view.getScrollY());
            }
        }
    }
}

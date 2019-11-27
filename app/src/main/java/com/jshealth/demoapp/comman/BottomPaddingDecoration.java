package com.jshealth.demoapp.comman;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class BottomPaddingDecoration extends RecyclerView.ItemDecoration {
    private final int bottomPadding;

    public BottomPaddingDecoration(int bottomPadding) {
        this.bottomPadding = bottomPadding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (position == parent.getAdapter().getItemCount() - 1) {
            outRect.set(0, 0, 0, bottomPadding);
        }
    }
}

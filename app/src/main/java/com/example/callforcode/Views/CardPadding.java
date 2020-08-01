package com.example.callforcode.Views;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CardPadding extends RecyclerView.ItemDecoration{
    private  final int left;
    private  final int top;
    private  final int right;
    private  final int bottom;

    public  CardPadding(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(left,top,right,bottom);
    }
}

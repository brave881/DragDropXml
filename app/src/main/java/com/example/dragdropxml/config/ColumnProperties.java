package com.example.dragdropxml.config;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Properties of adding/inserting column to the {@link BoardView}
 */
public class ColumnProperties {

    private DragItemAdapter mDragItemAdapter;
    private LayoutManager mLayoutManager;
    private List<RecyclerView.ItemDecoration> mItemDecorations;
    private boolean mHasFixedItemSize;
    private int mColumnBackgroundColor;
    private int mItemsSectionBackgroundColor;
    private View mHeader;
    private View mFooter;
    private View mColumnDragView;
    private Integer mColumnWidth;
    private Drawable mColumnBackgroundDrawable;

    private ColumnProperties(DragItemAdapter adapter, LayoutManager layoutManager, List<RecyclerView.ItemDecoration> itemDecorations, boolean hasFixedItemSize, int columnBackgroundColor, int itemsSectionBackgroundColor, View columnDragView, View header, View footer, Integer columnWidth, Drawable columnBackgroundDrawable) {
        mDragItemAdapter = adapter;
        mLayoutManager = layoutManager;
        mItemDecorations = itemDecorations;
        mHasFixedItemSize = hasFixedItemSize;
        mColumnBackgroundColor = columnBackgroundColor;
        mItemsSectionBackgroundColor = itemsSectionBackgroundColor;
        mHeader = header;
        mFooter = footer;
        mColumnDragView = columnDragView;
        mColumnWidth = columnWidth;
        mColumnBackgroundDrawable = columnBackgroundDrawable;
    }

    @NonNull
    DragItemAdapter getDragItemAdapter() {
        return mDragItemAdapter;
    }

    LayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    @NonNull
    List<RecyclerView.ItemDecoration> getItemDecorations() {
        return mItemDecorations;
    }

    boolean hasFixedItemSize() {
        return mHasFixedItemSize;
    }

    @ColorInt
    int getColumnBackgroundColor() {
        return mColumnBackgroundColor;
    }

    @ColorInt
    int getItemsSectionBackgroundColor() {
        return mItemsSectionBackgroundColor;
    }

    View getHeader() {
        return mHeader;
    }

    View getFooter() {
        return mFooter;
    }

    View getColumnDragView() {
        return mColumnDragView;
    }

    Integer getColumnWidth() {
        return mColumnWidth;
    }

    Drawable getColumnBackgroundDrawable() {
        return mColumnBackgroundDrawable;
    }

    /**
     * Builder for {@link ColumnProperties}.
     */
    public static class Builder {

        private DragItemAdapter mDragItemAdapter;
        private LayoutManager mLayoutManager = null;
        private ArrayList<RecyclerView.ItemDecoration> mItemDecoration = new ArrayList<>();
        private boolean mHasFixedItemSize = false;
        private int mColumnBackgroundColor = Color.TRANSPARENT;
        private int mItemsSectionBackgroundColor = Color.TRANSPARENT;
        private View mHeader = null;
        private View mFooter = null;
        private View mColumnDragView = null;
        private Integer mColumnWidth = null;
        private Drawable mColumnBackgroundDrawable = null;

        private Builder(@NonNull DragItemAdapter adapter) {

            mDragItemAdapter = adapter;
        }

        public static Builder newBuilder(@NonNull DragItemAdapter adapter) {
            return new Builder(adapter);
        }

        public Builder setLayoutManager(LayoutManager layoutManager) {
            mLayoutManager = layoutManager;
            return this;
        }

        public Builder addItemDecorations(@NonNull RecyclerView.ItemDecoration... itemDecorations) {
            Collections.addAll(this.mItemDecoration, itemDecorations);
            return this;
        }

        public Builder setHasFixedItemSize(boolean hasFixedItemSize) {
            mHasFixedItemSize = hasFixedItemSize;
            return this;
        }

        public Builder setColumnBackgroundColor(@ColorInt int backgroundColor) {
            mColumnBackgroundColor = backgroundColor;
            return this;
        }

        public Builder setItemsSectionBackgroundColor(@ColorInt int backgroundColor) {
            mItemsSectionBackgroundColor = backgroundColor;
            return this;
        }

        public Builder setHeader(@Nullable View header) {
            mHeader = header;
            return this;
        }

        public Builder setFooter(@Nullable View footer) {
            mFooter = footer;
            return this;
        }

        public Builder setColumnDragView(@Nullable View columnDragView) {
            mColumnDragView = columnDragView;
            return this;
        }

        public Builder setColumnWidth(@Nullable Integer columnWidth) {
            mColumnWidth = columnWidth;
            return this;
        }


        public Builder setColumnBackgroundDrawable(@Nullable Drawable backgroundDrawable) {
            mColumnBackgroundDrawable = backgroundDrawable;
            return this;
        }

        public ColumnProperties build() {
            return new ColumnProperties(mDragItemAdapter, mLayoutManager, mItemDecoration, mHasFixedItemSize, mColumnBackgroundColor, mItemsSectionBackgroundColor, mColumnDragView, mHeader, mFooter, mColumnWidth, mColumnBackgroundDrawable);
        }
    }
}

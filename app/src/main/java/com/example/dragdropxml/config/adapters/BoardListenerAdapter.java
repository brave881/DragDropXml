package com.example.dragdropxml.config.adapters;

import com.example.dragdropxml.config.callbacks.BoardListener;

public abstract class BoardListenerAdapter implements BoardListener {
    @Override
    public void onItemDragStarted(int column, int row) {
    }

    @Override
    public void onItemDragEnded(int fromColumn, int fromRow, int toColumn, int toRow) {
    }

    @Override
    public void onItemChangedPosition(int oldColumn, int oldRow, int newColumn, int newRow) {
    }

    @Override
    public void onItemChangedColumn(int oldColumn, int newColumn) {
    }

    @Override
    public void onFocusedColumnChanged(int oldColumn, int newColumn) {
    }

    @Override
    public void onColumnDragStarted(int position) {
    }

    @Override
    public void onColumnDragChangedPosition(int oldPosition, int newPosition) {
    }

    @Override
    public void onColumnDragEnded(int fromPosition, int toPosition) {
    }
}

package com.example.dragdropxml.config.callbacks;

public interface BoardListener {
    void onItemDragStarted(int column, int row);

    void onItemDragEnded(int fromColumn, int fromRow, int toColumn, int toRow);

    void onItemChangedPosition(int oldColumn, int oldRow, int newColumn, int newRow);

    void onItemChangedColumn(int oldColumn, int newColumn);

    void onFocusedColumnChanged(int oldColumn, int newColumn);

    void onColumnDragStarted(int position);

    void onColumnDragChangedPosition(int oldPosition, int newPosition);

    void onColumnDragEnded(int fromPosition, int toPosition);
}

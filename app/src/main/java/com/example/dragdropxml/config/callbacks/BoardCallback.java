package com.example.dragdropxml.config.callbacks;

public interface BoardCallback {
    boolean canDragItemAtPosition(int column, int row);

    boolean canDropItemAtPosition(int oldColumn, int oldRow, int newColumn, int newRow);

    boolean canDragColumnAtPosition(int index);

    boolean canDropColumnAtPosition(int oldIndex, int newIndex);
}

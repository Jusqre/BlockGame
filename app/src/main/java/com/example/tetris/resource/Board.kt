package com.example.tetris.resource

import android.widget.TextView

class Board(private val row: Int, private val column: Int) {
    var board: Array<Array<TextView?>> = Array(row) { Array(column) { null } }

}

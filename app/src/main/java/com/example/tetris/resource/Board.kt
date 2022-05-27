package com.example.tetris.resource

import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.core.graphics.toColorInt

class Board(row: Int, private val column: Int) {
    var board: Array<Array<TextView?>> = Array(row) { Array(column) { null } }

    fun clearing() {
        for (i in board.indices) {
            var token = true
            for (j in board[i]) {
                if ((j?.background as ColorDrawable).color == "#FFFFFF".toColorInt()) {
                    token = false
                    break
                }
            }
            if (token) {
                for (j in board[i]) {
                    j?.setBackgroundColor("#FFFFFF".toColorInt())
                }
            }
        }
    }

    fun sliding() {

    }
}

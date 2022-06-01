package com.example.tetris.resource

import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.core.graphics.toColorInt

class Board(row: Int, private val column: Int) {
    var board: Array<Array<TextView?>> = Array(row) { Array(column) { null } }

    fun getScoreWithClearing(): Int {
        var i = board.size - 1
        var score = 0

        while (i > 0) {
            var token = true
            for (j in board[i]) {
                if ((j?.background as ColorDrawable).color == "#FFFFFF".toColorInt()) {
                    token = false
                    break
                }
            }
            if (token) {
                score+= 10
                for (k in i downTo 0) {
                    if (k >= 1) {
                        for (j in board[k].indices) {
                            board[k][j]?.setBackgroundColor((board[k - 1][j]?.background as ColorDrawable).color)
                            board[k - 1][j]?.setBackgroundColor("#FFFFFF".toColorInt())
                        }
                    } else {
                        for (j in board[k].indices) {
                            board[k][j]?.setBackgroundColor("#FFFFFF".toColorInt())
                        }
                    }
                }
            } else {
                i--
            }
        }
        return score
    }
}

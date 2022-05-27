package com.example.tetris.resource

import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.core.graphics.toColorInt
import java.lang.Exception

/**
 * shape
 * 0 -> 사각형
 * 1 -> 가로 1자형
 * 2 -> 세로 1자형
 *
 */

data class Block(val shape: Int, var x: Int, var y: Int, val color: Int) {
    private var prevIdx: MutableList<IntArray>
    private var idx: MutableList<IntArray>

    init {
        this.prevIdx = setBlockIndex()
        this.idx = setBlockIndex()
    }

    fun moveLeft() {
        if ((shape == 0 && y == 0)) {
            return
        }
        y -= 1
    }

    fun moveRight() {
        if (shape == 0 && y == 5) {
            return
        }
        y += 1
    }

    fun setBlockIndex(): MutableList<IntArray> {
        val tempList = mutableListOf<IntArray>()

        prevIdx = this.idx

        when (shape) {
            0 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x + 1, y + 1))
            }
            1 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x, y + 2))
                tempList.add(intArrayOf(x, y + 3))
            }
            2 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 2, y))
                tempList.add(intArrayOf(x + 3, y))
            }
        }
        this.idx = tempList
        return tempList
    }

    fun drawOnBoard(board: Array<Array<TextView?>>) {
        for (prev in prevIdx) {
            board[prev[0]][prev[1]]?.setBackgroundColor("#FFFFFF".toColorInt())
        }
        for (cur in idx) {
            board[cur[0]][cur[1]]?.setBackgroundColor(color)
        }

    }

    fun isMovable(board: Array<Array<TextView?>>, xx: Int, yy: Int): Boolean {
        var answer = true
        when (shape) {
            0 -> answer = x + xx <= 9
            1 -> answer = x + xx <= 10
            2 -> answer = x + xx <= 7
        }


        for (list in idx) {
            try {
                if ((board[list[0]+xx][list[1]+yy]?.background as ColorDrawable).color != "#FFFFFF".toColorInt()
                    && (board[list[0]+xx][list[1]+yy]?.background as ColorDrawable).color != color
                ) {
                    return false
                }
            } catch(e: Exception) {
                continue
            }
        }

        return answer
    }

}

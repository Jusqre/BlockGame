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
 * 3 -> ㄴ자 형
 * 4 -> ㄹ자 형
 */

data class Block(var shape: Int, var x: Int, var y: Int, val color: Int) {
    private var prevIdx: MutableList<IntArray>
    private var idx: MutableList<IntArray>

    init {
        this.prevIdx = setBlockIndex()
        this.idx = setBlockIndex()
    }

    fun moveLeft(board: Array<Array<TextView?>>) {
        if (isMovable(board, 0, -1)) {
            y -= 1
            setBlockIndex()
            drawOnBoard(board)
        }
    }

    fun moveRight(board: Array<Array<TextView?>>) {
        if (isMovable(board, 0, 1)) {
            y += 1
            setBlockIndex()
            drawOnBoard(board)
        }
    }

    fun rotate(board: Array<Array<TextView?>>) {
        when (shape) {
            1 -> {
                shape = 2
                x -= 1
                y += 1
                setBlockIndex()
                drawOnBoard(board)
            }
            2 -> {
                shape = 1
                x += 1
                y -= 1
                setBlockIndex()
                drawOnBoard(board)
            }
        }
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
            3 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 1, y + 2))
            }
            4 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 1, y + 2))
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

    fun isRotatable(board: Array<Array<TextView?>>): Boolean {
        when (shape) {
        }


        return true
    }

    fun isMovable(board: Array<Array<TextView?>>, xx: Int, yy: Int): Boolean {
        var answer = true
        when (shape) {
            0 -> answer = (x + 1) + xx <= 11 && y + yy >= 0 && (y + 1) + yy <= 7
            1 -> answer = x + xx <= 11 && y + yy >= 0 && (y + 3) + yy <= 7
            2 -> answer = (x + 3) + xx <= 11 && y + yy >= 0 && y + yy <= 7
            3 -> answer = (x + 1) + xx <= 11 && y + yy >= 0 && (y + 2) + yy <= 7
            4 -> answer = (x + 1) + xx <= 11 && y + yy >= 0 && (y + 2) + yy <= 7
        }


        for (list in idx) {
            try {
                if ((board[list[0] + xx][list[1] + yy]?.background as ColorDrawable).color != "#FFFFFF".toColorInt()
                    && (board[list[0] + xx][list[1] + yy]?.background as ColorDrawable).color != color
                ) {
                    return false
                }
            } catch (e: Exception) {
                continue
            }
        }

        return answer
    }

}

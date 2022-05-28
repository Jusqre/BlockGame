package com.example.tetris.resource

import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.core.graphics.toColorInt
import java.lang.Exception

/**
 * shape
 * 0 -> 사각형
 * 1 -> 가로 1자형, 1.1 -> 세로 1자형
 * 2 -> ㄴ자 형, 2.1 -> 90, 2.2 -> 180, 2.3 -> 270
 * 3 -> ㄹ자 형 3.1 -> 90
 * 4 -> ㅗ, 4.1 -> ㅏ, 4.2 -> ㅜ, 4.3 -> ㅓ
 */

data class Block(var shape: Double, var x: Int, var y: Int, val color: Int) {
    private lateinit var prevIdx: MutableList<IntArray>
    private var idx: MutableList<IntArray>

    init {
        this.idx = getBlockInfo(shape)
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
            1.0 -> {
                x -= 1
                y += 1
                if (isRotatable(board, getBlockInfo(1.1))) {
                    shape = 1.1
                    setBlockIndex()
                    drawOnBoard(board)
                } else {
                    x += 1
                    y -= 1
                }
            }
            1.1 -> {
                x += 1
                y -= 1
                if (isRotatable(board, getBlockInfo(1.0))) {
                    shape = 1.0
                    setBlockIndex()
                    drawOnBoard(board)
                } else {
                    x -= 1
                    y += 1
                }
            }
            2.0 -> {
                if (isRotatable(board, getBlockInfo(2.1))) {
                    shape = 2.1
                    setBlockIndex()
                    drawOnBoard(board)
                }
            }
            2.1 -> {
                if (isRotatable(board, getBlockInfo(2.2))) {
                    shape = 2.2
                    setBlockIndex()
                    drawOnBoard(board)
                }
            }
            2.2 -> {
                if (isRotatable(board, getBlockInfo(2.3))) {
                    shape = 2.3
                    setBlockIndex()
                    drawOnBoard(board)
                }
            }
            2.3 -> {
                if (isRotatable(board, getBlockInfo(2.0))) {
                    shape = 2.0
                    setBlockIndex()
                    drawOnBoard(board)
                }
            }
            3.0 -> {
                if (isRotatable(board, getBlockInfo(3.1))) {
                    shape = 3.1
                    setBlockIndex()
                    drawOnBoard(board)
                }
            }
            3.1 -> {
                if (isRotatable(board, getBlockInfo(3.0))) {
                    shape = 3.0
                    setBlockIndex()
                    drawOnBoard(board)
                }
            }
            4.0 -> {
                if (isRotatable(board, getBlockInfo(4.1))) {
                    shape = 4.1
                    setBlockIndex()
                    drawOnBoard(board)
                }
            }
            4.1 -> {
                if (isRotatable(board, getBlockInfo(4.2))) {
                    shape = 4.2
                    setBlockIndex()
                    drawOnBoard(board)
                }
            }
            4.2 -> {
                if (isRotatable(board, getBlockInfo(4.3))) {
                    shape = 4.3
                    setBlockIndex()
                    drawOnBoard(board)
                }
            }
            4.3 -> {
                if (isRotatable(board, getBlockInfo(4.0))) {
                    shape = 4.0
                    setBlockIndex()
                    drawOnBoard(board)
                }
            }
        }
    }

    private fun getBlockInfo(shape: Double): MutableList<IntArray> {
        val tempList = mutableListOf<IntArray>()

        when (shape) {
            0.0 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x + 1, y + 1))
            }
            1.0 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x, y + 2))
                tempList.add(intArrayOf(x, y + 3))
            }
            1.1 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 2, y))
                tempList.add(intArrayOf(x + 3, y))
            }
            2.0 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 1, y + 2))
            }
            2.1 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x, y + 2))
            }
            2.2 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x, y + 2))
                tempList.add(intArrayOf(x + 1, y + 2))
            }
            2.3 -> {
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 2, y + 1))
                tempList.add(intArrayOf(x + 2, y))
            }
            3.0 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 1, y + 2))
            }
            3.1 -> {
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 2, y))
            }
            4.0 -> {
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 1, y + 2))
            }
            4.1 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 2, y))
                tempList.add(intArrayOf(x + 1, y + 1))
            }
            4.2 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x, y + 2))
                tempList.add(intArrayOf(x + 1, y + 1))
            }
            4.3 -> {
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 2, y + 1))
                tempList.add(intArrayOf(x + 1, y))
            }
        }
        return tempList
    }

    fun setBlockIndex() {
        val tempList = getBlockInfo(shape)

        this.prevIdx = this.idx
        this.idx = tempList
    }

    fun drawOnBoard(board: Array<Array<TextView?>>) {
        for (prev in prevIdx) {
            board[prev[0]][prev[1]]?.setBackgroundColor("#FFFFFF".toColorInt())
        }
        for (cur in idx) {
            board[cur[0]][cur[1]]?.setBackgroundColor(color)
        }

    }

    private fun isRotatable(
        board: Array<Array<TextView?>>,
        tempList: MutableList<IntArray>
    ): Boolean {
        for (list in tempList) {
            try {
                if ((board[list[0]][list[1]]?.background as ColorDrawable).color != "#FFFFFF".toColorInt()
                    && (board[list[0]][list[1]]?.background as ColorDrawable).color != color
                ) {
                    return false
                }
            } catch (e: Exception) {
                return false
            }
        }
        return true
    }

    fun isMovable(board: Array<Array<TextView?>>, xx: Int, yy: Int): Boolean {
        for (list in idx) {
            try {
                if ((board[list[0] + xx][list[1] + yy]?.background as ColorDrawable).color != "#FFFFFF".toColorInt()
                    && (board[list[0] + xx][list[1] + yy]?.background as ColorDrawable).color != color
                ) {
                    return false
                }
            } catch (e: Exception) {
                return false
            }
        }

        return true
    }

}

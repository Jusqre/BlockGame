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
 * 5 -> 역 ㄹ자형 5.1 -> 90
 * 6 -> 역 ㄴ자형
 */

data class Block(var shape: Double, var x: Int, var y: Int, val color: Int) {
    private var previousIndex: MutableList<IntArray>
    private var blockIndex: MutableList<IntArray>

    init {
        this.blockIndex = getBlockInfo(shape)
        this.previousIndex = this.blockIndex
    }

    fun moveBelow(board: Array<Array<TextView?>>) {
        if (isMovable(board, 1, 0)) {
            x += 1
            setBlockIndex()
        }
    }

    fun moveLeft(board: Array<Array<TextView?>>) {
        if (isMovable(board, 0, -1)) {
            y -= 1
            setBlockIndex()
        }
    }

    fun moveRight(board: Array<Array<TextView?>>) {
        if (isMovable(board, 0, 1)) {
            y += 1
            setBlockIndex()
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
                } else {
                    x -= 1
                    y += 1
                }
            }
            2.0 -> {
                if (isRotatable(board, getBlockInfo(2.1))) {
                    shape = 2.1
                    setBlockIndex()
                }
            }
            2.1 -> {
                if (isRotatable(board, getBlockInfo(2.2))) {
                    shape = 2.2
                    setBlockIndex()
                }
            }
            2.2 -> {
                if (isRotatable(board, getBlockInfo(2.3))) {
                    shape = 2.3
                    setBlockIndex()
                }
            }
            2.3 -> {
                if (isRotatable(board, getBlockInfo(2.0))) {
                    shape = 2.0
                    setBlockIndex()
                }
            }
            3.0 -> {
                if (isRotatable(board, getBlockInfo(3.1))) {
                    shape = 3.1
                    setBlockIndex()
                }
            }
            3.1 -> {
                if (isRotatable(board, getBlockInfo(3.0))) {
                    shape = 3.0
                    setBlockIndex()
                }
            }
            4.0 -> {
                if (isRotatable(board, getBlockInfo(4.1))) {
                    shape = 4.1
                    setBlockIndex()
                }
            }
            4.1 -> {
                if (isRotatable(board, getBlockInfo(4.2))) {
                    shape = 4.2
                    setBlockIndex()
                }
            }
            4.2 -> {
                if (isRotatable(board, getBlockInfo(4.3))) {
                    shape = 4.3
                    setBlockIndex()
                }
            }
            4.3 -> {
                if (isRotatable(board, getBlockInfo(4.0))) {
                    shape = 4.0
                    setBlockIndex()
                }
            }
            5.0 -> {
                if (isRotatable(board, getBlockInfo(5.1))) {
                    shape = 5.1
                    setBlockIndex()
                }
            }
            5.1 -> {
                if (isRotatable(board, getBlockInfo(5.0))) {
                    shape = 5.0
                    setBlockIndex()
                }
            }
            6.0 -> {
                if (isRotatable(board, getBlockInfo(6.1))) {
                    shape = 6.1
                    setBlockIndex()
                }
            }
            6.1 -> {
                if (isRotatable(board, getBlockInfo(6.2))) {
                    shape = 6.2
                    setBlockIndex()
                }
            }
            6.2 -> {
                if (isRotatable(board, getBlockInfo(6.3))) {
                    shape = 6.3
                    setBlockIndex()
                }
            }
            6.3 -> {
                if (isRotatable(board, getBlockInfo(6.0))) {
                    shape = 6.0
                    setBlockIndex()
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
                tempList.add(intArrayOf(x + 2, y))
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
            5.0 -> {
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x, y + 2))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 1, y + 1))
            }
            5.1 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 2, y + 1))
            }
            6.0 -> {
                tempList.add(intArrayOf(x, y + 2))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 1, y + 2))
            }
            6.1 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x + 2, y + 1))
                tempList.add(intArrayOf(x + 1, y))
                tempList.add(intArrayOf(x + 2, y))
            }
            6.2 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x, y + 1))
                tempList.add(intArrayOf(x, y + 2))
                tempList.add(intArrayOf(x + 1, y))
            }
            6.3 -> {
                tempList.add(intArrayOf(x, y))
                tempList.add(intArrayOf(x + 1, y + 1))
                tempList.add(intArrayOf(x + 2, y + 1))
                tempList.add(intArrayOf(x, y + 1))
            }
        }
        return tempList
    }

    fun setBlockIndex() {
        val tempList = getBlockInfo(shape)

        this.previousIndex = this.blockIndex
        this.blockIndex = tempList
    }

    fun drawOnBoard(board: Array<Array<TextView?>>) {
        for (prev in previousIndex) {
            board[prev[0]][prev[1]]?.setBackgroundColor("#FFFFFF".toColorInt())
        }
        for (cur in blockIndex) {
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
        for (list in blockIndex) {
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

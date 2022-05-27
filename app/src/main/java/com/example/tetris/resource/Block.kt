package com.example.tetris.resource

import android.widget.TextView
import androidx.core.graphics.toColorInt

data class Block(val shape: Int, var x: Int, var y: Int, val color: Int) {
    var prevIdx: MutableList<IntArray>
    var idx: MutableList<IntArray>

    init {
        this.prevIdx = setBlockIndex()
        this.idx = setBlockIndex()
    }

    fun moveLeft() {
        y -= 1
        setBlockIndex()
    }
    fun moveRight() {
        y += 1
        setBlockIndex()
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

    fun checkStatus(block: Block): Boolean {


        return true

    }

}

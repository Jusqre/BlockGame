package com.example.tetris.resource

import androidx.annotation.ColorInt

data class Block(val shape: Int, val x: Int, val y: Int, val color: ColorInt) {
    private var idx: MutableList<IntArray>

    init {
        this.idx = setBlockIndex()
    }

    private fun setBlockIndex(): MutableList<IntArray> {
        val tempList = mutableListOf<IntArray>()

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
        return tempList
    }

    fun checkStatus(block: Block): Boolean {


        return true

    }

}

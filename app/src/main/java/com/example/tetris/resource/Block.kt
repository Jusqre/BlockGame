package com.example.tetris.resource

data class Block(val shape: Int, var x: Int, var y: Int, val color: Int) {
    var idx: MutableList<IntArray>

    init {
        this.idx = setBlockIndex()
    }

    fun moveBlock() {
        x += 1
        y += 1
    }

    fun setBlockIndex(): MutableList<IntArray> {
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
        this.idx = tempList
        return tempList
    }

    fun checkStatus(block: Block): Boolean {


        return true

    }

}

package com.example.tetris.resource

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.core.graphics.toColorInt
import com.example.tetris.R
import java.lang.Exception

/**
 * direction 0: 북, 1: 동, 2: 남, 3: 서
 */

var dx = intArrayOf(-1, 0, 1, 0)
var dy = intArrayOf(0, 1, 0, -1)

data class Snake(var x: Int, var y: Int, var direction: Int, var list: MutableList<IntArray>) {
    private var lastIndex: IntArray = intArrayOf(x, y)

    fun turnRight() {
        direction = (direction + 1) % 4
    }

    fun turnLeft() {
        direction = (direction + 3) % 4
    }

    fun goStraight(board: Board): Int {
        if (!isSnakeEat(board)) {
            list.add(0, intArrayOf(x + dx[direction], y + dy[direction]))
            x += dx[direction]
            y+= dy[direction]
            lastIndex = list.last()
            list.removeLast()
            return 0
        } else {
            list.add(0, intArrayOf(x + dx[direction], y + dy[direction]))
            x += dx[direction]
            y += dy[direction]
            board.foodCount = 0
            return 10
        }
    }

    fun drawOnBoard(board: Array<Array<TextView?>>) {
        board[lastIndex[0]][lastIndex[1]]?.apply {
            setBackgroundColor(Color.WHITE)
            text = null
        }
        board[list[0][0]][list[0][1]]?.apply {
            setBackgroundColor(Color.GREEN)
            when (direction) {
                0 -> {
                    text = "↑"
                }
                1 -> {
                    text = "→"
                }
                2 -> {
                    text = "↓"
                }
                3 -> {
                    text = "←"
                }
            }
            setTextColor(Color.BLACK)
            textSize = 20F
            text
        }
        if (list.size >1) {
            board[list[1][0]][list[1][1]]?.setBackgroundColor(Color.YELLOW)
            board[list[1][0]][list[1][1]]?.text = null
        }
    }

    private fun isSnakeEat(board: Board): Boolean {
        return (board.board[x + dx[direction]][y + dy[direction]]?.background as ColorDrawable).color == Color.RED
    }

    fun isMovable(board: Array<Array<TextView?>>, xx: Int, yy: Int): Boolean {
        try {
            if ((board[xx+dx[direction]][yy+dy[direction]]?.background as ColorDrawable).color == Color.YELLOW
            ) {
                return false
            }
        } catch (e: Exception) {
            return false
        }

        return true
    }


}

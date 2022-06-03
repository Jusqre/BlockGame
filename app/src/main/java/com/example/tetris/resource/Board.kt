package com.example.tetris.resource

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.core.graphics.toColorInt
import kotlin.random.Random

class Board(row: Int, private val column: Int) {
    var board: Array<Array<TextView?>> = Array(row) { Array(column) { null } }

    private lateinit var mView : View
    private lateinit var mContext : Context
    private lateinit var mResource : Resources

    var foodCount = 0

    fun adapt(view: View?, context: Context?, resources: Resources) {
        if (view != null) {
            mView = view
        }
        if (context != null) {
            mContext = context
        }
        mResource = resources
    }

    fun initialize() {
        var current = 1
        for (i in 0 until 11) {
            for (j in 0 until 8) {
                board[i][j] = mView.findViewById(
                    mResource.getIdentifier(
                        "T${current}",
                        "id",
                        mContext.packageName ?: "com.example.tetris"
                    )
                )
                board[i][j]?.setBackgroundColor("#FFFFFF".toColorInt())
                current++
            }
        }
    }

    fun makeSnakeFood() {
        if (foodCount == 0) {
            board[Random.nextInt(1,10)][Random.nextInt(1,7)]?.setBackgroundColor("#555555".toColorInt())
            foodCount++
        }
    }

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
                score += 10
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

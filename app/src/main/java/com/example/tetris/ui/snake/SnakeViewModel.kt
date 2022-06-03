package com.example.tetris.ui.snake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tetris.resource.Board
import com.example.tetris.resource.Snake


class SnakeViewModel : ViewModel() {

    private val _snake = MutableLiveData<Snake>()
    val snake: LiveData<Snake>
        get() = _snake

    private val _score = MutableLiveData<Int>()

    init {
        _snake.postValue(
            Snake(5,3,0, mutableListOf(intArrayOf(5,3)))
        )
        _score.postValue(0)
    }

    fun goWithTime(board: Board) {
        _snake.value?.apply {
            if (isMovable(board.board, x,y)) {
                goStraight(board)
                _snake.postValue(this)
            }
        }
    }

    fun turnLeft() {
        _snake.value?.apply {
            this.turnLeft()
            _snake.postValue(this)
        }
    }

    fun turnRight() {
        _snake.value?.apply {
            this.turnRight()
            _snake.postValue(this)
        }
    }

    fun drawOnBoard(board: Board) {
        _snake.value?.apply {
            this.drawOnBoard(board.board)
        }
    }

}
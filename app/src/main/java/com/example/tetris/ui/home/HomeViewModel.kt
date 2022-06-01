package com.example.tetris.ui.home

import androidx.core.graphics.toColorInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tetris.resource.Block
import com.example.tetris.resource.Board
import kotlin.random.Random

class HomeViewModel : ViewModel() {

    private val _block = MutableLiveData<Block>()
    val block: LiveData<Block>
        get() = _block

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    init {
        _block.postValue(
            Block(
                Random.nextInt(0, 7).toDouble(),
                0,
                0,
                Random.nextInt("#000000".toColorInt(), "#FFFFFF".toColorInt())
            )
        )
        _score.postValue(0)
    }

    fun goWithTime(board: Board) {
        if (_block.value?.isMovable(board.board, 1, 0) == true) {
            _block.value?.apply {
                this.x += 1
                this.setBlockIndex()
                _block.postValue(this)
            }
        } else {
            _score.value?.apply {
                _score.postValue(this + board.getScoreWithClearing())
            }
            _block.postValue(
                Block(
                    Random.nextInt(0, 7).toDouble(),
                    0,
                    0,
                    Random.nextInt("#000000".toColorInt(), "#FFFFFF".toColorInt())
                )
            )
        }
    }

    fun moveBelow(board: Board) {
        _block.value?.apply {
            this.moveBelow(board.board)
            _block.postValue(this)
        }

    }

    fun moveLeft(board: Board) {
        _block.value?.apply {
            this.moveLeft(board.board)
            _block.postValue(this)
        }
    }

    fun moveRight(board: Board) {
        _block.value?.apply {
            this.moveRight(board.board)
            _block.postValue(this)
        }
    }

    fun rotate(board: Board) {
        _block.value?.apply {
            this.rotate(board.board)
            _block.postValue(this)
        }
    }

    fun drawOnBoard(board: Board) {
        _block.value?.apply {
            this.drawOnBoard(board.board)
        }
    }
}
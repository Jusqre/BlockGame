package com.example.tetris.resource

class Board(val row: Int, val column: Int) {
    var board: Array<Array<Int>> = Array(row) {Array(column) {0} }

}

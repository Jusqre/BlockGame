package com.example.tetris.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tetris.databinding.FragmentHomeBinding
import com.example.tetris.resource.Block
import com.example.tetris.resource.Board
import kotlin.random.Random

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var tetrisGame : CountDownTimer
    private lateinit var board : Board
    private lateinit var block : Block
    private lateinit var leftButton: Button
    private lateinit var middleButton: Button
    private lateinit var rightButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        board = Board(11, 8)
        block = Block(Random.nextInt(0,5).toDouble(), 0, 0, Random.nextInt("#000000".toColorInt(), "#FFFFFF".toColorInt()))

        middleButton = binding.button2
        middleButton.setOnClickListener {
            block.rotate(board.board)
        }

        leftButton = binding.button
        leftButton.setOnClickListener {
            block.moveLeft(board.board)

        }

        rightButton = binding.button3
        rightButton.setOnClickListener {
            block.moveRight(board.board)
        }

        homeViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onResume() {
        super.onResume()

        var current = 1
        for (i in 0 until 11) {
            for (j in 0 until 8) {
                board.board[i][j] = view?.findViewById(
                    resources.getIdentifier(
                        "T${current}",
                        "id",
                        context?.packageName ?: "com.example.tetris"
                    )
                )
                current++
            }
        }

        tetrisGame = object : CountDownTimer(1000 * 120, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                block.x += 1
                if (block.isMovable(board.board,1,0)) {
                    block.setBlockIndex()
                    block.drawOnBoard(board.board)
                } else {
                    board.clearing()
                    board.sliding()
                    block = Block(Random.nextInt(0,5).toDouble(), 0, 0, Random.nextInt("#000000".toColorInt(), "#FFFFFF".toColorInt()))
                    block.setBlockIndex()
                    block.drawOnBoard(board.board)
                }
            }

            override fun onFinish() {
                cancel()
            }
        }

        tetrisGame.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        tetrisGame.onFinish()
    }
}
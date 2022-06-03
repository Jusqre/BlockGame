package com.example.tetris.ui.snake

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tetris.databinding.FragmentSnakeBinding
import com.example.tetris.resource.Board

class SnakeFragment : Fragment() {

    private var _binding: FragmentSnakeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var snakeGame: CountDownTimer
    private lateinit var board: Board
    private lateinit var leftButton: Button
    private lateinit var middleButton: Button
    private lateinit var rightButton: Button
    lateinit var snakeViewModel: SnakeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        snakeViewModel =
            ViewModelProvider(this)[SnakeViewModel::class.java]

        _binding = FragmentSnakeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        board = Board(11, 8)

        middleButton = binding.button2
        middleButton.setOnClickListener {

        }

        leftButton = binding.button
        leftButton.setOnClickListener {
            snakeViewModel.turnLeft()

        }

        rightButton = binding.button3
        rightButton.setOnClickListener {
            snakeViewModel.turnRight()
        }

        snakeViewModel.snake.observe(viewLifecycleOwner) {
            snakeViewModel.drawOnBoard(board)
        }

        return root
    }

    override fun onResume() {
        super.onResume()

        board.adapt(view,context,resources)
        board.initialize()
        var interval = 700L

        snakeGame = object : CountDownTimer(1000 * 600, interval) {
            override fun onTick(millisUntilFinished: Long) {
                if (board.foodCount == 0) {
                    interval *= 0.9.toLong()
                }
                board.makeSnakeFood()
                snakeViewModel.goWithTime(board)
            }

            override fun onFinish() {
                cancel()
            }
        }

        snakeGame.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        snakeGame.onFinish()
    }
}
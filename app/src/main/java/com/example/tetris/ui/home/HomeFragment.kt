package com.example.tetris.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tetris.databinding.FragmentHomeBinding
import com.example.tetris.resource.Board

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var tetrisGame: CountDownTimer
    private lateinit var board: Board
    private lateinit var startButton: Button
    private lateinit var leftButton: Button
    private lateinit var downButton: Button
    private lateinit var middleButton: Button
    private lateinit var rightButton: Button
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        board = Board(11, 8)

        startButton = binding.button00
        startButton.setOnClickListener {
            tetrisGame.onFinish()
            board.initialize()
            tetrisGame.start()
        }

        downButton = binding.button0
        downButton.setOnClickListener {
            homeViewModel.moveBelow(board)
        }

        middleButton = binding.button2
        middleButton.setOnClickListener {
            homeViewModel.rotate(board)
        }

        leftButton = binding.button
        leftButton.setOnClickListener {
            homeViewModel.moveLeft(board)

        }

        rightButton = binding.button3
        rightButton.setOnClickListener {
            homeViewModel.moveRight(board)
        }

        homeViewModel.block.observe(viewLifecycleOwner) {
            homeViewModel.drawOnBoard(board)
        }

        homeViewModel.score.observe(viewLifecycleOwner) {
            binding.scoreBoard.text = it.toString()
        }
        return root
    }

    override fun onResume() {
        super.onResume()

        board.adapt(view,context,resources)
        board.initialize()

        tetrisGame = object : CountDownTimer(1000 * 600, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                homeViewModel.goWithTime(board)
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
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
import com.example.tetris.ui.tetris.TetrisViewModel

class SnakeFragment : Fragment() {

    private var _binding: FragmentSnakeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var tetrisGame: CountDownTimer
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
        val snakeViewModel =
            ViewModelProvider(this)[SnakeViewModel::class.java]

        _binding = FragmentSnakeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        board = Board(11, 8)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
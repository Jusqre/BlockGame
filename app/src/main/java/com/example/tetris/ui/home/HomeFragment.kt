package com.example.tetris.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tetris.databinding.FragmentHomeBinding
import com.example.tetris.resource.Block
import com.example.tetris.resource.Board
import java.util.*
import kotlin.random.Random

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var board = Board(10, 7).board
    var block = Block(0, 0, 0, "#000000".toColorInt())
    lateinit var leftButton : Button
    lateinit var middleButton: Button
    lateinit var rightButton : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        middleButton = binding.button2
        middleButton.setOnClickListener {
            for (i in block.idx) {
                board[i[0]][i[1]]?.setBackgroundColor(block.color)
            }
        }

        leftButton = binding.button
        leftButton.setOnClickListener {
            block.moveLeft()
            block.drawOnBoard(board)
        }

        rightButton = binding.button3
        rightButton.setOnClickListener {
            block.moveRight()
            block.drawOnBoard(board)
        }

        homeViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onResume() {
        super.onResume()

        var current = 1
        for (i in 0 until 10) {
            for (j in 0 until 7) {
                board[i][j] = view?.findViewById(
                    resources.getIdentifier(
                        "T${current}",
                        "id",
                        context?.packageName ?: "com.example.tetris"
                    )
                )
                current++
            }
        }

        object : CountDownTimer(1000*24, 3000) {
            override fun onTick(millisUntilFinished: Long) {
                block.x += 1
                block.setBlockIndex()
                block.drawOnBoard(board)
            }

            override fun onFinish() {
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.tetris.ui.home

import android.os.Bundle
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
import kotlin.random.Random

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var textViewSet = mutableListOf<TextView>()
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

        leftButton = binding.button
        leftButton.setOnClickListener { block.setBlockIndex() }

        middleButton = binding.button2
        middleButton.setOnClickListener {
            for (i in block.idx) {
                board[i[0]][i[1]]?.setBackgroundColor(block.color)
            }
        }

        rightButton = binding.button3
        rightButton.setOnClickListener { block.moveBlock() }
        homeViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onResume() {
        super.onResume()


        for (i in 1..70) {
            val tv = view?.findViewById<TextView>(
                resources.getIdentifier(
                    "T${i}",
                    "id",
                    context?.packageName ?: "com.example.tetris"
                )
            )
            if (tv != null) {
                textViewSet.add(tv)
            }
        }

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

        for (i in board[0]) {
            i?.setBackgroundColor(Random.nextInt("#000000".toColorInt(), "#FFFFFF".toColorInt()))
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
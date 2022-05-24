package com.example.tetris.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tetris.databinding.FragmentHomeBinding
import com.example.tetris.resource.Board
import kotlin.random.Random

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var textViewSet = mutableListOf<TextView>()
    var board = Board(11, 8).board

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onResume() {
        super.onResume()



        for (i in 1..70) {
            var tv = view?.findViewById<TextView>(
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
        for (i in 1 until 11) {
            for (j in 1 until 8) {
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

        for (i in board[1]) {
            i?.setBackgroundColor(Random.nextInt("#000000".toColorInt(), "#FFFFFF".toColorInt()))
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
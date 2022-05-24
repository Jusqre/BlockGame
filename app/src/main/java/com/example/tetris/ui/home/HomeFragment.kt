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

        var textViewSet= mutableListOf<TextView>()

        for (i in 1..70) {
            var tv = view?.findViewById<TextView>(resources.getIdentifier("T${i}","id", context?.packageName ?: "com.example.tetris"))
            if (tv != null) {
                textViewSet.add(tv)
            }
        }

        for (i in textViewSet) {
            i.setBackgroundColor(Random.nextInt("#000000".toColorInt(),"#FFFFFF".toColorInt()))
        }

        println("asdfasdfasdfsadfsadfa dsafsadfsadsafd ${textViewSet.size}")

        var board = Board(7,10).board

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
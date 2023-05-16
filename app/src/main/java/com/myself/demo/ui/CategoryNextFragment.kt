package com.myself.demo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myself.demo.R
import com.myself.demo.databinding.FragmentCategoryNextBinding


class CategoryNextFragment : Fragment() {
    private var _binding: FragmentCategoryNextBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryNextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.card2.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    findNavController().navigate(R.id.action_categoryNextFragment_to_quizFragment)
                    true
                }

                else -> false
            }
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
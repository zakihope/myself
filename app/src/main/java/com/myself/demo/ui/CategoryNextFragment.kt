package com.myself.demo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myself.demo.R
import com.myself.demo.databinding.FragmentCategoryBinding
import com.myself.demo.databinding.FragmentCategoryNextBinding


class CategoryNextFragment : Fragment() {
    private var _binding: FragmentCategoryNextBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCategoryNextBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.card2.setOnClickListener{ findNavController().navigate(R.id.action_categoryNextFragment_to_quizFragment) }
    }
}
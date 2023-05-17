package com.myself.demo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.myself.demo.R
import com.myself.demo.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //back
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_categourieFragment_to_homeFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        val bundle = Bundle()
        bundle.putString("previus","category")
        binding.card2.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    findNavController().navigate(R.id.action_categourieFragment_to_categoryNextFragment,bundle)
                    true
                }

                else -> false
            }
        }
    }
}
package com.myself.demo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myself.demo.R
import com.myself.demo.databinding.FragmentForumBinding
import com.myself.demo.databinding.FragmentHelloBinding

class ForumFragment : Fragment() {
    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.setVisibility(View.GONE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForumBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var next = false

        binding.card4.setOnClickListener {
            binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
            binding.card5.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card8.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.text12.setTextColor(resources.getColor(R.color.black))
            next = true
        }
        binding.card5.setOnClickListener {
            binding.card5.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
            binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card8.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.text12.setTextColor(resources.getColor(R.color.black))
            next = true
        }

        binding.card8.setOnClickListener {
            if (next)
                findNavController().navigate(R.id.action_forumFragment_to_homeFragment)
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val navController = Navigation.findNavController(requireActivity(), R.id.fragment)
                navController.navigate(R.id.action_forumFragment_to_helloFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        binding.image1.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.fragment)
            navController.navigate(R.id.action_forumFragment_to_helloFragment)
        }
    }
}
package com.myself.demo.ui

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myself.demo.R
import com.myself.demo.databinding.FragmentHelloBinding
import com.myself.demo.databinding.FragmentHomeBinding
import com.myself.demo.databinding.FragmentQuizBinding

class HelloFragment : Fragment() {

    private var _binding: FragmentHelloBinding? = null
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
        _binding = FragmentHelloBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var next = false

        binding.card1.setOnClickListener {
            binding.card1.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
            binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card3.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.text9.setTextColor(resources.getColor(R.color.black))
            next = true
        }
        binding.card2.setOnClickListener {
            binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
            binding.card1.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card3.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.text9.setTextColor(resources.getColor(R.color.black))
            next = true
        }
        binding.card3.setOnClickListener {
            binding.card3.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
            binding.card1.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.text9.setTextColor(resources.getColor(R.color.black))
            next = true
        }
        binding.card4.setOnClickListener {
            if (next)
                findNavController().navigate(R.id.action_helloFragment_to_forumFragment)
        }
    }
}
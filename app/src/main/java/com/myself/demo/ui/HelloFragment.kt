package com.myself.demo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myself.demo.R
import com.myself.demo.databinding.FragmentHelloBinding

class HelloFragment : Fragment() {
    var bundl = Bundle()
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
    ): View {
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.setVisibility(View.GONE)

        _binding = FragmentHelloBinding.inflate(inflater, container, false)
        return binding.root
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
            bundl.putInt("userType",1)
            bundl.putString("userText1",binding.text3.text.toString())
            bundl.putString("userText2",binding.text4.text.toString())
            next = true
        }
        binding.card2.setOnClickListener {
            binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
            binding.card1.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card3.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.text9.setTextColor(resources.getColor(R.color.black))
            bundl.putInt("userType",2)
            bundl.putString("userText1",binding.text5.text.toString())
            bundl.putString("userText2",binding.text6.text.toString())
            next = true
        }
        binding.card3.setOnClickListener {
            binding.card3.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
            binding.card1.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.text9.setTextColor(resources.getColor(R.color.black))
            bundl.putInt("userType",3)
            bundl.putString("userText1",binding.text7.text.toString())
            bundl.putString("userText2",binding.text8.text.toString())
            next = true
        }

        binding.card4.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    if (next)
                        findNavController().navigate(
                            R.id.action_helloFragment_to_forumFragment,
                            bundl
                        )
                    true
                }

                else -> false
            }
        }
    }
}
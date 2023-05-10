package com.myself.demo.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myself.demo.R
import com.myself.demo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.setVisibility(View.VISIBLE)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val navController = Navigation.findNavController(requireActivity(), R.id.fragment)
                navController.navigate(R.id.action_homeFragment_to_forumFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        binding.loading.max = 100
        binding.loading1.max = 100
        binding.loading2.max = 100
        binding.loading3.max = 100
        val val1=70
        val val2=60
        val val3=50
        val val0=(val1+val2+val3)/3
        ObjectAnimator.ofInt(binding.loading,"progress",val0).setDuration(1000).start()
        binding.percentag1.text = "$val0 %"
        ObjectAnimator.ofInt(binding.loading1,"progress",val1).setDuration(1000).start()
        binding.percentag2.text = "$val1%"
        ObjectAnimator.ofInt(binding.loading2,"progress",val2).setDuration(1000).start()
        binding.percentag3.text = "$val2 %"
        ObjectAnimator.ofInt(binding.loading3,"progress",val3).setDuration(1000).start()
        binding.percentag4.text = "$val3 %"
    }
}
package com.myself.demo.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myself.demo.MainActivity
import com.myself.demo.R
import com.myself.demo.databinding.FragmentForumBinding
import com.myself.demo.databinding.FragmentProfileBinding
import com.myself.demo.databinding.LogOutDialogeBinding
import com.myself.demo.model.User

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //back
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        //logout
        binding.logout.setOnClickListener {
            val dialogBinding = LogOutDialogeBinding.inflate(LayoutInflater.from(requireContext()))
            val dialog = Dialog(requireContext())
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(dialogBinding.root)
            dialogBinding.annulerBtn.setOnClickListener { dialog.dismiss() }
            dialogBinding.seDeconnecterBtn.setOnClickListener {
                val user = User(0, 0, 0, 0, "", 0)
                (activity as MainActivity).clearUserData()
                dialog.dismiss()
                findNavController(requireView()).navigate(R.id.action_profileFragment_to_helloFragment)
            }
            dialog.show()
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
            dialog.window!!.setGravity(Gravity.CENTER)
            dialog.setCanceledOnTouchOutside(false)
        }
    }
}
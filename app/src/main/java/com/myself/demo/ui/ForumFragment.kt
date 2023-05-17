package com.myself.demo.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.myself.demo.R
import com.myself.demo.databinding.FragmentForumBinding
import com.myself.demo.model.Question
import com.myself.demo.model.User

class ForumFragment : Fragment() {
    private var _binding: FragmentForumBinding? = null
    private val binding get() = _binding!!
    val items1 = listOf("ذكر", "أنثى")
    val items2 = listOf("الوالدين", "أحد الوالدين", "آخر")
    val items3 = listOf("أعزب / عزباء", "متزوج(ة)", "مطلق(ة)")

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
        _binding = FragmentForumBinding.inflate(inflater, container, false)

        val adapter1 =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items1)
        binding.autoCompleteTxt1.setAdapter(adapter1)
        val adapter2 =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items2)
        binding.autoCompleteTxt2.setAdapter(adapter2)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        val userType = args?.getInt("userType")
        val userText1 = args?.getString("userText1")
        val userText2 = args?.getString("userText2")
        binding.text1.text = userText1.toString()
        binding.text2.text = userText2.toString()

        var next = false

        binding.card4.setOnClickListener {
            binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
            binding.card5.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card8.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.text12.setTextColor(resources.getColor(R.color.black))
            binding.textInput2.visibility = View.VISIBLE
            binding.autoCompleteTxt2.text = null
            binding.textInput2.hint = "تعيش مع"
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items2)
            binding.autoCompleteTxt2.setAdapter(adapter)
            next = true
        }
        binding.card5.setOnClickListener {
            binding.card5.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
            binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.card8.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
            binding.text12.setTextColor(resources.getColor(R.color.black))
            binding.textInput2.visibility = View.VISIBLE
            binding.autoCompleteTxt2.text = null
            binding.textInput2.hint = "الحالة الإجتماعية"
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items3)
            binding.autoCompleteTxt2.setAdapter(adapter)
            next = true
        }

        binding.card8.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.card8.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.card8.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    if (next) {
                        findNavController().navigate(R.id.action_forumFragment_to_homeFragment)
                        saveData()
                    }
                    true
                }

                else -> false
            }
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

    fun saveData() {
        val sharedPreferences = context?.getSharedPreferences("cach", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        val args = this.arguments
        val userType = args?.getInt("userType")
        var gender = 1
        if (binding.autoCompleteTxt1.text.toString() == "أنثى")
            gender = 2
        val typeAge = 1
        if (binding.card5.cardBackgroundColor.equals(resources.getColor(R.color.ms_grey1)))
            gender = 2
        val user = User(1, userType!!, gender, typeAge, binding.usernameBox.text.toString())
        val gson = Gson()
        val json = gson.toJson(user)
        editor?.putString("user", json)
        editor?.putBoolean("session", true)
        editor?.apply()
    }
}
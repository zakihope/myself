package com.myself.demo.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.myself.demo.R
import com.myself.demo.adapter.ImageAdapter
import com.myself.demo.databinding.FragmentResultBinding
import java.lang.Math.abs

class ResultFragment : Fragment() {
    val bundle = Bundle()
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // binding.card2.setOnClickListener({ v->findNavController().navigate(R.id.action_quizFragment_self) })
        val args = this.arguments
        val result = args?.getInt("result")
        if (result != null) {
            if (result < 115) {
                binding.text2.text = "منخفض"
                binding.text3.text = "لابد من برنامج تدريبي في أقرب وقت"
            } else if (result < 153) {
                binding.text2.text = "متوسط"
                binding.text3.text = "تحتاج إلى برنامج تدريبي"
            } else {
                binding.text2.text = "مرتفع"
                binding.text3.text = "تقدير الذات مرتفع حافظ عليه و نميه أكثر"
            }
        }

        init()
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val navController = Navigation.findNavController(requireActivity(), R.id.fragment)
                navController.navigate(R.id.action_resultFragment_to_homeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        binding.card2.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.card2.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    val dialIntent = Intent(Intent.ACTION_DIAL)
                    dialIntent.data = Uri.parse("tel:0657577574")
                    startActivity(dialIntent)
                    if (result != null)
                        bundle.putInt("numberQuiz", 38)
                    findNavController().navigate(R.id.action_resultFragment_to_homeFragment, bundle)
                    true
                }

                else -> false
            }
        }
      /*  binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }*/
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable, 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init() {
        viewPager2 = binding.viewPager2
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.a)
        imageList.add(R.drawable.b)
        imageList.add(R.drawable.c)
        imageList.add(R.drawable.d)
        imageList.add(R.drawable.e)
        imageList.add(R.drawable.f)
        imageList.add(R.drawable.g)
        imageList.add(R.drawable.k)
        imageList.add(R.drawable.i)
        imageList.add(R.drawable.l)
        imageList.add(R.drawable.m)
        imageList.add(R.drawable.n)

        adapter = ImageAdapter(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }

}
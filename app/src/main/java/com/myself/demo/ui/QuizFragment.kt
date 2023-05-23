package com.myself.demo.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myself.demo.MainActivity
import com.myself.demo.utlis.Constants
import com.myself.demo.R
import com.myself.demo.databinding.FragmentQuizBinding
import com.myself.demo.model.Test

class QuizFragment : Fragment() {
    private lateinit var animator: ObjectAnimator
    val bundle = Bundle()
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    val arrQuiz = Constants().getQuestion()
    var ansersList = ArrayList<Int>()
    var QuizNumbe = 1
    val test = Test(0, 0, 0)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        QuizNumbe = (activity as MainActivity).loadTestData()!! + 1
        if (QuizNumbe > 38) {
            binding.numberQuiz.text = "38"
            binding.quizBody.text = arrQuiz.get(QuizNumbe - 2).question
        }
        binding.loading.max = 38
        test.result = (activity as MainActivity).loadTestResult()!!
        ansersList = loadansersList()
        pushQuiz()

        binding.anser1.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.card3.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.card3.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    if (QuizNumbe < 39) {
                        ansersList.add(4)
                        test.result = test.result + arrQuiz.get(QuizNumbe - 1).anser.get(4).value
                        QuizNumbe++
                        pushQuiz()
                    }
                    true
                }

                else -> false
            }
        }
        binding.anser2.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.card4.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    if (QuizNumbe < 39) {
                        ansersList.add(3)
                        test.result = test.result + arrQuiz.get(QuizNumbe - 1).anser.get(3).value
                        QuizNumbe++
                        pushQuiz()
                    }
                    true
                }

                else -> false
            }
        }
        binding.anser3.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.card5.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.card5.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    if (QuizNumbe < 39) {
                        ansersList.add(2)
                        test.result = test.result + arrQuiz.get(QuizNumbe - 1).anser.get(2).value
                        QuizNumbe++
                        pushQuiz()
                    }
                    true
                }

                else -> false
            }
        }
        binding.anser4.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.card6.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.card6.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    if (QuizNumbe < 39) {
                        ansersList.add(1)
                        test.result = test.result + arrQuiz.get(QuizNumbe - 1).anser.get(1).value
                        QuizNumbe++
                        pushQuiz()
                    }
                    true
                }

                else -> false
            }
        }
        binding.anser5.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.card7.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.card7.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    if (QuizNumbe < 39) {
                        ansersList.add(0)
                        test.result = test.result + arrQuiz.get(QuizNumbe - 1).anser.get(0).value
                        QuizNumbe++
                        pushQuiz()
                    }
                    true
                }

                else -> false
            }
        }



        binding.backQuiz.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.backQuiz.setCardBackgroundColor(resources.getColor(R.color.ms_grey1))
                    true
                }

                MotionEvent.ACTION_UP -> {
                    binding.backQuiz.setCardBackgroundColor(resources.getColor(R.color.ms_grey))
                    if (QuizNumbe > 1) {
                        QuizNumbe--
                        Log.d("TAG", "onViewCreated: test.result "+test.result)
                        Log.d("TAG", "onViewCreated: QuizNumbe "+QuizNumbe)
                        Log.d("TAG", "onViewCreated: arrQuiz.get(QuizNumbe - 1) "+arrQuiz.get(QuizNumbe - 1))
                        Log.d("TAG", "onViewCreated: test.result - arrQuiz.get(QuizNumbe - 1).anser.get(0).value "+(test.result - arrQuiz.get(QuizNumbe - 1).anser.get(0).value))

                        test.result = test.result - arrQuiz.get(QuizNumbe - 1).anser.get(ansersList.get(QuizNumbe-1)).value
                        ansersList.removeAt(QuizNumbe-1)
                        pushQuiz()
                    }
                    if (QuizNumbe == 38)
                        binding.loading.progressDrawable = resources.getDrawable(R.drawable.circle)
                    true
                }

                else -> false
            }
        }


        binding.loading.setOnClickListener {
            if (binding.percentag.text == "النتيجة")
                findNavController().navigate(R.id.action_quizFragment_to_resultFragment, bundle)
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    fun pushQuiz() {
        animator = ObjectAnimator.ofInt(binding.loading, "progress", QuizNumbe - 1)
        animator.start()
        if (QuizNumbe < 39) {
            var n = QuizNumbe - 1
            binding.percentag.text = "$n/38"
            binding.numberQuiz.text = "$QuizNumbe"
            binding.quizBody.text = arrQuiz.get(QuizNumbe - 1).question
        } else {
            binding.loading.progressDrawable = resources.getDrawable(R.drawable.circle1)
            binding.percentag.text = "النتيجة"
            bundle.putInt("result", test.result)
        }
        saveTestStatusData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        animator.cancel()
        binding.loading.progress = 0
    }

    fun saveTestStatusData() {
        val sharedPreferences = context?.getSharedPreferences("cach", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        val QuizDone = QuizNumbe - 1
       // Log.d("TAG", "saveTestStatusData: QuizDone " + QuizDone)
        editor?.putInt("QuizDone", QuizDone)
        Log.d("TAG", "onViewCreated: test.result "+test.result)
        editor?.putInt("testResult", test.result)
        val gson = Gson()
        val json = gson.toJson(ansersList)
        editor?.putString("ansersList", json)
        editor?.apply()
    }
    fun loadansersList(): ArrayList<Int> {
        val sharedPreferences = activity?.getSharedPreferences("cach", Context.MODE_PRIVATE)
        val json = sharedPreferences?.getString("ansersList", null)
        val gson = Gson()
        if (!json.isNullOrEmpty()) {
            return gson.fromJson(json, object : TypeToken<ArrayList<Int>>() {}.type)
        } else {
            return  ArrayList()
        }
    }
}
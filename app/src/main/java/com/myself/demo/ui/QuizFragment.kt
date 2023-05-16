package com.myself.demo.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myself.demo.Constants
import com.myself.demo.R
import com.myself.demo.databinding.FragmentQuizBinding
import com.myself.demo.model.Test

class QuizFragment : Fragment() {
    private lateinit var animator: ObjectAnimator
    val bundle = Bundle()
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    val arrQuiz = Constants().getQuestion()
    var QuizNumbe = 1
    val test = Test(0, 0, 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loading.max = 38
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
       animator =  ObjectAnimator.ofInt(binding.loading, "progress", QuizNumbe - 1)
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
    }
    override fun onDestroyView() {
        super.onDestroyView()
        animator.cancel()
        binding.loading.progress = 0
    }
}
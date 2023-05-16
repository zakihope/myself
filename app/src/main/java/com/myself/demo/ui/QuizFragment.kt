package com.myself.demo.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myself.demo.Constants
import com.myself.demo.R
import com.myself.demo.databinding.FragmentQuizBinding
import com.myself.demo.model.Test

class QuizFragment : Fragment() {
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

        //binding.card2.setOnClickListener { findNavController().navigate(R.id.action_quizFragment_self) }
        binding.loading.max = 38
        pushQuiz()

        binding.anser1.setOnClickListener {
            test.result = test.result + arrQuiz.get(QuizNumbe - 1).anser.get(4).value
            QuizNumbe++
            pushQuiz()
        }
        binding.anser2.setOnClickListener {
            if (QuizNumbe < 39) {
                test.result = test.result + arrQuiz.get(QuizNumbe - 1).anser.get(3).value
                QuizNumbe++
                pushQuiz()
            }
        }
        binding.anser3.setOnClickListener {
            if (QuizNumbe < 39) {
                test.result = test.result + arrQuiz.get(QuizNumbe - 1).anser.get(2).value
                QuizNumbe++
                pushQuiz()
            }
        }
        binding.anser4.setOnClickListener {
            if (QuizNumbe < 39) {
                test.result = test.result + arrQuiz.get(QuizNumbe - 1).anser.get(1).value
                QuizNumbe++
                pushQuiz()
            }
        }
        binding.anser5.setOnClickListener {
            if (QuizNumbe < 39) {
                test.result = test.result + arrQuiz.get(QuizNumbe - 1).anser.get(0).value
                QuizNumbe++
                pushQuiz()
            }
        }

        binding.backQuiz.setOnClickListener {
            if (QuizNumbe < 39) {
                QuizNumbe--
                pushQuiz()
            }
        }
        binding.loading.setOnClickListener {
            if (binding.percentag.text == "النتيجة")
                findNavController().navigate(R.id.action_quizFragment_to_resultFragment, bundle)
        }
    }

    public fun pushQuiz() {
        ObjectAnimator.ofInt(binding.loading, "progress", QuizNumbe - 1).setDuration(1000).start()
        if (QuizNumbe < 39) {
            var n = QuizNumbe - 1
            binding.percentag.text = "$n/38"
            binding.titelquiz.text = "السؤال $QuizNumbe"
            binding.quizBody.text = arrQuiz.get(QuizNumbe - 1).question
        } else {
            binding.percentag.text = "النتيجة"
            bundle.putInt("result", test.result)
        }
    }
}
package top.iu1314.calculation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import top.iu1314.calculation.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_question, container, false)
//        val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        val myViewModel = ViewModelProvider(
            requireActivity(),
            SavedStateViewModelFactory(requireActivity().application, requireActivity())
        ).get(
            MyViewModel::class.java
        )
        myViewModel.generator()
//        myViewModel.currentScore.value = 0
//       利用 Fragment 进去的action传值，问题回答正确时，将当前分数赋值给action的arguments 修复旋转导致的分数重置
        myViewModel.currentScore.value = arguments?.getInt("initCurrentScore")?:0
        val binding: FragmentQuestionBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)
        binding.data = myViewModel
        binding.lifecycleOwner = this  // binding.lifecycleOwner = requireActivity()
        val builder: StringBuilder = StringBuilder()
        val listener = View.OnClickListener {
            when (it.id) {
                R.id.button0 -> builder.append("0")
                R.id.button1 -> builder.append("1")
                R.id.button2 -> builder.append("2")
                R.id.button3 -> builder.append("3")
                R.id.button4 -> builder.append("4")
                R.id.button5 -> builder.append("5")
                R.id.button6 -> builder.append("6")
                R.id.button7 -> builder.append("7")
                R.id.button8 -> builder.append("8")
                R.id.button9 -> builder.append("9")
                R.id.buttonClear -> builder.setLength(0)
            }
            if (builder.isEmpty()) {
                binding.textView9.text = getString(R.string.input_indicator)
            } else {
                binding.textView9.text = builder.toString()
            }
        }
        binding.button0.setOnClickListener(listener)
        binding.button1.setOnClickListener(listener)
        binding.button2.setOnClickListener(listener)
        binding.button3.setOnClickListener(listener)
        binding.button4.setOnClickListener(listener)
        binding.button5.setOnClickListener(listener)
        binding.button6.setOnClickListener(listener)
        binding.button7.setOnClickListener(listener)
        binding.button8.setOnClickListener(listener)
        binding.button9.setOnClickListener(listener)
        binding.buttonClear.setOnClickListener(listener)


        binding.buttonSubmit.setOnClickListener { v ->
            if (builder.isEmpty()) {
                Toast.makeText(context, R.string.question_no_answer, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (builder.toString().toInt() == myViewModel.answer.value) {
                myViewModel.answerCorrect()
                builder.setLength(0)
                binding.textView9.text = getString(R.string.answer_Correct_message)
                arguments?.putInt("initCurrentScore", myViewModel.currentScore.value!!)

            } else {
                if (myViewModel.winFlag) {
                    Navigation.findNavController(v)
                        .navigate(R.id.action_questionFragment_to_winFragment)
                    myViewModel.winFlag = false
                    myViewModel.save()

                } else {
                    Navigation.findNavController(v)
                        .navigate(R.id.action_questionFragment_to_loseFragment)

                }
            }
        }


        return binding.root
    }


}

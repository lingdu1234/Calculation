package top.iu1314.calculation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import top.iu1314.calculation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_title, container, false)
//        val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        val myViewModel = ViewModelProvider(requireActivity(), SavedStateViewModelFactory(requireActivity().application, requireActivity()))[MyViewModel::class.java]
        val binding: FragmentTitleBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        binding.data = myViewModel
        binding.lifecycleOwner = requireActivity()
        binding.button.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_titleFragment_to_questionFragment)
        })
        return binding.root


    }
}
package top.iu1314.calculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import top.iu1314.calculation.databinding.FragmentLoseBinding

class LoseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        val binding: FragmentLoseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_lose, container, false)
        binding.data = myViewModel
        binding.lifecycleOwner = this
        binding.button11.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loseFragment_to_titleFragment))
//        return inflater.inflate(R.layout.fragment_win, container, false)
        return binding.root
    }
}
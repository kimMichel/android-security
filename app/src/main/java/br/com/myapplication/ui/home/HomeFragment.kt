package br.com.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.myapplication.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtons()
    }

    private fun setButtons() = with(binding) {
        btn4digit.setOnClickListener {
            viewModel.generatePassword(4, textResult)
        }
        btn6digit.setOnClickListener {
            viewModel.generatePassword(6, textResult)
        }
        btn8digit.setOnClickListener {
            viewModel.generatePassword(8, textResult)
        }
        btnAlpha.setOnClickListener {
            viewModel.generateAlphaNumPassword(6)
        }
        btnStore.setOnClickListener {
            HomeFragmentDirections.actionHomeFragmentToStoreFragment().apply {
                try {
                    findNavController().navigate(this)
                } catch (e: Exception) {
                    Log.e("NavigationException", e.message, e)
                }
            }
        }
    }
}
package br.com.myapplication.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.myapplication.databinding.FragmentStoreBinding
import org.koin.android.ext.android.inject

class StoreFragment : Fragment() {

    private lateinit var binding: FragmentStoreBinding
    private val viewModel: StoreViewModel by inject()

    private val storeAdapter: StoreAdapter = StoreAdapter(
        passwords = viewModel.passwordList,
        passwordDecodeClickListener = {position, decodeText ->
            viewModel.decode(position, decodeText)
        },
        deletePasswordClickListener = {position ->
            viewModel.delete(position)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
    }

    private fun setRecycler() = with(binding) {
        storeRecycler.layoutManager = LinearLayoutManager(requireContext())
        storeRecycler.adapter = storeAdapter
    }
}
package com.openBank.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.openBank.R
import com.openBank.databinding.DetailsCharacterFragmentBinding
import com.openBank.model.Status
import com.openBank.util.SharedViewModel
import com.openBank.util.load
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsCharacterFragment : Fragment() {

    private val viewModel by viewModel<DetailsCharacterViewModel>()
    private val sharedViewModel by sharedViewModel<SharedViewModel>()
    private lateinit var binding: DetailsCharacterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsCharacterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.idCharacter.observe(viewLifecycleOwner) { idCharacter ->
            setDetails(idCharacter)
        }
    }

    private fun setDetails(idCharacter: Int) {
        viewModel.getCharacter(
            idCharacter
        ).observe(
            viewLifecycleOwner
        ) { resource ->
            when (resource.status) {
                Status.LOADING -> binding.loading.progress.visibility = View.VISIBLE
                Status.ERROR -> {
                    binding.loading.progress.visibility = View.GONE
                    resource.message?.let {
                        Toast.makeText(
                            context,
                            R.string.error_server,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                Status.SUCCESS -> {
                    binding.loading.progress.visibility = View.GONE
                    resource.data?.let { character ->
                        binding.characterImage.load(character.data.results[0].thumbnail.getUrl())
                        binding.title.text = character.data.results[0].name
                        binding.description.text = character.data.results[0].description
                    }
                }
            }
        }
    }
}
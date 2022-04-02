package com.openBank.ui.character

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.openBank.R
import com.openBank.databinding.CharacterFragmentBinding
import com.openBank.model.KeyLocalStorage
import com.openBank.model.Status
import com.openBank.ui.adapters.SearchCharacterAdapter
import com.openBank.util.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : Fragment(), SearchCharacterAdapter.Listener {
    private val viewModel by viewModel<CharacterViewModel>()
    private val sharedViewModel by sharedViewModel<SharedViewModel>()
    private var adapterCharacter: SearchCharacterAdapter? = null
    private lateinit var binding: CharacterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            CharacterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCharacters.layoutManager = LinearLayoutManager(context)
        binding.recyclerCharacters.hasFixedSize()

        setAdapterCharacter()

        binding.character.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapterCharacter!!.filter.filter(s)
                binding.recyclerCharacters.isVisible = true
            }
        })
    }

    private fun setAdapterCharacter() {
        viewModel.getCharacterList(
            KeyLocalStorage.CHARACTER
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
                    resource.data?.let { characterList ->
                    adapterCharacter = SearchCharacterAdapter(this, characterList.data.results)
                    binding.recyclerCharacters.adapter = adapterCharacter
                    }
                }
            }
        }
    }

    override fun onClick(character: com.openBank.model.Character, position: Int) {
        sharedViewModel.setIdCharacter(character.id)
        val action = CharacterFragmentDirections.actionCharacterFragmentToDetailsCharacterFragment()
        Navigation.findNavController(requireView()).navigate(action)
    }
}
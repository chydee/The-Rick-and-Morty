package com.chidi.therickandmorty.presentation.view.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chidi.therickandmorty.R
import com.chidi.therickandmorty.databinding.CharacterDetailsFragmentBinding
import com.chidi.therickandmorty.domain.Character
import com.chidi.therickandmorty.presentation.utils.Resource
import com.chidi.therickandmorty.presentation.utils.autoCleared
import com.chidi.therickandmorty.presentation.utils.binding.bindSrcUrl
import com.chidi.therickandmorty.presentation.utils.extensions.gone
import com.chidi.therickandmorty.presentation.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private val viewModel: CharacterDetailsViewModel by viewModels()
    private var binding: CharacterDetailsFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
    }

    private fun getCharacterDetail(id: Int) {
        viewModel.read(id)
    }

    private fun setupObserver() {
        viewModel.character.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    setupCharacterDetails(it.data!!)
                    hideProgressIndicator()
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    showProgressIndicator()
                }
            }
        })
    }

    private fun setupCharacterDetails(character: Character) {
        binding.apply {
            characterImage.bindSrcUrl(character.url, false, AppCompatResources.getDrawable(requireActivity(), R.drawable.ricky))
            characterSpeciesText.text = character.species
            characterStatusText.text = character.status
            characterTypeText.text = character.type
            characterGenderText.text = character.gender
        }
    }

    private fun showProgressIndicator() {
        binding.progressIndicator.progressGroup.show()
        binding.characterDetails.gone()
    }

    private fun hideProgressIndicator() {
        binding.progressIndicator.progressGroup.gone()
        binding.characterDetails.show()
    }

}
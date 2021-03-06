package com.chidi.therickandmorty.presentation.view.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chidi.therickandmorty.databinding.CharactersFragmentBinding
import com.chidi.therickandmorty.presentation.adapter.CharactersAdapter
import com.chidi.therickandmorty.presentation.utils.LoadingDialog
import com.chidi.therickandmorty.presentation.utils.Resource
import com.chidi.therickandmorty.presentation.utils.autoCleared
import com.chidi.therickandmorty.presentation.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(), CharactersAdapter.OnCharacterClickListener {

    private var binding: CharactersFragmentBinding by autoCleared()
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter

    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharactersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        loadingDialog = LoadingDialog(requireActivity())
        setupRecyclerView()
        observeViewModel()
    }

    override fun onItemClick(characterId: Int) {
        //When A Character is clicked navigate to the CharacterDetailsFragment
        findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(characterId))
    }

    /**
     *  Setup Characters RecyclerView and display
     *  characters in form of a list
     */
    private fun setupRecyclerView() {
        adapter = CharactersAdapter()
        binding.charactersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRecyclerView.adapter = adapter

        adapter.setOnClickListener(this)
    }


    private fun observeViewModel() {
        viewModel.characters.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    loadingDialog.dismissDialog()
                    if (!it.data.isNullOrEmpty()) adapter.submitList(it.data) else showEmptyState("No Characters available")
                }
                Resource.Status.ERROR -> {
                    it.message?.let { message -> showEmptyState(message) }
                }
                Resource.Status.LOADING -> {
                    loadingDialog.startLoadingDialog()
                }
            }
        })
    }

    /**
     * Show empty-state TextView and set the message
     * from the Resource as Text
     */
    private fun showEmptyState(message: String) {
        loadingDialog.dismissDialog()
        binding.emptyStateTextView.apply {
            show()
            text = message
        }
    }

}

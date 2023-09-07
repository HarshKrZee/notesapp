package com.example.notesapplofcoding.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapplofcoding.MainActivity
import com.example.notesapplofcoding.R
import com.example.notesapplofcoding.databinding.FragmentNotesListBinding
import com.example.notesapplofcoding.ui.adapters.NotesAdapter
import com.example.notesapplofcoding.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
    private lateinit var binding: FragmentNotesListBinding
    private lateinit var notesAdapter: NotesAdapter

    // this activityViewModel() help to share the same instance of viewmodel in activity file
    private val noteviewModel: NoteViewModel by activityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        viewModel = (activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

//        viewModel.getPostLiveData().observe(viewLifecycleOwner ){
//            Log.d("APIReponse222",it.toString())
//        }

//        viewModel.getPost()

//        viewModel.getPostLiveData().observe(viewLifecycleOwner ){
//            Log.d("APIReponse",it.toString())
//        }
//
//        viewModel.getPost()

//        Toast.makeText(context,"xvjhjkhhigighgjhgjgjgjgjgygygyggyz", Toast.LENGTH_SHORT).show()

        lifecycleScope.launchWhenStarted {
            noteviewModel.notes.collect{noteList ->
                notesAdapter.differ.submitList(noteList)
            }
        }

        lifecycleScope.launchWhenStarted{
            noteviewModel.searchNotes.collect{searchedNotes ->
                notesAdapter.differ.submitList(searchedNotes)
            }
        }

        binding.edSearch.addTextChangedListener {
            noteviewModel.searchNotes(it.toString().trim())
        }

        binding.btnAddNote.setOnClickListener{
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
        }

        binding.btnPost.setOnClickListener{
            findNavController().navigate(R.id.action_notesListFragment_to_postsListFragment)
        }

        notesAdapter.onClick = {note ->
            val bundle = Bundle().apply {
                putParcelable("note",note)
            }

            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment,bundle )
        }
    }

    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter()
        binding.rvNotes.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
        }
    }
}
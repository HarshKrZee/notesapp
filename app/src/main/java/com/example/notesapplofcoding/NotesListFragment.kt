package com.example.notesapplofcoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapplofcoding.databinding.FragmentNotesListBinding
import com.example.notesapplofcoding.viewmodel.NoteViewModel


class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
    private lateinit var binding: FragmentNotesListBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

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

//        Toast.makeText(context,"xvjhjkhhigighgjhgjgjgjgjgygygyggyz", Toast.LENGTH_SHORT).show()

        lifecycleScope.launchWhenStarted {
            viewModel.notes.collect{noteList ->
                notesAdapter.differ.submitList(noteList)
            }
        }

        lifecycleScope.launchWhenStarted{
            viewModel.searchNotes.collect{searchedNotes ->
                notesAdapter.differ.submitList(searchedNotes)
            }
        }

        binding.edSearch.addTextChangedListener {
            viewModel.searchNotes(it.toString().trim())
        }

        binding.btnAddNote.setOnClickListener{
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
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
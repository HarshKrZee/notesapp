package com.example.notesapplofcoding.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapplofcoding.MainActivity
import com.example.notesapplofcoding.R
import com.example.notesapplofcoding.databinding.FragmentPostsListBinding
import com.example.notesapplofcoding.ui.adapters.PostsAdapter
import com.example.notesapplofcoding.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class PostsListFragment : Fragment(R.layout.fragment_posts_list) {
    private lateinit var binding: FragmentPostsListBinding
    private lateinit var postsAdapter: PostsAdapter

    // this activityViewModel() help to share the same instance of viewmodel in activity file
    private val noteviewModel: NoteViewModel by activityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsListBinding.inflate(inflater)
        return binding.root
          }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

//        viewModel.getPostLiveData().observe(viewLifecycleOwner ){
//            Log.d("APIReponse",it.toString())
//            postsAdapter.differ.submitList(it)
//        }

        noteviewModel.getPost()


        lifecycleScope.launchWhenStarted {
            Log.d("posts" , "api fetching with flows successful !")
            noteviewModel.post.collect{noteList ->
                postsAdapter.differ.submitList(noteList)
            }
        }
    }


    private fun setupRecyclerView() {
        postsAdapter = PostsAdapter()
        binding.posts.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
        }
    }
}
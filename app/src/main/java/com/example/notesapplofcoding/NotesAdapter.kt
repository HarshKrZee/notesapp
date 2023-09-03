package com.example.notesapplofcoding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapplofcoding.databinding.NotesItemBinding
import com.example.notesapplofcoding.model.Note

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: NotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.tvNoteTitle.text = note.noteTitle
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NotesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)

        holder.itemView.setOnClickListener{
            onClick?.invoke(note)
        }
    }

    var onClick : ((Note) -> Unit)?= null

}
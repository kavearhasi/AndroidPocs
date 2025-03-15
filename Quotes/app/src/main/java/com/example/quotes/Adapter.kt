package com.example.quotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.databinding.CardBinding
import com.example.quotes.domain.models.Quotes

class Adapter : RecyclerView.Adapter<Adapter.ViewHold>(){
    private var quotesList = emptyList<Quotes>()
    inner class ViewHold(private val binding : CardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(quotes : Quotes){
            binding.author.text = quotes.quote
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val binding = CardBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHold(binding)
    }

    override fun getItemCount(): Int = quotesList.size

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
               holder.bind(quotesList[position])
    }
    fun update(newList : List<Quotes> ){

        quotesList = newList
        notifyDataSetChanged()
    }

}
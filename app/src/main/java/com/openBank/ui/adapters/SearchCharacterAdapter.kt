package com.openBank.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.openBank.databinding.ItemCharacterBinding
import com.openBank.model.Character
import com.openBank.util.load


class SearchCharacterAdapter (private val listener: Listener,
                              private var listCharacter: List<Character>) :
    RecyclerView.Adapter<SearchCharacterAdapter.ViewHolder>(), Filterable {

    var characterFilterList = ArrayList<Character>()
    var characterFilterListOriginal = ArrayList<Character>()

    init {
        characterFilterList = listCharacter as ArrayList<Character>
        characterFilterListOriginal = listCharacter as ArrayList<Character>
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    characterFilterList = listCharacter as ArrayList<Character>
                } else {
                    val resultList = ArrayList<Character>()
                    for (row in characterFilterListOriginal) {
                        row.name?.let {
                            if (it.contains(charSearch, ignoreCase = true)) {
                                resultList.add(row)
                            }
                        }
                    }
                    listCharacter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listCharacter
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                characterFilterList = results?.values as ArrayList<Character>
                notifyDataSetChanged()
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(parent)

    override fun getItemCount(): Int {
        return listCharacter.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listCharacter[position],listener)

    class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character, listener: Listener) {
            binding.title.setOnClickListener {
                listener.onClick(character, absoluteAdapterPosition)
            }
            binding.characterImage.load(character.thumbnail.getUrl())
            binding.title.text = character.name
            binding.description.text = character.description
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val binding = ItemCharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }
    interface Listener {
        fun onClick(character: Character, position: Int)
    }
}
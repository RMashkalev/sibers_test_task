package com.example.features.feed.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.features.feed.domain.entity.BasePokemon
import com.example.feed.R
import com.example.feed.databinding.PokemonItemBinding

class PokemonRVAdapter(
	private val onItemClick: (name: String) -> Unit
) : ListAdapter<BasePokemon, PokemonRVAdapter.PokemonViewHolder>(PokemonDiffCallback) {

	inner class PokemonViewHolder(private val binding: PokemonItemBinding) :
		RecyclerView.ViewHolder(binding.root) {

		fun bind(pokemon: BasePokemon) = with(binding) {
			pokemonName.text = pokemon.name
			pokemonAttack.text = pokemon.statAttack.toString()
			pokemonDefense.text = pokemon.statDefense.toString()
			pokemonHealth.text = pokemon.statHealth.toString()

			pokemonImg.load(pokemon.imgUrl) {
				placeholder(R.drawable.pokemon_load)
				error(R.drawable.placeholder)
			}

			itemView.setOnClickListener {
				pokemon.name?.let { name -> onItemClick(name) }
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = PokemonItemBinding.inflate(inflater, parent, false)
		return PokemonViewHolder(binding)
	}

	override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
		holder.bind(getItem(position))
	}
}

object PokemonDiffCallback : DiffUtil.ItemCallback<BasePokemon>() {

	override fun areItemsTheSame(oldItem: BasePokemon, newItem: BasePokemon): Boolean {
		return oldItem.name == newItem.name
	}

	override fun areContentsTheSame(oldItem: BasePokemon, newItem: BasePokemon): Boolean {
		return oldItem == newItem
	}
}
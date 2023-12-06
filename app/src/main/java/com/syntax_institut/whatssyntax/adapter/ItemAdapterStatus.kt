package com.syntax_institut.whatssyntax.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.data.model.Contact
import com.syntax_institut.whatssyntax.databinding.ListItemBinding
import com.syntax_institut.whatssyntax.databinding.ListItemStatusBinding
import com.syntax_institut.whatssyntax.fragments.StatusFragment
import com.syntax_institut.whatssyntax.fragments.StatusFragmentDirections

class ItemAdapterStatus(
    private var datasetContacts : List<Contact>


    ) : RecyclerView.Adapter<ItemAdapterStatus.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListItemStatusBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //val withStatus = datasetContacts.
        val item = datasetContacts[position]

        holder.binding.ivChatPicture.setImageResource(item.image)
        holder.binding.tvChatName.text = item.name

        holder.binding.contactCard.setOnClickListener{
            val navController = holder.binding.root.findNavController()
            navController.navigate(StatusFragmentDirections.actionStatusFragmentToStatusDetailFragment(position))
        }

    }

    override fun getItemCount(): Int {
        return datasetContacts.size
    }
}

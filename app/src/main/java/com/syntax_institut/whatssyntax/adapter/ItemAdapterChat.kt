package com.syntax_institut.whatssyntax.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.data.model.Chat
import com.syntax_institut.whatssyntax.databinding.ListItemBinding
import com.syntax_institut.whatssyntax.fragments.ChatFragmentDirections

/**
 * Diese Klasse organisiert mithilfe der ViewHolder Klasse das Recycling
 */
class ItemAdapterChat(
    private var datasetChats: List<Chat>,


    ) : RecyclerView.Adapter<ItemAdapterChat.ItemViewHolder>() {


    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * hier findet der Recyclingprozess statt
     * die vom ViewHolder bereitgestellten Parameter erhalten die Information des Listeneintrags
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = datasetChats[position]


        //val mainActivity = activity as MainActivity
        holder.binding.ivChatPicture.setImageResource(item.contact.image)
        holder.binding.tvChatName.text = item.contact.name
        holder.binding.tvChatMessage.text = item.messages.last().text



        holder.binding.contactCard.setOnClickListener{
            holder.itemView.findNavController().navigate(ChatFragmentDirections.actionChatFragmentToSingleChatFragment(position))
        }



    }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return datasetChats.size
    }
}

package com.syntax_institut.whatssyntax.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.data.model.Contact
import com.syntax_institut.whatssyntax.databinding.ListItemBinding
import com.syntax_institut.whatssyntax.databinding.ListItemStatusBinding

/**
 * Diese Klasse organisiert mithilfe der ViewHolder Klasse das Recycling
 */
class ItemAdapterStatus(
    private var datasetContacts : List<Contact>


    ) : RecyclerView.Adapter<ItemAdapterStatus.ItemViewHolder>() {


    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(val binding: ListItemStatusBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //val withStatus = datasetContacts.
        val item = datasetContacts[position]
        val status = item.status?.text

        holder.binding.ivChatPicture.setImageResource(item.image)
        holder.binding.tvChatName.text = item.name

    }


    /**
     * hier findet der Recyclingprozess statt
     * die vom ViewHolder bereitgestellten Parameter erhalten die Information des Listeneintrags
     */

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return datasetContacts.size
    }
}

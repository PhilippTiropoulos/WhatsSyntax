package com.syntax_institut.whatssyntax.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.model.Call
import com.syntax_institut.whatssyntax.databinding.ListItemBinding
import com.syntax_institut.whatssyntax.databinding.ListItemCallsBinding

/**
 * Diese Klasse organisiert mithilfe der ViewHolder Klasse das Recycling
 */
class ItemAdapterCall(
    private var datasetCalls: List<Call>


) : RecyclerView.Adapter<ItemAdapterCall.ItemViewHolder>() {


    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(val binding: ListItemCallsBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ListItemCallsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = datasetCalls[position]

        holder.binding.ivCalls.setImageResource(item.contact.image)
        if (item.accepted) {
            holder.binding.ivArrowCalls.setImageResource(R.drawable.icon_call_accepted)
        } else {
            holder.binding.ivArrowCalls.setImageResource(R.drawable.icon_call_missed)
        }
        holder.binding.tvNamesCall.text = item.contact.name
        holder.binding.tvDateCalls.text = item.time


    }

    override fun getItemCount(): Int {
        return datasetCalls.size
    }
}

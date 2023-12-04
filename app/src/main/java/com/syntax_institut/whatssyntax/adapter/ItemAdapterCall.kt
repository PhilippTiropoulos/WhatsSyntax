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

        holder.binding.ivCallPicture.setImageResource(item.contact.image)



        if (item.accepted && item.incoming) {
            holder.binding.ivArrowCalls.setImageResource(R.drawable.icon_call_accepted)
            holder.binding.ivArrowCalls.rotation = 180F

        }

        if (item.accepted && !item.incoming) {
            holder.binding.ivArrowCalls.setImageResource(R.drawable.icon_call_accepted)
            holder.binding.ivArrowCalls.rotation = 0f
        }

        if (!item.accepted && item.incoming) {
            holder.binding.ivArrowCalls.setImageResource(R.drawable.icon_call_missed)
            holder.binding.ivArrowCalls.rotation = 180F
        }

        if (!item.accepted && !item.incoming) {
            holder.binding.ivArrowCalls.setImageResource(R.drawable.icon_call_missed)
            holder.binding.ivArrowCalls.rotation = 0f
        }

        /*if (item.accepted) {
            holder.binding.ivArrowCalls.setImageResource(R.drawable.icon_call_accepted)
            if(item.incoming) {
                holder.binding.ivArrowCalls.rotation = 180F
            }
        } else {
            holder.binding.ivArrowCalls.setImageResource(R.drawable.icon_call_missed)
            if (item.incoming) {
                holder.binding.ivArrowCalls.rotation = 180F
            }
        }*/
        holder.binding.tvCallName.text = item.contact.name
        holder.binding.tvCallMessage.text = item.time

    }

    override fun getItemCount(): Int {
        return datasetCalls.size
    }
}

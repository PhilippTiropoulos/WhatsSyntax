package com.syntax_institut.whatssyntax.adapter

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.databinding.ListItemChatBinding
import java.util.Locale

/**
 * Diese Klasse organisiert mithilfe der ViewHolder Klasse das Recycling
 */
class ItemAdapterSingleChat(
    private var messages : List<Message>,
    private var pos: Int = 0

    ) : RecyclerView.Adapter<ItemAdapterSingleChat.ItemViewHolder>() {


    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(val binding: ListItemChatBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * hier findet der Recyclingprozess statt
     * die vom ViewHolder bereitgestellten Parameter erhalten die Information des Listeneintrags
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val layoutParams = holder.binding.cvChatMessage.layoutParams as ConstraintLayout.LayoutParams
        // erstellt solange ViewHolder solange es noch Nachrichten in der Liste gibt
        if (pos < messages.size) {
            // falls die Nachricht von uns selbst stammt andere Farbe
            if (!messages[pos].incoming) {
                layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                layoutParams.startToStart = ConstraintLayout.LayoutParams.UNSET
                holder.binding.cvChatMessage.setCardBackgroundColor(
                    holder.itemView.context.getColor(com.google.android.material.R.color.m3_ref_palette_dynamic_primary90)
                )
            //
            } else {
                layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                layoutParams.endToEnd = ConstraintLayout.LayoutParams.UNSET
                holder.binding.cvChatMessage.setCardBackgroundColor(
                    holder.itemView.context.getColor(com.google.android.material.R.color.material_personalized_color_on_background)
                )
            }

            holder.binding.cvChatMessage.requestLayout()
            holder.binding.cvChatMessage.layoutParams = layoutParams
            holder.binding.tvMessage.text = messages[pos].text

            val format = SimpleDateFormat("HH:mm", Locale.GERMANY)
            val formattedDate = format.format(messages[pos].timestamp.time)
            holder.binding.tvDate.text = formattedDate
            pos++

        }

    }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return messages.size
    }


}

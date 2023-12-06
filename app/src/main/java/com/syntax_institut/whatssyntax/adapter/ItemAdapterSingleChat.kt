package com.syntax_institut.whatssyntax.adapter

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.databinding.ListItemChatBinding
import java.util.Locale

/**
 * Diese Klasse organisiert mithilfe der ViewHolder Klasse das Recycling
 */
class ItemAdapterSingleChat(
    private var messages: List<Message>

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
    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val layoutParams = holder.binding.cvChatMessage.layoutParams as ConstraintLayout.LayoutParams
        val currentMessage = messages[position]

            // falls die Nachricht von uns selbst stammt, am Rechtem Bildschirmrand constrainen
            // und andere Hintergrundfarbe setzen
            if (!currentMessage.incoming) {
                layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                layoutParams.startToStart = ConstraintLayout.LayoutParams.UNSET
                holder.binding.cvChatMessage.setCardBackgroundColor(
                    MaterialColors.getColor(holder.itemView, com.google.android.material.R.attr.colorPrimaryContainer)
                )
            // anderenfalls am linken Rand
            } else {
                layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                layoutParams.endToEnd = ConstraintLayout.LayoutParams.UNSET
                holder.binding.cvChatMessage.setCardBackgroundColor(
                    MaterialColors.getColor(holder.itemView, com.google.android.material.R.attr.colorSurfaceContainerLowest)
                )
            }
            holder.binding.cvChatMessage.layoutParams = layoutParams
            //holder.binding.cvChatMessage.requestLayout()

            // Message und Uhrzeit in TextViews schreiben
            holder.binding.tvMessage.text = messages[position].text
            val format = SimpleDateFormat("HH:mm", Locale.GERMANY)
            val formattedDate = format.format(messages[position].timestamp.time)
            holder.binding.tvDate.text = formattedDate

        }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return messages.size
    }
}

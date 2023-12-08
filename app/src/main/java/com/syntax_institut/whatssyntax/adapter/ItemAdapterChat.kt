package com.syntax_institut.whatssyntax.adapter

import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.data.model.Chat
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.databinding.ListItemBinding
import com.syntax_institut.whatssyntax.fragments.ChatFragmentDirections
import java.util.Calendar
import java.util.Locale

/**
 * Diese Klasse organisiert mithilfe der ViewHolder Klasse das Recycling
 */
class ItemAdapterChat(
    private var datasetChats: List<Chat>

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
        val chat = datasetChats[position]
        val lastMessage = chat.messages.last()
        val format = if (isChatMessageFromToday(lastMessage)) {
            // Nachrichten vom heutigen Tag ohne Tagangabe
            SimpleDateFormat("HH:mm", Locale.GERMANY)
        } else {
            // Nachrichten von z.b. gestern mit Tagesangabe
            SimpleDateFormat("E HH:mm", Locale.GERMANY)
        }
        val formattedDate = format.format(lastMessage.timestamp.time)
        //val mainActivity = activity as MainActivity
        holder.binding.ivChatPicture.setImageResource(chat.contact.image)
        holder.binding.tvChatName.text = chat.contact.name
        holder.binding.tvChatMessage.text = chat.messages.last().text
        holder.binding.tvDatum.text = formattedDate

        // zum jeweiligen Chat navigieren
        holder.binding.contactCard.setOnClickListener{
            holder.itemView.findNavController().navigate(ChatFragmentDirections.actionChatFragmentToSingleChatFragment(position))
        }
    }

    private fun isChatMessageFromToday(message: Message) : Boolean {
        val currentDay = Calendar.getInstance()
        Log.d("days", currentDay.get(Calendar.DAY_OF_YEAR).toString())
        val messageDay = Calendar.getInstance().apply {
            time = message.timestamp.time
        }
        // Prüft ob die jeweilige Message von diesem Jahr und diesem Tag ist
        if (currentDay.get(Calendar.YEAR) == messageDay.get(Calendar.YEAR) && currentDay.get(Calendar.DAY_OF_YEAR) == messageDay.get(Calendar.DAY_OF_YEAR)) {
            return true
        }
        return false
    }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
    override fun getItemCount(): Int {
        return datasetChats.size
    }
}

package com.syntax_institut.whatssyntax.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.data.model.Call
import com.syntax_institut.whatssyntax.databinding.ListItemBinding
import com.syntax_institut.whatssyntax.databinding.ListItemCallsBinding
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random
import android.os.Handler
import android.os.Looper

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
        // val dasdas

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

        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val currentDateTime = Calendar.getInstance().time
//        val dateTime = dateFormat.format(currentDateTime)

        holder.binding.tvCallName.text = item.contact.name
        holder.binding.tvCallMessage.text = dateFormat.format(item.time)

        holder.binding.contactCard.setOnClickListener {
            val phoneNumber = item.contact.number

            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))

            holder.itemView.context.startActivity(dialIntent)

            Handler(Looper.getMainLooper()).postDelayed({
                item.incoming = false
//            item.accepted = listOf(true, false).random()
                item.accepted = Random.nextBoolean()



                item.time = currentDateTime

                datasetCalls = datasetCalls.sortedByDescending { it.time }

                notifyDataSetChanged()
            }, 0)
        }

    }

    override fun getItemCount(): Int {
        return datasetCalls.size
    }
}

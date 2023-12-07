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
    private var datasetCalls: MutableList<Call>,


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

        val imageRes = if (item.accepted) R.drawable.icon_call_accepted else R.drawable.icon_call_missed
        val rotation = if (item.incoming) 180f else 0f
        holder.binding.ivArrowCalls.setImageResource(imageRes)
        holder.binding.ivArrowCalls.rotation = rotation

        /*        if (item.accepted && item.incoming) {
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
                }*/


        //bestimmt Uhrzeit und Datum
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
//        val dateTime = dateFormat.format(currentDateTime)

        holder.binding.tvCallName.text = item.contact.name
        holder.binding.tvCallMessage.text = dateFormat.format(item.time)

        // onClick mit intent auf DIAL
        holder.binding.contactCard.setOnClickListener {
            val phoneNumber = item.contact.number

            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))

            holder.itemView.context.startActivity(dialIntent)

            val call = Call(item.contact, false, Random.nextBoolean(), Calendar.getInstance().time)
            addCall(call)
            // Rufe die Callback-Methode nach einer Verzögerung auf
            /*Handler(Looper.getMainLooper()).postDelayed({
                val call = Call(item.contact, false, Random.nextBoolean(), Calendar.getInstance().time)
                addCall(call)
            }, 0)*/

            /*            val call = Call(item.contact, false, Random.nextBoolean(), Calendar.getInstance().time)
                        updateData(datasetCalls+call)*/
            /*            item.incoming = false
                        item.accepted = Random.nextBoolean()
                        item.time = Calendar.getInstance().time*/

            /*            // bessers thread sleep
            Handler(Looper.getMainLooper()).postDelayed({
                item.incoming = false
                item.accepted = Random.nextBoolean()
                item.time = currentDateTime
                datasetCalls = datasetCalls.sortedByDescending { it.time }
                notifyDataSetChanged()
            },500)
        }*/

        }
    }
    // Neue Methode, um einen Anruf hinzuzufügen
    private fun addCall(call: Call) {
        datasetCalls.add(0, call)
        notifyItemInserted(0)
    }

    override fun getItemCount(): Int {
        return datasetCalls.size
    }
}

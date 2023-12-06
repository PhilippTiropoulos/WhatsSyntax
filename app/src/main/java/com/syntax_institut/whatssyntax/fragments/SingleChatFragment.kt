package com.syntax_institut.whatssyntax.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.adapter.ItemAdapterSingleChat
import com.syntax_institut.whatssyntax.data.model.Message
import com.syntax_institut.whatssyntax.databinding.FragmentSingleChatBinding
import java.util.Calendar

class SingleChatFragment : Fragment() {

    private lateinit var binding: FragmentSingleChatBinding
    private val args: SingleChatFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingleChatBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        mainActivity.profile
        // Chat mit jeweiligem Kontakt zwischenspeichern
        val chat = mainActivity.chats[args.position]

        binding.recyclerView.post {
            binding.recyclerView.scrollToPosition(chat.messages.size - 1)
        }

        // Toolbar mit Daten des Chatpartners befüllen
        binding.imageView.setImageResource(chat.contact.image)
        binding.tvChatDetailName.text = chat.contact.name
        binding.tvNumberChatDetails.text = chat.contact.number

        //Messageliste erstellen um damit ItemAdapter zu erstellen usw.
        var itemAdapter = ItemAdapterSingleChat(chat.messages)
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // Zurück - Button
        binding.btnBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }

        var isSendIconActive = false
        // Textwatcher der Icons ändert je nachdem ob Eingabefeld leer ist oder nicht
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // nichts
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // nichts
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.isNullOrEmpty()) {
                    binding.imageButton.setImageResource(R.drawable.baseline_mic_24)
                    isSendIconActive = false
                } else {
                    binding.imageButton.setImageResource(R.drawable.baseline_send_24)
                    isSendIconActive = true
                }
            }
        }
        binding.textInputLayout.editText?.addTextChangedListener(textWatcher)


        binding.imageButton.setOnClickListener {
            if (binding.textInputLayout.editText?.text?.trim()
                    .isNullOrEmpty()
            ) return@setOnClickListener

            if (isSendIconActive) {
                // Neue Message erstellen anhand dessen, was eingegeben wurde und aktueller Zeit
                val textString = binding.textInputLayout.editText?.text.toString()
                val message = Message(textString, false, Calendar.getInstance())
                // Zur Messagelist hinzufügen
                chat.messages.add(message)
                // Neue Position der hinzugefügten Nachricht
                val newPosition = chat.messages.size - 1
                // RecyclerView aktualisieren
                itemAdapter.notifyItemInserted(newPosition)
                // Zur neusten Nachricht scrollen
                binding.recyclerView.scrollToPosition(newPosition)
                // Eingabefeld leeren
                binding.textInputLayout.editText?.text?.clear()
            }
        }

        binding.clClick.setOnClickListener {
            val position = args.position
            val navController = binding.root.findNavController()
            navController.navigate(
                SingleChatFragmentDirections.actionSingleChatFragmentToDetailFragment(
                    position
                )
            )
        }
    }
}

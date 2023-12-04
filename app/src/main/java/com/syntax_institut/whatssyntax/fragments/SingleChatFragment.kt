package com.syntax_institut.whatssyntax.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.adapter.ItemAdapterSingleChat
import com.syntax_institut.whatssyntax.databinding.FragmentSingleChatBinding

class SingleChatFragment : Fragment() {

    private lateinit var binding: FragmentSingleChatBinding
    private val args : SingleChatFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingleChatBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Dataset "rüberholen"
        val mainActivity = activity as MainActivity
        mainActivity.profile
        val chats = mainActivity.chats
        val chat = chats[args.position]

        binding.imageView.setImageResource(chat.contact.image)
        binding.tvChatDetailName.text = chat.contact.name
        binding.tvNumberChatDetails.text = chat.contact.number

        val messages = chat.messages

        val itemAdapter = ItemAdapterSingleChat(messages)
        Log.d("Items", messages.toString())

        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().navigate(SingleChatFragmentDirections.actionSingleChatFragmentToChatFragment())
        }
    }
}
package com.syntax_institut.whatssyntax.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.adapter.ItemAdapterChat
import com.syntax_institut.whatssyntax.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    //private val args : DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ItemAdapter für Chats
        val mainActivity = activity as MainActivity
        val itemAdapter = ItemAdapterChat(mainActivity.chats)
        binding.rvChatFragment.adapter = itemAdapter
        binding.rvChatFragment.layoutManager = LinearLayoutManager(context)

    }
}
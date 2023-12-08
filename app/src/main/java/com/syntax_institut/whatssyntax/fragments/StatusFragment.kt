package com.syntax_institut.whatssyntax.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.adapter.ItemAdapterStatus
import com.syntax_institut.whatssyntax.databinding.FragmentStatusBinding

class StatusFragment : Fragment() {

    private lateinit var binding: FragmentStatusBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatusBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        val contactsWithStatus = mainActivity.data.getContacts().filter { it.status != null }
        // ItemAdapter für alle Kontakte mit Status
        val itemAdapter = ItemAdapterStatus(contactsWithStatus)
        binding.rvStatus.adapter = itemAdapter
        binding.rvStatus.layoutManager = LinearLayoutManager(context)
    }
}
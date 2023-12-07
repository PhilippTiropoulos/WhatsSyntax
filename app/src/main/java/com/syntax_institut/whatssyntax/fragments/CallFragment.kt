package de.syntax_institut.telefonbuch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.adapter.ItemAdapterCall
import com.syntax_institut.whatssyntax.databinding.FragmentCallBinding


class CallFragment : Fragment() {

    private lateinit var binding: FragmentCallBinding
    //private val args : DetailFragmentArgs by navArgs()
    private lateinit var itemAdapter: ItemAdapterCall

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCallBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ItemAdapter für Anrufe
        val mainActivity = activity as MainActivity
        itemAdapter = ItemAdapterCall(mainActivity.calls.sortedByDescending { it.time }.toMutableList())
        binding.rvFragmentCall.adapter = itemAdapter
        binding.rvFragmentCall.layoutManager = LinearLayoutManager(context)


    }
}
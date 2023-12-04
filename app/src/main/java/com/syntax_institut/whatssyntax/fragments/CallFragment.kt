package de.syntax_institut.telefonbuch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.adapter.ItemAdapterCall
import com.syntax_institut.whatssyntax.adapter.ItemAdapterChat
import com.syntax_institut.whatssyntax.databinding.FragmentCallBinding

class CallFragment : Fragment() {

    private lateinit var binding: FragmentCallBinding
    //private val args : DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCallBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Dataset "rüberholen"
        //val mainActivity = activity as MainActivity
        val mainActivity = activity as MainActivity
        var itemAdapter = ItemAdapterCall(mainActivity.calls)
        binding.rvFragmentCall.adapter = itemAdapter
        binding.rvFragmentCall.layoutManager = LinearLayoutManager(context)




    }
}
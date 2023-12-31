package com.syntax_institut.whatssyntax.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.databinding.FragmentStatusDetailBinding

class StatusDetailFragment : Fragment() {
    private lateinit var binding: FragmentStatusDetailBinding
    private val args: StatusDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatusDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        val position = args.position
        val contact = mainActivity.data.getContacts().filter { it.status != null }[position]

        // Views füllen
        binding.ivContacePic.setImageResource(contact.image)
        contact.status?.let { binding.statusPicTV.setImageResource(it.img) }
        binding.tvChatDetailName.text = contact.name
        binding.tvStatusDetail.text = contact.status?.text
        // Zurück navigieren
        binding.btnBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}

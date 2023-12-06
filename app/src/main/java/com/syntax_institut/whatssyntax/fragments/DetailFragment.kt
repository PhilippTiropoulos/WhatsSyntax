package com.syntax_institut.whatssyntax.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity
        val position = args.position
        val user = mainActivity.chats[position].contact
        val filteredList = mainActivity.contacts.filter { it.status != null }
        val userPos = filteredList.indexOf(user)

        // Daten setzen
        binding.materialToolbar.title = user.name
        binding.userNameTV.text = user.name
        binding.userNumberTV.text = user.number
        binding.userPicIV.setImageResource(user.image)

        // Setze Status wenn vorhanden
        if (user.status != null) {
            binding.userStatusTV.text = user.status.text

            // Nur Klickbar wenn Status vorhanden ist
            binding.userStatusTV.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToStatusDetailFragment(userPos))
            }
            // anderenfalls lasse TV leer und Hintergrund transparent
        } else {
            binding.userStatusTV.setBackgroundColor(Color.TRANSPARENT)
            binding.userStatusTV.text = ""
        }

        // Zurück navigieren
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        // Zum Status des jeweiligen Kontaktes navigieren


    }

}
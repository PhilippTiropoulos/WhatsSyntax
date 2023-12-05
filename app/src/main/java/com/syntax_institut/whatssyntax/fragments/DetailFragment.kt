package com.syntax_institut.whatssyntax.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.adapter.ItemAdapterChat
import com.syntax_institut.whatssyntax.databinding.FragmentDetailBinding
import com.syntax_institut.whatssyntax.databinding.FragmentSettingsBinding


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

        binding.userNameTV.text = user.name
        binding.userNumberTV.text = user.number
        binding.userPicIV.setImageResource(user.image)

    }

}
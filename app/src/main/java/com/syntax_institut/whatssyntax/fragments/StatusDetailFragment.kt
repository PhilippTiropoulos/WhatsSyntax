package com.syntax_institut.whatssyntax.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.R
import com.syntax_institut.whatssyntax.databinding.FragmentDetailBinding
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
        val status = mainActivity.contacts.filter { it.status != null }[position]

        binding.imageView.setImageResource(status.image)
        binding.tvChatDetailName.text = status.name
        binding.tvStatusDetail.text = status.status?.text

        binding.btnBackArrow.setOnClickListener {
            findNavController().navigate(R.id.statusFragment)

        }
    }
}

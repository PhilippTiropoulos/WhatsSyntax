package com.syntax_institut.whatssyntax.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    //private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        val profile = mainActivity.profile

        // Werte des Users übergeben
        binding.ivPictureSettings.setImageResource(profile.image)
        binding.tilSettingsOne.editText?.setText(profile.name)
        binding.tilSettingsTwo.editText?.setText(profile.number)

        // Um den Button erst klickbar zu machen wenn ein Textfeld verändert wurde
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Button deaktivieren zu beginn
                binding.btSettingsSave.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                // falls Text bearbeitet wird und dann leer ist wird deaktiviert anderenfalls aktiviert
                binding.btSettingsSave.isEnabled = !p0.isNullOrEmpty()
            }
        }
        // TextWatcher den beiden Edittexts hinzufügen
        binding.tilSettingsOne.editText?.addTextChangedListener(textWatcher)
        binding.tilSettingsTwo.editText?.addTextChangedListener(textWatcher)

        binding.btSettingsSave.setOnClickListener {
            // Daten setzen aus EditTexts
            profile.name = binding.tilSettingsOne.editText?.text.toString()
            profile.number = binding.tilSettingsTwo.editText?.text.toString()
            // Button wieder deaktivieren
            binding.btSettingsSave.isEnabled = false
            // Snackbarausgabe
            Snackbar.make(
                this.requireView(),
                "Änderungen wurden gespeichert!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

}
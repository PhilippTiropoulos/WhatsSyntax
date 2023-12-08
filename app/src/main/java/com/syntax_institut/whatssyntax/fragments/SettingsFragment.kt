package com.syntax_institut.whatssyntax.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.syntax_institut.whatssyntax.MainActivity
import com.syntax_institut.whatssyntax.data.model.Profile
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
        val profile = mainActivity.data.getProfile()
        binding.btSettingsSave.isEnabled = false

        // Werte des Users übergeben
        binding.ivPictureSettings.setImageResource(profile.image)
        binding.tilSettingsOne.editText?.setText(profile.name)
        binding.tilSettingsTwo.editText?.setText(profile.number)

        // Um den Button erst klickbar zu machen wenn ein Textfeld verändert wurde
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Button deaktivieren zu beginn

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
            val newProfileName = binding.tilSettingsOne.editText?.text.toString()
            val newProfileNumber = binding.tilSettingsTwo.editText?.text.toString()
            val newProfile = Profile(newProfileName, newProfileNumber, profile.image)
            mainActivity.data.setProfile(newProfile)
            // Button wieder deaktivieren
            binding.btSettingsSave.isEnabled = false
            // Snackbar erstellen
            val snackBar = Snackbar.make(
                binding.root,
                "Änderungen wurden gespeichert!",
                Snackbar.LENGTH_SHORT
            )
            // Setzt Snackbar Gravity nach oben statt unten
            val params = snackBar.view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            params.topMargin = 12
            snackBar.view.layoutParams = params
            // Ausgabe der Snackbar
            snackBar.show()
        }
    }

}
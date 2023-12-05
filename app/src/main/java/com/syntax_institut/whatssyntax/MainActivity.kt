package com.syntax_institut.whatssyntax

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.syntax_institut.whatssyntax.data.Datasource
import com.syntax_institut.whatssyntax.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var chats = Datasource().getChats()
    var calls = Datasource().getCalls()
    var contacts = Datasource().getContacts()
    var profile = Datasource().getProfile()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BottomNavigationView anbinden
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController


        // Blendet Bottom App Bar im Einzelchat oder bestimmten Fragmenten aus
        binding.bnwBottomNavBar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if (destination.id == R.id.singleChatFragment) {
                binding.bnwBottomNavBar.visibility = View.GONE
            } else {
                binding.bnwBottomNavBar.visibility = View.VISIBLE
            }
        }

    }
}
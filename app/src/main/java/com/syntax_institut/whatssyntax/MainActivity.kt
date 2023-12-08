package com.syntax_institut.whatssyntax

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.syntax_institut.whatssyntax.data.Datasource
import com.syntax_institut.whatssyntax.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val data = Datasource()
    var chats = Datasource().getChats()


//    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Navigationsicon mit Navcontroller verbinden
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.navHostFragment.findNavController().navigateUp()

            }
        })

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BottomNavigationView anbinden
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Blendet Bottom App Bar im Einzelchat oder bestimmten Fragmenten aus
        binding.bnwBottomNavBar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if (destination.id == R.id.singleChatFragment || destination.id == R.id.statusDetailFragment || destination.id == R.id.detailFragment) {
                binding.bnwBottomNavBar.visibility = View.GONE
            } else {
                binding.bnwBottomNavBar.visibility = View.VISIBLE
            }
        }
    }
}
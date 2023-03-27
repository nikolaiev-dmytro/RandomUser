package com.android.randomuser.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.android.randomuser.R
import com.android.randomuser.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment)?.navController
    }
    private val topDestinations by lazy { setOf(R.id.users, R.id.history) }
    private val appBarConfiguration by lazy { AppBarConfiguration(topDestinations) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController?.let {
            NavigationUI.setupActionBarWithNavController(this, it, appBarConfiguration)
            NavigationUI.setupWithNavController(binding.mainBottomNavigation, it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp(appBarConfiguration) == true || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
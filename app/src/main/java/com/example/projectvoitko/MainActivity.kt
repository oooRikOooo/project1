package com.example.projectvoitko
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.transition.FragmentTransitionSupport
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //private lateinit var menuInflater : MenuInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolBar)
        toolBar.title = ""
        toolBar.setNavigationIcon(R.drawable.ic_baseline_account_circle_24)*/

        val bottomNavigationView  = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        //val navController = findNavController(R.id.fragment)
        //bottomNavigationView.setupWithNavController(navController)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

    }
    /*override fun onPrepareOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu,menu)
        val item: MenuItem = menu.findItem(R.id.app_bar_search)
        item.isVisible = false
        return super.onPrepareOptionsMenu(menu)
    }*/

}
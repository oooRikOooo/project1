package com.example.projectvoitko.MainFragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.projectvoitko.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.toolbar.*


class SecondFragment : Fragment() {
    var navc : NavController?= null
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //setHasOptionsMenu(true)
        navc = Navigation.findNavController(view)
        firebaseAuth = FirebaseAuth.getInstance()
        imageButtonAccount.setOnClickListener {
            val firebaseUser = firebaseAuth.currentUser
            if(firebaseUser!=null){
                navc?.navigate(R.id.action_secondFragment_to_profileFragment)
            } else {
                navc?.navigate(R.id.action_secondFragment_to_loginRegisterFragment)
            }
        }
        calendarView?.setOnDateChangeListener{view, year, month, dayOfMonth ->
            buttonCalendar.setOnClickListener {
                val msg =  "Current date is "+dayOfMonth + "." + (month + 1) + "." + year
                textViewCalendar.text = msg
            }

        }
    }

    /*override fun onPrepareOptionsMenu(menu: Menu) {
        val menuInflater : MenuInflater
        menuInflater.inflate(R.menu.main_menu,menu)
        val item: MenuItem = menu.findItem(R.id.searchView)
        item.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }*/




}
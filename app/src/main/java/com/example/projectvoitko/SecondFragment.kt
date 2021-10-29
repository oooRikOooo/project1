package com.example.projectvoitko

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.toolbar.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var navc : NavController?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
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
        imageButtonAccount.setOnClickListener {
            navc?.navigate(R.id.action_secondFragment_to_loginRegisterFragment)
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




    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
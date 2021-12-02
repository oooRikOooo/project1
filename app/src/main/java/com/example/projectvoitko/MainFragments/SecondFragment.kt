package com.example.projectvoitko.MainFragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectvoitko.MainFragments.SecondFragmentElements.CalendarAdapter
import com.example.projectvoitko.MainFragments.SecondFragmentElements.CalendarEvents
import com.example.projectvoitko.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.toolbar.*


class SecondFragment : Fragment() {
    var navc : NavController?= null
    private lateinit var firebaseAuth: FirebaseAuth
    val db = Firebase.firestore
    private lateinit var eventsRecyclerView : RecyclerView
    private lateinit var eventsArrayList : ArrayList<CalendarEvents>


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

        eventsRecyclerView = view.findViewById(R.id.recyclerViewCalendar)
        eventsRecyclerView.layoutManager = LinearLayoutManager(context)
        eventsRecyclerView.setHasFixedSize(true)

        eventsArrayList = arrayListOf<CalendarEvents>()

        getUserData()


        imageButtonAccount.setOnClickListener {
            val firebaseUser = firebaseAuth.currentUser
            if(firebaseUser!=null){
                navc?.navigate(R.id.action_secondFragment_to_profileFragment)
            } else {
                navc?.navigate(R.id.action_secondFragment_to_loginRegisterFragment)
            }
        }

        /*calendarView?.setOnDateChangeListener{view, year, month, dayOfMonth ->
            buttonCalendar.setOnClickListener {
                val msg =  "Current date is "+dayOfMonth + "." + (month + 1) + "." + year
                textViewCalendar.text = msg
                navc?.navigate(R.id.action_secondFragment_to_newEventFragment)
            }

        }*/
        buttonCalendar.setOnClickListener {
            navc?.navigate(R.id.action_secondFragment_to_newEventFragment)
        }
    }

    private fun getUserData() {
        db.collection("users").get().addOnSuccessListener { result ->

            //progressRL.visibility = View.VISIBLE
            //mainLayout_profile.visibility = View.GONE

            //activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            val userId = firebaseAuth.currentUser!!.uid
            for (document in result){
                val id = document.data["userId"]

                if(id == userId){
                    val event = CalendarEvents("1","02.12.2021","1","Посадити картоплю","1")
                    eventsArrayList.add(event)
                    /*val datas = db.collection("users").document(document.id)
                    datas.update(
                        mapOf(
                            "add_info" to "1",
                            "date" to "21.10.2021",
                            "title_event" to "die"
                        )
                    )*/

                    /*val name = document.data["first"] as String
                    textViewNameProfile.text = name*/
                    //progressBar.visibility = View.GONE
                    //progressRL.visibility = View.GONE
                    //mainLayout_profile.visibility = View.VISIBLE
                    //cardViewProfile.visibility = View.VISIBLE
                }
                //Log.v(TAG, "${document.id} => ${document.data["first"]}")
            }

            eventsRecyclerView.adapter = CalendarAdapter(eventsArrayList)

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
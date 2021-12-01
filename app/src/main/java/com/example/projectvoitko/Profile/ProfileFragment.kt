package com.example.projectvoitko

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.projectvoitko.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar_profile.*

class profileFragment : Fragment() {

    private val TAG: String = "myLogs";
    val db = Firebase.firestore
    var navc : NavController ?= null
    private lateinit var firebaseAuth : FirebaseAuth

    //private var mDatabaseReference: DatabaseReference? = null
    //private var mDatabase: FirebaseDatabase? = null
    //private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //progressBar.visibility = View.VISIBLE
        //constraint_profile.visibility = View.GONE

        cardViewProfile.visibility = View.GONE

        firebaseAuth = FirebaseAuth.getInstance()
        //mDatabase = FirebaseDatabase.getInstance()
        //mDatabaseReference = mDatabase!!.reference.child("Users")
        navc = Navigation.findNavController(view)



        checkUser()
        val firebaseUser = firebaseAuth.currentUser
        /*val mUserReference = mDatabaseReference!!.child(firebaseUser!!.uid)
        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                textViewNameProfile!!.text = snapshot.child("name").value as String

            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })*/

        buttonToEditProfile.setOnClickListener {
            navc?.navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        imageButtonLogout.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
        //Log.v(TAG, "${db.collection("users")}")



        db.collection("users").get().addOnSuccessListener { result ->

            progressRL.visibility = View.VISIBLE
            //mainLayout_profile.visibility = View.GONE

            //activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            val userId = firebaseAuth.currentUser!!.uid
            for (document in result){
                val id = document.data["userId"]

                if(id == userId){
                    val name = document.data["first"] as String
                    textViewNameProfile.text = name
                    //progressBar.visibility = View.GONE
                    progressRL.visibility = View.GONE
                    //mainLayout_profile.visibility = View.VISIBLE
                    cardViewProfile.visibility = View.VISIBLE
                }
                //Log.v(TAG, "${document.id} => ${document.data["first"]}")
            }

        }


        /*
        constraint_profile.visibility = View.VISIBLE*/

        //constraint_profile.visibility = View.VISIBLE

        //textViewNameProfile.text = name
        //progressRL.visibility = View.GONE


    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        //textViewLogout.text = firebaseUser.toString()

        if(firebaseUser != null){
            val email = firebaseUser.email
            //val name = firebaseUser.displayName

            textViewEmailProfile.text = email


            //textViewLogout.text = email
        } else {
            //textViewLogout.text = "Out"
            navc?.navigate(R.id.action_profileFragment_to_loginFragment22)
        }

    }

}
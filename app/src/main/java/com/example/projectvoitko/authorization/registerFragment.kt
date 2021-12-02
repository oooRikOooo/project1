package com.example.projectvoitko.authorization

import android.graphics.Typeface
import android.nfc.Tag
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.projectvoitko.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login_register.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.toolbar_login.*

class registerFragment : Fragment() {
    var navc : NavController?= null
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    private var name = ""
    private var mDatabaseReference:DatabaseReference?=null
    private var mDatabase: FirebaseDatabase? = null
    val db = Firebase.firestore
    //val useId : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Firebase.firestore
        val typeface : Typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/Roboto-Light.ttf")
        buttonCreate.typeface = typeface
        tvCreateAccount.typeface = typeface
        editTextEmailAddress.typeface = typeface
        editTextName.typeface = typeface
        editTextPassword.typeface = typeface
        navc = Navigation.findNavController(view)
        imageButtonBack.setOnClickListener {
            navc?.navigate(R.id.action_registerFragment_to_loginRegisterFragment)
        }
        firebaseAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")

        buttonCreate.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        email = editTextEmailAddress.text.toString().trim()
        password = editTextPassword.text.toString().trim()
        name = editTextName.text.toString().trim()

        /*val user = hashMapOf(
            "first" to "$name"
        )*/

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailAddress.error = "Invalid email format"
        } else if(TextUtils.isEmpty(password)){
            editTextPassword.error = "Please enter password"
        } else if(password.length<6){
            editTextPassword.error = "Password is short"
        }else if(TextUtils.isEmpty(name)){
            editTextName.error = "Please enter your name"
        } else {
            //addToDb(user)
            firebaseSignUp()
        }
    }

    /*private fun addToDb(user:HashMap<String,String>) {
        db.collection("users").add(user).addOnSuccessListener { documentReference->

        }
    }*/

    private fun firebaseSignUp() {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //val firebaseUser = firebaseAuth.currentUser
                val userId = firebaseAuth.currentUser!!.uid
                val currentUserDb = mDatabaseReference!!.child(userId)
                currentUserDb.child("name").setValue(name)
                val user = hashMapOf(
                    "first" to "$name",
                    "userId" to "$userId",
                    "imageName" to "",
                    "nickname" to "",
                    "about" to ""

                )
                db.collection("users").add(user).addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                    .addOnFailureListener { e->
                        Log.w("TAG", "Error adding document", e)
                    }

                //val email = firebaseUser!!.email
                navc?.navigate(R.id.action_registerFragment_to_loginFragment2)
            }
            .addOnFailureListener {

            }
    }


}
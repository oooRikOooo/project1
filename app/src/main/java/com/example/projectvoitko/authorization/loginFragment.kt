package com.example.projectvoitko.authorization

import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.projectvoitko.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login_register.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar_login.*


class loginFragment : Fragment() {
    var navc : NavController?= null
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val typeface : Typeface = Typeface.createFromAsset(requireActivity().assets, "fonts/Roboto-Light.ttf")
        buttonSignIn.typeface = typeface
        editTextEmailAddress.typeface = typeface
        editTextPassword.typeface = typeface
        tvLogin.typeface = typeface

        cardViewLogin.visibility = View.GONE
        navc = Navigation.findNavController(view)
        imageButtonBack.setOnClickListener {
            navc?.navigate(R.id.action_loginFragment2_to_loginRegisterFragment)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        buttonSignIn.setOnClickListener {
            progressRLLogin.visibility = View.VISIBLE
            validateData()
            progressRLLogin.visibility = View.GONE
            cardViewLogin.visibility = View.VISIBLE
        }

        cardViewLogin.visibility = View.VISIBLE
        progressRLLogin.visibility = View.GONE
    }

    private fun validateData() {
        email = editTextEmailAddress.text.toString().trim()
        password = editTextPassword.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailAddress.error = "Invalid email format"
        } else if (TextUtils.isEmpty(password)){
            editTextPassword.error = "Please enter password"
        } else {
            progressRLLogin.visibility = View.GONE
            cardViewLogin.visibility = View.VISIBLE
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //val firebaseUser = firebaseAuth.currentUser
                //val email = firebaseUser!!.email
                navc?.navigate(R.id.action_loginFragment2_to_profileFragment)
            }
            .addOnFailureListener { //e->
                //Toast.makeText(this,"Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()

            }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser!=null){
            navc?.navigate(R.id.action_loginFragment2_to_profileFragment)
        }
    }


}
package com.example.projectvoitko

import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_login_register.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.toolbar_login.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [registerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class registerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var navc : NavController?= null
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""
    private var name = ""
    private var mDatabaseReference:DatabaseReference?=null
    private var mDatabase: FirebaseDatabase? = null

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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailAddress.error = "Invalid email format"
        } else if(TextUtils.isEmpty(password)){
            editTextPassword.error = "Please enter password"
        } else if(password.length<6){
            editTextPassword.error = "Password is short"
        }else if(TextUtils.isEmpty(name)){
            editTextName.error = "Please enter your name"
        } else {
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //val firebaseUser = firebaseAuth.currentUser
                val userId = firebaseAuth.currentUser!!.uid
                val currentUserDb = mDatabaseReference!!.child(userId)
                currentUserDb.child("name").setValue(name)
                //val email = firebaseUser!!.email
                navc?.navigate(R.id.action_registerFragment_to_loginFragment2)
            }
            .addOnFailureListener {

            }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment registerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            registerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
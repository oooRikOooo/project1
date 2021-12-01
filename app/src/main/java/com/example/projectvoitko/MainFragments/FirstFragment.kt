package com.example.projectvoitko.MainFragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.projectvoitko.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.toolbar.*

class FirstFragment : Fragment() {
    var navc: NavController? = null
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navc = Navigation.findNavController(view)
        firebaseAuth = FirebaseAuth.getInstance()
        imageButtonAccount.setOnClickListener {
            val firebaseUser = firebaseAuth.currentUser
            if(firebaseUser!=null){
                navc?.navigate(R.id.action_firstFragment_to_profileFragment)
            } else {
                navc?.navigate(R.id.action_firstFragment_to_loginRegisterFragment)
            }
        }
    }
}


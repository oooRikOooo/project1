package com.example.projectvoitko.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.projectvoitko.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.toolbar_edit_profile_fragments.*

class EditAboutFragment : Fragment() {
    private lateinit var firebaseAuth : FirebaseAuth
    val dataBase = Firebase.firestore
    var navc : NavController?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navc = Navigation.findNavController(view)
        firebaseAuth = FirebaseAuth.getInstance()
        var eText = view.findViewById<EditText>(R.id.editTextEditAbout)

        imageButtonSave.setOnClickListener {


            dataBase.collection("users").get().addOnSuccessListener { result ->
                val userId = firebaseAuth.currentUser!!.uid
                for (document in result) {
                    val id = document.data["userId"]
                    if (id == userId) {
                        val datas = dataBase.collection("users").document(document.id)
                        datas.update(
                            mapOf(
                                "about" to "${eText.text}",
                            )
                        )
                    }

                }

            }
            Toast.makeText(context, "Ім'я оновлено", Toast.LENGTH_SHORT).show()
        }

        imageButtonBack.setOnClickListener {
            navc?.navigate(R.id.action_editAboutFragment_to_editProfileFragment)
        }
    }
}
package com.example.projectvoitko.Profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.projectvoitko.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*

class EditProfileFragment : Fragment() {

    val database = Firebase.firestore

    var navc : NavController?= null
    var storageRef : StorageReference = FirebaseStorage.getInstance().reference
    private lateinit var firebaseAuth : FirebaseAuth
    private var imageName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObjectNotVisible()

        navc = Navigation.findNavController(view)
        firebaseAuth = FirebaseAuth.getInstance()
        //storageRef = FirebaseStorage.getInstance().reference

        fab.setImageResource(R.drawable.ic_name_camera)

        buttonCancelChanges.setOnClickListener {
            navc?.navigate(R.id.action_editProfileFragment_to_profileFragment)
        }

        fab.setOnClickListener {
            var openGalleryIntent:Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            startActivityForResult(openGalleryIntent,1000)
        }

        textViewName.setOnClickListener {
            navc?.navigate(R.id.action_editProfileFragment_to_editNameFragment)

        }

        textViewNickName.setOnClickListener {
            navc?.navigate(R.id.action_editProfileFragment_to_editNickNameFragment)
        }

        textViewAbout.setOnClickListener {
            navc?.navigate(R.id.action_editProfileFragment_to_editAboutFragment)
        }

        database.collection("users").get().addOnSuccessListener { result ->

            progressRLEditProfile.visibility = View.VISIBLE

            val userId = firebaseAuth.currentUser!!.uid
            for (document in result){
                val id = document.data["userId"]

                if(id == userId){
                    val name = document.data["first"] as String
                    textViewName.text = name
                    val nickNameTmp = document.data["nickname"] as String
                    val nickName = "@$nickNameTmp"
                    textViewNickName.text = nickName
                    val about = document.data["about"] as String
                    if (about == "")
                        textViewAbout.text = "Нажміть щоб додати інформацію про себе"
                    else textViewAbout.text = about
                    progressRLEditProfile.visibility = View.GONE
                    setObjectVisible()
                }
            }

        }
        /*buttonSaveChanges.setOnClickListener {

            database.collection("users").get().addOnSuccessListener { result ->
                val userId = firebaseAuth.currentUser!!.uid
                for (document in result){
                    val id = document.data["userId"]
                    if (id == userId){
                        val datas = database.collection("users").document(document.id)
                        datas.update(mapOf(
                            "first" to "${editTextEditName.text}",
                            "imageName" to "",
                            "nickname" to "${edittextEditSurname.text}",
                            "about" to "${editTextAbout.text}"
                        ))
                        *//*val user = hashMapOf(
                            "first" to "$editTextEditName",
                            "imageName" to "",
                            "nickname" to "$edittextEditSurname",
                            "about" to "$editTextAbout"
                        )*//*
                    }

                }

            }
            //navc?.navigate(R.id.action_editProfileFragment_to_profileFragment)

        }*/

        /*requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                navc?.navigate(R.id.action_editProfileFragment_to_profileFragment)
            }

        })*/


    }



    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        *//*if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                val imageUri = data?.data
                imageViewProfile.setImageURI(imageUri)
                uploadImageToFirebase(imageUri)
            }
        }*//*
    }*/

     private fun setObjectNotVisible(){
        fab.visibility = View.GONE
        cardViewEditProfile.visibility = View.GONE
        textView2.visibility = View.GONE
        textViewName.visibility = View.GONE
        textView3.visibility = View.GONE
        textViewNickName.visibility = View.GONE
        textViewAbout.visibility = View.GONE

    }

    private fun setObjectVisible(){
        fab.visibility = View.VISIBLE
        cardViewEditProfile.visibility = View.VISIBLE
        textView2.visibility = View.VISIBLE
        textViewName.visibility = View.VISIBLE
        textView3.visibility = View.VISIBLE
        textViewNickName.visibility = View.VISIBLE
        textViewAbout.visibility = View.VISIBLE

    }


    /*override fun onStart() {
        super.onStart()
        database.collection("users").get().addOnSuccessListener { result ->

            //progressRL.visibility = View.VISIBLE
            //mainLayout_profile.visibility = View.GONE

            //activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            val userId = firebaseAuth.currentUser!!.uid
            for (document in result){
                val id = document.data["userId"]

                if(id == userId){
                    val name = document.data["first"] as String
                    textViewName.text = name
                    val nickNameTmp = document.data["nickname"] as String
                    val nickName = "@$nickNameTmp"
                    textViewNickName.text = nickName
                    val about = document.data["about"] as String
                    if (about == "")
                        textViewAbout.text = "Нажміть щоб додати інформацію про себе"
                    else textViewAbout.text = about
                    //progressBar.visibility = View.GONE
                    //progressRL.visibility = View.GONE
                    //mainLayout_profile.visibility = View.VISIBLE
                    //cardViewProfile.visibility = View.VISIBLE
                }
                //Log.v(TAG, "${document.id} => ${document.data["first"]}")
            }

        }
    }*/
}
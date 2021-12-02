package com.example.projectvoitko.MainFragments.SecondFragmentElements

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.projectvoitko.R
import kotlinx.android.synthetic.main.fragment_new_event.*

class NewEventFragment : Fragment() {

    private var arrayList : ArrayList<String> = arrayListOf("Не дуже важливо", "Важливо", "Дуже важливо" )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chooseIsImportant : Spinner = view.findViewById(R.id.spinner)
        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, arrayList)
        //adapter = (context,android.R.layout.simple_spinner_item, arrayList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        chooseIsImportant.adapter = adapter
        var titleNewEvent : TextView = view.findViewById(R.id.editTextAddTitle)
        var addInformationEvent : TextView = view.findViewById(R.id.editTextAddInformation)
        var dateEvent : TextView = view.findViewById(R.id.editTextAddDate)





    }

}
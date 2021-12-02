package com.example.projectvoitko.MainFragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectvoitko.MainFragments.FirstFragmentElements.Plants
import com.example.projectvoitko.MainFragments.FirstFragmentElements.PlantsAdapter
import com.example.projectvoitko.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.toolbar.*

class FirstFragment : Fragment() {
    var navc: NavController? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Plants>
    private lateinit var adapter: PlantsAdapter
    lateinit var imageId : Array<Int>
    lateinit var titleName : Array<String>
    lateinit var description : Array<String>
    private lateinit var newEditText: SearchView


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
        imageId = arrayOf(
            R.drawable.rose,
            R.drawable.potato,
            R.drawable.grape,
            R.drawable.apple,
            R.drawable.pear
        )

        titleName = arrayOf(
            "Троянда",
            "Картопля",
            "Виноград",
            "Яблуня",
            "Груша звичайна"

        )

        description = arrayOf(
            "Рід і культурна форма рослин родини трояндових, листопадні, рідко вічнозелені кущі до 4 метрів заввишки. У класичному зображенні троянда має 32 пелюстки, звідси назва роза (троянда) вітрів.",
            "Вид рослин родини пасльонових, поширена сільськогосподарська культура, яку в народі називають «другим хлібом», одна з найважливіших продовольчих, технічних і кормових культур.",
            "Рід рослин родини виноградових (Vitaceae). Рід містить ≈ 75 видів, які зростають у Євразії, Північній Америці й на півночі Південної Америки. Україна є батьківщиною одного виду — виноград справжній Vitis vinifera L.",
            "Рід листопадних дерев і кущів родини трояндових з кулеподібними солодкими чи кисло-солодкими плодами.",
            "Місцеві назви — груша дика, лісовка, дичка тощо. З родини розових — Rosaceae."
        )


        newRecyclerView = view.findViewById(R.id.recyclerViewMain)
        newRecyclerView.layoutManager = LinearLayoutManager(context)
        newRecyclerView.setHasFixedSize(true)


        newArrayList = arrayListOf<Plants>()
        getPlantsData()
        adapter = PlantsAdapter(newArrayList)
        newRecyclerView.adapter = adapter

        newEditText = view.findViewById(R.id.searchView)
        /*newEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

        })*/
        newEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(s: String?): Boolean {
                filter(s.toString())
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                filter(s.toString())
                return false
            }

        })


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

    private fun filter(s: String) {
        var filteredList: ArrayList<Plants> = arrayListOf<Plants>()
        for (i in newArrayList.indices){
            if (newArrayList[i].title.lowercase().contains(s.lowercase())){
                filteredList.add(newArrayList[i])
            }
        }
        //newRecyclerView.filterList(filteredList)
        adapter.filterList(filteredList)

    }

    private fun getPlantsData() {
        for (i in imageId.indices){
            val plants = Plants(imageId[i], titleName[i], description[i])
            newArrayList.add(plants)
        }

        newRecyclerView.adapter = PlantsAdapter(newArrayList)
    }
}


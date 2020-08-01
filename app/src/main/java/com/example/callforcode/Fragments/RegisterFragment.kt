package com.example.callforcode.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.callforcode.R
import com.example.callforcode.Services.BussinesService
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {


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

    override fun onStart() {
        super.onStart()
        val name = view?.findViewById<EditText>(R.id.register_name)
        val category = view?.findViewById<EditText>(R.id.register_category)
        val description = view?.findViewById<EditText>(R.id.register_description)




        button_register_bussines.setOnClickListener {
            Log.i("name",name?.text.toString())
            Log.i("category",category?.text.toString())
            Log.i("description",description?.text.toString())
            var bussines = BussinesService.getInstance()
                .registerBussines(context, name?.text.toString(), category?.text.toString(), description?.text.toString(), "19.123456", "-99.123456")
            Log.i("ID",bussines.toString())

        }
    }


}
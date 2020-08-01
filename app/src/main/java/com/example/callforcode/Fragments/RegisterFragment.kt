package com.example.callforcode.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import com.example.callforcode.R
import com.example.callforcode.Services.BussinesService
import com.google.android.gms.common.util.SharedPreferencesUtils
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
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

            var bussines = BussinesService.getInstance()
                .registerBussines(context, name?.text.toString(), category?.text.toString(), description?.text.toString(), "15.123", "-20.123")
            Thread.sleep(1000)
            lateinit var homeFragment: HomeFragment
            homeFragment = HomeFragment()
            this.activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout,homeFragment)
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ?.commit()
            nav_view.setCheckedItem(R.id.home_item)




        }
    }


}
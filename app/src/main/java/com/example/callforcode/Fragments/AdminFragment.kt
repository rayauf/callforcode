package com.example.callforcode.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import com.example.callforcode.R
import com.example.callforcode.Services.BussinesService


@Suppress("UNREACHABLE_CODE")
class AdminFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false)

    }

    override fun onStart() {
        super.onStart()

        val sw1 = view?.findViewById<Switch>(R.id.switch_update)
        sw1?.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                var bussines = BussinesService.getInstance()
                    .updateSwitch(context, "open")
            }else{
                var bussines = BussinesService.getInstance()
                    .updateSwitch(context, "close")
            }

        }
    }
}
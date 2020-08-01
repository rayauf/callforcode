package com.example.callforcode.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.callforcode.Adapters.BussinesAdapter
import com.example.callforcode.Model.Bussines
import com.example.callforcode.R
import com.example.callforcode.Services.BussinesService
import com.example.callforcode.Views.CardPadding
import kotlinx.android.synthetic.main.activity_main.*

class HomeFragment : Fragment() {

    private val bussinesAdapter:BussinesAdapter? = null
    private var bussines = ArrayList<Bussines>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        val listener: placesDL = object : placesDL {
            override fun success(success: Boolean) {
                    if (success) {
                        setUpRecycler(view!!)
                    }
            }
        }
        setUpRecycler(view!!)
        bussines = BussinesService.getInstance().getAllBussies(context, listener)

    }

    private fun setUpRecycler(view: View){
        val recyclerView:RecyclerView =view.findViewById(R.id.recycler_main)
        recyclerView.setHasFixedSize(true)
        val adapter:BussinesAdapter = BussinesAdapter(bussines)
        recyclerView.adapter = adapter
        val layoutManager : RecyclerView.LayoutManager =LinearLayoutManager(context)
        layoutManager.canScrollVertically()
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(CardPadding(0, 0, 0, 10))
    }
    interface placesDL {
        fun success(success:Boolean)
    }
}
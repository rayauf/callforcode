package com.example.callforcode.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.callforcode.Model.Bussines
import com.example.callforcode.R
import kotlinx.android.synthetic.main.cardview_bussines.view.*

class BussinesAdapter(items:ArrayList<Bussines>) : RecyclerView.Adapter<BussinesAdapter.ViewHolder>() {

    var items:ArrayList<Bussines>? = null
    init {
        this.items  = items
    }
    var viewHolder:ViewHolder? = null

    override fun onBindViewHolder(holder: BussinesAdapter.ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.name?.text = item?.name!!
        holder.category?.text = item?.category!!
        if(item?.status == "open"){
            holder.state?.setImageResource(R.drawable.ic_open)
        }
        else
            holder.state?.setImageResource(R.drawable.ic_close)

    }

    override fun getItemCount(): Int {
       return this.items?.count()!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BussinesAdapter.ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.cardview_bussines,parent,false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!
    }



    class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){
        var vista = vista
        var name:TextView? = null
        var category:TextView? = null
        var state:ImageView? = null
        init {
            name=vista.bussiness_name
            category=vista.bussines_category
            state=vista.bussiness_state
        }
    }

}
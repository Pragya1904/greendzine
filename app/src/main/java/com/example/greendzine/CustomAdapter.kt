package com.example.greendzine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CustomAdapter(val empDataList: ArrayList<ModelEmp>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bindData(empModel:ModelEmp){
            val idTextView=itemView.findViewById<TextView>(R.id.idTextView)
            val nameTextView=itemView.findViewById<TextView>(R.id.nameTextView)
            val dobTextView=itemView.findViewById<TextView>(R.id.dobTextView)
            val roleTextView=itemView.findViewById<TextView>(R.id.roleTextView)
            val badgeTextView=itemView.findViewById<TextView>(R.id.badgeTextView)
            nameTextView.text= empModel.name
            idTextView.text=empModel.emp_id.toString()
            roleTextView.text=empModel.role
            dobTextView.text=empModel.dob
            badgeTextView.text=empModel.emp_id.toString()

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        //  used to bind the data on the list
        holder.bindData(empDataList[position])

    }

    override fun getItemCount(): Int {
        return empDataList.size
    }
}

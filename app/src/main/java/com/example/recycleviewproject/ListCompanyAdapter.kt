package com.example.recycleviewproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListCompanyAdapter(private val listCompany: ArrayList<Company>): RecyclerView.Adapter<ListCompanyAdapter.ListViewHolder>()  {
    private lateinit var onItemClickCallback : OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_company, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListCompanyAdapter.ListViewHolder, position: Int) {
        val (name, description, developer, photo) = listCompany[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDeveloper.text = developer
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listCompany[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = listCompany.size


    interface OnItemClickCallback {
        fun onItemClicked(data: Company)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDeveloper: TextView = itemView.findViewById(R.id.tv_item_developer)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }



}
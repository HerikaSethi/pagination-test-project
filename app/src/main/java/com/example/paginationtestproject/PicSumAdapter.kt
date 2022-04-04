package com.example.paginationtestproject

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paginationtestproject.model.PicResult
import com.example.paginationtestproject.model.PicResultItem

class PicSumAdapter(val context: Context, val picResultItem: ArrayList<PicResultItem>):RecyclerView.Adapter<PicSumAdapter.PicSumViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicSumViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_row,parent,false)
        return PicSumViewHolder(view)
    }

    override fun onBindViewHolder(holder: PicSumViewHolder, position: Int) {
        val picS = picResultItem[position]

        holder.picSumAuthor.text = picS.author
        Glide.with(context).load(picS.download_url).into(holder.picSumImage)

        holder.itemView.setOnClickListener() {
            val url = picS.download_url
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            context.startActivity(i)
        }

       if (position == itemCount -1){

            Toast.makeText(context,"I am the chosen one: Last item reached",Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return picResultItem.size
    }

    class PicSumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var picSumImage = itemView.findViewById<ImageView>(R.id.picSumImage)
        var picSumAuthor = itemView.findViewById<TextView>(R.id.picSumAuthor)
    }

}
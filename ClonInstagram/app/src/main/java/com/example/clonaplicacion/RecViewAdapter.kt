package com.example.clonaplicacion

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class RecViewAdapter(
        private val listPosts:List<String>,
        private val context:Instagram,
        private val recView:androidx.recyclerview.widget.RecyclerView
) :androidx.recyclerview.widget.RecyclerView.Adapter<RecViewAdapter.Holder>(){



    inner class Holder(view: View):
            androidx.recyclerview.widget.RecyclerView.ViewHolder(view){

                val likesTex:TextView
                val heartLike:ImageView
                var likes=0
        var liked=false
                init{
                    likesTex=view.findViewById(R.id.Likes)
                    heartLike=view.findViewById(R.id.iv_btn_heart)
                    heartLike.isClickable
                    heartLike.setOnClickListener{
                        this.addlike()
                    }

                }

        fun addlike(){
            if(!liked){
                liked=true
                var likesCurrent=likesTex.text.toString().toInt()
                likesCurrent++
                likesTex.setText(likesCurrent.toString())


                heartLike.setImageResource(R.drawable.inst_heart_liked)
            }else{
                liked=false
                var likesCurrent=likesTex.text.toString().toInt()
                likesCurrent--
                likesTex.setText(likesCurrent.toString())


                heartLike.setImageResource(R.drawable.inst_heart)
            }



        }

    }
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int)
    : RecViewAdapter.Holder {
        val itemView=LayoutInflater
                .from(parent.context)
                .inflate(
                        R.layout.recycle_view_layout,
                        parent,
                        false
                )
        return Holder(itemView)

    }

    override fun onBindViewHolder(holder: RecViewAdapter.Holder, position: Int) {
        var post=listPosts[position]
        holder.likesTex.text=post

    }

    override fun getItemCount(): Int {
        return listPosts.size
    }


}
package com.sitamrock11.networkingdemo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class UserAdapter(val context: Context): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
        var userList=ArrayList<User?>()
    fun swapData(userList:ArrayList<User?>){
        this.userList=userList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
            return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(userList!![position]?.avatarUrl).into(holder.imageView)
        holder.tvUserId.text= userList!![position]?.login
        holder.tvName.text= userList!![position]?.name
        holder.tvLocation.text= userList!![position]?.location
        holder.itemView.setOnClickListener {
            val intent= Intent(context,UserActivity::class.java)
            intent.putExtra("name",userList[position]!!.name)
            intent.putExtra("company",userList[position]?.company)
            intent.putExtra("location",userList[position]!!.location)
            intent.putExtra("bio",userList[position]!!.bio)
            intent.putExtra("p_repos",userList[position]!!.publicRepos)
            intent.putExtra("p_gists",userList[position]!!.publicGists)
            intent.putExtra("followers",userList[position]!!.followers)
            intent.putExtra("following",userList[position]!!.following)
            intent.putExtra("image",userList[position]!!.avatarUrl)
            intent.putExtra("twitterId",userList[position]?.twitterUsername)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList!!.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView=itemView.findViewById<ImageView>(R.id.imageView)
        val tvUserId=itemView.findViewById<TextView>(R.id.tvUserId)
        val tvName=itemView.findViewById<TextView>(R.id.tvName)
        val tvLocation=itemView.findViewById<TextView>(R.id.tvLocation)
    }

}
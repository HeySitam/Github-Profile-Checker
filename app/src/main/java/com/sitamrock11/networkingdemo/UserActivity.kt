package com.sitamrock11.networkingdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val extras = intent.extras
        val name = extras!!.getString("name")
        val company = extras.getString("company")
        val location =" "+ extras.getString("location")
        val bio = extras.getString("bio")
        val p_repos = extras.getInt("p_repos")
        val p_gists = extras.getInt("p_gists")
        val followers = extras.getInt("followers")
        val following = extras.getInt("following")
        val image = extras.getString("image")
        val twitterId = extras.getString("twitterId")
        Picasso.get().load(image).into(imgFull)
        tvNameFull.text=name
        tvBio.text=bio
        tvLocationFull.text=location
        followers.toString().also { tvFollowers.text = it }
        tvFollowing.text=following.toString()
        tvRepos.text=p_repos.toString()
        tvGists.text=p_gists.toString()
        tvCompany.text=company
        btnTwitter.setOnClickListener {
            if (twitterId != null) {
                val i = Intent()
                i.action = Intent.ACTION_VIEW
                i.data = Uri.parse("https://twitter.com/${twitterId}")
                startActivity(i)
            }else
                Toast.makeText(this,"Sorry!! No Twitter Username found..",Toast.LENGTH_SHORT).show()
        }
    }
}
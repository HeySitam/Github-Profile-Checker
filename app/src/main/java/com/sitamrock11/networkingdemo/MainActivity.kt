package com.sitamrock11.networkingdemo


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.*
import java.util.*


class MainActivity : AppCompatActivity() {
    val userAdapter by lazy{
        UserAdapter(this)
    }

    val list=ArrayList<User?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val list=ArrayList<User?>()
        etSearch.requestFocus()
        title="GitHub Profile Checker"
         rvUser.apply{
             layoutManager=LinearLayoutManager(this@MainActivity)
             adapter=this@MainActivity.userAdapter
         }
        ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(rvUser)
    imgSearch.setOnClickListener {
            CoroutineScope(IO).launch {

                val response = Client.api.getUserById(etSearch.text.toString())
                withContext(Main) {
                    if (response.isSuccessful) {
                        response.body().let {
                            if (it != null) {
                                list.add(it)
                                userAdapter.swapData(list)
                            } else {
                                Toast.makeText(
                                        this@MainActivity,
                                        "OOPs!! No user found.",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }

        userAdapter.swapData(list)
        }
    }

    fun initRecyclerView(list:ArrayList<User?>){
        rvUser.apply{
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=this@MainActivity.userAdapter
        }
        ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(rvUser)
        userAdapter.swapData(list)
    }




        var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            //Remove swiped item from list and notify the RecyclerView
            val position = viewHolder.adapterPosition
            Toast.makeText(this@MainActivity, "${list[position]?.login} have been removed.", Toast.LENGTH_SHORT).show()
            val deletedItem=list[position]
            list.removeAt(position)
            userAdapter.notifyDataSetChanged()
            Snackbar.make(llMainActivity,"Deleted Successfully !",Snackbar.LENGTH_SHORT)
                    .setAction("Undo", View.OnClickListener {
                        list.add(position,deletedItem)
                        userAdapter.notifyDataSetChanged()
                    }).show()
        }
    }
    }


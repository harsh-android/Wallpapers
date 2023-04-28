package com.hinfo.allgames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hinfo.allgames.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbRef = FirebaseDatabase.getInstance().reference

        dbRef.root.child("Images").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var list = ArrayList<ImageModel>()
                for (data in snapshot.children) {
                    var model = data.getValue(ImageModel::class.java)
                    list.add(model!!)
                }

                binding.rcvWallpapers.layoutManager = GridLayoutManager(this@MainActivity,3)
                var adapter= ImagesAdapter{ data->


                }
                adapter.setArray(list)
                binding.rcvWallpapers.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
}
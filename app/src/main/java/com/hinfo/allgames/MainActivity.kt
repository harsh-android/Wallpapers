package com.hinfo.allgames

import android.R
import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hinfo.allgames.databinding.ActivityMainBinding
import com.hinfo.allgames.databinding.ImageDialogBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var dbRef: DatabaseReference
    var cateList = ArrayList<CategoryModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbRef = FirebaseDatabase.getInstance().reference

        dbRef.root.child("Category").addValueEventListener(object : ValueEventListener {
            @SuppressLint("ResourceAsColor")
            override fun onDataChange(snapshot: DataSnapshot) {
                cateList.clear()
                for (data in snapshot.children) {
                    var model = data.getValue(CategoryModel::class.java)
                    binding.tabCategory.addTab(binding.tabCategory.newTab().setText(model?.name))
                    cateList.add(model!!)
                }


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


        dbRef.root.child("Images").addValueEventListener(object : ValueEventListener {
            @SuppressLint("ResourceAsColor")
            override fun onDataChange(snapshot: DataSnapshot) {
                var list = ArrayList<ImageModel>()
                for (data in snapshot.children) {
                    var model = data.getValue(ImageModel::class.java)
                    list.add(model!!)
                }

                binding.rcvWallpapers.layoutManager = GridLayoutManager(this@MainActivity, 3)
                var adapter = ImagesAdapter { data ->

                    var dialog = Dialog(this@MainActivity)
                    dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))
                    var bin = ImageDialogBinding.inflate(
                        LayoutInflater.from(this@MainActivity),
                        null,
                        false
                    )
                    dialog.setContentView(bin.root)

                    Glide.with(this@MainActivity).load(data.image).into(bin.imgPhoto)

                    dialog.show()

                }
                adapter.setArray(list)
                binding.rcvWallpapers.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
}
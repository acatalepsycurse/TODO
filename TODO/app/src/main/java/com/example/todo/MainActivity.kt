package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.Insets.add
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listAdapter = ListAdapter(mutableListOf())

        rvListItems.adapter = listAdapter
        rvListItems.layoutManager = LinearLayoutManager(this)

        btnAddList.setOnClickListener {
            val toListTitle = etListTitle.text.toString()
            if(toListTitle.isNotEmpty()) {
                val list = List(toListTitle)
                listAdapter.addToList(list)
                etListTitle.text.clear()
            }
        }
        btnDelList.setOnClickListener {
            listAdapter.deleteMarkedLists()
        }
    }
}
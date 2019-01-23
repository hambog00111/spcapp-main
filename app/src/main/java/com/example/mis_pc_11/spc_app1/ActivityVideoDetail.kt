package com.example.mis_pc_11.spc_app1

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_video_detail.*

class ActivityVideoDetail : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_video_detail)
        recycler_video_detail.layoutManager = LinearLayoutManager(this)

    }

}
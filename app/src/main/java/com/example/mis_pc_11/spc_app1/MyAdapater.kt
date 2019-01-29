package com.example.mis_pc_11.spc_app1

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class MyAdapater(private val place : IntArray, private val name: Array<String>, private val mContext: Context)
    :RecyclerView.Adapter<PictureHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PictureHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.activity_picture_main,p0, false)
        return PictureHolder(v, mContext)
    }

    override fun onBindViewHolder(p0: PictureHolder, p1: Int) {
        p0.index(place[p1],name[p1])
    }

    override fun getItemCount(): Int {
        return place.size
    }
}
package com.example.mis_pc_11.spc_app1

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

class VideoAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>(){

val videoTitles = listOf("First Title", "Second", "Third")

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.video_row, p0, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int ) {
        //val videoTitle = videoTitles.get(position)
        val video = homeFeed.videos.get(position)

        holder.itemView.textView_video_name.text = video.name
        holder.itemView.textView_video_channel.text = video.channel.name


        val thumbnailImageView = holder.itemView.imageView_video_thumbnail
        Picasso.with(holder.itemView.context).load(video.imageUrl).into(thumbnailImageView)

        val channelImageView = holder.itemView.imageView_video_profile
        Picasso.with(holder.itemView.context).load(video.channel.profileImageUrl).into(channelImageView)

    }
}

class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){

    init{
        view.setOnClickListener {
            println("TEST")

            val intent = Intent(itemView.context,MainActivity::class.java)

            itemView.context.startActivity(intent)
        }
}


}
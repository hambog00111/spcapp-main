<<<<<<< Updated upstream
package com.example.mis_pc_11.spc_app1

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_video_detail.*
import kotlinx.android.synthetic.main.activity_video_detail_list.view.*
import okhttp3.*
import java.io.IOException

class ActivityVideoDetail : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_video_detail)

        recycler_video_detail.layoutManager = LinearLayoutManager(this)


        val navBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTitle



      //  println(videoDetailUrl)

        fetchJSON()
    }

    private fun fetchJSON(){

        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_KEY, -1)
        val videoDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=" + videoId

        val client = OkHttpClient()
        val request = Request.Builder().url(videoDetailUrl).build()
        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call, response: Response) {

                val body = response.body()?.string()

                val gson = GsonBuilder().create()

                val videoDetailList = gson.fromJson(body, Array<VideoDetail>::class.java)
                //println(body)
                runOnUiThread {

                    recycler_video_detail.adapter = AdapterVideoDetail(videoDetailList)
                }

            }

            override fun onFailure(call: Call, e: IOException) {

            }

        })
    }



    private class AdapterVideoDetail(val videoDetailList: Array<VideoDetail>): RecyclerView.Adapter<HolderDetailVideo>(){

        override fun getItemCount(): Int {
            return videoDetailList.size
        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderDetailVideo {
            val layoutInflater = LayoutInflater.from(p0.context)
            val customView = layoutInflater.inflate(R.layout.activity_video_detail_list, p0, false)

            return HolderDetailVideo(customView)
        }

        override fun onBindViewHolder(p0: HolderDetailVideo, p1: Int) {
            val videoDetailLists = videoDetailList.get(p1)
//show all the text and pic in the videolist
            p0.customView.textView_video_detail_name.text = videoDetailLists.name
            p0.customView.textView_video_detail_duration.text = videoDetailLists.duration

            val imageViews = p0.customView.imageView_video_detail_picture
            Picasso.with(p0.customView.context).load(videoDetailLists.imageUrl).into(imageViews)

            p0.videoCustom = videoDetailLists
       }
    }
     class HolderDetailVideo(val customView: View, var videoCustom: VideoDetail? =  null): RecyclerView.ViewHolder(customView){
        //link to website
        companion object {
            val VIDEO_WEB_LINK = "VIDEO WEB"
        }
        init{
            customView.setOnClickListener {
              //  println("soload")

                val intent = Intent(customView.context, ActivityVideoView::class.java)
                customView.context.startActivity(intent)

                intent.putExtra(VIDEO_WEB_LINK, videoCustom?.link)
            }
        }
    }
=======
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

>>>>>>> Stashed changes
}
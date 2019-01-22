package com.example.mis_pc_11.spc_app1

class HomeFeed(val videos: List<Video>)

class Video(val id : Int , val name: String, val link: String, val imageUrl: String,
            val numberOfViews: Int, val channel: com.example.mis_pc_11.spc_app1.Channel )

class Channel(val name: String, val profileImageUrl: String)
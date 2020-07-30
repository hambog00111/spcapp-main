package ph.Sanpablocitygov.iSanPablo.tourism

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ph.Sanpablocitygov.iSanPablo.R

class
FragmentLandmarks : Fragment() {

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.tourism_landmarks_fragment, null)
//

//        val btn_pictures = view?.findViewById<Button>(R.id.button_picture)
//        btn_pictures?.setOnClickListener{
//            val intent = Intent (activity, ActivityPictures::class.java)
//            activity?.startActivity(intent)
//        }
//        val btn_videos = view?.findViewById<Button>(R.id.button_videos)
//        btn_videos?.setOnClickListener{
//            val intent = Intent (activity, ActivityVideos::class.java)
//            activity?.startActivity(intent)
//        }
        return view
    }
}

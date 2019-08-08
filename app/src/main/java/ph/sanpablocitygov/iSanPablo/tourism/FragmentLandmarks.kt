package ph.sanpablocitygov.iSanPablo.tourism

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.sanpablocitygov.iSanPablo.R

class FragmentLandmarks : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_landmarks, null)
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

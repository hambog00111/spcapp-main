package ph.sanpablocitygov.iSanPablo

import android.support.v7.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView

class FragmentOurCity : Fragment() {
    var str_url: String =
        "https://ia801501.us.archive.org/35/items/SanPabloHymnWithLyrics/San%20Pablo%20Hymn%20with%20Lyrics.mp4"
    lateinit var mediaController: MediaController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.our_city_fragment, container, false)

        val hymn1 = view.findViewById<Button>(R.id.btn_play_hymn)
        hymn1.setOnClickListener {

            val hymnView = LayoutInflater.from(requireContext()).inflate(R.layout.our_city_hymn_dialogbox, null)

            val hymnBuilder = AlertDialog.Builder(requireContext())
                .setView(hymnView)

            val bploDialog = hymnBuilder.show()

            val videohome = hymnView.findViewById<View>(R.id.videohome) as VideoView
            videohome.setVideoPath(str_url)
            videohome.start()
            mediaController = MediaController(requireContext())
            mediaController.setAnchorView(videohome)
            videohome.setMediaController(mediaController)

            val canhymn = hymnView.findViewById<TextView>(R.id.btn_hymn_cancel)
                canhymn.setOnClickListener { bploDialog.dismiss() }

        }

        return  view

    }
}
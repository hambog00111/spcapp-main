package ph.sanpablocitygov.iSanPablo.tourism


import android.support.v7.app.AlertDialog
import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

import kotlinx.android.synthetic.main.dialog_tourism_masterplan.view.*
import ph.sanpablocitygov.iSanPablo.FragmentGoogleMap
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.tourism.Festival.FragmentFestival
import ph.sanpablocitygov.iSanPablo.tourism.WheretoStayEat.FragmentWheretoStayEat


class FragmentTourism : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_tourism_layout, container, false)



        val titletour = view.findViewById<TextView>(R.id.txt_title_tour)
        titletour.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_transition_animation)

        val linear_tm  = view.findViewById<LinearLayout>(R.id.linear_tour_tm)
        linear_tm.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val linear_fest  = view.findViewById<LinearLayout>(R.id.linear_tour_fest)
        linear_fest.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val linear_wtg  = view.findViewById<LinearLayout>(R.id.linear_tour_wtg)
        linear_wtg.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val linear_wts  = view.findViewById<LinearLayout>(R.id.linear_tour_wts)
        linear_wts.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val linear_htg  = view.findViewById<LinearLayout>(R.id.linear_tour_htgt)
        linear_htg.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val linear_landmarks  = view.findViewById<LinearLayout>(R.id.linear_tour_land)
        linear_landmarks.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)






        val btngallery = view.findViewById<Button>(R.id.btn_tourism_gallery)
        btngallery?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentLandmarks() , null)
                .addToBackStack(null)
                .commit()
        }
        val howto = view.findViewById<Button>(R.id.btn_tourism_how_tgt)
        howto?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentGoogleMap() , null)
                .addToBackStack(null)
                .commit()
        }
        val btngo = view.findViewById<Button>(R.id.btn_tourism_go)
        btngo?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentWheretogo() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnfes = view.findViewById<Button>(R.id.btn_tourism_festivals)
        btnfes?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentFestival(), null)
                .addToBackStack(null)
                .commit()
        }
        val btnstay = view.findViewById<Button>(R.id.btn_tourism_where)
        btnstay?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentWheretoStayEat(), null)
                .addToBackStack(null)
                .commit()
        }

        val btnmp = view.findViewById<Button>(R.id.btn_tourism_masterplan)
        btnmp.setOnClickListener {
            val dbView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_tourism_masterplan, null)

            val dbBuilder = AlertDialog.Builder(requireContext())
                .setView(dbView)

            val dbDialog = dbBuilder.show()

            dbView.btn_doing_master_confirm.setOnClickListener {
                dbDialog.dismiss()
                val downloadManager: DownloadManager = ContextCompat.getSystemService(
                    requireContext(),
                    DownloadManager::class.java
                ) as DownloadManager
                val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/SP%20TMP.pdf")
                val request = DownloadManager.Request(uri)
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                downloadManager.enqueue(request)  }

            dbView.btn_doing_master_cancel.setOnClickListener {
                dbDialog.dismiss()
            }
        }
        return view
    }
}
package ph.Sanpablocitygov.iSanPablo.home.isanpablo.GovernmentOnlineServices

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import ph.Sanpablocitygov.iSanPablo.R

class FragmentGovermentOnlineServices : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view: View = inflater.inflate(R.layout.home_isanpablo_govolser_fragment, container, false)


        val titlegos = view.findViewById<TextView>(R.id.txt_title_gos)
        titlegos.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_transition_animation)

        val lnrbir  = view.findViewById<LinearLayout>(R.id.linear_gos_bir)
        lnrbir.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrdti  = view.findViewById<LinearLayout>(R.id.linear_gos_dti)
        lnrdti.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrpass= view.findViewById<LinearLayout>(R.id.linear_gos_poa)
        lnrpass.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrnbi  = view.findViewById<LinearLayout>(R.id.linear_gos_nbi)
        lnrnbi.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrsss  = view.findViewById<LinearLayout>(R.id.linear_gos_sss)
        lnrsss.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrphil  = view.findViewById<LinearLayout>(R.id.linear_gos_philsys)
        lnrphil.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrph  = view.findViewById<LinearLayout>(R.id.linear_gos_philhealth)
        lnrph.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrpagibig  = view.findViewById<LinearLayout>(R.id.linear_gos_pagibig)
        lnrpagibig.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)











        val btnbir = view.findViewById<Button>(R.id.btn_govser_bir)
        btnbir?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentLinkBIR() , null)
                .addToBackStack(null)
                .commit()
        }
        val btndti = view.findViewById<Button>(R.id.btn_govser_dti)
        btndti?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentLinkDTI() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnpagibig = view.findViewById<Button>(R.id.btn_govser_pagibig)
        btnpagibig?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentLinkPagibig() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnpassport = view.findViewById<Button>(R.id.btn_govser_passport)
        btnpassport?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentLinkPassport() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnphilhealth = view.findViewById<Button>(R.id.btn_govser_philhealth)
        btnphilhealth?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentLinkPhilHealth() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnphilsys = view.findViewById<Button>(R.id.btn_govser_philsys)
        btnphilsys?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentLinkPhilSys() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnlinksss = view.findViewById<Button>(R.id.btn_govser_sss)
        btnlinksss?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentLinkSSS() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnnbi = view.findViewById<Button>(R.id.btn_govser_nbi)
        btnnbi?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentNBI() , null)
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}

package ph.sanpablocitygov.iSanPablo.home.isanpablo.GovernmentOnlineServices

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ph.sanpablocitygov.iSanPablo.R

class FragmentGovermentOnlineServices : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_isanpablo_govolser, container, false)
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

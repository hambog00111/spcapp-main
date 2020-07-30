package ph.Sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.update_fragment.view.*

import ph.Sanpablocitygov.iSanPablo.R

open class FragmentMyTaxes : Fragment(){

@SuppressLint("InflateParams")
override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val view: View = inflater.inflate(R.layout.home_isanpablo_mytaxes_fragment, container, false)


    //animation

    val titlemt = view.findViewById<TextView>(R.id.txt_title_mt)
    titlemt.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_transition_animation)

    val lnrrpt  = view.findViewById<LinearLayout>(R.id.linear_mt_rpt)
    lnrrpt.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
    val lnrfp  = view.findViewById<LinearLayout>(R.id.linear_mt_fp)
    lnrfp.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
    val lnrmop  = view.findViewById<LinearLayout>(R.id.linear_mt_mop)
    lnrmop.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
    val lnrlcr  = view.findViewById<LinearLayout>(R.id.linear_mt_lcr)
    lnrlcr.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
    val lnrcert  = view.findViewById<LinearLayout>(R.id.linear_mt_cert)
    lnrcert.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
    val lnrop  = view.findViewById<LinearLayout>(R.id.linear_mt_op)
    lnrop.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)




    val btnrtpp = view.findViewById<Button>(R.id.btn_rpt_payment)
    btnrtpp?.setOnClickListener {
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.frag_container,
            FragmentRTPPayment() , null)
            .addToBackStack(null)
            .commit()
    }
    val btnfp = view.findViewById<Button>(R.id.btn_fp)
    btnfp?.setOnClickListener {
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.frag_container,
            FragmentRTPPayment() , null)
            .addToBackStack(null)
            .commit()
    }

    val btnmop = view.findViewById<Button>(R.id.btn_mop)
    btnmop?.setOnClickListener {
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.frag_container,
            FragmentRTPPayment() , null)
            .addToBackStack(null)
            .commit()
    }


    val btnlcrrp = view.findViewById<Button>(R.id.btn_lcr_request_payment)
    btnlcrrp?.setOnClickListener {
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.frag_container,
            FragmentRTPPayment() , null)
            .addToBackStack(null)
            .commit()
    }

    val btncert = view.findViewById<Button>(R.id.btn_op)
    btncert.setOnClickListener {

        val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.update_fragment, null)

        val mybusBuilder = AlertDialog.Builder(requireContext())
            .setView(mybus)

        val mybusDialog = mybusBuilder.show()

        mybus.txt_confirm_update.setOnClickListener {
            mybusDialog.dismiss()
        }


    }

    val mybusiness = view.findViewById<Button>(R.id.btn_my_taxes_cert)
    mybusiness.setOnClickListener {

        val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.update_fragment, null)

        val mybusBuilder = AlertDialog.Builder(requireContext())
            .setView(mybus)

        val mybusDialog = mybusBuilder.show()

        mybus.txt_confirm_update.setOnClickListener {
            mybusDialog.dismiss()
        }


    }


    return view
}
}
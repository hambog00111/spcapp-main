package ph.sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import ph.sanpablocitygov.iSanPablo.R

open class FragmentMyTaxes : Fragment(){

override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val view: View = inflater.inflate(R.layout.fragment_isanpablo_mytaxes, container, false)

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

    val btnop = view.findViewById<Button>(R.id.btn_op)
    btnop?.setOnClickListener {
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.frag_container,
            FragmentRTPPayment() , null)
            .addToBackStack(null)
            .commit()
    }

    return view
}
}
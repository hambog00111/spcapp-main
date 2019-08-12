package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_fill_up.view.*
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.PrintMayorsPermit.FragmentMayorsPermit


class FragmentBPLOProfile : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bc_bplo_profile, null)

        val btnlogin = view.findViewById<Button>(R.id.txt_bplo_apply_now)
        btnlogin.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep1() , null)
                .addToBackStack(null)
                .commit()
        }
        return view
    }
    }

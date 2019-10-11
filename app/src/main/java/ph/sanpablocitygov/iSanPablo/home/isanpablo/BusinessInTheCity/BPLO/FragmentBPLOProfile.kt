package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.DialogInterface
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.dialog_bplo.view.*
import kotlinx.android.synthetic.main.dialoglogout.view.*
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.Amendments.FragmentAmendments1
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Loginbplo.FragmentLoginBPLO
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.PrintMayorsPermit.FragmentMayorsPermit


class FragmentBPLOProfile : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bc_bplo_profile, null)

        val btnapply = view.findViewById<Button>(R.id.txt_bplo_apply_now)
        btnapply.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep1() , null)
                .addToBackStack(null)
                .commit()
        }

        val btnpaynow = view.findViewById<Button>(R.id.txt_bplo_pay_now)
        btnpaynow.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOPayNow() , null)
                .addToBackStack(null)
                .commit()
        }

        val btnamendments = view.findViewById<Button>(R.id.txt_bplo_amendments)
        btnamendments.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentAmendments1() , null)
                .addToBackStack(null)
                .commit()
        }

        val btnlogout = view.findViewById<TextView>(R.id.txt_bplo_log_out)
        btnlogout.setOnClickListener {

            var logout = LayoutInflater.from(requireContext()).inflate(R.layout.dialoglogout, null)
            val bplobBuilder = AlertDialog.Builder(requireContext())
                .setView(logout)

            val bploDialog = bplobBuilder.show()



            logout.btn_ok.setOnClickListener {
                bploDialog.dismiss()
                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentLoginBPLO() , null)
                    .addToBackStack(null)
                    .commit()
            }

            logout.btn_cancel.setOnClickListener {
                bploDialog.dismiss()
            }



            }



        return view
    }
    }

package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessPermit

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getSystemService
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController

import kotlinx.android.synthetic.main.dialog_bplo.view.*
import ph.sanpablocitygov.iSanPablo.FragmentFillUp
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.links.FragmentFBCIO
import ph.sanpablocitygov.iSanPablo.links.FragmentHomeBPLO

class FragmentBusinessPermit : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_business_layout, container, false)

        val btnbplo = view.findViewById<Button>(R.id.btn_business_bplo)
        btnbplo.setOnClickListener {

            val bploView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_bplo, null)

            val bplobBuilder = AlertDialog.Builder(requireContext())
                .setView(bploView)
                .setTitle("BPLO Fill Up Forms")

            val bploDialog = bplobBuilder.show()

            bploView.btn_download_bplo.setOnClickListener {
                bploDialog.dismiss()
                var str = "Would you like to download this document?"
                val builder = AlertDialog.Builder(requireContext())
                with(builder) {
                    setMessage(str)
                    setTitle("Download BPLO Form")

                    setPositiveButton("OK", DialogInterface.OnClickListener
                    { _, _ ->  val downloadManager: DownloadManager = getSystemService(requireContext(),
                        DownloadManager::class.java) as DownloadManager
                        val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/BUSINESS%20PERMIT%20APPLICATION%20FORM.docx")
                        val request = DownloadManager.Request(uri)
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        downloadManager.enqueue(request)  })
                    setNegativeButton("CANCEL", null)
                }
                val alertDialog = builder.create()
                alertDialog.show()
            }

            bploView.btn_online_bplo.setOnClickListener {
                bploDialog.dismiss()
                    activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentFillUp()
                    ).commit()
                }

        }


        return  view

    }
}
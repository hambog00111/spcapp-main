package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity

import android.annotation.SuppressLint
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

import kotlinx.android.synthetic.main.dialog_bplo.view.*
import kotlinx.android.synthetic.main.dialog_bplo.view.btn_download_bplo
import kotlinx.android.synthetic.main.dialog_bplo.view.btn_online_bplo
import kotlinx.android.synthetic.main.dialog_bta.view.*
import kotlinx.android.synthetic.main.dialog_business_doing_business.view.*
import kotlinx.android.synthetic.main.dialog_business_mayor_permit.view.*
import kotlinx.android.synthetic.main.dialog_disclosure.view.*
import kotlinx.android.synthetic.main.dialog_investment.view.*
import kotlinx.android.synthetic.main.dialog_investment.view.btn_business_investment
import kotlinx.android.synthetic.main.dialog_investment.view.btn_invest_cancel
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOonline
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BusinessTaxAssessment.FragmentBusinessTaxAssessment
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.PrintMayorsPermit.FragmentMayorsPermit
import ph.sanpablocitygov.iSanPablo.home.isanpablo.testlng.Fragmentpastdata
import ph.sanpablocitygov.iSanPablo.links.FragmentHomeCSC

@Suppress("PLUGIN_WARNING")
class FragmentBusinessInTheCity : Fragment() {

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_isanpablo_businesscity, container, false)

        val btnbplo = view.findViewById<Button>(R.id.btn_business_bplo)
        btnbplo.setOnClickListener {

            val bploView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_bplo, null)

            val bplobBuilder = AlertDialog.Builder(requireContext())
                .setView(bploView)

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
                    FragmentBPLOonline()
                    , null)
                    .addToBackStack(null)
                    .commit()
            }
            bploView.btn_bplo_cancel.setOnClickListener {
                bploDialog.dismiss()
            }

        }


        val btnbta = view.findViewById<Button>(R.id.btn_business_bta)
        btnbta.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBusinessTaxAssessment() , null)
                .addToBackStack(null)
                .commit()

        }



        val btninvest = view.findViewById<Button>(R.id.btn_business_investment)
        btninvest.setOnClickListener {

            val investmentView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_investment, null)

            val investbBuilder = AlertDialog.Builder(requireContext())
                .setView(investmentView)

            val investDialog = investbBuilder.show()

            investmentView.btn_business_investment.setOnClickListener {
                investDialog.dismiss()
                var str = "Would you like to download this document?"
                val builder = AlertDialog.Builder(requireContext())
                with(builder) {
                    setMessage(str)
                    setTitle("Download CDP Form")

                    setPositiveButton("OK", DialogInterface.OnClickListener
                    { _, _ ->  val downloadManager: DownloadManager = getSystemService(requireContext(),
                        DownloadManager::class.java) as DownloadManager
                        val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/CDP%20Annexes%202018-2023.pdf?fbclid=IwAR2UpsGrvzmKHABFyjzDjN6A2TZ2CFLXKo2DbN-pcMaGTkHkYubf0bTOxbE")
                        val request = DownloadManager.Request(uri)
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        downloadManager.enqueue(request)  })
                    setNegativeButton("CANCEL", null)
                }
                val alertDialog = builder.create()
                alertDialog.show()
            }
//            investmentView.btn_online_bplo.setOnClickListener {
//                bploDialog.dismiss()
//                activity!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.frag_container,
//                    FragmentBPLOonline()
//                    , null)
//                    .addToBackStack(null)
//                    .commit()
//            }
            investmentView.btn_invest_cancel.setOnClickListener {
                investDialog.dismiss()
            }
        }








        val btndb = view.findViewById<Button>(R.id.btn_business_doing_business)
        btndb.setOnClickListener {
            val dbView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_business_doing_business, null)

            val dbBuilder = AlertDialog.Builder(requireContext())
                .setView(dbView)

            val dbDialog = dbBuilder.show()

            dbView.btn_doing_business_confirm.setOnClickListener {
                dbDialog.dismiss()
                val downloadManager: DownloadManager = getSystemService(requireContext(),
                    DownloadManager::class.java) as DownloadManager
                val uri = Uri.parse("https://cdn.fbsbx.com/v/t59.2708-21/64329806_2133055023490219_6338292588298108928_n.docx/citizen-charter-final-latest-edit.docx?_nc_cat=100&_nc_oc=AQmUfUeDrDFJ5oErypQlMYMn8AzefS_jOlOGmyaa73hyxcXmHrzIevPEVjdvcr7BcQNLoPUk8Dw29B3DDjYH8qpY&_nc_ht=cdn.fbsbx.com&oh=c29736b01c0e5335511e027dace205d9&oe=5D4B2F5F&dl=1&fbclid=IwAR2RUXd0-w4ylrIZ3xUw9ywm3nyTwNOadbCem6ldUSTmI7y_16ElAoa6ghI")
                val request = DownloadManager.Request(uri)
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                downloadManager.enqueue(request)  }

            dbView.btn_doing_business_cancel.setOnClickListener {
                dbDialog.dismiss()
            }
        }


        val btnmp = view.findViewById<Button>(R.id.btn_business_printmayor)
        btnmp.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                Fragmentpastdata() , null)
                .addToBackStack(null)
                .commit()
        }

        return  view

    }
}
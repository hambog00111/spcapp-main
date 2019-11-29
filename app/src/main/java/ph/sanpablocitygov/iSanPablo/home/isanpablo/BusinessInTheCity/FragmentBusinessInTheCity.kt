package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity

import android.annotation.SuppressLint
import android.support.v7.app.AlertDialog
import android.app.DownloadManager

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getSystemService

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

import kotlinx.android.synthetic.main.bc_bplo_dialogbox.view.*
import kotlinx.android.synthetic.main.bc_bplo_dialogbox.view.btn_download_bplo
import kotlinx.android.synthetic.main.bc_bplo_dialogbox.view.btn_online_bplo
import kotlinx.android.synthetic.main.bc_bta_dialog_box.view.*
import kotlinx.android.synthetic.main.bc_investment_dialogbox.view.btn_business_investment
import kotlinx.android.synthetic.main.bc_investment_dialogbox.view.btn_invest_cancel
import kotlinx.android.synthetic.main.update_fragment.view.*
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOProfile
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOstep5
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BusinessTaxPayment.FragmentBusinessTaxPayment
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.DoingBusiness.FragmentDoingBusiness

@Suppress("PLUGIN_WARNING")
class FragmentBusinessInTheCity : Fragment() {

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.home_isanpablo_businesscity_fragment, container, false)

        //animation
        val titlebc = view.findViewById<TextView>(R.id.txt_title_bc)
        titlebc.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_transition_animation)

        val lnrdb  = view.findViewById<LinearLayout>(R.id.linear_bc_db)
        lnrdb.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnri  = view.findViewById<LinearLayout>(R.id.linear_bc_inv)
        lnri.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrbplo  = view.findViewById<LinearLayout>(R.id.linear_bc_bplo)
        lnrbplo.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrbta  = view.findViewById<LinearLayout>(R.id.linear_bc_bta)
        lnrbta.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrpmp  = view.findViewById<LinearLayout>(R.id.linear_bc_mp)
        lnrpmp.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnra  = view.findViewById<LinearLayout>(R.id.linear_bc_a)
        lnra.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrbtp  = view.findViewById<LinearLayout>(R.id.linear_bc_btp)
        lnrbtp.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val linrmb  = view.findViewById<LinearLayout>(R.id.linear_bc_mb)
        linrmb.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)








        val mybusiness = view.findViewById<Button>(R.id.btn_business_mybusiness)
        mybusiness.setOnClickListener {

            val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.update_fragment, null)

            val mybusBuilder = AlertDialog.Builder(requireContext())
                .setView(mybus)
            mybusBuilder.setCancelable(false)
            val mybusDialog = mybusBuilder.show()


            mybus.txt_confirm_update.setOnClickListener {
                mybusDialog.dismiss()

            }


        }


        val btnbplo = view.findViewById<Button>(R.id.btn_business_bplo)
        btnbplo.setOnClickListener {

            val bploView = LayoutInflater.from(requireContext()).inflate(R.layout.bc_bplo_dialogbox, null)

            val bplobBuilder = AlertDialog.Builder(requireContext())
                .setView(bploView)
            bplobBuilder.setCancelable(false)
            val bploDialog = bplobBuilder.show()

            bploView.btn_download_bplo.setOnClickListener {
                bploDialog.dismiss()
                val str = "Would you like to download this document?"
                val builder = AlertDialog.Builder(requireContext())
                with(builder) {
                    setMessage(str)
                    setTitle("Download BPLO Form")

                    setPositiveButton("OK")
                    { _, _ ->  val downloadManager: DownloadManager = getSystemService(requireContext(), DownloadManager::class.java) as DownloadManager
                        val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/BUSINESS%20PERMIT%20APPLICATION%20FORM.docx")
                        val request = DownloadManager.Request(uri)
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        downloadManager.enqueue(request)  }
                    setNegativeButton("CANCEL", null)
                }
                val alertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }



            bploView.btn_online_bplo.setOnClickListener {
                bploDialog.dismiss()
//                val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.update_fragment, null)
//
//                val mybusBuilder = AlertDialog.Builder(requireContext())
//                    .setView(mybus)
//                mybusBuilder.setCancelable(false)
//                val mybusDialog = mybusBuilder.show()
//
//                mybus.txt_confirm_update.setOnClickListener {
//                    mybusDialog.dismiss()
//                }

                activity!!.supportFragmentManager
                    .beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.frag_container, FragmentBPLOProfile(), null)
                    .addToBackStack(null)
                    .commit()


            }



            bploView.btn_bplo_cancel.setOnClickListener {
                bploDialog.dismiss()
            }

        }


        val btnbta = view.findViewById<Button>(R.id.btn_business_bta)
        btnbta.setOnClickListener {
            val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.bc_bta_dialog_box, null)

            val mybusBuilder = AlertDialog.Builder(requireContext())
                .setView(mybus)
            mybusBuilder.setCancelable(false)
            val mybusDialog = mybusBuilder.show()

            mybus.btn_bta_cancel.setOnClickListener {
                mybusDialog.dismiss()
            }

//            mybus.txt_confirm_update.setOnClickListener {
//                mybusDialog.dismiss()
//            }
        }
        val btnbs = view.findViewById<Button>(R.id.btn_business_amendments)
        btnbs.setOnClickListener {
            val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.update_fragment, null)

            val mybusBuilder = AlertDialog.Builder(requireContext())
                .setView(mybus)
            mybusBuilder.setCancelable(false)
            val mybusDialog = mybusBuilder.show()

            mybus.txt_confirm_update.setOnClickListener {
                mybusDialog.dismiss()
            }
        }
        val btntp = view.findViewById<Button>(R.id.btn_business_btp)

        btntp.setOnClickListener {
            //            val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.update_fragment, null)
//
//            val mybusBuilder = AlertDialog.Builder(requireContext())
//                .setView(mybus)
//            mybusBuilder.setCancelable(false)
//            val mybusDialog = mybusBuilder.show()
//
//            mybus.txt_confirm_update.setOnClickListener {
//                mybusDialog.dismiss()

//        }

            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBusinessTaxPayment() , null)
                .addToBackStack(null)
                .commit()
        }


        val btninvest = view.findViewById<Button>(R.id.btn_business_investment)
        btninvest.setOnClickListener {

            val investmentView = LayoutInflater.from(requireContext()).inflate(R.layout.bc_investment_dialogbox, null)

            val investbBuilder = AlertDialog.Builder(requireContext())
                .setView(investmentView)
            investbBuilder.setCancelable(false)
            val investDialog = investbBuilder.show()

            investmentView.btn_business_investment.setOnClickListener {
                investDialog.dismiss()
                val str = "Would you like to download this document?"
                val builder = AlertDialog.Builder(requireContext())


                with(builder) {
                    setMessage(str)
                    setTitle("Download CDP Form")

                    setPositiveButton("OK"
                    ) { _, _ ->  val downloadManager: DownloadManager = getSystemService(requireContext(),
                        DownloadManager::class.java) as DownloadManager
                        val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/CDP%20Annexes%202018-2023.pdf?fbclid=IwAR2UpsGrvzmKHABFyjzDjN6A2TZ2CFLXKo2DbN-pcMaGTkHkYubf0bTOxbE")
                        val request = DownloadManager.Request(uri)
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        downloadManager.enqueue(request)  }
                    setNegativeButton("CANCEL", null)
                }
                val alertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
//
            investmentView.btn_invest_cancel.setOnClickListener {
                investDialog.dismiss()
            }
        }



        val btndb = view.findViewById<Button>(R.id.btn_business_doing_business)
        btndb.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentDoingBusiness() , null)
                .addToBackStack(null)
                .commit()
        }


        val btnmp = view.findViewById<Button>(R.id.btn_business_printmayor)
        btnmp.setOnClickListener {
            val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.update_fragment, null)

            val mybusBuilder = AlertDialog.Builder(requireContext())
                .setView(mybus)
            mybusBuilder.setCancelable(false)
            val mybusDialog = mybusBuilder.show()

            mybus.txt_confirm_update.setOnClickListener {
                mybusDialog.dismiss()
            }
        }

        return  view

    }
}
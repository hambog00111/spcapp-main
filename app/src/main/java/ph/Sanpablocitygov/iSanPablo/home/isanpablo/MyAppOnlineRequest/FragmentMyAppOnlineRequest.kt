package ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest

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
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.BirthCertificate.FragmentBirthCertificate
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.DeathCertificate.FragmentDeathCertificate
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.MarriageCertificate.FragmentMarriageCertificate
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.RequestTDOHA.FragmentRequestTDOHA

class FragmentMyAppOnlineRequest : Fragment(){
    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.home_isanpablo_myappolreq_fragment, container, false)

        //animation
        val titlemaor = view.findViewById<TextView>(R.id.txt_title_maor)
        titlemaor.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_transition_animation)

        val lnrtd  = view.findViewById<LinearLayout>(R.id.linear_maor_td)
        lnrtd.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrbc  = view.findViewById<LinearLayout>(R.id.linear_maor_bc)
        lnrbc.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrmc  = view.findViewById<LinearLayout>(R.id.linear_maor_mc)
        lnrmc.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrdc  = view.findViewById<LinearLayout>(R.id.linear_maor_dc)
        lnrdc.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)


        val btntd = view.findViewById<Button>(R.id.btn_myapp_td)
        btntd.setOnClickListener {

            activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentRequestTDOHA() , null)
                .addToBackStack(null)
                .commit()

        }

            val btnbc = view.findViewById<Button>(R.id.btn_myapp_bc)
             btnbc.setOnClickListener {
                 activity!!.supportFragmentManager.beginTransaction().replace(
                         R.id.frag_container,
                         FragmentBirthCertificate() , null)
                     .addToBackStack(null)
                     .commit()


//                val mybus =
//                    LayoutInflater.from(requireContext()).inflate(R.layout.update_fragment, null)
//
//                val mybusBuilder = AlertDialog.Builder(requireContext())
//                    .setView(mybus)
//                 mybusBuilder.setCancelable(false)
//                val mybusDialog = mybusBuilder.show()
//
//                mybus.txt_confirm_update.setOnClickListener {
//                    mybusDialog.dismiss()
//                }
            }

                val btndc = view.findViewById<Button>(R.id.btn_myapp_dc)
                btndc.setOnClickListener {



                    activity!!.supportFragmentManager.beginTransaction().replace(
                            R.id.frag_container,
                            FragmentDeathCertificate() , null)
                        .addToBackStack(null)
                        .commit()
                }
                    val btnmc = view.findViewById<Button>(R.id.btn_myapp_mc)
                     btnmc.setOnClickListener {


                         activity!!.supportFragmentManager.beginTransaction().replace(
                                 R.id.frag_container,
                                 FragmentMarriageCertificate() , null)
                             .addToBackStack(null)
                             .commit()
                    }

                        return view
    }
}
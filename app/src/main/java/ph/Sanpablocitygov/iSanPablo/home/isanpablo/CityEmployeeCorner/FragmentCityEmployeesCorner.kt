package ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner

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
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.FragmentCopyofPayslip.FragmentCopyofPayslip

class FragmentCityEmployeesCorner : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.home_isanpablo_emplocor_fragment, container, false)


        val titlecec = view.findViewById<TextView>(R.id.txt_title_cec)
        titlecec.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_transition_animation)

        val lnrsr  = view.findViewById<LinearLayout>(R.id.linear_cec_sr)
        lnrsr.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrcos  = view.findViewById<LinearLayout>(R.id.linear_cec_cos)
        lnrcos.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)




        val btnsr = view.findViewById<Button>(R.id.btn_cec_sr)
        btnsr.setOnClickListener {

            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentServiceRecord() , null)
                .addToBackStack(null)
                .commit()
        }


        val btncop = view.findViewById<Button>(R.id.btn_cec_cop)
        btncop.setOnClickListener {

//            val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.update_fragment, null)
//
//            val mybusBuilder = AlertDialog.Builder(requireContext())
//                .setView(mybus)
//            mybusBuilder.setCancelable(false)
//            val mybusDialog = mybusBuilder.show()
//
//            mybus.txt_confirm_update.setOnClickListener {
//                mybusDialog.dismiss()
//            }


            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                    FragmentCopyofPayslip(), null)
                .addToBackStack(null)
                .commit()
        }



        return view
    }
}
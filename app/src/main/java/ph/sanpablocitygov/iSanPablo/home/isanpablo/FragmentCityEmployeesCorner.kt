package ph.sanpablocitygov.iSanPablo.home.isanpablo

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
import kotlinx.android.synthetic.main.fragment_update.view.*
import ph.sanpablocitygov.iSanPablo.R

class FragmentCityEmployeesCorner : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_isanpablo_emplocor, container, false)


        val titlecec = view.findViewById<TextView>(R.id.txt_title_cec)
        titlecec.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_transition_animation)

        val lnrsr  = view.findViewById<LinearLayout>(R.id.linear_cec_sr)
        lnrsr.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lnrcos  = view.findViewById<LinearLayout>(R.id.linear_cec_cos)
        lnrcos.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)




        val btnsr = view.findViewById<Button>(R.id.btn_cec_sr)
        btnsr.setOnClickListener {

            val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_update, null)

            val mybusBuilder = AlertDialog.Builder(requireContext())
                .setView(mybus)

            val mybusDialog = mybusBuilder.show()

            mybus.txt_confirm_update.setOnClickListener {
                mybusDialog.dismiss()
            }
        }


        val btncop = view.findViewById<Button>(R.id.btn_cec_cop)
        btncop.setOnClickListener {

            val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_update, null)

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
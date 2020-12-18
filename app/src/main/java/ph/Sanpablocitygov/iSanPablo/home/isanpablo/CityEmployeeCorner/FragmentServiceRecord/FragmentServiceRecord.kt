package ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.FragmentServiceRecord

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.Toast
import kotlinx.android.synthetic.main.cec_copy_of_payslip_fragment.*

import kotlinx.android.synthetic.main.cec_service_record_dialogbox.view.*
import kotlinx.android.synthetic.main.cec_service_record_fragment.*
import kotlinx.android.synthetic.main.cec_service_record_fragment.emp_email
import kotlinx.android.synthetic.main.cec_service_record_fragment.emp_num

import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.FragmentCityEmployeesCorner


class FragmentServiceRecord : Fragment() {


    @SuppressLint("InflateParams")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cec_service_record_fragment, null)


        val btnback = view.findViewById<Button>(R.id.txt_cec_sr_back)
        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentCityEmployeesCorner(), null)
                .addToBackStack(null)
                .commit()
        }

        val btnnext = view.findViewById<Button>(R.id.txt_cec_sr_next)
        btnnext.setOnClickListener {




            val uempnum = emp_num!!.text.toString()


            val uempemail = emp_email.text.toString()
            val uornum = ornum.text.toString()



            if (uempnum.trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Please enter your Employee Number!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if ((uempnum).length > 4){
                Toast.makeText(activity, "Employee number too long", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }


            if ((uempnum).length < 4){
                Toast.makeText(activity, "Employee number too short", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            if (uempemail.trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Please enter your Email Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else if (!Patterns.EMAIL_ADDRESS.matcher(uempemail).matches()) {
                Toast.makeText(activity, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (uornum.trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Please enter OR number!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else{
                activity!!.intent.putExtra("empnum", uempnum)
                activity!!.intent.putExtra("emp_email", uempemail)
                activity!!.intent.putExtra("ornum", uornum)
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentServiceRecordStep2(), null)
                    .addToBackStack(null)
                    .commit()

            }





        }



        return view
    }


}

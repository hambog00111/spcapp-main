package ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.FragmentCopyofPayslip



import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi

import android.support.v4.app.Fragment
import android.util.Patterns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import kotlinx.android.synthetic.main.cec_copy_of_payslip_fragment.*

import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.FragmentCityEmployeesCorner
import java.text.SimpleDateFormat
import java.util.*


@Suppress("PLUGIN_WARNING")
class FragmentCopyofPayslip : Fragment() {

    @SuppressLint("InflateParams")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { val view = inflater.inflate(R.layout.cec_copy_of_payslip_fragment, null)

        val date = view.findViewById<EditText>(R.id.date)
        val dateto = view.findViewById<EditText>(R.id.date_to)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        c.add(Calendar.DATE,0)




        date.setOnClickListener {

    val dpd = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        // Display Selected date in TextView

        date.setText("$month/$dayOfMonth/$year")


    }, year, month, day)

            dpd.datePicker.maxDate = c.timeInMillis
    dpd.show()
}

        dateto.setOnClickListener {

            val dpd = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView

                date_to.setText("$month/$dayOfMonth/$year")
            }, year, month, day)
            dpd.datePicker.maxDate = c.timeInMillis


            dpd.show()
        }

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
            val uempdatefrom = date.text.toString()
            val uempdateto= date_to.text.toString()


            if (uempnum.trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Please enter your Employee Number!", Toast.LENGTH_SHORT).show()
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
            if (uempdatefrom.trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Please enter Pay Period From!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (uempdateto.trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Please enter Pay Period To!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                activity!!.intent.putExtra("empnum", uempnum)
                activity!!.intent.putExtra("emp_email", uempemail)
                activity!!.intent.putExtra("date_from", uempdatefrom)
                activity!!.intent.putExtra("date_to", uempdateto)

                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentCopyofPayslipStep2(), null)


                    .addToBackStack(null)

                    .commit()

            }





        }

        return view
    }




}

package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.bc_bplo_fillup_step1_fragment.*
import okhttp3.*
import org.jetbrains.anko.toast
import ph.Sanpablocitygov.iSanPablo.R


import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class FragmentBPLOstep1 : Fragment() {
    class buss_type( val id : String ,
                      val kod : String)

    class buss_handler(val kodname : String ,val kodid : String){ override fun toString(): String { return kodname } }

    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null

    @SuppressLint("InflateParams", "NewApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bc_bplo_fillup_step1_fragment, null)

         val date_of_reg = view!!.findViewById<EditText>(R.id.txt_bplo_date_dti_registration)
        val setdate = view!!.findViewById<EditText>(R.id.txt_bplo_date_application)
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
//        val formatted = current.format(formatter)
//        setdate!!.setText(formatted)
        val currentDate = DateTime()
        setdate.setText(currentDate)

        val btnlogin = view.findViewById<Button>(R.id.txt_bplo_next_page)
        btnlogin.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep2(), null)
                .addToBackStack(null)
                .commit()
        }

        date_of_reg.setOnClickListener {

            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE,0)
            val year = cal[Calendar.YEAR]
            val month = cal[Calendar.MONTH]
            val day = cal[Calendar.DAY_OF_MONTH]

            val dialog = DatePickerDialog(
                activity!!,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.datePicker.maxDate = cal.timeInMillis
            dialog.show()
        }

        mDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            var month = month
            month += 1
            Log.d(ContentValues.TAG, "onDateSet: mm/dd/yyy: $month/$day/$year")
            val date = "$month/$day/$year"
            date_of_reg!!.setText(date)
        }

        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()

        val okHttpClient = OkHttpClient()
        val formBody: RequestBody = FormBody.Builder()
            .build()

        val request = Request.Builder()
            //.method("GET", formBody)
            .url("http://192.168.3.208:8000/api/getOrganization")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                pdLoading.dismiss()
                activity!!.runOnUiThread(java.lang.Runnable {
                    println(e)
                    activity!!.toast("Unable to connect to the server please try again later")
                })
            }

            @SuppressLint("ShowToast", "SetTextI18n")
            override fun onResponse(call: Call, response: Response) {
                pdLoading.dismiss()
                val body = response.body?.string()
                val gson = Gson()
                println(body)
                val list = gson.fromJson(body, Array<buss_type>::class.java).toList()
                val brgylistmain: MutableList<buss_handler> = ArrayList()



                val i =  buss_handler("Select Type", "0" )
                brgylistmain.add(i)

                for (entry in list) {
                    val i =  buss_handler(entry.kod,entry.id )
                    brgylistmain.add(i)
                }

                activity!!.runOnUiThread(java.lang.Runnable {


                    var spinner: Spinner? = null
                    spinner = activity!!.spinner_bplo_association
                    spinner!!.onItemSelectedListener
                    val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, brgylistmain)


                    spinner.adapter = adapter


                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val data = parent.selectedItem as buss_handler
                            getbrgyforsub(data)

                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                })

            }

            //
            private fun getbrgyforsub(data: buss_handler) {
                val spinner1id = view!!.findViewById<TextView>(R.id.type_bus)
                spinner1id.text = data.kodid

            }
            })
        return view
    }

    private fun DateTime():String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("MM/dd/yyyy")
        return df.format(c)
    }

}
package ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.MarriageCertificate

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import okhttp3.*
import org.jetbrains.anko.toast
import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes.rptassementhandler
import java.io.IOException
import java.util.*

class FragmentMarriageCertificate: Fragment(){
    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null
    private var mDateSetListener2: DatePickerDialog.OnDateSetListener? = null
    @SuppressLint("InflateParams")
    override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.my_app_online_req_marriage_cert, container, false)

        val submit =view.findViewById<Button>(R.id.mc_submit)

        val  mc_namereq =view.findViewById<EditText>(R.id.mc_namereq)
        val  mc_address =view.findViewById<EditText>(R.id.mc_address)
        val  mc_number =view.findViewById<EditText>(R.id.mc_number)
        val  mc_email =view.findViewById<EditText>(R.id.mc_email)
        val  mc_numcopy =view.findViewById<EditText>(R.id.mc_numcopy)
        val  mc_husname =view.findViewById<EditText>(R.id.mc_husname)
        val  mc_wifename =view.findViewById<EditText>(R.id.mc_wifename)
        val  mc_place_marriage =view.findViewById<EditText>(R.id.mc_place_marriage)
        val  mc_date_marriage =view.findViewById<EditText>(R.id.mc_date_marriage)
        val  mc_date_marriage_late =view.findViewById<EditText>(R.id.mc_date_marriage_late)
        val  mc_purpose =view.findViewById<EditText>(R.id.mc_purpose)


        mc_date_marriage.setOnClickListener {

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


        mc_date_marriage_late.setOnClickListener {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE,0)
            val year = cal[Calendar.YEAR]
            val month = cal[Calendar.MONTH]
            val day = cal[Calendar.DAY_OF_MONTH]

            val dialog = DatePickerDialog(
                activity!!,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener2,
                year, month, day)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.datePicker.maxDate = cal.timeInMillis
            dialog.show()
        }

        mDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            var month = month
            month = month + 1
            Log.d(ContentValues.TAG, "onDateSet: mm/dd/yyy: $month/$day/$year")
            val date = "$month/$day/$year"
            mc_date_marriage!!.setText(date)
        }
        mDateSetListener2 = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            var month = month
            month = month + 1
            Log.d(ContentValues.TAG, "onDateSet: mm/dd/yyy: $month/$day/$year")
            val date = "$month/$day/$year"
            mc_date_marriage_late!!.setText(date)
        }


        submit.setOnClickListener {
            if (mc_namereq!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (mc_address!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mc_number!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Number!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (mc_email!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Email Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            else if (!Patterns.EMAIL_ADDRESS.matcher(mc_email.text.toString()).matches()) {
                mc_email.error = "Please enter a valid Email Address"
                return@setOnClickListener
            }

            if (mc_numcopy!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the number of copy!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mc_husname!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Name of Husband!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mc_wifename!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Maiden Name of Wife!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mc_place_marriage!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Place of Marriage!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mc_date_marriage!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Date of Marriage!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (mc_purpose!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Purpose!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


                else{

                Request()
            }


        }

        return view
    }

    fun Request(){

        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()
        val  mc_namereq =view!!.findViewById<EditText>(R.id.mc_namereq).text.toString()
        val  mc_address =view!!.findViewById<EditText>(R.id.mc_address).text.toString()
        val  mc_number =view!!.findViewById<EditText>(R.id.mc_number).text.toString()
        val  mc_email =view!!.findViewById<EditText>(R.id.mc_email).text.toString()
        val  mc_numcopy =view!!.findViewById<EditText>(R.id.mc_numcopy).text.toString()
        val  mc_husname =view!!.findViewById<EditText>(R.id.mc_husname).text.toString()
        val  mc_wifename =view!!.findViewById<EditText>(R.id.mc_wifename).text.toString()
        val  mc_place_marriage =view!!.findViewById<EditText>(R.id.mc_place_marriage).text.toString()
        val  mc_date_marriage =view!!.findViewById<EditText>(R.id.mc_date_marriage).text.toString()
        val  mc_date_marriage_late =view!!.findViewById<EditText>(R.id.mc_date_marriage_late).text.toString()
        val  mc_purpose =view!!.findViewById<EditText>(R.id.mc_purpose).text.toString()


        val formBody: RequestBody = FormBody.Builder()
            .add("requestor_name", mc_namereq)
            .add("requestor_address", mc_address)
            .add("requestor_contact_number", mc_number)
            .add("requestor_email", mc_email)
            .add("number_of_copies", mc_numcopy)
            .add("name_of_husband", mc_husname)
            .add("maiden_name_of_wife", mc_wifename)
            .add("place_of_marriage", mc_place_marriage)
            .add("date_of_marriage", mc_date_marriage)
            .add("date_of_registration", mc_date_marriage_late)
            .add("purpose_of_request", mc_purpose)
            .build()

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .method("POST",formBody)
            .url("http://www.sanpablocitygov.ph/api/add_request_marriagecert")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity!!.runOnUiThread(java.lang.Runnable {
                    activity!!.toast("unable to connect to the server please try again later")

                })
                pdLoading.dismiss()
                println(e)
            }

            @SuppressLint("ShowToast", "SetTextI18n")
            override fun onResponse(call: Call, response: Response) {
                pdLoading.dismiss()
                val body = response.body?.string()
                println(body)
                val gson = Gson()
                val rptrequest = gson.fromJson(body, rptassementhandler::class.java)
                when (rptrequest.message) {
                    "Success" -> {


                        activity!!.runOnUiThread(java.lang.Runnable {
                            val  mc_namereq =view!!.findViewById<EditText>(R.id.mc_namereq)
                            val  mc_address =view!!.findViewById<EditText>(R.id.mc_address)
                            val  mc_number =view!!.findViewById<EditText>(R.id.mc_number)
                            val  mc_email =view!!.findViewById<EditText>(R.id.mc_email)
                            val  mc_numcopy =view!!.findViewById<EditText>(R.id.mc_numcopy)
                            val  mc_husname =view!!.findViewById<EditText>(R.id.mc_husname)
                            val  mc_wifename =view!!.findViewById<EditText>(R.id.mc_wifename)
                            val  mc_place_marriage =view!!.findViewById<EditText>(R.id.mc_place_marriage)
                            val  mc_date_marriage =view!!.findViewById<EditText>(R.id.mc_date_marriage)
                            val  mc_date_marriage_late =view!!.findViewById<EditText>(R.id.mc_date_marriage_late)
                            val  mc_purpose =view!!.findViewById<EditText>(R.id.mc_purpose)

                            mc_namereq.text.clear()
                            mc_address.text.clear()
                            mc_number.text.clear()
                            mc_email.text.clear()
                            mc_numcopy.text.clear()
                            mc_husname.text.clear()
                            mc_wifename.text.clear()
                            mc_place_marriage.text.clear()
                            mc_date_marriage.text.clear()
                            mc_date_marriage_late.text.clear()
                            mc_purpose.text.clear()
                            val dialogBuilder = AlertDialog.Builder(requireContext())
                            dialogBuilder.setMessage("Request for marriage certificate successfully Sent. Please wait within 24 hrs and check your email for confirmation. Thank you!")
                                // if the dialog is cancelable
                                .setCancelable(false)
                                // positive button text and action
                                .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, _ ->
                                    dialog.dismiss()
                                })
                            val alert = dialogBuilder.create()
                            alert.show()

                        })
                    }
                    "Failed" -> {
                        activity!!.runOnUiThread(java.lang.Runnable {
                            activity!!.toast("Request for marriage certificate failed please try again")

                        })


                    }
                    else -> {
                        activity!!.runOnUiThread(java.lang.Runnable {

                            activity!!.toast("unable to connect to the server please try again later")

                        })
                    }
                }

            }
        })
    }

}
package ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.DeathCertificate

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
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

class FragmentDeathCertificate: Fragment(){

    @SuppressLint("InflateParams")
    override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.my_app_online_req_death_cert, container, false)

        val submit= view.findViewById<Button>(R.id.dc_submit)
        val  dc_namereq =view.findViewById<EditText>(R.id.dc_namereq)
        val  dc_address =view.findViewById<EditText>(R.id.dc_address)
        val  dc_number =view.findViewById<EditText>(R.id.dc_number)
        val  dc_email =view.findViewById<EditText>(R.id.dc_email)
        val  dc_numcopy =view.findViewById<EditText>(R.id.dc_numcopy)
        val  dc_name =view.findViewById<EditText>(R.id.dc_name)
        val  dc_sex =view.findViewById<EditText>(R.id.dc_sex)
        val  dc_place_death =view.findViewById<EditText>(R.id.dc_place_death)
        val  dc_date_death =view.findViewById<EditText>(R.id.dc_date_death)
        val  dc_date_death_late =view.findViewById<EditText>(R.id.dc_date_death_late)
        val  dc_purpose =view.findViewById<EditText>(R.id.dc_purpose)
        submit.setOnClickListener {
            if (dc_namereq!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (dc_address!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (dc_number!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Number!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (dc_email!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Email Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            else if (!Patterns.EMAIL_ADDRESS.matcher(dc_email.text.toString()).matches()) {
                dc_email.error = "Please enter a valid Email Address"
                return@setOnClickListener
            }

            if (dc_numcopy!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the number of copy!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (dc_name!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Name of the Deceased!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (dc_sex!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Gender!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (dc_place_death!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Place of Death!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (dc_date_death!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Date of Death!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (dc_purpose!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter your Purpose!", Toast.LENGTH_SHORT).show()
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
        val  dc_namereq =view!!.findViewById<EditText>(R.id.dc_namereq).text.toString()
        val  dc_address =view!!.findViewById<EditText>(R.id.dc_address).text.toString()
        val  dc_number =view!!.findViewById<EditText>(R.id.dc_number).text.toString()
        val  dc_email =view!!.findViewById<EditText>(R.id.dc_email).text.toString()
        val  dc_numcopy =view!!.findViewById<EditText>(R.id.dc_numcopy).text.toString()
        val  dc_name =view!!.findViewById<EditText>(R.id.dc_name).text.toString()
        val  dc_sex =view!!.findViewById<EditText>(R.id.dc_sex).text.toString()
        val  dc_place_death =view!!.findViewById<EditText>(R.id.dc_place_death).text.toString()
        val  dc_date_death =view!!.findViewById<EditText>(R.id.dc_date_death).text.toString()
        val  dc_date_death_late =view!!.findViewById<EditText>(R.id.dc_date_death_late).text.toString()
        val  dc_purpose =view!!.findViewById<EditText>(R.id.dc_purpose).text.toString()

        val formBody: RequestBody = FormBody.Builder()
            .add("requestor_name", dc_namereq)
            .add("requestor_address", dc_address)
            .add("requestor_contact_number", dc_number)
            .add("requestor_email", dc_email)
            .add("number_of_copies", dc_numcopy)
            .add("name_of_deceased", dc_name)
            .add("sex", dc_sex)
            .add("place_of_death", dc_place_death)
            .add("date_of_death", dc_date_death)
            .add("date_of_registration", dc_date_death_late)
            .add("purpose_of_request", dc_purpose)
            .build()

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .method("POST",formBody)
            .url("http://192.168.3.172:8080/api/add_request_deathcert")
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
                        val  dc_namereq =view!!.findViewById<EditText>(R.id.dc_namereq)
                        val  dc_address =view!!.findViewById<EditText>(R.id.dc_address)
                        val  dc_number =view!!.findViewById<EditText>(R.id.dc_number)
                        val  dc_email =view!!.findViewById<EditText>(R.id.dc_email)
                        val  dc_numcopy =view!!.findViewById<EditText>(R.id.dc_numcopy)
                        val  dc_name =view!!.findViewById<EditText>(R.id.dc_name)
                        val  dc_sex =view!!.findViewById<EditText>(R.id.dc_sex)
                        val  dc_place_death =view!!.findViewById<EditText>(R.id.dc_place_death)
                        val  dc_date_death =view!!.findViewById<EditText>(R.id.dc_date_death)
                        val  dc_date_death_late =view!!.findViewById<EditText>(R.id.dc_date_death_late)
                        val  dc_purpose =view!!.findViewById<EditText>(R.id.dc_purpose)

                        dc_namereq.text.clear()
                        dc_address.text.clear()
                        dc_number.text.clear()
                        dc_email.text.clear()
                        dc_numcopy.text.clear()
                        dc_name.text.clear()
                        dc_sex.text.clear()
                        dc_place_death.text.clear()
                        dc_date_death.text.clear()
                        dc_date_death_late.text.clear()
                        dc_purpose.text.clear()
                        activity!!.runOnUiThread(java.lang.Runnable {
                            val dialogBuilder = AlertDialog.Builder(requireContext())
                            dialogBuilder.setMessage("Request for death certificate successfully Sent.")
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
                            activity!!.toast("Request for death certificate failed please try again")

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
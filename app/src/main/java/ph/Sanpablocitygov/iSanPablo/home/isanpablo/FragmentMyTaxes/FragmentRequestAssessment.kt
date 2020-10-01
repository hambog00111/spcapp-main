package ph.Sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
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
import java.io.IOException
import kotlin.contracts.contract

class FragmentRequestAssessment : Fragment(){

    @SuppressLint("InflateParams")
    override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.mt_rpt_request_assessment, container, false)

        val ownname = view.findViewById<EditText>(R.id.rpt_ownname)
        val email = view.findViewById<EditText>(R.id.rpt_email)
        val property_add = view.findViewById<EditText>(R.id.rpt_property_add)
        val contact = view.findViewById<EditText>(R.id.rpt_contact)
        val pin = view.findViewById<EditText>(R.id.rpt_pin)
        val submit = view.findViewById<Button>(R.id.rpt_submit)

     submit.setOnClickListener {
         if (ownname!!.text.toString().trim { it <= ' ' }.isEmpty()) {
             Toast.makeText(activity!!, "Please enter Owner's Name!", Toast.LENGTH_SHORT).show()
             return@setOnClickListener
         }

         if (email!!.text.toString().trim { it <= ' ' }.isEmpty()) {
             Toast.makeText(activity!!, "Please enter Email Address!", Toast.LENGTH_SHORT).show()
             return@setOnClickListener
         }

         if (property_add!!.text.toString().trim { it <= ' ' }.isEmpty()) {
             Toast.makeText(activity!!, "Please enter Property Address!", Toast.LENGTH_SHORT).show()
             return@setOnClickListener
         }


         else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
             email.error = "Please enter a valid email address"
             return@setOnClickListener
         }

         if (contact!!.text.toString().trim { it <= ' ' }.isEmpty()) {
             Toast.makeText(activity!!, "Please enter your Contact Number!", Toast.LENGTH_SHORT).show()
             return@setOnClickListener
         }
    


         if (pin!!.text.toString().trim { it <= ' ' }.isEmpty()) {
             Toast.makeText(activity!!, "Please enter your Pin", Toast.LENGTH_SHORT).show()
             return@setOnClickListener
         }
//         if ((pin).length() > 18){
//             Toast.makeText(activity!!, "Pin too long", Toast.LENGTH_SHORT).show()
//             return@setOnClickListener
//
//         }
//
//
//         if ((contact).length() < 18){
//             Toast.makeText(activity!!, "Pin is atleast 18 digits include the dash", Toast.LENGTH_SHORT).show()
//             return@setOnClickListener
//
//         }

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
        val ownname = view!!.findViewById<EditText>(R.id.rpt_ownname).text.toString()
        val email = view!!.findViewById<EditText>(R.id.rpt_email).text.toString()
        val property_add = view!!.findViewById<EditText>(R.id.rpt_property_add).text.toString()
        val contact = view!!.findViewById<EditText>(R.id.rpt_contact).text.toString()
        val pin = view!!.findViewById<EditText>(R.id.rpt_pin).text.toString()

        val formBody: RequestBody = FormBody.Builder()
            .add("RPname", ownname)
            .add("RPemail", email)
            .add("RPaddress", property_add)
            .add("RPnumber", contact)
            .add("RPpid", pin)

            .build()

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .method("POST",formBody)
//        www.sanpablocitygov.ph
            .url("http://www.sanpablocitygov.ph/api/add_request_property")
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
                            val ownname = view!!.findViewById<EditText>(R.id.rpt_ownname)
                            val email = view!!.findViewById<EditText>(R.id.rpt_email)
                            val property_add = view!!.findViewById<EditText>(R.id.rpt_property_add)
                            val contact = view!!.findViewById<EditText>(R.id.rpt_contact)
                            val pin = view!!.findViewById<EditText>(R.id.rpt_pin)
                            ownname.text.clear()
                            email.text.clear()
                            property_add.text.clear()
                            contact.text.clear()
                            pin.text.clear()
                            val dialogBuilder = AlertDialog.Builder(requireContext())
                            dialogBuilder.setMessage("Request for assessment Successfully sent . Please wait within 24 hrs and check your email for confirmation. Thank you!")
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
                            activity!!.toast("Request for assessment failed please try again")

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
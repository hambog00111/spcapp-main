package ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.RequestTDOHA

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
import kotlinx.android.synthetic.main.my_app_online_req_death_cert.*
import okhttp3.*
import org.jetbrains.anko.toast
import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes.rptassementhandler
import java.io.IOException

class FragmentRequestTDOHA : Fragment(){

    @SuppressLint("InflateParams")
    override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.my_app_online_req_td_oha_cert, container, false)
          val submit=view.findViewById<Button>(R.id.td_submit)

        val  td_namereq =view.findViewById<EditText>(R.id.td_namereq)
        val  td_address =view.findViewById<EditText>(R.id.td_address)
        val  td_number =view.findViewById<EditText>(R.id.td_number)
        val  td_email =view.findViewById<EditText>(R.id.td_email)
        val  td_numcopy =view.findViewById<EditText>(R.id.td_numcopy)
        val  td_name_owner =view.findViewById<EditText>(R.id.td_name_owner)
        val  td_location =view.findViewById<EditText>(R.id.td_location)
        val  td_pin =view.findViewById<EditText>(R.id.td_pin)
        val  td_arp =view.findViewById<EditText>(R.id.td_arp)



        submit.setOnClickListener {
            if (td_namereq!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (td_address!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (td_number!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Number!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (td_email!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Email Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            else if (!Patterns.EMAIL_ADDRESS.matcher(td_email.text.toString()).matches()) {
                td_email.error = "Please enter a valid Email Address"
                return@setOnClickListener
            }

            if (td_numcopy!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the number of copy!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (td_name_owner!!.text.toString().trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(activity!!, "Please enter the Name of Owner!", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
            if (td_location!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Location of the land!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (td_pin!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Pin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (td_arp!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the ARP!", Toast.LENGTH_SHORT).show()
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
        val  td_namereq =view!!.findViewById<EditText>(R.id.td_namereq).text.toString()
        val  td_address =view!!.findViewById<EditText>(R.id.td_address).text.toString()
        val  td_number =view!!.findViewById<EditText>(R.id.td_number).text.toString()
        val  td_email =view!!.findViewById<EditText>(R.id.td_email).text.toString()
        val  td_numcopy =view!!.findViewById<EditText>(R.id.td_numcopy).text.toString()
        val  td_name_owner =view!!.findViewById<EditText>(R.id.td_name_owner).text.toString()
        val  td_location =view!!.findViewById<EditText>(R.id.td_location).text.toString()
        val  td_pin =view!!.findViewById<EditText>(R.id.td_pin).text.toString()
        val  td_arp =view!!.findViewById<EditText>(R.id.td_arp).text.toString()

        val formBody: RequestBody = FormBody.Builder()
            .add("requestor_name", td_namereq)
            .add("requestor_address", td_address)
            .add("requestor_contact_number", td_number)
            .add("requestor_email", td_email)
            .add("number_of_copies", td_numcopy)
            .add("name_of_land_owner", td_name_owner)
            .add("location_of_the_land", td_location)
            .add("pin_number", td_pin)
            .add("arp", td_arp)

            .build()

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .method("POST",formBody)
            .url("http://www.sanpablocitygov.ph/api/add_request_td_oha")
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
                            val  td_namereq =view!!.findViewById<EditText>(R.id.td_namereq)
                            val  td_address =view!!.findViewById<EditText>(R.id.td_address)
                            val  td_number =view!!.findViewById<EditText>(R.id.td_number)
                            val  td_email =view!!.findViewById<EditText>(R.id.td_email)
                            val  td_numcopy =view!!.findViewById<EditText>(R.id.td_numcopy)
                            val  td_name_owner =view!!.findViewById<EditText>(R.id.td_name_owner)
                            val  td_location =view!!.findViewById<EditText>(R.id.td_location)
                            val  td_pin =view!!.findViewById<EditText>(R.id.td_pin)
                            val  td_arp =view!!.findViewById<EditText>(R.id.td_arp)


                            td_namereq.text.clear()
                            td_address.text.clear()
                            td_number.text.clear()
                            td_email.text.clear()
                            td_numcopy.text.clear()
                            td_name_owner.text.clear()
                            td_location.text.clear()
                            td_pin.text.clear()
                            td_arp.text.clear()
                            val dialogBuilder = AlertDialog.Builder(requireContext())
                            dialogBuilder.setMessage("Request for death certificate successfully Sent. Please wait within 24 hrs and check your email for confirmation. Thank you!")
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
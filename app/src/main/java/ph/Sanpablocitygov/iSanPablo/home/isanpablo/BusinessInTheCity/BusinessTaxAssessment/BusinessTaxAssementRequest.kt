package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BusinessTaxAssessment

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
import kotlinx.android.synthetic.main.bta_request_for_copy.*
import okhttp3.*
import org.jetbrains.anko.toast
import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes.rptassementhandler
import java.io.IOException

class BusinessTaxAssementRequest : Fragment() {

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.bta_request_for_copy, container, false)
val bta_submit=view.findViewById<Button>(R.id.bta_submit)

        val  bcnamereq =view.findViewById<EditText>(R.id.bc_namereq)
        val  bc_address =view.findViewById<EditText>(R.id.bc_address)
        val  bc_number =view.findViewById<EditText>(R.id.bc_number)
        val  bc_email =view.findViewById<EditText>(R.id.bc_email)
        val  bc_purpose =view.findViewById<EditText>(R.id.bc_purpose)
        val  bta_bin =view.findViewById<EditText>(R.id.bta_bin)
        val  bta_bus_name =view.findViewById<EditText>(R.id.bta_bus_name)
        val  bta_own_name =view.findViewById<EditText>(R.id.bta_own_name)
        val  bta_bus_address =view.findViewById<EditText>(R.id.bta_bus_address)
        bta_submit.setOnClickListener {


            if (bcnamereq!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (bc_address!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (bc_number!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Requestor's Number!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (bc_email!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Email Address!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            else if (!Patterns.EMAIL_ADDRESS.matcher(bc_email.text.toString()).matches()) {
                bc_email.error = "Please enter a valid Email Address"
                return@setOnClickListener
            }

            if (bc_purpose!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Purpose!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (bta_bin!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the BIN No!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if ((bta_bin).length() > 4){
                Toast.makeText(activity!!, "BIN number too long", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }


            if ((bta_bin).length() < 4){
                Toast.makeText(activity!!, "BIN number too short", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if (bta_bus_name!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Business Name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (bta_own_name!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Owner's Name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (bta_bus_address!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Business Address!", Toast.LENGTH_SHORT).show()
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
        val  btanamereq =view!!.findViewById<EditText>(R.id.bc_namereq).text.toString()
        val  bc_address =view!!.findViewById<EditText>(R.id.bc_address).text.toString()
        val  bc_number =view!!.findViewById<EditText>(R.id.bc_number).text.toString()
        val  bc_email =view!!.findViewById<EditText>(R.id.bc_email).text.toString()
        val  bc_purpose =view!!.findViewById<EditText>(R.id.bc_purpose).text.toString()
        val  bc_bin =view!!.findViewById<EditText>(R.id.bta_bin).text.toString()
        val  bc_bus_name =view!!.findViewById<EditText>(R.id.bta_bus_name).text.toString()
        val  bc_own_name =view!!.findViewById<EditText>(R.id.bta_own_name).text.toString()
        val  bc_bus_address =view!!.findViewById<EditText>(R.id.bta_bus_address).text.toString()
        val formBody: RequestBody = FormBody.Builder()
            .add("requestor_name", btanamereq)
            .add("requestor_address", bc_address)
            .add("requestor_contact_number", bc_number)
            .add("requestor_email", bc_email)
            .add("purpose_of_request",bc_purpose)
            .add("bin",bc_bin)
            .add("business_name",bc_bus_name)
            .add("owner_name",bc_own_name)
            .add("business_address",bc_bus_address)



            .build()

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .method("POST",formBody)
            .url("http://www.sanpablocitygov.ph/api/add_request_tax")
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
                            val  btanamereq =view!!.findViewById<EditText>(R.id.bc_namereq)
                            val  bc_address =view!!.findViewById<EditText>(R.id.bc_address)
                            val  bc_number =view!!.findViewById<EditText>(R.id.bc_number)
                            val  bc_email =view!!.findViewById<EditText>(R.id.bc_email)
                            val  bc_purpose =view!!.findViewById<EditText>(R.id.bc_purpose)
                            val  bc_bin =view!!.findViewById<EditText>(R.id.bta_bin)
                            val  bc_bus_name =view!!.findViewById<EditText>(R.id.bta_bus_name)
                            val  bc_own_name =view!!.findViewById<EditText>(R.id.bta_own_name)
                            val  bc_bus_address =view!!.findViewById<EditText>(R.id.bta_bus_address)


                            btanamereq.text.clear()
                            bc_address.text.clear()
                            bc_number.text.clear()
                            bc_email.text.clear()
                            bc_purpose.text.clear()
                            bc_bin.text.clear()
                            bc_own_name.text.clear()
                            bc_bus_name.text.clear()
                            bc_bus_address.text.clear()
                            val dialogBuilder = AlertDialog.Builder(requireContext())
                            dialogBuilder.setMessage("Request for Business Tax Assessment successfully Sent.Please wait within 24 hrs and check your email for confirmation. Thank you!")
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
                            activity!!.toast("Request for Business Tax Assessment failed please try again")

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
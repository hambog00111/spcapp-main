package ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.BirthCertificate

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

class FragmentBirthCertificate : Fragment(){

    @SuppressLint("InflateParams")
    override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view: View = inflater.inflate(R.layout.my_app_online_req_birth_cert, container, false)
        val submit = view.findViewById<Button>(R.id.bc_submit)

        val  bcnamereq =view.findViewById<EditText>(R.id.bc_namereq)
        val  bc_address =view.findViewById<EditText>(R.id.bc_address)
        val  bc_number =view.findViewById<EditText>(R.id.bc_number)
        val  bc_email =view.findViewById<EditText>(R.id.bc_email)
        val  bc_numcopy =view.findViewById<EditText>(R.id.bc_numcopy)
        val  bc_name =view.findViewById<EditText>(R.id.bc_name)
        val  bc_sex =view.findViewById<EditText>(R.id.bc_sex)
        val  bc_place_birth =view.findViewById<EditText>(R.id.bc_place_birth)
        val  bc_date_birth =view.findViewById<EditText>(R.id.bc_date_birth)
        val  bc_father_name =view.findViewById<EditText>(R.id.bc_father_name)
        val  bc_mother_name =view.findViewById<EditText>(R.id.bc_mother_name)
        val  bc_date_reg =view.findViewById<EditText>(R.id.bc_date_reg)
        val  bc_purpose =view.findViewById<EditText>(R.id.bc_purpose)
        val  bc_relation =view.findViewById<EditText>(R.id.bc_relation)

        submit.setOnClickListener {

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

            if (bc_numcopy!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the number copy!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (bc_name!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (bc_sex!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Gender!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (bc_place_birth!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Place of Birth!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (bc_date_birth!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter Date of Birth!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (bc_purpose!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter the Purpose!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (bc_relation!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity!!, "Please enter your Relation to the Owner !", Toast.LENGTH_SHORT).show()
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
        val  bcnamereq =view!!.findViewById<EditText>(R.id.bc_namereq).text.toString()
        val  bc_address =view!!.findViewById<EditText>(R.id.bc_address).text.toString()
        val  bc_number =view!!.findViewById<EditText>(R.id.bc_number).text.toString()
        val  bc_email =view!!.findViewById<EditText>(R.id.bc_email).text.toString()
        val  bc_numcopy =view!!.findViewById<EditText>(R.id.bc_numcopy).text.toString()
        val  bc_name =view!!.findViewById<EditText>(R.id.bc_name).text.toString()
        val  bc_sex =view!!.findViewById<EditText>(R.id.bc_sex).text.toString()
        val  bc_place_birth =view!!.findViewById<EditText>(R.id.bc_place_birth).text.toString()
        val  bc_date_birth =view!!.findViewById<EditText>(R.id.bc_date_birth).text.toString()
        val  bc_father_name =view!!.findViewById<EditText>(R.id.bc_father_name).text.toString()
        val  bc_mother_name =view!!.findViewById<EditText>(R.id.bc_mother_name).text.toString()
        val  bc_date_reg =view!!.findViewById<EditText>(R.id.bc_date_reg).text.toString()
        val  bc_purpose =view!!.findViewById<EditText>(R.id.bc_purpose).text.toString()
        val  bc_relation =view!!.findViewById<EditText>(R.id.bc_relation).text.toString()

        val formBody: RequestBody = FormBody.Builder()
            .add("requestor_name", bcnamereq)
            .add("requestor_address", bc_address)
            .add("requestor_contact_number", bc_number)
            .add("requestor_email", bc_email)
            .add("number_of_copies", bc_numcopy)
            .add("name", bc_name)
            .add("sex", bc_sex)
            .add("place_of_birth", bc_place_birth)
            .add("date_of_birth", bc_date_birth)
            .add("father's_name", bc_father_name)
            .add("mother's_name", bc_mother_name)
            .add("date_of_register",bc_date_reg )
            .add("purpose_of_request",bc_purpose )
            .add("relationship_to_owner", bc_relation)

            .build()

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .method("POST",formBody)
            .url("http://192.168.3.172:8080/api/add_request_birthcert")
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
                        val  bcnamereq =view!!.findViewById<EditText>(R.id.bc_namereq)
                        val  bc_address =view!!.findViewById<EditText>(R.id.bc_address)
                        val  bc_number =view!!.findViewById<EditText>(R.id.bc_number)
                        val  bc_email =view!!.findViewById<EditText>(R.id.bc_email)
                        val  bc_numcopy =view!!.findViewById<EditText>(R.id.bc_numcopy)
                        val  bc_name =view!!.findViewById<EditText>(R.id.bc_name)
                        val  bc_sex =view!!.findViewById<EditText>(R.id.bc_sex)
                        val  bc_place_birth =view!!.findViewById<EditText>(R.id.bc_place_birth)
                        val  bc_date_birth =view!!.findViewById<EditText>(R.id.bc_date_birth)
                        val  bc_father_name =view!!.findViewById<EditText>(R.id.bc_father_name)
                        val  bc_mother_name =view!!.findViewById<EditText>(R.id.bc_mother_name)
                        val  bc_date_reg =view!!.findViewById<EditText>(R.id.bc_date_reg)
                        val  bc_purpose =view!!.findViewById<EditText>(R.id.bc_purpose)
                        val  bc_relation =view!!.findViewById<EditText>(R.id.bc_relation)


                        bcnamereq.text.clear()
                        bc_address.text.clear()
                        bc_number.text.clear()
                        bc_email.text.clear()
                        bc_numcopy.text.clear()
                        bc_name.text.clear()
                        bc_sex.text.clear()
                        bc_place_birth.text.clear()
                        bc_date_birth.text.clear()
                        bc_father_name.text.clear()
                        bc_mother_name.text.clear()
                        bc_date_reg.text.clear()
                        bc_purpose.text.clear()
                        bc_relation.text.clear()


                        activity!!.runOnUiThread(java.lang.Runnable {

                            val dialogBuilder = AlertDialog.Builder(requireContext())
                            dialogBuilder.setMessage("Request for birth certificate successfully Sent.")
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
                            activity!!.toast("Request for birth certificate failed please try again")

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
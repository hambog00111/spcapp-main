@file:Suppress("DEPRECATION")

package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Loginbplo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.ammendments_login_dailog.view.*
import okhttp3.*
import org.jetbrains.anko.toast

import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.Amendments.Ammendments_handler
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOProfile
import java.io.IOException


class FragmentLoginBPLO: Fragment() {

    private  var contact_number: EditText? = null

    private  var password: EditText? = null

    private  var progressBar: ProgressBar? = null

    private  var btn_bplo_login: Button? = null

    private  var token: TextView? = null

    private  var txt_assessment_register: TextView? = null

    private  var txt_assess_ip: TextInputLayout? = null




    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bc_bplo_login_fragment, null)

        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        contact_number = view.findViewById<View>(R.id.txt_bplo_username_login)as EditText
        password = view.findViewById<View>(R.id.txt_bplo_password_login)as EditText
        btn_bplo_login = view.findViewById<View>(R.id.btn_bplo_login) as Button
                  btn_bplo_login!!.setOnClickListener {

                      if (contact_number!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                          Toast.makeText(activity, "Please put Business Identification Number(BIN)!", Toast.LENGTH_SHORT).show()
                          return@setOnClickListener
                      }

                      if (password!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                          Toast.makeText(activity, "Please put Password!", Toast.LENGTH_SHORT).show()

                          return@setOnClickListener
                      }else{

                          login()

                      }
                  }


        return view
    }

    fun login() {
        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()
        contact_number = view!!.findViewById<View>(R.id.txt_bplo_username_login)as EditText
        password = view!!.findViewById<View>(R.id.txt_bplo_password_login)as EditText
//    val link = findViewById<EditText>(R.id.link)
        val formBody: RequestBody = FormBody.Builder()
            .add("contact_number", contact_number!!.text.toString())
            .add("password", password!!.text.toString())
            .build()
        val okHttpClient = OkHttpClient()

        val request = Request.Builder()
            .url("http://192.168.3.208:8080/api/auth/login")
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e)
                pdLoading.dismiss()
                activity!!.runOnUiThread(java.lang.Runnable {
                    activity!!.toast("Server Error Please Contact Admin")
                })
            }
            override fun onResponse(call: Call, response: Response) {

                pdLoading.dismiss()
                val body = response.body?.string()

                val gson = Gson()

                println(body);

                val res = gson.fromJson(body, Ammendments_handler::class.java)

                println(res)

                if(res.message =="Login Success"){
                    activity!!.runOnUiThread(java.lang.Runnable {
////                        val ulink = findViewById<EditText>(R.id.link).text.toString()
//                        val myIntent = Intent(baseContext, home::class.java)
//                        //   myIntent.putExtra("deputa",ulink)
//                        startActivity(myIntent)

                        val token = view!!.findViewById<TextView>(R.id.token)
                        token.text = res.access_token
                        val mDialogView = LayoutInflater.from(context).inflate(R.layout.ammendments_login_dailog, null)
                        //AlertDialogBuilder

                        val mBuilder = AlertDialog.Builder(context)
                            .setView(mDialogView)
                        mBuilder.setCancelable(false)

                        //show dialog
                        val  mAlertDialog = mBuilder.show()
                        //login button click of custom layout
                        mDialogView.btn_success.setOnClickListener {
                            //dismiss dialog
                            mAlertDialog.dismiss()
                            getinfo()

                            //   toast(res.access_token)
                        }
                    })
                }else{
                    activity!!.runOnUiThread(java.lang.Runnable {
                        pdLoading.dismiss()
                        activity!!.toast(res.message)
                    })

                }


            }
        })

    }


    fun getinfo(){
        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()
        val utoken =view!!.findViewById<TextView>(R.id.token).text.toString()
        val formBody: RequestBody = FormBody.Builder()
            .build()
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .method("POST",formBody)
            .header("Authorization","Bearer $utoken")
            .url("http://192.168.3.208:8080/api/auth/me")

            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: okhttp3.Call, e: IOException) {
                activity!!.runOnUiThread(java.lang.Runnable {
                    pdLoading.dismiss()
                    activity!!.toast("unable to connect to the server please try again later") })
                println(e)
            }

            @SuppressLint("ShowToast", "SetTextI18n")
            override fun onResponse(call: okhttp3.Call, response: Response) {
                pdLoading.dismiss()
                val body = response.body?.string()
                println(body)
                val gson = Gson()
                val user_info = gson.fromJson(body, Ammendments_handler::class.java)
                activity!!.runOnUiThread(java.lang.Runnable {
                    val token =view!!.findViewById<TextView>(R.id.token).text.toString()
                    val myIntent = activity!!.intent
                    myIntent.putExtra("user_id",user_info.id)
                    myIntent.putExtra("utoken",token)

                    activity!!.supportFragmentManager.beginTransaction().replace(
                            R.id.frag_container,
                            FragmentBPLOProfile(), null
                        )
                        .addToBackStack(null)
                        .commit()
//                toast(user_info.id)
//               toast(token)
                })
            }
        })



    }
}



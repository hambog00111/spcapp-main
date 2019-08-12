package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BusinessTaxAssessment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.fragment_bc_assessment_registration.*
import ph.sanpablocitygov.iSanPablo.R

class BusinessTaxAssessmentRegActivity : AppCompatActivity() {



    private var txt_assess_last: EditText? = null
    private var txt_assess_first: EditText? = null
    private var txt_assess_middle: EditText? = null
    private var txt_assess_mob_num: EditText? = null

    private var txt_assess_tel_num: EditText? = null

    private var txt_assess_email: EditText? = null
    private var txt_assess_username: EditText? = null
    private var txt_assess_password: EditText? = null
    private var txt_assess_ip: TextInputLayout? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_bc_assessment_registration)
        txt_assess_last = findViewById(R.id.txt_assess_last) as EditText
        txt_assess_first = findViewById(R.id.txt_assess_first) as EditText
        txt_assess_middle = findViewById(R.id.txt_assess_middle) as EditText
        txt_assess_mob_num = findViewById(R.id.txt_assess_mob_num) as EditText
        txt_assess_tel_num = findViewById(R.id.txt_assess_tel_num) as EditText
        txt_assess_email = findViewById(R.id.txt_assess_email) as EditText

        txt_assess_username = findViewById(R.id.txt_assess_username) as EditText

        txt_assess_password = findViewById(R.id.txt_assess_password) as EditText
        txt_assess_ip = findViewById(R.id.txt_assess_ip) as TextInputLayout
        //adding a click listener to button
        btn_asses_register.setOnClickListener {
            addArtist() }


//        buttonViewArtist.setOnClickListener {
//            val intent = Intent(applicationContext, SecondActivity::class.java)
//            startActivity(intent)
//        }
    }






    //adding a new record to database
    private fun addArtist() {
        //getting the record values
        val lname = txt_assess_last?.text.toString()
        val fname = txt_assess_first?.text.toString()
        val mname = txt_assess_middle?.text.toString()
        val mnum = txt_assess_mob_num?.text.toString()
        val tnum = txt_assess_tel_num?.text.toString()
        val ed = txt_assess_email?.text.toString()
        val uname = txt_assess_username?.text.toString()
        val pass = txt_assess_password?.text.toString()
        val UPDATE_URL =("http://"+txt_assess_ip?.getEditText()?.getText()+"/api/android_api/").toString()

        if (TextUtils.isEmpty(lname)) {
            txt_assess_last?.error = "Please enter your last name"
            txt_assess_last?.requestFocus()
            return
        }

        if (TextUtils.isEmpty(fname)) {
            txt_assess_first?.error = "Please enter your first name"
            txt_assess_first?.requestFocus()
            return
        }
        if (TextUtils.isEmpty(mname)) {
            txt_assess_middle?.error = "Please enter your last name"
            txt_assess_middle?.requestFocus()
            return
        }


        if (TextUtils.isEmpty(tnum)) {
            txt_assess_tel_num?.error = "Please enter your last name"
            txt_assess_tel_num?.requestFocus()
            return
        }


        if ((mnum).length > 11)
        {
            txt_assess_mob_num!!.error = ("mobile number too long")
            txt_assess_mob_num?.requestFocus()
            return

        }
        if(TextUtils.isEmpty(mnum)){
            txt_assess_mob_num?.error = "Please enter your mobile number "
            txt_assess_mob_num?.requestFocus()
            return

        }
        if (TextUtils.isEmpty(ed)) {
            txt_assess_email?.error = "Please enter your email"
            txt_assess_email?.requestFocus()
            return
        }
        if (TextUtils.isEmpty(uname)) {
            txt_assess_username?.error = "Please enter your username"
            txt_assess_username?.requestFocus()
            return
        }
        if ((uname).length > 7)
        {
            txt_assess_mob_num!!.error = ("user name too long")
            txt_assess_mob_num?.requestFocus()
            return

        }

        if (TextUtils.isEmpty(pass)) {
            txt_assess_password?.error = "Please enter your password"
            txt_assess_password?.requestFocus()
            return
        }



//        val UPDATE_URL = "http://192.168.100.207:8080/android_api/"
        var volleyRequest: RequestQueue? = null

        volleyRequest = Volley.newRequestQueue(this)


        val req = object : StringRequest(Method.POST,
            UPDATE_URL,
            Response.Listener { response ->
                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                val user = Intent(this, BusinessTaxAssessmentRegActivity::class.java)
                startActivity(user)

            }, Response.ErrorListener { e ->

                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("buss_client[last_name]", lname)
                params.put("buss_client[first_name]", fname)
                params.put("buss_client[middle_name]", mname)
                params.put("buss_client[mobile_no]", mnum)
                params.put("buss_client[tel_no]", tnum)
                params.put("buss_client[email]", ed)
                params.put("user[username]", uname)
                params.put("user[password]", pass)



                return params
            }
        }
        req.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        volleyRequest!!.add(req)

    }




}
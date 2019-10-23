package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Loginbplo


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import ph.sanpablocitygov.iSanPablo.R



class FragmentBPLOAccountReg: Fragment(), View.OnClickListener {

    private var txt_assess_last: EditText? = null
    private var txt_assess_first: EditText? = null
    private var txt_assess_middle: EditText? = null
    private var txt_assess_mob_num: EditText? = null

    private var txt_assess_tel_num: EditText? = null

    private var txt_assess_email: EditText? = null
    private var txt_assess_username: EditText? = null
    private var txt_assess_password: EditText? = null
    private var txt_confirm_password: EditText? = null
    private var btn_asses_register: Button? = null
    private var btn_clearall: Button? = null
    private var txt_assess_ip: TextInputLayout? = null
    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.bc_bplo_registration_fragmet, container, false)

        txt_assess_last = view.findViewById<View>(R.id.txt_assess_last) as EditText
        txt_assess_first = view.findViewById<View>(R.id.txt_assess_first) as EditText
        txt_assess_middle =view.findViewById<View>(R.id.txt_assess_middle) as EditText
        txt_assess_mob_num = view.findViewById<View>(R.id.txt_assess_mob_num) as EditText
        txt_assess_tel_num = view.findViewById<View>(R.id.txt_assess_tel_num) as EditText
        txt_assess_email = view.findViewById<View>(R.id.txt_assess_email) as EditText

        txt_assess_username = view.findViewById<View>(R.id.txt_assess_username) as EditText

        txt_assess_password = view.findViewById<View>(R.id.txt_assess_password) as EditText
        txt_confirm_password = view.findViewById<View>(R.id.txt_confirm_password) as EditText
        btn_asses_register = view.findViewById<View>(R.id.btn_asses_register) as Button
        btn_clearall = view.findViewById<View>(R.id.btn_clearall) as Button
       txt_assess_ip = view.findViewById<View>(R.id.txt_assess_ip) as TextInputLayout
        //adding a click listener to button
        btn_clearall!!.setOnClickListener{
        txt_assess_last!!.text.clear()
        txt_assess_first!!.text.clear()
        txt_assess_middle!!.text.clear()
        txt_assess_mob_num!!.text.clear()
        txt_assess_tel_num!!.text.clear()
        txt_assess_email!!.text.clear()
        txt_assess_username!!.text.clear()
        txt_assess_password!!.text.clear()
       txt_confirm_password!!.text.clear()
        txt_assess_last!!.text.clear()
        }

        btn_asses_register!!.setOnClickListener(this)


//
//        btn_asses_register.setOnClickListener {
//            addArtist() }




        return view

    }


    override fun  onClick(view: View)  {
        //getting the record values
        val lname = txt_assess_last!!.text.toString()
        val fname = txt_assess_first!!.text.toString()
        val mname = txt_assess_middle!!.text.toString()
        val mnum = txt_assess_mob_num!!.text.toString()
        val tnum = txt_assess_tel_num!!.text.toString()
        val ed = txt_assess_email!!.text.toString()
        val uname = txt_assess_username!!.text.toString()
        val conpass = txt_confirm_password!!.text.toString()
        val pass = txt_assess_password!!.text.toString()
        val UPDATE_URL =("http://"+txt_assess_ip?.getEditText()?.getText()+"/api/spc_api/").toString()

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
        if ((mnum).length < 11)
        {
            txt_assess_mob_num!!.error = ("mobile number too short")
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
        if(TextUtils.isEmpty(conpass)){
            txt_confirm_password?.error = "Please enter your mobile confirmation password "
            txt_confirm_password?.requestFocus()
            return

        }
        if (pass != conpass)
        {
            txt_confirm_password?.error = "password do not match "
            txt_confirm_password?.requestFocus()
            return

        }


   // val UPDATE_URL = "http://192.168.100.207:8080/api/spc_api/"
        var volleyRequest: RequestQueue? = null

        volleyRequest = Volley.newRequestQueue(context)



        val req = object : StringRequest(Method.POST,
            UPDATE_URL,
            Listener {
                Toast.makeText(context, "register success", Toast.LENGTH_LONG).show()

                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentBPLOAccountReg(), null)
                    .addToBackStack(null)
                    .commit()

//                val user = Intent(context, FragmentBusinessTaxAssessmentRegistration::class.java)
//                startActivity(user)
            }, Response.ErrorListener {

                Toast.makeText(context, "Internet connection failed, please try again", Toast.LENGTH_LONG).show()
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






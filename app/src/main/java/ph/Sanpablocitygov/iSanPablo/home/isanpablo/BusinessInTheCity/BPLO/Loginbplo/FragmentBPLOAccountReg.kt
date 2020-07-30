package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Loginbplo


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import ph.Sanpablocitygov.iSanPablo.R



class FragmentBPLOAccountReg: Fragment(){

    private var txtassesslast: EditText? = null
    private var txtassessfirst: EditText? = null
    private var txtassessmiddle: EditText? = null
    private var txtassessmobnum: EditText? = null

    private var txtassesstelnum: EditText? = null

    private var txtassessemail: EditText? = null
    private var txtassessusername: EditText? = null
    private var txtassesspassword: EditText? = null
    private var txtconfirmpassword: EditText? = null
    private var btnassesregister: Button? = null
    private var btnclearall: Button? = null
//    private var txtassessip: TextInputLayout? = null
    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.bc_bplo_registration_fragmet, container, false)

        txtassesslast = view.findViewById<View>(R.id.txt_assess_last) as EditText
        txtassessfirst = view.findViewById<View>(R.id.txt_assess_first) as EditText
        txtassessmiddle =view.findViewById<View>(R.id.txt_assess_middle) as EditText
        txtassessmobnum = view.findViewById<View>(R.id.txt_assess_mob_num) as EditText
        txtassesstelnum = view.findViewById<View>(R.id.txt_assess_tel_num) as EditText
        txtassessemail = view.findViewById<View>(R.id.txt_assess_email) as EditText

        txtassessusername = view.findViewById<View>(R.id.txt_assess_username) as EditText

        txtassesspassword = view.findViewById<View>(R.id.txt_assess_password) as EditText
        txtconfirmpassword = view.findViewById<View>(R.id.txt_confirm_password) as EditText
        btnassesregister = view.findViewById<View>(R.id.btn_asses_register) as Button
        btnclearall = view.findViewById<View>(R.id.btn_clearall) as Button
//       txtassessip = view.findViewById<View>(R.id.txt_assess_ip) as TextInputLayout
        //adding a click listener to button
        btnclearall!!.setOnClickListener{
            txtassesslast!!.text.clear()
            txtassessfirst!!.text.clear()
        txtassessmiddle!!.text.clear()
        txtassessmobnum!!.text.clear()
        txtassesstelnum!!.text.clear()
        txtassessemail!!.text.clear()
        txtassessusername!!.text.clear()
        txtassesspassword!!.text.clear()
       txtconfirmpassword!!.text.clear()
        txtassesslast!!.text.clear()
        }

       // btn_asses_register!!.setOnClickListener(this)


//
//        btn_asses_register.setOnClickListener {
//            addArtist() }




        return view

    }


//    override fun  onClick(view: View)  {
//        //getting the record values
//        val lname = txt_assess_last!!.text.toString()
//        val fname = txt_assess_first!!.text.toString()
//        val mname = txt_assess_middle!!.text.toString()
//        val mnum = txt_assess_mob_num!!.text.toString()
//        val tnum = txt_assess_tel_num!!.text.toString()
//        val ed = txt_assess_email!!.text.toString()
//        val uname = txt_assess_username!!.text.toString()
//        val conpass = txt_confirm_password!!.text.toString()
//        val pass = txt_assess_password!!.text.toString()
//       // val updateurl =("http://"+txt_assess_ip?.editText?.text +"/api/spc_api/").toString()
//
//        if (TextUtils.isEmpty(lname)) {
//            txt_assess_last?.error = "Please enter your last name"
//            txt_assess_last?.requestFocus()
//            return
//        }
//
//        if (TextUtils.isEmpty(fname)) {
//            txt_assess_first?.error = "Please enter your first name"
//            txt_assess_first?.requestFocus()
//            return
//        }
//        if (TextUtils.isEmpty(mname)) {
//            txt_assess_middle?.error = "Please enter your last name"
//            txt_assess_middle?.requestFocus()
//            return
//        }
//
//
//        if (TextUtils.isEmpty(tnum)) {
//            txt_assess_tel_num?.error = "Please enter your last name"
//            txt_assess_tel_num?.requestFocus()
//            return
//        }
//
//
//        if ((mnum).length > 11)
//        {
//            txt_assess_mob_num!!.error = ("mobile number too long")
//            txt_assess_mob_num?.requestFocus()
//            return
//
//        }
//        if ((mnum).length < 11)
//        {
//            txt_assess_mob_num!!.error = ("mobile number too short")
//            txt_assess_mob_num?.requestFocus()
//            return
//
//        }
//
//        if(TextUtils.isEmpty(mnum)){
//            txt_assess_mob_num?.error = "Please enter your mobile number "
//            txt_assess_mob_num?.requestFocus()
//            return
//
//        }
//        if (TextUtils.isEmpty(ed)) {
//            txt_assess_email?.error = "Please enter your email"
//            txt_assess_email?.requestFocus()
//            return
//        }
//        if (TextUtils.isEmpty(uname)) {
//            txt_assess_username?.error = "Please enter your username"
//            txt_assess_username?.requestFocus()
//            return
//        }
//        if ((uname).length > 7)
//        {
//            txt_assess_mob_num!!.error = ("user name too long")
//            txt_assess_mob_num?.requestFocus()
//            return
//
//        }
//
//        if (TextUtils.isEmpty(pass)) {
//            txt_assess_password?.error = "Please enter your password"
//            txt_assess_password?.requestFocus()
//            return
//        }
//        if(TextUtils.isEmpty(conpass)){
//            txt_confirm_password?.error = "Please enter your confirmation password "
//            txt_confirm_password?.requestFocus()
//            return
//
//        }
//        if (pass != conpass)
//        {
//            txt_confirm_password?.error = "password do not match "
//            txt_confirm_password?.requestFocus()
//            return
//
//        }
//
//
//   // val updateurl = "http://192.168.100.207:8080/api/spc_api/"
//
//        var volleyRequest: RequestQueue = Volley.newRequestQueue(context)
//
//
//
//        val req = object : StringRequest(Method.POST, updateurl, Listener {
//                Toast.makeText(context, "register success", Toast.LENGTH_LONG).show()
//
//                activity!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.frag_container,
//                    FragmentBPLOAccountReg(), null)
//                    .addToBackStack(null)
//                    .commit()
//
////                val user = Intent(context, FragmentBusinessTaxAssessmentRegistration::class.java)
////                startActivity(user)
//            }, Response.ErrorListener {
//
//                Toast.makeText(context, "Internet connection failed, please try again", Toast.LENGTH_LONG).show()
//            }) {
//            override fun getParams(): Map<String, String> {
//                val params = HashMap<String, String>()
//                params.put("buss_client[last_name]", lname)
//                params.put("buss_client[first_name]", fname)
//                params.put("buss_client[middle_name]", mname)
//                params.put("buss_client[mobile_no]", mnum)
//                params.put("buss_client[tel_no]", tnum)
//                params.put("buss_client[email]", ed)
//                params.put("user[username]", uname)
//                params.put("user[password]", pass)
//
//                return params
//
//            }
//
//
//        }
//        req.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
//
//        volleyRequest!!.add(req)
//
//
//    }


}






package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Loginbplo

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOProfile
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOonline


class FragmentLoginBPLO: Fragment(),View.OnClickListener {

    private  var etName: EditText? = null

    private  var etPassword: EditText? = null

    private  var progressBar: ProgressBar? = null

    private  var btn_bplo_login: Button? = null

    private  var txt_assessment_register: TextView? = null

    private  var txt_assess_ip: TextInputLayout? = null




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bc_bplo_home_login, null)

        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        etName = view.findViewById<View>(R.id.txt_bplo_username_login)as EditText
        etPassword = view.findViewById<View>(R.id.txt_bplo_password_login)as EditText
        txt_assess_ip =  view.findViewById<View>(R.id.txt_assess_ip) as TextInputLayout
        btn_bplo_login = view.findViewById<View>(R.id.btn_bplo_login) as Button
        txt_assessment_register = view.findViewById<View>(R.id.txt_assessment_register) as TextView
        //calling the method userLogin() for login the user
        btn_bplo_login!!.setOnClickListener (this)

        txt_assessment_register!!.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOAccountReg(), null)
                .addToBackStack(null)
                .commit()
        }



        return view
    }




    override fun  onClick(view: View){
        //first getting the values
        val username = etName!!.text.toString()
        val password = etPassword!!.text.toString()
        val UPDATE_URL =("http://"+txt_assess_ip?.getEditText()?.getText()+"/api/login_api/").toString()
        //validating inputs
        if (TextUtils.isEmpty(username)) {
            etName?.error = "Please enter your username"
            etName?.requestFocus()
            return
        }

        if (TextUtils.isEmpty(password)) {
            etPassword?.error = "Please enter your password"
            etPassword?.requestFocus()
            return
        }

        //if everything is fine
        val stringRequest = object : StringRequest(
            Request.Method.POST,UPDATE_URL,
            Response.Listener { response ->
                progressBar!!.visibility = View.GONE

                try {
                    //converting response to json object
                    val obj = JSONObject(response)

                    //if no error in response
                    if (obj.getBoolean("response")) {
                        Toast.makeText(context, obj.getString("msg"), Toast.LENGTH_SHORT).show()

                        //getting the user from the response
                        //              val userJson = obj.getJSONObject("user")

                        //creating a new user object
//                        val user = User(
//                            userJson.getInt("id"),
//                            userJson.getString("username"),
//                            userJson.getString("password"),
//                            userJson.getString("t
//
//                            ype"),
//                            userJson.getString("created_at"),
//                            userJson.getString("updated_at")
//                        )
                        //    Toast.makeText(applicationContext,user.id,Toast.LENGTH_LONG).show()
                        //storing the user in shared preferences
                        // SharedPrefManager.getInstance(applicationContext).userLogin(user)
                        //starting the MainActivity
                        //  finish()
                        // startActivity(Intent(applicationContext, Main3Activity::class.java))
//
//                        val intent = Intent(applicationContext, FragmentBPLOstep1::class.java)
//                        startActivity(intent)

                        activity!!.supportFragmentManager.beginTransaction().replace(
                            R.id.frag_container,
                            FragmentBPLOProfile(), null)
                            .addToBackStack(null)
                            .commit()



                    } else {
                        Toast.makeText(context, obj.getString("msg"), Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()

                params.put("user[username]", username)
                params.put("user[password]", password)
                return params
            }
        }

        VolleySingleton1.getInstance(requireContext()).addToRequestQueue(stringRequest)
        //  stringRequest.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)



    }




}



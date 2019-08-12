package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Loginbplo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.fragment_bc_bplo_home_login.*
import org.json.JSONException
import org.json.JSONObject
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOstep1
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BusinessTaxAssessment.BusinessTaxAssessmentRegActivity
import java.util.HashMap

class LoginBPLOActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etPassword: EditText
    private lateinit var progressBar: ProgressBar
    private var txt_assess_ip: TextInputLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_bc_bplo_home_login)

//        if (SharedPrefManager.getInstance(this).isLoggedIn) {
//            finish()
//            startActivity(Intent(this,Main3Activity::class.java))
//        }

        progressBar = findViewById(R.id.progressBar)
        etName = findViewById(R.id.txt_bplo_username_login)
        etPassword = findViewById(R.id.txt_bplo_password_login)
        txt_assess_ip = findViewById(R.id.txt_assess_ip) as TextInputLayout
        //calling the method userLogin() for login the user
        btn_bplo_login.setOnClickListener (View.OnClickListener {
            userLogin()
        })

        txt_assessment_register.setOnClickListener {
                        val intent = Intent(applicationContext, BusinessTaxAssessmentRegActivity::class.java)
          startActivity(intent)
        }


        //if user presses on textview it call the activity RegisterActivity

    }

    private fun userLogin() {
        //first getting the values
        val username = etName.text.toString()
        val password = etPassword.text.toString()
        val UPDATE_URL =("http://"+txt_assess_ip?.getEditText()?.getText()+"/api/login_api/").toString()
        //validating inputs
        if (TextUtils.isEmpty(username)) {
            etName.error = "Please enter your username"
            etName.requestFocus()
            return
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.error = "Please enter your password"
            etPassword.requestFocus()
            return
        }

        //if everything is fine
        val stringRequest = object : StringRequest(
            Request.Method.POST,UPDATE_URL,
            Response.Listener { response ->
                progressBar.visibility = View.GONE

                try {
                    //converting response to json object
                    val obj = JSONObject(response)

                    //if no error in response
                    if (obj.getBoolean("response")) {
                        Toast.makeText(applicationContext, obj.getString("msg"), Toast.LENGTH_SHORT).show()

                        //getting the user from the response
                        //              val userJson = obj.getJSONObject("user")

                        //creating a new user object
//                        val user = User(
//                            userJson.getInt("id"),
//                            userJson.getString("username"),
//                            userJson.getString("password"),
//                            userJson.getString("type"),
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


                    } else {
                        Toast.makeText(applicationContext, obj.getString("msg"), Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error -> Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show() }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()

                params.put("user[username]", username)
                params.put("user[password]", password)
                return params
            }
        }

        VolleySingleton1.getInstance(this).addToRequestQueue(stringRequest)
    }
}
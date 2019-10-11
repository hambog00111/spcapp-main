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
import com.android.volley.Response.Listener
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import ph.sanpablocitygov.iSanPablo.R

class fragmentsubmmit : Fragment(), View.OnClickListener {

    private var submmitt: EditText? = null

    private var btn_reg: Button? = null

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragmentsubmmit, container, false)

        submmitt = view.findViewById<View>(R.id.sub) as EditText

        btn_reg = view.findViewById<View>(R.id.btn_reg) as Button




        btn_reg!!.setOnClickListener(this)


//
//        btn_asses_register.setOnClickListener {
//            addArtist() }




        return view

    }


    override fun  onClick(view: View)  {
        //getting the record values
        val lname = submmitt!!.text.toString()


         val UPDATE_URL = "http://192.168.100.207:8080/api/bplo_api/"
        var volleyRequest: RequestQueue? = null

        volleyRequest = Volley.newRequestQueue(context)



        val req = object : StringRequest(Method.POST,
            UPDATE_URL,
            Listener {
                Toast.makeText(context, "register success", Toast.LENGTH_LONG).show()

                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    fragmentsubmmit(), null)
                    .addToBackStack(null)
                    .commit()

//                val user = Intent(context, FragmentBusinessTaxAssessmentRegistration::class.java)
//                startActivity(user)
            }, Response.ErrorListener {

                Toast.makeText(context, "Internet connection failed, please try again", Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("buss_profile[buss_name]", lname)


                return params

            }


        }
        req.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        volleyRequest!!.add(req)


    }


}






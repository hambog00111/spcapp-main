package ph.sanpablocitygov.iSanPablo.home.isanpablo.testlng

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_past_data.*
import ph.sanpablocitygov.iSanPablo.R

class Fragmentpastdata: Fragment(){

    private var editTextArtistName: EditText? = null
    private var spinnerGenre: EditText? = null


    override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_past_data, container, false)

        val RegBtn = view.findViewById<View>(R.id.buttonAddArtist) as Button
        editTextArtistName = view.findViewById(R.id.editTextArtistName) as EditText
        spinnerGenre =view.findViewById (R.id.spinnerGenre) as EditText

        //adding a click listener to button
        RegBtn.setOnClickListener {
            onClick() }

        return view
    }



    private fun onClick() {
        //getting the record values
        val name = editTextArtistName?.text.toString()
        val genre = spinnerGenre?.text.toString()
        if (name.isEmpty() ||
            genre.isEmpty()){
            Toast.makeText(context, "Please input required fields ", Toast.LENGTH_SHORT).show()
        }


        val UPDATE_URL = "http://192.168.100.207:8080/api/android_api/"
        var volleyRequest: RequestQueue? = null

        volleyRequest = Volley.newRequestQueue(context)


        val req = object : StringRequest(Method.POST,
            UPDATE_URL,
            Response.Listener { response ->
                Toast.makeText(requireContext(), response, Toast.LENGTH_LONG).show()
                val user = Intent(requireContext(), Fragmentpastdata::class.java)
                startActivity(user)

            }, Response.ErrorListener { e ->

                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("buss_client[last_name]", name)
                params.put("buss_client[first_name]", genre)
                return params
            }
        }
        req.retryPolicy = DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        volleyRequest!!.add(req)

    }

}

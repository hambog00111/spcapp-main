@file:Suppress("DEPRECATION")

package ph.Sanpablocitygov.iSanPablo.OurGovernment
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.support.v7.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.our_government_fragment.*
import org.json.JSONArray
import org.json.JSONObject
import ph.Sanpablocitygov.iSanPablo.LoadingActivity
import ph.Sanpablocitygov.iSanPablo.R
import java.net.HttpURLConnection
import java.net.URL


    @Suppress("PLUGIN_WARNING", "DEPRECATION")
class FragmentOurGoverment  : Fragment(),View.OnClickListener {

    private var pLoading: ProgressDialog? = null

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(
                ConnectivityManager
                    .EXTRA_NO_CONNECTIVITY, false
            )
            if (notConnected) {
                disconnected()
            } else {
                connected()
            }
        }
    }


    private lateinit var data: JSONArray
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.our_government_fragment, container, false)

//        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
//        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == false
//
//        if (isConnected){
//            activity!!.supportFragmentManager.beginTransaction().replace(
//                R.id.frag_container,
//                FragmentHome()
//                , null
//            )
//                .addToBackStack(null)
//                .commit()
//        }

        val t1: TextView = view.findViewById(R.id.dept_mayor_office)
        t1.setOnClickListener(this)
        pLoading = ProgressDialog(requireContext())
        pLoading!!.setMessage("\tLoading, please wait")
        pLoading!!.setCancelable(false)
        pLoading!!.show()

        GetDeptLs().execute()
        //getData()
        val t2: TextView = view.findViewById(R.id.dept_administrator_office)
        t2.setOnClickListener(this)
        GetDeptLs().execute()
        val t3: TextView = view.findViewById(R.id.dept_accounting_office)
        t3.setOnClickListener(this)
        GetDeptLs().execute()
        val t4: TextView = view.findViewById(R.id.dept_agriculture_office)
        t4.setOnClickListener(this)
        GetDeptLs().execute()
        val t5: TextView = view.findViewById(R.id.dept_assesor_office)
        t5.setOnClickListener(this)
        GetDeptLs().execute()
        val t6: TextView = view.findViewById(R.id.dept_budget_office)
        t6.setOnClickListener(this)
        GetDeptLs().execute()
        val t7: TextView = view.findViewById(R.id.dept_cooperative_office)
        t7.setOnClickListener(this)
        GetDeptLs().execute()
        val t8: TextView = view.findViewById(R.id.dept_environmental_office)
        t8.setOnClickListener(this)
        GetDeptLs().execute()
        val t9: TextView = view.findViewById(R.id.dept_engineer_office)
        t9.setOnClickListener(this)
        GetDeptLs().execute()
        val t10: TextView = view.findViewById(R.id.dept_general_office)
        t10.setOnClickListener(this)
        GetDeptLs().execute()
        val t11: TextView = view.findViewById(R.id.dept_human_office)
        t11.setOnClickListener(this)
        GetDeptLs().execute()
        val t12: TextView = view.findViewById(R.id.dept_legal_office)
        t12.setOnClickListener(this)
        GetDeptLs().execute()
        val t13: TextView = view.findViewById(R.id.dept_local_civil_office)
        t13.setOnClickListener(this)
        GetDeptLs().execute()
        val t14: TextView = view.findViewById(R.id.dept_planning_office)
        t14.setOnClickListener(this)
        GetDeptLs().execute()
        val t15: TextView = view.findViewById(R.id.dept_population_office)
        t15.setOnClickListener(this)
        GetDeptLs().execute()
        val t16: TextView = view.findViewById(R.id.dept_social_office)
        t16.setOnClickListener(this)
        GetDeptLs().execute()
        val t17: TextView = view.findViewById(R.id.dept_tourism_office)
        t17.setOnClickListener(this)
        GetDeptLs().execute()
        val t18: TextView = view.findViewById(R.id.dept_treasurer_office)
        t18.setOnClickListener(this)
        GetDeptLs().execute()
        val t19: TextView = view.findViewById(R.id.dept_veterinarian_office)
        t19.setOnClickListener(this)
        GetDeptLs().execute()
        val t20: TextView = view.findViewById(R.id.dept_vice_office)
        t20.setOnClickListener(this)
        GetDeptLs().execute()
        val t21: TextView = view.findViewById(R.id.dept_secretary_office)
        t21.setOnClickListener(this)
        GetDeptLs().execute()
        val t22: TextView = view.findViewById(R.id.dept_office_office)
        t22.setOnClickListener(this)
        GetDeptLs().execute()
        val t23: TextView = view.findViewById(R.id.dept_health_office)
        t23.setOnClickListener(this)
        GetDeptLs().execute()
        val t24: TextView = view.findViewById(R.id.dept_information_office)
        t24.setOnClickListener(this)
        GetDeptLs().execute()
        val t25: TextView = view.findViewById(R.id.dept_hospital_office)
        t25.setOnClickListener(this)
        GetDeptLs().execute()
        val t26: TextView = view.findViewById(R.id.dept_solid_office)
        t26.setOnClickListener(this)
        GetDeptLs().execute()
        val t27: TextView = view.findViewById(R.id.dept_dalubhasan_office)
        t27.setOnClickListener(this)
        GetDeptLs().execute()
        val t28: TextView = view.findViewById(R.id.dept_disaster_office)
        t28.setOnClickListener(this)
        GetDeptLs().execute()
        val t29: TextView = view.findViewById(R.id.dept_traffic_office)
        t29.setOnClickListener(this)
        GetDeptLs().execute()


//        initdata()

        return view

    }
//
//    private fun initdata() {
//
//        localoff = ArrayList()
//
//        localoff.add(localoff)
//    }


    override fun onStart() {
        super.onStart()
        activity!!.registerReceiver(
            broadcastReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

    }

    override fun onStop() {
        super.onStop()
        activity!!.unregisterReceiver(broadcastReceiver)
    }


    @SuppressLint("SetTextI18n", "InflateParams")
    private fun disconnected() {
        clickbutton.visibility = View.GONE

        val Nonet = LayoutInflater.from(requireActivity())
            .inflate(R.layout.fragment_our_gov_dialogbox, null)

        val builder = android.app.AlertDialog.Builder(requireContext())
            .setView(Nonet)

        val tv = Nonet.findViewById(R.id.resultTv) as TextView

        tv.text = "You need to have Mobile Data or wifi to access this."
        builder.setCancelable(false)

        builder.setPositiveButton("OK") { _, _ ->

            val intent = Intent(context, LoadingActivity::class.java)
            startActivity(intent)

        }
        builder.setNegativeButton("CANCEL") { dialog, _ ->
            dialog.dismiss()

        }


        val dialog: android.app.AlertDialog = builder.create()

        dialog.show()

    }

    private fun connected() {
        clickbutton.visibility = View.VISIBLE
    }


//    fun getData() {
//        val conn: HttpURLConnection =
//            URL("http://www.sanpablocitygov.ph/api/get-dept-list").openConnection() as HttpURLConnection
//        val res = conn.inputStream.bufferedReader().readText()
//        JSONObject(res).getJSONArray("depts")
//    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.dept_mayor_office -> dispMyrOff()
            R.id.dept_secretary_office -> dispSecOff()
            R.id.dept_traffic_office -> dispTrfOff()
            else -> dispOth(v.id)
        }
    }

//    private fun isNetworkConnected(): Boolean {
//        val connectivityManager = getSystemService(activity!!.supportFragmentManager.CONNECTIVITY_SERVICE) as ConnectivityManager //1
//        val networkInfo = connectivityManager.activeNetworkInfo //2
//        return networkInfo != null && networkInfo.isConnected //3
//    }


    fun dispMyrOff() {

        try {
            var str = ""
            for (i: Int in 0 until 7 step 1) {
                val post: JSONObject = data.getJSONObject(i)
                str =
                    str + post.getString("dept_position") + "\n" + post.getString("dept_name") + "\n\n"
            }
            val builder = AlertDialog.Builder(requireContext())
            with(builder) {
                setMessage(str)
                setTitle("Mayor\'s Office")
                setPositiveButton("OK", null)
            }
            val alertDialog = builder.create()
            alertDialog.show()
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "unable to connect to the server please try again later ",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun dispSecOff() {
        try {
            var str = ""
            for (i: Int in 26 until 28 step 1) {
                val post: JSONObject = data.getJSONObject(i)
                str =
                    str + post.getString("dept_position") + "\n" + post.getString("dept_name") + "\n\n"
            }
            val builder = AlertDialog.Builder(requireContext())
            with(builder) {
                setMessage(str)
                setTitle("Secretary to the Sanguniang Panglungsod")
                setPositiveButton("OK", null)
            }
            val alertDialog = builder.create()
            alertDialog.show()
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "unable to connect to the server please try again later ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun dispTrfOff() {
        try {
            var str = ""
            for (i: Int in 35 until 37 step 1) {
                val post: JSONObject = data.getJSONObject(i)
                str =
                    str + post.getString("dept_position") + "\n" + post.getString("dept_name") + "\n\n"
            }
            val builder = AlertDialog.Builder(requireContext())
            with(builder) {
                setMessage(str)
                setTitle("Traffic Management Office")
                setPositiveButton("OK", null)
            }
            val alertDialog = builder.create()
            alertDialog.show()
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "unable to connect to the server please try again later ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun dispOth(v: Int) = try {
        var str = ""
        val txt: TextView = view!!.findViewById(v)
        for (i: Int in 0 until 37 step 1) {
            val post: String = (data.getJSONObject(i)).getString("dept_office")
            if (post.equals(txt.text.toString(), true)) {
                str =
                    str + (data.getJSONObject(i)).getString("dept_position") + "\n" + (data.getJSONObject(
                        i
                    )).getString("dept_name") + "\n\n"
            }
        }
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setMessage(str)
            setTitle(txt.text.toString())
            setPositiveButton("OK", null)
        }
        val alertDialog = builder.create()
        alertDialog.show()

    } catch (e: Exception) {
        Toast.makeText(
            requireContext(),
            "unable to connect to the server please try again later ",
            Toast.LENGTH_SHORT
        ).show()
    }


    //    @SuppressLint("StaticFieldLeak")

    @SuppressLint("StaticFieldLeak")
    inner class GetDeptLs internal constructor() :
        AsyncTask<Void, Void, String>() {
        //     private var res: String? = null
     //  private val fragRef: WeakReference<FragmentOurGoverment> = WeakReference(mContext)
        //var mView: ListView = fragRef.get()!!.listView

        override fun onPreExecute() {


        }

        override fun doInBackground(vararg params: Void?): String {
            var xhr = ""
            val conn =
                URL("http://www.sanpablocitygov.ph/api/get-dept-list").openConnection() as HttpURLConnection
            try {

                conn.readTimeout = 10000
                conn.connectTimeout = 15000
                conn.connect()
                if (conn.responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
//                  xhr =
//                        "{\"depts\": \"null\",[{\"dept_office\": \"null\", \"dept_position\": \"null\", \"dept_name\":\"null\"}]}"

                } else {

                    xhr = conn.inputStream.bufferedReader().readText()
                }


            } catch (e: Exception) {
                xhr = "conErr"

                e.printStackTrace()
            }

//            finally {
//                disconnected()
//            }
            return xhr
        }

        override fun onPostExecute(result: String?) {


            if (result.toString() == "conErr") {
                Toast.makeText(requireContext(), "Connection failed", Toast.LENGTH_SHORT).show()
            } else {

                data = JSONObject(result.toString()).getJSONArray("depts")
            }
            pLoading!!.dismiss()


        }

//        fun ListDept(jsonStr: CharSequence) {
//
//            val list = mutableListOf<OurGovernmentModel>()
//            try {
//                val dLs: JSONArray = JSONObject(jsonStr.toString()).getJSONArray("depts")
//                for (i in 0 until dLs.length()) {
//                    val post: JSONObject = dLs.getJSONObject(i)
//
//                    list.add(
//                        OurGovernmentModel(
//                            post.getString("dept_office"),
//                            post.getString("dept_position"),
//                            post.getString("dept_name")
//                        )
//                    )
//
//                }
//                OurGovernmentAdapter(
//                    fragRef.get()!!.requireContext(),
//                    R.layout.our_government_department_row,
//                    list
//                )
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            return
//        }

    }


}


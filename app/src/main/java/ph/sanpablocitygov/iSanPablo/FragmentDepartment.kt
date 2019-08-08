package ph.sanpablocitygov.iSanPablo

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import ph.sanpablocitygov.iSanPablo.OurGovernment.OurGovernmentAdapter
import ph.sanpablocitygov.iSanPablo.OurGovernment.OurGovernmentModel
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL

class FragmentDepartment : Fragment(), View.OnClickListener{

    //private lateinit var listView: ListView
    private lateinit var data: JSONArray

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_our_goverment_layout, container, false)
        //listView = view.findViewById(R.id.listview_department)
        val t1: TextView = view.findViewById(R.id.dept_mayor_office)
        t1.setOnClickListener(this)
        GetDeptLs(this).execute()
        //getData()
        val t2: TextView = view.findViewById(R.id.dept_administrator_office)
        t2.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t3: TextView = view.findViewById(R.id.dept_accounting_office)
        t3.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t4: TextView = view.findViewById(R.id.dept_agriculture_office)
        t4.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t5: TextView = view.findViewById(R.id.dept_assesor_office)
        t5.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t6: TextView = view.findViewById(R.id.dept_budget_office)
        t6.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t7: TextView = view.findViewById(R.id.dept_cooperative_office)
        t7.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t8: TextView = view.findViewById(R.id.dept_environmental_office)
        t8.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t9: TextView = view.findViewById(R.id.dept_engineer_office)
        t9.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t10: TextView = view.findViewById(R.id.dept_general_office)
        t10.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t11: TextView = view.findViewById(R.id.dept_human_office)
        t11.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t12: TextView = view.findViewById(R.id.dept_legal_office)
        t12.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t13: TextView = view.findViewById(R.id.dept_local_civil_office)
        t13.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t14: TextView = view.findViewById(R.id.dept_planning_office)
        t14.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t15: TextView = view.findViewById(R.id.dept_population_office)
        t15.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t16: TextView = view.findViewById(R.id.dept_social_office)
        t16.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t17: TextView = view.findViewById(R.id.dept_tourism_office)
        t17.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t18: TextView = view.findViewById(R.id.dept_treasurer_office)
        t18.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t19: TextView = view.findViewById(R.id.dept_veterinarian_office)
        t19.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t20: TextView = view.findViewById(R.id.dept_vice_office)
        t20.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t21: TextView = view.findViewById(R.id.dept_secretary_office)
        t21.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t22: TextView = view.findViewById(R.id.dept_office_office)
        t22.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t23: TextView = view.findViewById(R.id.dept_health_office)
        t23.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t24: TextView = view.findViewById(R.id.dept_information_office)
        t24.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t25: TextView = view.findViewById(R.id.dept_hospital_office)
        t25.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t26: TextView = view.findViewById(R.id.dept_solid_office)
        t26.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t27: TextView = view.findViewById(R.id.dept_dalubhasan_office)
        t27.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t28: TextView = view.findViewById(R.id.dept_disaster_office)
        t28.setOnClickListener(this)
        GetDeptLs(this).execute()
        val t29: TextView = view.findViewById(R.id.dept_traffic_office)
        t29.setOnClickListener(this)
        GetDeptLs(this).execute()





        return  view

    }


//    fun getData() {
//        val conn: HttpURLConnection = URL("http://www.sanpablocitygov.ph/api/get-dept-list").openConnection() as HttpURLConnection
//        val res = conn.inputStream.bufferedReader().readText()
//        val data: JSONArray = JSONObject(res).getJSONArray("depts")
//    }
override fun onClick(v: View?) {
    when(v!!.id) {
        R.id.dept_mayor_office -> dispMyrOff()
        R.id.dept_secretary_office -> dispSecOff()
        R.id.dept_traffic_office -> dispTrfOff()
        else -> dispOth(v.id)
    }
}


    fun dispMyrOff() {
        var str = ""
        for(i: Int in 0 until 7 step 1) {
            val post: JSONObject = data.getJSONObject(i)
            str = str + post.getString("dept_position") + "\n" + post.getString("dept_name") + "\n\n"
        }
        val builder = AlertDialog.Builder(requireContext())
            with(builder) {
                setMessage(str)
                setTitle("Mayor\'s Office")
                setPositiveButton("OK",null)
            }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun dispSecOff() {
        var str = ""
        for(i: Int in 26 until 28 step 1) {
            val post: JSONObject = data.getJSONObject(i)
            str = str + post.getString("dept_position") + "\n" + post.getString("dept_name") + "\n\n"
        }
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setMessage(str)
            setTitle("Secretary to the Sanguniang Panglungsod")
            setPositiveButton("OK",null)
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun dispTrfOff() {
        var str = ""
        for(i: Int in 35 until 37 step 1) {
            val post: JSONObject = data.getJSONObject(i)
            str = str + post.getString("dept_position") + "\n" + post.getString("dept_name") + "\n\n"
        }
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setMessage(str)
            setTitle("Traffic Management Office")
            setPositiveButton("OK",null)
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun dispOth(v: Int) {
        var str = ""
        val txt: TextView = view!!.findViewById(v)
         for(i: Int in 0 until 37 step 1) {
             val post: String = (data.getJSONObject(i)).getString("dept_office")
             if(post.equals(txt.text.toString(), true)) {
                 str = str + (data.getJSONObject(i)).getString("dept_position") + "\n" + (data.getJSONObject(i)).getString("dept_name") + "\n\n"
             }
        }
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setMessage(str)
            setTitle(txt.text.toString())
            setPositiveButton("OK",null)
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }







    inner class GetDeptLs internal constructor(mContext: FragmentDepartment): AsyncTask<Void,Void,String>(){
        private var res: String? = null
        private val fragRef: WeakReference<FragmentDepartment> = WeakReference(mContext)
        //var mView: ListView = fragRef.get()!!.listView

        override fun onPreExecute() {

        }

        override fun doInBackground(vararg params: Void?): String {
            var xhr: String = ""
            val conn = URL("http://www.sanpablocitygov.ph/api/get-dept-list").openConnection() as HttpURLConnection
            try {
                conn.connect()
                if(conn.responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                    xhr =
                        "{\"depts\": [{\"dept_office\": \"null\", \"dept_position\": \"null\", \"dept_name\":\"null\"}]}"
                } else {
                    xhr = conn.inputStream.bufferedReader().readText()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                conn.disconnect()
            }
            return xhr
        }

        override fun onPostExecute(result: String?) {
            data = JSONObject(result.toString()).getJSONArray("depts")
            //res = result
            //ListDept(res!!)
        }

        fun ListDept(jsonStr: CharSequence) {
            val list = mutableListOf<OurGovernmentModel>()
            try {
                val dLs: JSONArray = JSONObject(jsonStr.toString()).getJSONArray("depts")
                for(i in 0 until dLs.length() step 1) {
                    val post: JSONObject = dLs.getJSONObject(i)
                    list.add(
                        OurGovernmentModel(
                            post.getString("dept_office"),
                            post.getString("dept_position"),
                            post.getString("dept_name")
                        )
                    )

                }
                OurGovernmentAdapter(
                    fragRef.get()!!.requireContext(),
                    R.layout.row_department,
                    list
                )
                //mView.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}
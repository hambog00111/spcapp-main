package ph.sanpablocitygov.iSanPablo.OurBarangay
import android.annotation.SuppressLint
//import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import ph.sanpablocitygov.iSanPablo.R

import org.json.JSONArray
import org.json.JSONObject
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL

class FragmentBarangay : Fragment() {

    private lateinit var listView : ListView

    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val rootview = inflater.inflate(R.layout.fragment_barangay, null)

        listView = view!!.findViewById(R.id.listview_barangay)

        GetBrgyLs(this).execute()

//        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
////            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
////
////                val itemValue = listView.getItemAtPosition(position) as String
////
////                Toast.makeText(
////                    requireContext(),
////                    "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG
////                )
////                    .show()
////            }
////
////        }


        return rootview
    }


    @SuppressWarnings("deprecation")
    inner class GetBrgyLs internal constructor(mContext: FragmentBarangay): AsyncTask<Void, Void, String>(){

        private var resp: String? = null
//        lateinit var pLoading: ProgressDialog
        private val fragRef: WeakReference<FragmentBarangay> = WeakReference(mContext)
        var mView: ListView = fragRef.get()!!.listView

        override fun onPreExecute(){
//            pLoading = ProgressDialog(fragRef.get()!!.context)
//            pLoading.setMessage("\t Loading, please wait")
//            pLoading.setCancelable(false)
//            pLoading.show()
        }

        override fun doInBackground(vararg params: Void?): String {
            var xhr: String = ""
            val conn = URL("http://www.sanpablocitygov.ph/api/get-brgy-list").openConnection() as HttpURLConnection
            try {
                xhr = conn.inputStream.bufferedReader().readText()
            } catch(e: Exception){
                e.printStackTrace()
            } finally {
                conn.disconnect()
            }
            return xhr
        }

        override fun onPostExecute(res: String){
//            pLoading.dismiss()
            resp = res
            ListBrgy(resp!!)

        }

        fun ListBrgy(jsonStr: CharSequence){
            val list = mutableListOf<BarangayModel>()

            try{
                val bLs: JSONArray = JSONObject(jsonStr.toString()).getJSONArray("brgys")
                for(i in 0 until bLs.length() step 1){
                    val post: JSONObject = bLs.getJSONObject(i)
                    list.add(
                        BarangayModel(
                            post.getString("brgy_name"),
                            post.getString("brgy_district"),
                            post.getString("brgy_code"),
                            post.getString("brgy_chairman"),
                            post.getString("brgy_contact")
                        )
                    )
                }

                val adapter = BarangayAdapter(
                    fragRef.get()!!.requireContext(),
                    R.layout.row_barangay,
                    list
                )
                mView.adapter = adapter

            } catch (e: Exception){
                e.printStackTrace()
            }
        }

    }




    companion object {

    }
}
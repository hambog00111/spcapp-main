package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.bc_bplo_fillup_step3_fragment.*
import okhttp3.*
import org.jetbrains.anko.toast
import ph.Sanpablocitygov.iSanPablo.R
import java.io.IOException

class FragmentBPLOstep3 : Fragment() {
    class brgyholder( val location_barangay_id : String ,
                      val barangay_name : String)


    class brgyhandler(val kodname : String ,val kodid : String){ override fun toString(): String { return kodname } }

    class subdvlistclass( val location_subdivision_id : String ,
                          val subdivision_name : String)

    class sudvhandler(  val subvname : String, val subvid : String)
    { override fun toString(): String { return subvname } }
    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bc_bplo_fillup_step3_fragment, null)

        val btnnext = view.findViewById<Button>(R.id.txt_bplo_next_page)
        val btnadd = view.findViewById<Button>(R.id.add)
        val nummale = view.findViewById<EditText>(R.id.txt_bplo_owners_info_total_male)
        val numfemale = view.findViewById<EditText>(R.id.txt_bplo_owners_info_total_female)
        val totalnum = view.findViewById<EditText>(R.id.txt_bplo_owners_info_total_num_employees_lgu)

        btnadd.setOnClickListener {

            if (nummale!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Please enter the number of Male employees!", Toast.LENGTH_SHORT).show()
                totalnum.text.clear()
                return@setOnClickListener
            }

           else if (numfemale!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Please enter the number of Female employees!", Toast.LENGTH_SHORT).show()
                totalnum.text.clear()
                return@setOnClickListener
            }else {
                val sum = nummale.text.toString().toInt() + numfemale.text.toString().toInt()
                totalnum.setText(sum.toString())
            }
        }



        btnnext.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep4() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnback = view.findViewById<Button>(R.id.txt_bplo_back)
        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep2() , null)
                .addToBackStack(null)
                .commit()
        }
        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()

        val okHttpClient = OkHttpClient()
        val formBody: RequestBody = FormBody.Builder()
            .build()

        val request = Request.Builder()
            //.method("GET", formBody)
            .url("http://192.168.3.208:8000/api/getBarangay")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                pdLoading.dismiss()
                activity!!.runOnUiThread(java.lang.Runnable {
                    println(e)
                    activity!!.toast("Unable to connect to the server please try again later")
                })
            }

            @SuppressLint("ShowToast", "SetTextI18n")
            override fun onResponse(call: Call, response: Response) {
                pdLoading.dismiss()
                val body = response.body?.string()
                val gson = Gson()
                println(body)
                val list = gson.fromJson(body, Array<brgyholder>::class.java).toList()
                val brgylistmain: MutableList<brgyhandler> = ArrayList()



                val i =  brgyhandler("SELECT BARANGAY", "0" )
                brgylistmain.add(i)

                for (entry in list) {
                    val i =  brgyhandler(entry.barangay_name,entry.location_barangay_id )
                    brgylistmain.add(i)
                }

                activity!!.runOnUiThread(java.lang.Runnable {


                    var spinner: Spinner? = null
                    spinner = activity!!.spinner_brgy_step3
                    spinner!!.onItemSelectedListener
                    val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, brgylistmain)

                    //adapter.setDropDownViewResource(R.layout.spinnercustomcolor)

                    spinner.adapter = adapter


                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val data = parent.selectedItem as brgyhandler
                            getbrgyforsub(data)

                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                })

            }

            //
            private fun getbrgyforsub(data: brgyhandler) {
                val spinner1id = view!!.findViewById<TextView>(R.id.brgy_step3)
                spinner1id.text = data.kodid


                val formBody: RequestBody = FormBody.Builder()
                    .build()

                val okHttpClient = OkHttpClient()
                val request = Request.Builder()
                    .method("POST" ,formBody )
                    .url("http://192.168.3.208:8000/api/getSubdivision/"+spinner1id.text)
                    .build()
                okHttpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        activity!!.runOnUiThread(java.lang.Runnable {
                            println(e)
                            activity!!.toast("Unable to connect to the server please try again later")
                        })
                    }

                    @SuppressLint("ShowToast", "SetTextI18n")
                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        val gson = Gson()
                        val sub = gson.fromJson(body, Array<subdvlistclass>::class.java).toList()

                        println(sub)
                        println(sub.size)

                        val sublistmain: MutableList<sudvhandler> = ArrayList()

                        if(sub.size == 0 ){
                            val i =  sudvhandler("NO SUBDIVISION FOUND" , "")
                            sublistmain.add(i)
                        }else{
                            for (entry in sub) {
                                val i =  sudvhandler(entry.subdivision_name , entry.location_subdivision_id)
                                sublistmain.add(i)
                            }
                        }


                        activity!!.runOnUiThread(java.lang.Runnable {
                            var spinnersub:Spinner? = null
                            spinnersub = activity!!.spinner_sub_step3
                            spinnersub!!.onItemSelectedListener

                            val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, sublistmain)

                            //adapter.setDropDownViewResource(R.layout.spinnercustomcolor)

                            spinnersub.adapter = adapter
                            spinnersub.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                                    val data2 = parent.selectedItem as sudvhandler
                                    getsub(data2)

                                }
                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }
                            }
                        })
                    }
                })
            }

            private fun getsub(data2: sudvhandler){
                val spinner2subdid = view!!.findViewById<TextView>(R.id.sub_step3)
                spinner2subdid.setText(data2.subvid)
            }

        })
           val rented = view!!.findViewById<RadioGroup>(R.id.rg_bplo_owner_info_rented)


        rented.setOnCheckedChangeListener{ _, checkedId ->
            if (checkedId==R.id.rb_bplo_owner_info_rented_yes){
                val lin = view!!.findViewById<CardView>(R.id.card_step3)
                lin.visibility = View.VISIBLE
               // Toast.makeText(activity!!,"yes",Toast.LENGTH_SHORT).show()
            }

            if (checkedId==R.id.rb_bplo_owner_info_rented_no){
                val lin = view!!.findViewById<CardView>(R.id.card_step3)
                lin.visibility = View.GONE
               // Toast.makeText(activity!!,"no",Toast.LENGTH_SHORT).show()
            }


        }

        getbrgy()

        return view
    }




    fun getbrgy(){

        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()

        val okHttpClient = OkHttpClient()
        val formBody: RequestBody = FormBody.Builder()
            .build()

        val request = Request.Builder()
            //.method("GET", formBody)
            .url("http://192.168.3.208:8000/api/getBarangay")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                pdLoading.dismiss()
                activity!!.runOnUiThread(java.lang.Runnable {
                    println(e)
                    activity!!.toast("Unable to connect to the server please try again later")
                })
            }

            @SuppressLint("ShowToast", "SetTextI18n")
            override fun onResponse(call: Call, response: Response) {
                pdLoading.dismiss()
                val body = response.body?.string()
                val gson = Gson()
                println(body)
                val list = gson.fromJson(body, Array<brgyholder>::class.java).toList()
                val brgylistmain: MutableList<brgyhandler> = ArrayList()



                val i =  brgyhandler("SELECT BARANGAY", "0" )
                brgylistmain.add(i)

                for (entry in list) {
                    val i =  brgyhandler(entry.barangay_name,entry.location_barangay_id )
                    brgylistmain.add(i)
                }

                activity!!.runOnUiThread(java.lang.Runnable {


                    var spinner: Spinner? = null
                    spinner = activity!!.spinner2_brgy_step3
                    spinner!!.onItemSelectedListener
                    val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, brgylistmain)

                    //adapter.setDropDownViewResource(R.layout.spinnercustomcolor)

                    spinner.adapter = adapter


                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val data = parent.selectedItem as brgyhandler
                            getbrgyforsub(data)

                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                })

            }

            //
            private fun getbrgyforsub(data: brgyhandler) {
                val spinner1id = view!!.findViewById<TextView>(R.id.brgy2_step3)
                spinner1id.text = data.kodid


                val formBody: RequestBody = FormBody.Builder()
                    .build()

                val okHttpClient = OkHttpClient()
                val request = Request.Builder()
                    .method("POST" ,formBody )
                    .url("http://192.168.3.208:8000/api/getSubdivision/"+spinner1id.text)
                    .build()
                okHttpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        activity!!.runOnUiThread(java.lang.Runnable {
                            println(e)
                            activity!!.toast("Unable to connect to the server please try again later")
                        })
                    }

                    @SuppressLint("ShowToast", "SetTextI18n")
                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        val gson = Gson()
                        val sub = gson.fromJson(body, Array<subdvlistclass>::class.java).toList()

                        println(sub)
                        println(sub.size)

                        val sublistmain: MutableList<sudvhandler> = ArrayList()

                        if(sub.size == 0 ){
                            val i =  sudvhandler("NO SUBDIVISION FOUND" , "")
                            sublistmain.add(i)
                        }else{
                            for (entry in sub) {
                                val i =  sudvhandler(entry.subdivision_name , entry.location_subdivision_id)
                                sublistmain.add(i)
                            }
                        }


                        activity!!.runOnUiThread(java.lang.Runnable {
                            var spinnersub:Spinner? = null

                            spinnersub = activity!!.spinner2_sub_step3
                            spinnersub!!.onItemSelectedListener

                            val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, sublistmain)

                            //adapter.setDropDownViewResource(R.layout.spinnercustomcolor)

                            spinnersub.adapter = adapter
                            spinnersub.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                                    val data2 = parent.selectedItem as sudvhandler
                                    getsub(data2)

                                }
                                override fun onNothingSelected(parent: AdapterView<*>?) {


                                }
                            }

                        })

                    }
                })

            }

            private fun getsub(data2: sudvhandler){
                val spinner2subdid = view!!.findViewById<TextView>(R.id.sub2_step3)
                spinner2subdid.setText(data2.subvid)
            }

        })

    }

}
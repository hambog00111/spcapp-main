package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent.getIntent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.bc_bplo_fillup_step2_fragment.*
import okhttp3.*
import org.jetbrains.anko.toast
import ph.Sanpablocitygov.iSanPablo.R
import java.io.IOException


class FragmentBPLOstep2 : Fragment()  , AdapterView.OnItemSelectedListener{

    class brgyholder( val location_barangay_id : String ,
                      val barangay_name : String)


    class brgyhandler(val kodname : String ,val kodid : String){ override fun toString(): String { return kodname } }

    class subdvlistclass( val location_subdivision_id : String ,
                          val subdivision_name : String)

    class sudvhandler(  val subvname : String, val subvid : String)
    { override fun toString(): String { return subvname } }


    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bc_bplo_fillup_step2_fragment, null)




        //step 1 data hold
        val intent = activity!!.intent
        val applicationtype = intent.getStringExtra("application_type")
        val modeofpayment = intent.getStringExtra("modeofpayment")
        val dateapplication = intent.getStringExtra("dateapplication")
        val dti_sec_cda_reg_num = intent.getStringExtra("dti_sec_cda_reg_num")
        val refnum = intent.getStringExtra("refnum")
        val dti_sec_cda_date_reg = intent.getStringExtra("dti_sec_cda_date_reg")
        val bus_type_id = intent.getStringExtra("bus_type_id")
        val tin_num = intent.getStringExtra("tin_num")
        val gov_incentive = intent.getStringExtra("gov_incentive")
        val mEdit_gov = intent.getStringExtra("mEdit_gov")
        val cctv_equppied = intent.getStringExtra("cctv_equppied")
        val mEditcctv = intent.getStringExtra("mEditcctv")
        val ulastname = intent.getStringExtra("ulastname")
        val ufirstname = intent.getStringExtra("ufirstname")
        val umiddlename = intent.getStringExtra("umiddlename")
        val ubussinessname = intent.getStringExtra("ubussinessname")
        val tradename_franchise = intent.getStringExtra("tradename_franchise")
        val gender = intent.getStringExtra("gender")


        //STEP 2 id

        val buss_address=view.findViewById<EditText>(R.id.txt_bplo_business_info_business_address)
        val building_name=view.findViewById<EditText>(R.id.txt_bplo_business_info_building_name)
        val street=view.findViewById<EditText>(R.id.txt_bplo_business_info_street)
        val brgy_step2=view.findViewById<TextView>(R.id.ad)
        val sub_step2=view.findViewById<TextView>(R.id.sub)
        val house_no=view.findViewById<EditText>(R.id.txt_bplo_business_info_house_num)
        val city_step2=view.findViewById<EditText>(R.id.txt_bplo_business_info_city)
        val contact_step2=view.findViewById<EditText>(R.id.txt_bplo_business_info_contact_num)
        val unit_no_step2=view.findViewById<EditText>(R.id.txt_bplo_business_info_unit_num)
        val province_step2=view.findViewById<EditText>(R.id.txt_bplo_business_info_province)
        val pin_step2=view.findViewById<EditText>(R.id.txt_bplo_business_info_pin)
        val email_address_step2=view.findViewById<EditText>(R.id.txt_bplo_business_info_email_address)


//        Toast.makeText(activity!!,applicationtype,Toast.LENGTH_SHORT).show()

        val btnnext = view.findViewById<Button>(R.id.txt_bplo_next_page)
        btnnext.setOnClickListener {
            val intent = activity!!.intent

            //STEP 1
            intent.putExtra("application_type", applicationtype)
            intent.putExtra("modeofpayment", modeofpayment)
            intent.putExtra("dateapplication", dateapplication)
            intent.putExtra("dti_sec_cda_reg_num", dti_sec_cda_reg_num)
            intent.putExtra("refnum",refnum)
            intent.putExtra("dti_sec_cda_date_reg", dti_sec_cda_date_reg)
            intent.putExtra("bus_type_id", bus_type_id)
            intent.putExtra("tin_num", tin_num)
            intent.putExtra("gov_incentive", gov_incentive)
            intent.putExtra("mEdit_gov", mEdit_gov)
            intent.putExtra("cctv_equppied", cctv_equppied)
            intent.putExtra("mEditcctv", mEditcctv)
            intent.putExtra("ulastname", ulastname)
            intent.putExtra("ufirstname",ufirstname)
            intent.putExtra("umiddlename",umiddlename)
            intent.putExtra("ubussinessname",ubussinessname)
            intent.putExtra("tradename_franchise",tradename_franchise)
            intent.putExtra("gender",gender)

             //STEP 2
            intent.putExtra("buss_address",buss_address.text.toString())
            intent.putExtra("building_name",building_name.text.toString())
            intent.putExtra("street",street.text.toString())
            intent.putExtra("brgy_step2",brgy_step2.text.toString())
            intent.putExtra("sub_step2",sub_step2.text.toString())
            intent.putExtra("house_no",house_no.text.toString())
            intent.putExtra("city_step2",city_step2.text.toString())
            intent.putExtra("contact_step2",contact_step2.text.toString())
            intent.putExtra("unit_no_step2",unit_no_step2.text.toString())
            intent.putExtra("province_step2",province_step2.text.toString())
            intent.putExtra("pin_step2",pin_step2.text.toString())
            intent.putExtra("email_address_step2",email_address_step2.text.toString())

            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep3() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnback = view.findViewById<Button>(R.id.txt_bplo_back)
        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep1() , null)
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
                    spinner = activity!!.spinner_barangay
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
                val spinner1id = view!!.findViewById<TextView>(R.id.ad)
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


                            spinnersub = activity!!.spinner_sub
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
                val spinner2subdid = view!!.findViewById<TextView>(R.id.sub)
                spinner2subdid.setText(data2.subvid)
            }

        })

        return view
    }



    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }
}
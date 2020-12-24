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

         //step3 id
        val sqm_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_business_area)

        val nummale = view.findViewById<EditText>(R.id.txt_bplo_owners_info_total_male)
        val numfemale = view.findViewById<EditText>(R.id.txt_bplo_owners_info_total_female)
        val unit_vehicle = view.findViewById<EditText>(R.id.txt_bplo_owners_info_unit_service)
        val totalnum = view.findViewById<EditText>(R.id.txt_bplo_owners_info_total_num_employees_lgu)

        //owners info
        val lname_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_lname)
        val fname_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_fname)
        val mname_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_mname)
        val owner_address_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_owners_address)
        val building_name_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_owners_name)
        val owners_street_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_street)
        val owners_brgy_step3 = view.findViewById<TextView>(R.id.brgy_step3)
        val owners_sub_step3 = view.findViewById<TextView>(R.id.sub_step3)

        val owner_house_no_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_house_num)
        val owner_city_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_city)
        val owner_contact_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_contact_num)
        val owner_email_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_email_address)
        val owner_unit_no_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_unit_num)
        val owner_province_step3 = view.findViewById<EditText>(R.id.txt_bplo_owners_info_province)





      //rented info id
        val lastname_step3 = view.findViewById<EditText>(R.id.lname_bplo_step3)
        val firstname_step3 = view.findViewById<EditText>(R.id.fname_bplo_step3)
        val middle_step3 = view.findViewById<EditText>(R.id.mname_bplo_step3)
        val full_add_step3 = view.findViewById<EditText>(R.id.lessor_add_bplo_step3)
        val monthly_rental_step3 = view.findViewById<EditText>(R.id.monthly_rental_bplo_step3)
        val house_no_step3 = view.findViewById<EditText>(R.id.house_number_bplo_step3)
        val brgy_step3 = view.findViewById<TextView>(R.id.brgy2_step3)
        val sub_step3 = view.findViewById<TextView>(R.id.sub2_step3)
        val street_step3 = view.findViewById<EditText>(R.id.street_bplo_step3)
        val city_step3 = view.findViewById<EditText>(R.id.city_bplo_step3)
        val province_step3 = view.findViewById<EditText>(R.id.province_bplo_step3)
        val mobile_step3 = view.findViewById<EditText>(R.id.mobile_bplo_step3)
        val telephone_step3 = view.findViewById<EditText>(R.id.telephone_bplo_step3)
        val email_step3 = view.findViewById<EditText>(R.id.Email_bplo_step3)




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

        //step 2 data hold
       val buss_address= intent.getStringExtra("buss_address")
        val building_name=intent.getStringExtra("building_name")
        val street= intent.getStringExtra("street")
        val brgy_step2 = intent.getStringExtra("brgy_step2")
        val sub_step2 = intent.getStringExtra("sub_step2")
        val house_no =intent.getStringExtra("house_no")
        val city_step2 = intent.getStringExtra("city_step2")
        val contact_step2 = intent.getStringExtra("contact_step2")
        val unit_no_step2 = intent.getStringExtra("unit_no_step2")
        val province_step2 =intent.getStringExtra("province_step2")
        val  pin_step2 = intent.getStringExtra("pin_step2")
        val email_address_step2 =intent.getStringExtra("email_address_step2")


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
            intent.putExtra("buss_address",buss_address)
            intent.putExtra("building_name",building_name)
            intent.putExtra("street",street)
            intent.putExtra("brgy_step2",brgy_step2)
            intent.putExtra("sub_step2",sub_step2)
            intent.putExtra("house_no",house_no)
            intent.putExtra("city_step2",city_step2)
            intent.putExtra("contact_step2",contact_step2)
            intent.putExtra("unit_no_step2",unit_no_step2)
            intent.putExtra("province_step2",province_step2)
            intent.putExtra("pin_step2",pin_step2)
            intent.putExtra("email_address_step2",email_address_step2)

            //STEP 3

            intent.putExtra("sqm_step3",sqm_step3.text.toString())

            intent.putExtra("nummale",nummale.text.toString())
            intent.putExtra("numfemale",numfemale.text.toString())
            intent.putExtra("unit_vehicle",unit_vehicle.text.toString())
            intent.putExtra("totalnum",totalnum.text.toString())

            //owners info
            intent.putExtra("lname_step3",lname_step3.text.toString())
            intent.putExtra("fname_step3",fname_step3.text.toString())
            intent.putExtra("mname_step3",mname_step3.text.toString())
            intent.putExtra("owner_address_step3",owner_address_step3.text.toString())
            intent.putExtra("building_name_step3",building_name_step3.text.toString())
            intent.putExtra("owners_street_step3",owners_street_step3.text.toString())
            intent.putExtra("owners_brgy_step3",owners_brgy_step3.text.toString())
            intent.putExtra("owners_sub_step3",owners_sub_step3.text.toString())
            intent.putExtra("owner_house_no_step3",owner_house_no_step3.text.toString())
            intent.putExtra("owner_city_step3",owner_city_step3.text.toString())
            intent.putExtra("owner_contact_step3",owner_contact_step3.text.toString())
            intent.putExtra("owner_email_step3",owner_email_step3.text.toString())
            intent.putExtra("owner_unit_no_step3",owner_unit_no_step3.text.toString())
            intent.putExtra("owner_province_step3",owner_province_step3.text.toString())


            //rented data pass
            intent.putExtra("lastname_step3",lastname_step3.text.toString())
            intent.putExtra("firstname_step3",firstname_step3.text.toString())
            intent.putExtra("middle_step3",middle_step3.text.toString())
            intent.putExtra("full_add_step3",full_add_step3.text.toString())
            intent.putExtra("monthly_rental_step3",monthly_rental_step3.text.toString())
            intent.putExtra("house_no_step3",house_no_step3.text.toString())
            intent.putExtra("brgy_step3",brgy_step3.text.toString())
            intent.putExtra("sub_step3",sub_step3.text.toString())
            intent.putExtra("street_step3",street_step3.text.toString())
            intent.putExtra("city_step3",city_step3.text.toString())
            intent.putExtra("province_step3",province_step3.text.toString())
            intent.putExtra("mobile_step3",mobile_step3.text.toString())
            intent.putExtra("telephone_step3",telephone_step3.text.toString())
            intent.putExtra("email_step3",email_step3.text.toString())






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
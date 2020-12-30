package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.bc_bplo_fillup_step1_fragment.*
import okhttp3.*
import org.jetbrains.anko.toast
import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Loginbplo.BPLOSTEP1_HANDLER
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FragmentBPLOstep1 : Fragment() {
    class buss_type( val id : String ,
                      val kod : String)

    class buss_handler(val kodname : String ,val kodid : String){ override fun toString(): String { return kodname } }

    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null

    @SuppressLint("InflateParams", "NewApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bc_bplo_fillup_step1_fragment, null)

         val date_of_reg = view!!.findViewById<EditText>(R.id.txt_bplo_date_dti_registration)
        val setdate = view!!.findViewById<EditText>(R.id.txt_bplo_date_application)
//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
//        val formatted = current.format(formatter)
//        setdate!!.setText(formatted)
        val currentDate = DateTime()
        setdate.setText(currentDate)
        reference()
        val btnnextpage = view.findViewById<Button>(R.id.txt_bplo_next_page)



        //val refnum1 =view!!.findViewById<TextView>(R.id.refnums).text.toString()
        //gov taxt incentive id
        val gov_incentive =view.findViewById<TextView>(R.id.tax_incentives)
        val mEdit_gov =view.findViewById<EditText>(R.id.txt_bplo_government_entity)

        //cctv_equipped id
        val cctv_equppied =view.findViewById<TextView>(R.id.cctv_equipped)
        val mEditcctv =view.findViewById<EditText>(R.id.txt_bplo_cctv_number_of_cameras)


        val gender =view.findViewById<TextView>(R.id.gender)

        // gov incentive enjoying function
        val gov_tax_incentive = view!!.findViewById<RadioGroup>(R.id.rg_bplo_government_entity)
        gov_incentive.text="no"
        mEdit_gov.isEnabled = false

        gov_tax_incentive.setOnCheckedChangeListener{ _, checkedId ->
            if (checkedId==R.id.rb_bplo_government_yes){
                gov_incentive.text="yes"
                mEdit_gov.isEnabled = true


            }
            if (checkedId==R.id.rb_bplo_government_no){
                mEdit_gov.isEnabled = false
                mEdit_gov.setText("")
                gov_incentive.text="no"
            }


        }

           // cctv function
        val cctv = view!!.findViewById<RadioGroup>(R.id.rg_bplo_cctv)

        cctv_equppied.text="no"
        mEditcctv.isEnabled = false
        cctv.setOnCheckedChangeListener{ _, checkedId ->
            if (checkedId==R.id.rb_bplo_cctv_yes){
                  cctv_equppied.text="yes"
                mEditcctv.isEnabled = true
            }
            if (checkedId==R.id.rb_cctv_no){
                cctv_equppied.text="no"

                mEditcctv.isEnabled = false
            }
        }

        // gender selection
        val gender_selection = view!!.findViewById<RadioGroup>(R.id.rg_bplo_name_of_taxpayer_owners_gender)

        gender_selection.setOnCheckedChangeListener{ _, checkedId ->
            if (checkedId==R.id.rb_bplo_name_of_tax_payer_male){
                 gender.text="male"
            }
            if (checkedId==R.id.rb_bplo_name_of_tax_payer_female){

                gender.text="female"
            }
            if (checkedId==R.id.rb_bplo_name_of_tax_payer_jp){

                gender.text="jp"
            }
        }


        btnnextpage.setOnClickListener {
          submit()
        }

        date_of_reg.setOnClickListener {

            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE,0)
            val year = cal[Calendar.YEAR]
            val month = cal[Calendar.MONTH]
            val day = cal[Calendar.DAY_OF_MONTH]

            val dialog = DatePickerDialog(
                activity!!,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.datePicker.maxDate = cal.timeInMillis
            dialog.show()
        }

        mDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            var month = month
            month += 1
            Log.d(ContentValues.TAG, "onDateSet: mm/dd/yyy: $month/$day/$year")
            val date = "$month/$day/$year"
            date_of_reg!!.setText(date)
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
            .url("http://www.sanpablocitygov.ph/api/getOrganization")
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
                val list = gson.fromJson(body, Array<buss_type>::class.java).toList()
                val brgylistmain: MutableList<buss_handler> = ArrayList()



                val i =  buss_handler("Select Type", "0" )
                brgylistmain.add(i)

                for (entry in list) {
                    val i =  buss_handler(entry.kod,entry.id )
                    brgylistmain.add(i)
                }

                activity!!.runOnUiThread(java.lang.Runnable {


                    var spinner: Spinner? = null
                    spinner = activity!!.spinner_bplo_association
                    spinner!!.onItemSelectedListener
                    val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, brgylistmain)


                    spinner.adapter = adapter


                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val data = parent.selectedItem as buss_handler
                            getbrgyforsub(data)

                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }
                    }
                })

            }

            //
            private fun getbrgyforsub(data: buss_handler) {
                val spinner1id = view!!.findViewById<TextView>(R.id.type_bus)
                spinner1id.text = data.kodid

            }
            })
        return view
    }

    fun reference(){

        val pdLoading = ProgressDialog(requireContext())
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url("http://www.sanpablocitygov.ph/api/reference")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                pdLoading.dismiss()
                activity!!.runOnUiThread {
                    activity!!.toast("Unable to connect to the server please try again later")
                }
            }

            @SuppressLint("ShowToast")
            override fun onResponse(call: Call, response: Response) {

                pdLoading.dismiss()
                val body = response.body?.string()

                val gson = Gson()

                val Reference = gson.fromJson(body,BPLOSTEP1_HANDLER::class.java)
                println(body)
                activity!!.runOnUiThread(java.lang.Runnable {
                    val refnum = view!!.findViewById<EditText>(R.id.txt_bplo_ref_number)

                    //val refnum1 =view!!.findViewById<TextView>(R.id.refnums)
                   // refnum1.text="${Reference.ref}"
                    refnum.setText(Reference.ref)

                })
            }
        })
    }

    fun submit(){
        val applicationtype = view!!.findViewById<EditText>(R.id.txt_bplo_application_type).text.toString()
        val modeofpayment = view!!.findViewById<EditText>(R.id.rb_mode_of_payment_annually).text.toString()
        val dateapplication = view!!.findViewById<EditText>(R.id.txt_bplo_date_application).text.toString()
        val dti_sec_cda_reg_num = view!!.findViewById<EditText>(R.id.txt_bplo_dti_registration_num).text.toString()
        val refnum = view!!.findViewById<EditText>(R.id.txt_bplo_ref_number).text.toString()
        val dti_sec_cda_date_reg = view!!.findViewById<EditText>(R.id.txt_bplo_date_dti_registration).text.toString()
        val bus_type_id = view!!.findViewById<TextView>(R.id.type_bus).text.toString()
        val tin_num = view!!.findViewById<EditText>(R.id.txt_bplo_tin_num).text.toString()

        val gov_incentive =view!!.findViewById<TextView>(R.id.tax_incentives).text.toString()
        val mEdit_gov =view!!.findViewById<EditText>(R.id.txt_bplo_government_entity)

        //cctv_equipped id
        val cctv_equppied =view!!.findViewById<TextView>(R.id.cctv_equipped).text.toString()
        val mEditcctv =view!!.findViewById<EditText>(R.id.txt_bplo_cctv_number_of_cameras)

        //name of taxpayer registrant
        val ulastname =view!!.findViewById<EditText>(R.id.txt_bplo_name_of_taxpayer_lname).text.toString()
        val ufirstname =view!!.findViewById<EditText>(R.id.txt_bplo_name_of_taxpayer_fname).text.toString()
        val umiddlename =view!!.findViewById<EditText>(R.id.txt_bplo_name_of_taxpayer_mname).text.toString()
        val ubussinessname =view!!.findViewById<EditText>(R.id.txt_bplo_name_of_taxpayer_business_name).text.toString()
        val tradename_franchise =view!!.findViewById<EditText>(R.id.txt_bplo_name_of_taxpayer_trade_name).text.toString()
        val gender =view!!.findViewById<TextView>(R.id.gender)

//        if (dti_sec_cda_reg_num.trim { it <= ' ' }.isEmpty()) {
//            Toast.makeText(activity, "Please enter DTI/SEC/CDA Registration No!", Toast.LENGTH_SHORT).show()
//
//            return
//        }


//        if (dti_sec_cda_date_reg.trim { it <= ' ' }.isEmpty()) {
//            Toast.makeText(activity, "Please Select DTI/SEC/CDA Date of Registration!", Toast.LENGTH_SHORT).show()
//
//            return
//        }

        if (bus_type_id=="0") {
            Toast.makeText(activity, "Please Select Business Type!", Toast.LENGTH_SHORT).show()

            return
        }

//        if (tin_num.trim { it <= ' ' }.isEmpty()) {
//            Toast.makeText(activity, "Please enter your TIN No!", Toast.LENGTH_SHORT).show()
//
//            return
//        }



        if(gov_incentive=="yes"){
            if (mEdit_gov.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Please Specify Entity!", Toast.LENGTH_SHORT).show()

                return
            }

        }

        if(cctv_equppied=="yes"){
            if (mEditcctv.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Please Enter the number of your cctv!", Toast.LENGTH_SHORT).show()

                return
            }

        }


        if (ulastname.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(activity, "Please enter Taxpayer/registrant Last name!", Toast.LENGTH_SHORT).show()

            return
        }

        if (ufirstname.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(activity, "Please enter  Taxpayer/registrant First name!", Toast.LENGTH_SHORT).show()

            return
        }
        if (ubussinessname.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(activity, "Please enter  Taxpayer/registrant Business name!", Toast.LENGTH_SHORT).show()

            return
        }

        if (gender.text.toString()=="") {
            Toast.makeText(activity, "Please Select Gender!", Toast.LENGTH_SHORT).show()

            return
        }else {


            //pass data to step 2 function
            val intent = activity!!.intent
            intent.putExtra("application_type", applicationtype)
            intent.putExtra("modeofpayment", modeofpayment)
            intent.putExtra("dateapplication", dateapplication)
            intent.putExtra("dti_sec_cda_reg_num", dti_sec_cda_reg_num)
            intent.putExtra("refnum", refnum)
            intent.putExtra("dti_sec_cda_date_reg", dti_sec_cda_date_reg)
            intent.putExtra("bus_type_id", bus_type_id)
            intent.putExtra("tin_num", tin_num)
            intent.putExtra("gov_incentive", gov_incentive)
            intent.putExtra("mEdit_gov", mEdit_gov.text.toString())
            intent.putExtra("cctv_equppied", cctv_equppied)
            intent.putExtra("mEditcctv", mEditcctv.text.toString())
            intent.putExtra("ulastname", ulastname)
            intent.putExtra("ufirstname", ufirstname)
            intent.putExtra("umiddlename", umiddlename)
            intent.putExtra("ubussinessname", ubussinessname)
            intent.putExtra("tradename_franchise", tradename_franchise)

            intent.putExtra("gender", gender.text.toString())


            activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentBPLOstep2(), null
                )
                .addToBackStack(null)
                .commit()
        }

    }


    private fun DateTime():String {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("MM/dd/yyyy")
        return df.format(c)
    }



}
package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Renew

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.bc_bplo_renew_fragment.*
import okhttp3.*
import org.jetbrains.anko.toast
import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.BPLOhandler
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOStep4_model
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class FragmentBPLORenew  : Fragment() {

    val addsub = ArrayList<String>()
    var mExampleList2: ArrayList<Fragment_Renew_model>? = null
    private var mAdapter2: Fragment_Renew_Adapter? = null

    private var addLayoutManager: RecyclerView.LayoutManager? = null

    class linebuss_holder( val id : String , val buss_category : String)


    class busline_holder(val buss_category : String ,val id : String){ override fun toString(): String { return buss_category } }

    class buss_type( val b_code : String , val b_sub_category : String)

    class buss_handler(val b_sub_category : String ,val b_code : String){ override fun toString(): String { return b_sub_category } }

    @SuppressLint("InflateParams", "NewApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.bc_bplo_renew_fragment, null)



        //additional sub id
        val busscate2 = view!!.findViewById<TextView>(R.id.addbusline_category_renew)
        val subccate2 = view!!.findViewById<EditText>(R.id.addsubcategory_renew)
        val capital2 = view!!.findViewById<EditText>(R.id.addcapital_renew)
        val buttonInsertsub = view!!.findViewById<Button>(R.id.btn_addbusiness_add_renew)
        val buttonremove = view!!.findViewById<Button>(R.id.btn_addbusiness_remove_renew)
        val buttonsubmit = view!!.findViewById<Button>(R.id.btn_renew_submit)



        getcategory()
        loadData()

        val mRecyclerView2 =view!!.findViewById<RecyclerView>(R.id.add_recyclerview)
        mRecyclerView2.setHasFixedSize(true)
        addLayoutManager = LinearLayoutManager(context)
        mAdapter2 = Fragment_Renew_Adapter(requireContext(),mExampleList2)
        mRecyclerView2.layoutManager = addLayoutManager
        mRecyclerView2.adapter = mAdapter2

        buttonInsertsub.setOnClickListener {

            if(busscate2.text.toString()=="SELECT CATEGORY"){
                Toast.makeText(context, "Please Select Additional Business Category!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (subccate2.text.toString()=="Select SubCategory") {
                Toast.makeText(context, "Please Enter Sub category!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (subccate2.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Sub Category is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (subccate2.text.toString()=="SPECIFY") {
                Toast.makeText(context, "Please SPECIFY your sub Category!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (capital2.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Please Enter your capital ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else{
                setInsertButton2()

            }

        }


        val renew_bin = view!!.findViewById<EditText>(R.id.renew_bin_num)
        val renew_lastname = view!!.findViewById<EditText>(R.id.renew_last_name)
        val renew_fname = view!!.findViewById<EditText>(R.id.renew_first_name)
        val renew_busname = view!!.findViewById<EditText>(R.id.renew_bus_name)
        val renew_mode = view!!.findViewById<EditText>(R.id.renew_mode_of_payment)
        val renew_main_bus = view!!.findViewById<EditText>(R.id.renew_main_bus)
        val renew_main_gross = view!!.findViewById<EditText>(R.id.renew_gross_main_sales)
        //applicant
        val renew_applicant_name = view!!.findViewById<EditText>(R.id.renew_applicant_name)
        val renew_email_add = view!!.findViewById<EditText>(R.id.renew_email_add)
        val renew_contact_num = view!!.findViewById<EditText>(R.id.renew_contact_number)

        buttonsubmit.setOnClickListener {
            if (renew_bin.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "BIN Number is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (renew_lastname.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Last Name is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (renew_fname.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "First Name is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (renew_busname.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Business Name is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (renew_mode.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Mode of Payment is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (renew_main_bus.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Main Business is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (renew_main_gross.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Main Gross Sales is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (renew_applicant_name.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Applicant Name is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (renew_email_add.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Email Address is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (renew_contact_num.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Contact Number is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            else{

                submit()
            }



        }

        buttonremove.setOnClickListener {

            removeitem()
        }

 return view
    }

    @SuppressLint("ShowToast")
    private fun setInsertButton2() {

        val line1 = view!!.findViewById<TextView>(R.id.addbusline_category_renew)
        val line2 = view!!.findViewById<EditText>(R.id.addb_code_renew)
        val line3 = view!!.findViewById<EditText>(R.id.addsubcategory_renew)
        val line4 = view!!.findViewById<EditText>(R.id.addunit_num_renew)
        val line5 = view!!.findViewById<EditText>(R.id.addcapital_renew)

        val text1 = view!!.findViewById<TextView>(R.id.addcount).text.toString()
        if (text1=="5"){
            //  Toast.makeText(activity,"Only One Main line of bussiness is acquired",Toast.LENGTH_SHORT).show()
            Toast.makeText(context,
                Html.fromHtml("<font color='#f74040'><b>" + "Maximum Additional line" + "</b></font>"), Toast.LENGTH_SHORT).show()

        }
        if (line3.text.toString()=="SPECIFY") {
            line3.setText("")
        }

        else {
            insertItem2(
                line1.text.toString(),
                line2.text.toString(),
                line3.text.toString(),
                line4.text.toString(),
                line5.text.toString()

            )

            val capital2 = view!!.findViewById<EditText>(R.id.addcapital_renew)
            val no_unit2 = view!!.findViewById<EditText>(R.id.addunit_num_renew)
            Toast.makeText(activity!!, "successfully added", Toast.LENGTH_SHORT).show()
            getcategory()
            capital2.setText("")
            no_unit2.setText("")

        }
    }


    private fun removeitem(){
        val text1 = view!!.findViewById<TextView>(R.id.addcount).text.toString()
        val list_value2 = view!!.findViewById<TextView>(R.id.list_value2).text.toString()
        if (text1=="0"){
            //  Toast.makeText(activity,"Only One Main line of bussiness is acquired",Toast.LENGTH_SHORT).show()
            Toast.makeText(context,
                Html.fromHtml("<font color='#f74040'><b>" + "Nothing to remove" + "</b></font>"), Toast.LENGTH_SHORT).show()

        }

        else{

            val lastIndex = mExampleList2!!.size -1
            mExampleList2!!.removeAt(lastIndex)
            mAdapter2!!.notifyItemRemoved(lastIndex)

            val list = list_value2
            addsub.remove(list)
            val text = view!!.findViewById<TextView>(R.id.data_1)
            text.text =addsub.toString()
            val listvalue=view!!.findViewById<TextView>(R.id.list_value2)

            for (i in addsub) {
                println(i)
                listvalue.text=i
            }

            val count = view!!.findViewById<TextView>(R.id.addcount)

//            val minus = hh2.size.toString().toInt() - item_count.toInt()
            count.text= addsub.size.toString()
        }


    }

    private fun insertItem2(
        line1: String,
        line2: String,
        line3: String,
        line4: String,

        line5: String
    ) {

        mExampleList2!!.add(Fragment_Renew_model(line1, line2, line3, line4,line5))
        mAdapter2!!.notifyItemInserted(mExampleList2!!.size)

        // val list2= "\"addbname : $line1\",\"addcode : $line2\",\"addsubcat: $line3\",\"addnumunit : $line4\",\"addcapital : $line5\""

        val list2 = "$line1;$line2;$line3;$line4;$line5"
        addsub.add(list2)
        val text = view!!.findViewById<View?>(R.id.data_1) as? TextView
        val text1 = view!!.findViewById<View?>(R.id.addcount) as? TextView
        val listvalue2 = view!!.findViewById<View?>(R.id.list_value2) as? TextView

        val chunked =addsub.chunked(1)
        text?.text =chunked.toString()
        println(chunked)
        for (i in addsub) {

            listvalue2!!.text=i
            text1!!.text=addsub.size.toString()
        }
    }
    private fun loadData() {
        val sharedPreferences = activity!!.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val gson = Gson()

        val json = sharedPreferences.getString("task list", null)
        val type = object : TypeToken<ArrayList<Fragment_Renew_model?>?>() {}.type
        mExampleList2 = gson.fromJson(json, type)
        if (mExampleList2 == null) {
            mExampleList2 = ArrayList()
        }
    }
    fun getcategory(){

        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()

        val okHttpClient = OkHttpClient()
        val formBody: RequestBody = FormBody.Builder()
            .build()

        val request = Request.Builder()
            //.method("GET", formBody)
            .url("http://www.sanpablocitygov.ph/api/getCategory")
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
                val list = gson.fromJson(body, Array<linebuss_holder>::class.java).toList()
                val brgylistmain: MutableList<busline_holder> = ArrayList()



                val i =  busline_holder("SELECT CATEGORY", "0" )
                brgylistmain.add(i)

                for (entry in list) {
                    val i =  busline_holder(entry.buss_category,entry.id )
                    brgylistmain.add(i)
                }

                activity!!.runOnUiThread(java.lang.Runnable {


                    var spinner: Spinner? = null
                    spinner = activity!!.spinner_addsubcate_renew
                    spinner!!.onItemSelectedListener
                    val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, brgylistmain)

                    //adapter.setDropDownViewResource(R.layout.spinnercustomcolor)

                    spinner.adapter = adapter


                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val data = parent.selectedItem as busline_holder
                            getbrgyforsub(data)

                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                    }
                })
            }

            private fun getbrgyforsub(data: busline_holder) {
                val spinner2id = view!!.findViewById<TextView>(R.id.addbusline_category_renew)
                spinner2id.text = data.buss_category
                val okHttpClient = OkHttpClient()
                val formBody: RequestBody = FormBody.Builder()
                    .build()

                val request = Request.Builder()
                    .method("POST", formBody)
                    .url("http://www.sanpablocitygov.ph/api/getSubcategory/"+spinner2id.text)
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

                        val i =  buss_handler("Select SubCategory", "0" )
                        brgylistmain.add(i)

                        for (entry in list) {
                            val i =  buss_handler(entry.b_sub_category,entry.b_code )
                            brgylistmain.add(i)
                        }

                        activity!!.runOnUiThread(java.lang.Runnable {
                            var spinner: Spinner? = null
                            spinner = activity!!.spinner_addlinebuss_renew
                            spinner!!.onItemSelectedListener
                            val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, brgylistmain)
                            spinner!!.adapter = adapter
                            spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                                    val data = parent.selectedItem as buss_handler
                                    getsubcategory(data)

                                }
                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }
                            }
                        })
                    }

                    private fun getsubcategory(data: buss_handler) {
                        val spinnersub = view!!.findViewById<EditText>(R.id.addsubcategory_renew)
                        val code = view!!.findViewById<EditText>(R.id.addb_code_renew)
                        code.setText(data.b_code)



                        spinnersub.setText(data.b_sub_category)
                    }
                })

            }

        })

    }


    fun submit() {

        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()
        val bin_num = view!!.findViewById<EditText>(R.id.renew_bin_num).text.toString()
        val renew_lname = view!!.findViewById<EditText>(R.id.renew_last_name).text.toString()
        val renew_fname = view!!.findViewById<EditText>(R.id.renew_first_name).text.toString()
        val renew_mname = view!!.findViewById<EditText>(R.id.renew_middle_name).text.toString()
        val renew_busname = view!!.findViewById<EditText>(R.id.renew_bus_name).text.toString()
        val renew_mode = view!!.findViewById<EditText>(R.id.renew_mode_of_payment).text.toString()

        val renew_main_bus = view!!.findViewById<EditText>(R.id.renew_main_bus).text.toString()
        val renew_main_gross = view!!.findViewById<EditText>(R.id.renew_gross_main_sales).text.toString()
        val abdata_1 = view!!.findViewById<TextView>(R.id.data_1).text.toString()
        val renew_applicant_name = view!!.findViewById<EditText>(R.id.renew_applicant_name).text.toString()
        val renew_email_add = view!!.findViewById<EditText>(R.id.renew_email_add).text.toString()
        val renew_contact_num = view!!.findViewById<EditText>(R.id.renew_contact_number).text.toString()
        val formBody: RequestBody = FormBody.Builder()

            .add("bin",bin_num)
            .add("lname",renew_lname)
            .add("fname",renew_fname)

            .add("mname",renew_mname)
            .add("bname",renew_busname)
            .add("mode",renew_mode)

            .add("mb_name",renew_main_bus)
            .add("mb_gross",renew_main_gross)
            .add("abdata",abdata_1)

            .add("applicant_name",renew_applicant_name)
            .add("email",renew_email_add)
            .add("contact",renew_contact_num)

            .build()

        val okHttpClient = OkHttpClient()
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
            .readTimeout(5, TimeUnit.MINUTES) // read timeout
        val request = Request.Builder()

            .method("POST", formBody)

            // 192.168.3.208:8000


            .url("http://192.168.3.208:8000/api/renewApplication")

            .build()


        okHttpClient.newCall(request).enqueue(object : Callback {


            override fun onFailure(call: Call, e: IOException) {
                activity!!.runOnUiThread(java.lang.Runnable {
                    activity!!.toast("unable to connect to the server please try again later")

                })
                pdLoading.dismiss()
                println(e)
            }

            @SuppressLint("ShowToast", "SetTextI18n")
            override fun onResponse(call: Call, response: Response) {
                pdLoading.dismiss()
                val body = response.body?.string()
                println(body)
                val gson = Gson()
                val bplo = gson.fromJson(body, RenewHandler::class.java)
                if (bplo.message=="Success") {

                    activity!!.runOnUiThread(Runnable {

                        val dialogBuilder = AlertDialog.Builder(activity!!)
                        dialogBuilder.setMessage("Request for New Business Permit successfully Sent.Please wait within 24 hrs and check your email for confirmation. Thank you!")
                            // if the dialog is cancelable
                            .setCancelable(false)
                            // positive button text and action
                            .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, _ ->

//                                activity!!.supportFragmentManager.beginTransaction().replace(
//                                        R.id.frag_container,
//                                        FragmentBusinessInTheCity(), null)
//                                    .addToBackStack(null)
//                                    .commit()
                                dialog.dismiss()
                            })
                        val alert = dialogBuilder.create()
                        alert.show()

                    })

                }


                else {
                    activity!!.runOnUiThread(Runnable {
                        activity!!.toast("Unable to connect to the server please try again later")

                    })
                }
            }

        })

    }
}
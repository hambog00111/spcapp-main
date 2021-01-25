@file:Suppress("DEPRECATION")

package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html.fromHtml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.bc_bplo_fillup_step4_fragment.*
import okhttp3.*
import org.jetbrains.anko.toast
import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.FragmentBusinessInTheCity
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.random.Random


@Suppress("DEPRECATION", "NAME_SHADOWING", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FragmentBPLOstep4 : Fragment() {

    val hh = ArrayList<String>()
    val hh2 = ArrayList<String>()
    var mExampleList: ArrayList<FragmentBPLOStep4_model>? = null
    var mExampleList2: ArrayList<FragmentBPLOStep4_model>? = null
//    val text: TextView? =null
  //  val text1: TextView? =null

    private var mAdapter: FragmentBPLOstep4_Adapter? = null
    private var mAdapter2: FragmentBPLOstep4_Adapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var addLayoutManager: RecyclerView.LayoutManager? = null

    class linebuss_holder( val id : String , val buss_category : String)


    class busline_holder(val buss_category : String ,val id : String){ override fun toString(): String { return buss_category } }

    class buss_type( val b_code : String , val b_sub_category : String)

    class buss_handler(val b_sub_category : String ,val b_code : String){ override fun toString(): String { return b_sub_category } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bc_bplo_fillup_step4_fragment, null)
        val buttonInsert = view!!.findViewById<Button>(R.id.btn_business_add)
        val buttonremove = view.findViewById<Button>(R.id.btn_business_remove)
        val buttonremove2 = view.findViewById<Button>(R.id.btn_addbusiness_remove)
        val buttonInsertsub = view.findViewById<Button>(R.id.btn_addbusiness_add)
        val buttonExecute = view.findViewById<Button>(R.id.txt_bplo_submit)
        val busscate = view.findViewById<TextView>(R.id.busline_category)
        val subccate = view.findViewById<EditText>(R.id.subcategory_step4)
        val capital = view.findViewById<EditText>(R.id.capital_step4)

        val busscate2 = view.findViewById<TextView>(R.id.addbusline_category)
        val subccate2 = view.findViewById<EditText>(R.id.addsubcategory_step4)
        val capital2 = view.findViewById<EditText>(R.id.addcapital_step4)
        //step 1 data hold

        val text = view.findViewById<TextView>(R.id.text_1)


        loadData()
        loadData2()
        buttonExecute.setOnClickListener {

            if (text!!.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Main Business is Required ", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }else{

                submit()

            }



        }
        buttonremove.setOnClickListener {
           // setremoveButton()
            removeitem()

        }

        buttonremove2.setOnClickListener {

            removeitem2()
        }
        buttonInsert.setOnClickListener {

            if(busscate.text.toString()=="SELECT CATEGORY"){
                Toast.makeText(context, "Please Select Main Business Category!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (subccate.text.toString()=="Select SubCategory") {
                Toast.makeText(context, "Please Enter Sub category!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (subccate.text.toString()=="SPECIFY") {
                Toast.makeText(context, "Please SPECIFY your sub Category!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (subccate.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Sub Category is Required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (capital.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Please Enter your capital ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            else{
                setInsertButton()

            }



        }

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
//        buildRecyclerView()

        val mRecyclerView =view.findViewById<RecyclerView>(R.id.recyclerview)
        mRecyclerView!!.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(context)
        mAdapter = FragmentBPLOstep4_Adapter(requireContext(),mExampleList)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mAdapter


//        mRecyclerView.setOnClickListener {
//            Toast.makeText(context,"try",Toast.LENGTH_SHORT).show()
//
//        }

        val mRecyclerView2 =view.findViewById<RecyclerView>(R.id.add_recyclerview)
        mRecyclerView2.setHasFixedSize(true)
        addLayoutManager = LinearLayoutManager(context)
        mAdapter2 = FragmentBPLOstep4_Adapter(requireContext(),mExampleList2)
        mRecyclerView2.layoutManager = addLayoutManager
        mRecyclerView2.adapter = mAdapter2

        val btnback = view.findViewById<Button>(R.id.txt_bplo_back)
        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep3() , null)
                .addToBackStack(null)
                .commit()
        }


        getcategory()
        getcategory2()
        return view
    }

    fun getcategory(){

        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()

        val okHttpClient = OkHttpClient()
//        val formBody: RequestBody = FormBody.Builder()
//            .build()

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

                activity!!.runOnUiThread {

                    var spinner: Spinner? = null
                    spinner = activity!!.spinner_linebuss_step4
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
                }
            }

            private fun getbrgyforsub(data: busline_holder) {
                val spinner1id = view!!.findViewById<TextView>(R.id.busline_category)
                spinner1id.text = data.buss_category
                val okHttpClient = OkHttpClient()
                val formBody: RequestBody = FormBody.Builder()
                    .build()

                val request = Request.Builder()
                    .method("POST", formBody)
                    .url("http://www.sanpablocitygov.ph/api/getSubcategory/"+spinner1id.text)
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

                        activity!!.runOnUiThread {
                            var spinner: Spinner? = null
                            spinner = activity!!.spinner_subcate_step4
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
                        }
                    }

                    private fun getsubcategory(data: buss_handler) {
                        val spinnersub = view!!.findViewById<EditText>(R.id.subcategory_step4)
                        val code = view!!.findViewById<EditText>(R.id.b_code_step4)
                        code.setText(data.b_code)

                            spinnersub.setText(data.b_sub_category)

                    }
                })

            }

        })

    }


//    @SuppressLint("ShowToast")
//    private fun setremoveButton() {
//
//
//        val line1 = view!!.findViewById<TextView>(R.id.busline_category)
//        val line2 = view!!.findViewById<EditText>(R.id.b_code_step4)
//        val line3 = view!!.findViewById<EditText>(R.id.subcategory_step4)
//        val line4 = view!!.findViewById<EditText>(R.id.unit_num_step_4)
//        val line5 = view!!.findViewById<EditText>(R.id.capital_step4)
//
//        val count= hh.size.toString()
//        if (count=="0"){
//            //  Toast.makeText(activity,"Only One Main line of bussiness is acquired",Toast.LENGTH_SHORT).show()
//            Toast.makeText(context, fromHtml("<font color='#f74040'><b>"+"Nothing to remove"+"</b></font>"), Toast.LENGTH_SHORT).show()
//
//        }
//
//        else {
//            removeItem(
//                line1.text.toString(),
//                line2.text.toString(),
//                line3.text.toString(),
//                line4.text.toString(),
//                line5.text.toString())
//             val countitem = view!!.findViewById<TextView>(R.id.count)
//            countitem.text = "0"
//         }
//
//    }



//    @SuppressLint("ShowToast")
//    private fun setremoveButton2() {
//
//
//        val line1 = view!!.findViewById<TextView>(R.id.addbusline_category)
//        val line2 = view!!.findViewById<EditText>(R.id.addb_code_step4)
//        val line3 = view!!.findViewById<EditText>(R.id.addsubcategory_step4)
//        val line4 = view!!.findViewById<EditText>(R.id.addunit_num_step_4)
//        val line5 = view!!.findViewById<EditText>(R.id.addcapital_step4)
//
//
//        val count= hh2.size.toString()
////      if(count=="1"){
////
////            val countitem = view!!.findViewById<TextView>(R.id.addcount)
//////            val itemlist = view!!.findViewById<TextView>(R.id.text1)
//////            itemlist.text = ""
////            countitem.text = "0"
////
////        }   else
//
//          if (count=="0"){
//            //  Toast.makeText(activity,"Only One Main line of bussiness is acquired",Toast.LENGTH_SHORT).show()
//            Toast.makeText(context, fromHtml("<font color='#f74040'><b>"+"Nothing to remove"+"</b></font>"), Toast.LENGTH_SHORT).show()
//
//        }
//
//        else {
//            removeItem2(
//                line1.text.toString(),
//                line2.text.toString(),
//                line3.text.toString(),
//                line4.text.toString(),
//                line5.text.toString())
//            val countitem = view!!.findViewById<TextView>(R.id.addcount)
//            countitem.text =hh2.size.toString()
//        }
//
//    }

    @SuppressLint("ShowToast")
    private fun setInsertButton() {


            val line1 = view!!.findViewById<TextView>(R.id.busline_category)
            val line2 = view!!.findViewById<EditText>(R.id.b_code_step4)
            val line3 = view!!.findViewById<EditText>(R.id.subcategory_step4)
            val line4 = view!!.findViewById<EditText>(R.id.unit_num_step_4)
             val line5 = view!!.findViewById<EditText>(R.id.capital_step4)


//            if (line1!!.text.toString().trim { it <= ' ' }.isEmpty()) {
//                Toast.makeText(this, "Please enter first name!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if (line3!!.text.toString().trim { it <= ' ' }.isEmpty()) {
//                Toast.makeText(this, "Please enter last name!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if (line7!!.text.toString().trim { it <= ' ' }.isEmpty()) {
//                Toast.makeText(this, "Please select your birthday!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//
//            if (line6!!.text.toString() == "--Select relationship--") {
//                Toast.makeText(
//                    this,
//                    "Please enter your relation with this person!",
//                    Toast.LENGTH_SHORT
//                ).show()
//                return@setOnClickListener
//            }
           // val text1 = view!!.findViewById<TextView>(R.id.count).text.toString()

            val count= hh.size.toString()
            if (count=="1"){
             //  Toast.makeText(activity,"Only One Main line of bussiness is acquired",Toast.LENGTH_SHORT).show()
                Toast.makeText(context, fromHtml("<font color='#f74040'><b>"+"Only One Main line of Business is Acquired"+"</b></font>"), Toast.LENGTH_SHORT).show()

            }

            else {
                insertItem(
                    line1.text.toString(),
                    line2.text.toString(),
                    line3.text.toString(),
                    line4.text.toString(),
                   line5.text.toString()

                )
                val capital = view!!.findViewById<EditText>(R.id.capital_step4)
                val no_unit = view!!.findViewById<EditText>(R.id.unit_num_step_4)
                Toast.makeText(activity!!, "successfully added", Toast.LENGTH_SHORT).show()
                       getcategory()
                        capital.setText("")
                        no_unit.setText("")





//                val fname = findViewById<EditText>(R.id.f_name)
//                val mname = findViewById<EditText>(R.id.m_name)
//                val lname = findViewById<EditText>(R.id.l_name)
//                val c_number = findViewById<EditText>(R.id.c_number)
//                val birthday= findViewById<TextView>(R.id.bd)
//                val suffix = findViewById<EditText>(R.id.suffix)
//                val relationship = findViewById<TextView>(R.id.relationship)


//                fname.text.clear()
//                mname.text.clear()
//                lname.text.clear()
//                suffix.text.clear()
//                c_number.text.clear()
//                birthday.text = ""
//                relationship.text = "Choose relationship"

            }


    }


    @SuppressLint("ShowToast")
    private fun setInsertButton2() {


        val line1 = view!!.findViewById<TextView>(R.id.addbusline_category)
        val line2 = view!!.findViewById<EditText>(R.id.addb_code_step4)
        val line3 = view!!.findViewById<EditText>(R.id.addsubcategory_step4)
        val line4 = view!!.findViewById<EditText>(R.id.addunit_num_step_4)
        val line5 = view!!.findViewById<EditText>(R.id.addcapital_step4)


//            if (line1!!.text.toString().trim { it <= ' ' }.isEmpty()) {
//                Toast.makeText(this, "Please enter first name!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if (line3!!.text.toString().trim { it <= ' ' }.isEmpty()) {
//                Toast.makeText(this, "Please enter last name!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if (line7!!.text.toString().trim { it <= ' ' }.isEmpty()) {
//                Toast.makeText(this, "Please select your birthday!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//
//            if (line6!!.text.toString() == "--Select relationship--") {
//                Toast.makeText(
//                    this,
//                    "Please enter your relation with this person!",
//                    Toast.LENGTH_SHORT
//                ).show()
//                return@setOnClickListener
//            }
        val text1 = view!!.findViewById<TextView>(R.id.addcount).text.toString()
        if (text1=="5"){
            //  Toast.makeText(activity,"Only One Main line of bussiness is acquired",Toast.LENGTH_SHORT).show()
            Toast.makeText(context, fromHtml("<font color='#f74040'><b>" + "Maximum Additional line" + "</b></font>"), Toast.LENGTH_SHORT).show()

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

            val capital2 = view!!.findViewById<EditText>(R.id.addcapital_step4)
            val no_unit2 = view!!.findViewById<EditText>(R.id.addunit_num_step_4)
            Toast.makeText(activity!!, "successfully added", Toast.LENGTH_SHORT).show()
            getcategory2()
            capital2.setText("")
            no_unit2.setText("")

        }


    }

    private fun loadData() {
        val sharedPreferences = activity!!.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val gson = Gson()

        val json = sharedPreferences.getString("task list", null)
        val type = object : TypeToken<ArrayList<FragmentBPLOStep4_model?>?>() {}.type
     mExampleList = gson.fromJson(json, type)
        if (mExampleList == null) {
            mExampleList = ArrayList()
        }
    }



    private fun loadData2() {
        val sharedPreferences = activity!!.getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val gson = Gson()

        val json = sharedPreferences.getString("task list", null)
        val type = object : TypeToken<ArrayList<FragmentBPLOStep4_model?>?>() {}.type
        mExampleList2 = gson.fromJson(json, type)
        if (mExampleList2 == null) {
            mExampleList2 = ArrayList()
        }
    }

    private fun insertItem2(
        line1: String,
        line2: String,
        line3: String,
        line4: String,

       line5: String
    ) {

        mExampleList2!!.add(FragmentBPLOStep4_model(line1, line2, line3, line4,line5))
            mAdapter2!!.notifyItemInserted(mExampleList2!!.size)


        val list2 = "$line1;$line2;$line3;$line4;$line5"
        hh2.add(list2)
        val text = view!!.findViewById<View?>(R.id.text1) as? TextView
        val text1 = view!!.findViewById<View?>(R.id.addcount) as? TextView
        val listvalue2 = view!!.findViewById<View?>(R.id.list_value2) as? TextView

        val chunked =hh2.chunked(1)
        text?.text =chunked.toString()
        println(chunked)
        for (i in hh2) {
          //  println(i)
//            println("Total elements in the list by count = "+ hh.size)
            listvalue2!!.text=i
            text1!!.text=hh2.size.toString()
        }
    }
    private fun insertItem(line1: String, line2: String, line3: String, line4: String, line5: String) {
        mExampleList!!.add(FragmentBPLOStep4_model(line1, line2, line3, line4,line5))
        mAdapter!!.notifyItemInserted(mExampleList!!.size)

        println(mExampleList)
        val list = "$line1;$line2;$line3;$line4;$line5"
        hh.add(list)
       val text = view!!.findViewById<View?>(R.id.text_1) as? TextView
        val text1 = view!!.findViewById<View?>(R.id.count) as? TextView
        val list_value = view!!.findViewById<View?>(R.id.list_value) as? TextView



        text?.text = hh.toString()
        text1?.text = hh.size.toString()
        for (i in hh) {
            println(i)
            list_value!!.text=i
        }
    }

    private fun removeitem(){
        val text1 = view!!.findViewById<TextView>(R.id.count).text.toString()
        val list_value = view!!.findViewById<TextView>(R.id.list_value).text.toString()
        if (text1=="0"){
            Toast.makeText(context, fromHtml("<font color='#f74040'><b>"+"Nothing to remove"+"</b></font>"), Toast.LENGTH_SHORT).show()

        }else{

         //   val index = Random.nextInt(1)
            val lastIndex = mExampleList!!.size -1
            mExampleList!!.removeAt(lastIndex)
            mAdapter!!.notifyItemRemoved(lastIndex)

            val list = list_value
            hh.remove(list)
            val text = view!!.findViewById<TextView>(R.id.list_value)
            text.text =""
            val listvalue=view!!.findViewById<TextView>(R.id.text_1)
            listvalue.text=hh.toString()
            val count = view!!.findViewById<TextView>(R.id.count)
            count.text="0"
        }


    }


    private fun removeitem2(){
        val text1 = view!!.findViewById<TextView>(R.id.addcount).text.toString()
        val list_value2 = view!!.findViewById<TextView>(R.id.list_value2).text.toString()
        if (text1=="0"){
            Toast.makeText(context, fromHtml("<font color='#f74040'><b>"+"Nothing to remove"+"</b></font>"), Toast.LENGTH_SHORT).show()

        }

        else{

          //  val index = Random.nextInt(1)
            val lastIndex = mExampleList2!!.size -1
            mExampleList2!!.removeAt(lastIndex)
            mAdapter2!!.notifyItemRemoved(lastIndex)

            val list = list_value2
            hh2.remove(list)
            val text = view!!.findViewById<TextView>(R.id.text1)
            text.text =hh2.toString()
            val listvalue=view!!.findViewById<TextView>(R.id.list_value2)

            for (i in hh2) {
                println(i)
                listvalue.text=i
            }

            val count = view!!.findViewById<TextView>(R.id.addcount)
//            val item_count ="1"
////            val minus = hh2.size.toString().toInt() - item_count.toInt()
              count.text= hh2.size.toString()
        }


    }

    fun getcategory2(){

        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()

        val okHttpClient = OkHttpClient()
//        val formBody: RequestBody = FormBody.Builder()
//            .build()

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

                activity!!.runOnUiThread {


                    var spinner: Spinner? = null
                    spinner = activity!!.spinner_addlinebuss_step4
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
                }
            }

            private fun getbrgyforsub(data: busline_holder) {
                val spinner2id = view!!.findViewById<TextView>(R.id.addbusline_category)
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

                        activity!!.runOnUiThread {
                            val spinner: Spinner? = activity!!.spinner_addsubcate_step4
                            spinner!!.onItemSelectedListener
                            val adapter = ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, brgylistmain)
                            spinner.adapter = adapter
                            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                                    val data = parent.selectedItem as buss_handler
                                    getsubcategory(data)

                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }
                            }
                        }
                    }

                    private fun getsubcategory(data: buss_handler) {
                        val spinnersub = view!!.findViewById<EditText>(R.id.addsubcategory_step4)
                        val code = view!!.findViewById<EditText>(R.id.addb_code_step4)
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


    val intent = activity!!.intent
    val applicationtype = (  activity!!.intent as Intent).getStringExtra("application_type")
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
//step 3
    val  sqm_step3=intent.getStringExtra("sqm_step3")

    val  nummale= intent.getStringExtra("nummale")
    val  numfemale=intent.getStringExtra("numfemale")
    val unit_vehicle =intent.getStringExtra("unit_vehicle")
    val totalnum =intent.getStringExtra("totalnum")


    //owners info step 3
    val lname_step3 =intent.getStringExtra("lname_step3")
    val fname_step3 =intent.getStringExtra("fname_step3")
    val mname_step3 =intent.getStringExtra("mname_step3")
    val owner_address_step3 =intent.getStringExtra("owner_address_step3")
    val building_name_step3 =intent.getStringExtra("building_name_step3")
    val owners_street_step3 =intent.getStringExtra("owners_street_step3")
    val  owners_brgy_step3=intent.getStringExtra("owners_brgy_step3")
    val owners_sub_step3 =intent.getStringExtra("owners_sub_step3")
    val  owner_house_no_step3=intent.getStringExtra("owner_house_no_step3")
    val owner_city_step3 =intent.getStringExtra("owner_city_step3")
    val owner_contact_step3 =intent.getStringExtra("owner_contact_step3")
    val owner_email_step3 =intent.getStringExtra("owner_email_step3")
    val owner_unit_no_step3 =intent.getStringExtra("owner_unit_no_step3")
    val owner_province_step3 =intent.getStringExtra("owner_province_step3")





    //rented data pass step 3
    val  lastname_step3=intent.getStringExtra("lastname_step3")
    val  firstname_step3=intent.getStringExtra("firstname_step3")
    val  middle_step3= intent.getStringExtra("middle_step3")
    val  full_add_step3=intent.getStringExtra("full_add_step3")
    val monthly_rental_step3 =intent.getStringExtra("monthly_rental_step3")
    val house_no_step3 =intent.getStringExtra("house_no_step3")
    val  brgy_step3=intent.getStringExtra("brgy_step3")
    val sub_step3 =intent.getStringExtra("sub_step3")
    val street_step3 =intent.getStringExtra("street_step3")
    val city_step3 =intent.getStringExtra("city_step3")
    val province_step3 =intent.getStringExtra("province_step3")
    val mobile_step3 =intent.getStringExtra("mobile_step3")
    val telephone_step3 =intent.getStringExtra("telephone_step3")
    val email_step3 =intent.getStringExtra("email_step3")
   // val text = view!!.findViewById<TextView>(R.id.list_value2).text.toString()
    val text = view!!.findViewById<TextView>(R.id.text_1).text.toString()
    val text2 = view!!.findViewById<TextView>(R.id.text1).text.toString()
    val formBody: RequestBody = FormBody.Builder()

        //step 1
        .add("application",applicationtype)
        .add("paymentmode",modeofpayment)
        .add("dateApplication",dateapplication)
        .add("ref_number",refnum)
        .add("business_type",bus_type_id)
        .add("incentive",gov_incentive)
        .add("tax_incentive_desc",mEdit_gov)
        .add("dsc_registration_num",dti_sec_cda_reg_num)
        .add("dscDate",dti_sec_cda_date_reg)
        .add("tin_no",tin_num)
        .add("camera",cctv_equppied)
        .add("camera_count",mEditcctv)
        .add("registrant_Lname",ulastname)
        .add("registrant_Fname",ufirstname)
        .add("registrant_Mname",umiddlename)
        .add("registrant_Bname",ubussinessname)
        .add("registrant_franchise",tradename_franchise)
        .add("registrant_gender",gender)
         //step 2
        .add("business_address",buss_address)
        .add("house_no_bldg_no",house_no)
        .add("building_name",building_name)
        .add("unit_no",unit_no_step2)
        .add("street",street)
        .add("biBarangay",brgy_step2)
        .add("biSubdivision",sub_step2)
        .add("city_municipality",city_step2)
        .add("province",province_step2)
        .add("contact_no",contact_step2)
        .add("email_address",email_address_step2)
        .add("pin",pin_step2)

        //step 3 owners info
        .add("owner_lname",lname_step3)
        .add("owner_fname",fname_step3)
        .add("owner_mname",mname_step3)
        .add("owner_address",owner_address_step3)
        .add("owner_house_no_bldg_no",owner_house_no_step3)
        .add("owner_building_name",building_name_step3)
        .add("owner_unit_no",owner_unit_no_step3)
        .add("owner_street",owners_street_step3)
        .add("oiBarangay",owners_brgy_step3)
        .add("oiSubdivision",owners_sub_step3)
        .add("owner_city",owner_city_step3)
        .add("owner_province",owner_province_step3)
        .add("owner_contact_no",owner_contact_step3)
        .add("owner_email",owner_email_step3)

        //step 3 Bussiness area
        .add("business_area",sqm_step3)
        .add("totalMale",nummale)
        .add("totalFemale",numfemale)
        .add("service_vehicle",unit_vehicle)
        .add("totalEmployee",totalnum)

        //step 3 rented lessor info
        .add("lessor_lname",lastname_step3)
        .add("lessor_fname",firstname_step3)
        .add("lessor_mname",middle_step3)
        .add("lessor_address",full_add_step3)
        .add("monthly_rental",monthly_rental_step3)
        .add("lessor_house_no_bldg_no",house_no_step3)

        .add("liBarangay",brgy_step3)
        .add("liSubdivision",sub_step3)

        .add("lessor_street",street_step3)
        .add("lessor_city",city_step3)
        .add("lessor_province",province_step3)
        .add("lessor_number",mobile_step3)
        .add("lessor_telephone",telephone_step3)
        .add("lessor_email",email_step3)

            //step4
        .add("mbdata",text)
        .add("abdata",text2)
        .build()

    val okHttpClient = OkHttpClient()
    val builder = OkHttpClient.Builder()
    builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
        .writeTimeout(5, TimeUnit.MINUTES) // write timeout
        .readTimeout(5, TimeUnit.MINUTES) // read timeout
    val request = Request.Builder()

        .method("POST", formBody)

    // 192.168.3.208:8000


        .url("http://www.sanpablocitygov.ph/api/addApplication")

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
            val bplo = gson.fromJson(body, BPLOhandler::class.java)
            if (bplo.message=="Success") {

                activity!!.runOnUiThread(Runnable {

                    val dialogBuilder = AlertDialog.Builder(activity!!)
                    dialogBuilder.setMessage("Request for New Business Permit successfully Sent.Please wait within 24 hrs and check your email for confirmation. Thank you!")
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, _ ->

                            activity!!.supportFragmentManager.beginTransaction().replace(
                                    R.id.frag_container,
                                    FragmentBusinessInTheCity(), null)
                                .addToBackStack(null)
                                .commit()
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


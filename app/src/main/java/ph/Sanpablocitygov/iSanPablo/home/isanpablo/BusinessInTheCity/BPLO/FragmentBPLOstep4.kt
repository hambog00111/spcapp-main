package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.Html.*
import android.view.Gravity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.firebase.ui.auth.AuthUI.getApplicationContext
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.bc_bplo_fillup_step4_fragment.*
import okhttp3.*
import org.jetbrains.anko.toast
import org.w3c.dom.Text
import ph.Sanpablocitygov.iSanPablo.R
import java.io.IOException
import kotlin.random.Random

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
        val buttonremove = view!!.findViewById<Button>(R.id.btn_business_remove)
        val buttonremove2 = view!!.findViewById<Button>(R.id.btn_addbusiness_remove)
        val buttonInsertsub = view!!.findViewById<Button>(R.id.btn_addbusiness_add)

       // val itemlist = activity!!.intent.getStringExtra("itemList")

        loadData()
        loadData2()

        buttonremove.setOnClickListener {
           // setremoveButton()
            removeitem()

        }

        buttonremove2.setOnClickListener {

            setremoveButton2()
        }
        buttonInsert.setOnClickListener {
            setInsertButton()
        }

        buttonInsertsub.setOnClickListener {

            setInsertButton2()
        }
//        buildRecyclerView()

        val mRecyclerView =view!!.findViewById<RecyclerView>(R.id.recyclerview)
        mRecyclerView!!.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(context)
        mAdapter = FragmentBPLOstep4_Adapter(requireContext(),mExampleList)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mAdapter


//        mRecyclerView.setOnClickListener {
//            Toast.makeText(context,"try",Toast.LENGTH_SHORT).show()
//
//        }

        val mRecyclerView2 =view?.findViewById<RecyclerView>(R.id.add_recyclerview)
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
//        val btnpass = view.findViewById<Button>(R.id.txt_bplo_submit)
//        btnpass.setOnClickListener {
//            activity!!.supportFragmentManager.beginTransaction().replace(
//                R.id.frag_container,
//                FragmentBPLOProfile() , null)
//                .addToBackStack(null)
//                .commit()
//        }

      val btnpass = view.findViewById<Button>(R.id.txt_bplo_submit)
        btnpass.setOnClickListener {
              activity!!.supportFragmentManager.beginTransaction().replace(
              R.id.frag_container,
              FragmentBPLOProfile() , null)
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
        val formBody: RequestBody = FormBody.Builder()
            .build()

        val request = Request.Builder()
            //.method("GET", formBody)
            .url("http://192.168.3.208:8000/api/getCategory")
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
                })
            }

            private fun getbrgyforsub(data: busline_holder) {
                val spinner1id = view!!.findViewById<TextView>(R.id.busline_category)
                spinner1id.text = data.buss_category
                val okHttpClient = OkHttpClient()
                val formBody: RequestBody = FormBody.Builder()
                    .build()

                val request = Request.Builder()
                    .method("POST", formBody)
                    .url("http://192.168.3.208:8000/api/getSubcategory/"+spinner1id.text)
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
                        })
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


    @SuppressLint("ShowToast")
    private fun setremoveButton() {


        val line1 = view!!.findViewById<TextView>(R.id.busline_category)
        val line2 = view!!.findViewById<EditText>(R.id.b_code_step4)
        val line3 = view!!.findViewById<EditText>(R.id.subcategory_step4)
        val line4 = view!!.findViewById<EditText>(R.id.unit_num_step_4)
        val line5 = view!!.findViewById<EditText>(R.id.capital_step4)

        val count= hh.size.toString()
        if (count=="0"){
            //  Toast.makeText(activity,"Only One Main line of bussiness is acquired",Toast.LENGTH_SHORT).show()
            Toast.makeText(context, fromHtml("<font color='#f74040'><b>"+"Nothing to remove"+"</b></font>"), Toast.LENGTH_SHORT).show()

        }

        else {
            removeItem(
                line1.text.toString(),
                line2.text.toString(),
                line3.text.toString(),
                line4.text.toString(),
                line5.text.toString())
             val countitem = view!!.findViewById<TextView>(R.id.count)
            countitem.text = "0"
         }

    }



    @SuppressLint("ShowToast")
    private fun setremoveButton2() {


        val line1 = view!!.findViewById<TextView>(R.id.addbusline_category)
        val line2 = view!!.findViewById<EditText>(R.id.addb_code_step4)
        val line3 = view!!.findViewById<EditText>(R.id.addsubcategory_step4)
        val line4 = view!!.findViewById<EditText>(R.id.addunit_num_step_4)
        val line5 = view!!.findViewById<EditText>(R.id.addcapital_step4)


        val count= hh2.size.toString()
//      if(count=="1"){
//
//            val countitem = view!!.findViewById<TextView>(R.id.addcount)
////            val itemlist = view!!.findViewById<TextView>(R.id.text1)
////            itemlist.text = ""
//            countitem.text = "0"
//
//        }   else

          if (count=="0"){
            //  Toast.makeText(activity,"Only One Main line of bussiness is acquired",Toast.LENGTH_SHORT).show()
            Toast.makeText(context, fromHtml("<font color='#f74040'><b>"+"Nothing to remove"+"</b></font>"), Toast.LENGTH_SHORT).show()

        }

        else {
            removeItem2(
                line1.text.toString(),
                line2.text.toString(),
                line3.text.toString(),
                line4.text.toString(),
                line5.text.toString())
            val countitem = view!!.findViewById<TextView>(R.id.addcount)
            countitem.text =hh2.size.toString()
        }

    }

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
            val text1 = view!!.findViewById<TextView>(R.id.count).text.toString()

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

                Toast.makeText(activity!!, "successfully added", Toast.LENGTH_SHORT).show()

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


        else {
            insertItem2(
                line1.text.toString(),
                line2.text.toString(),
                line3.text.toString(),
                line4.text.toString(),
                line5.text.toString()

            )

            Toast.makeText(activity!!, "successfully added", Toast.LENGTH_SHORT).show()

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


    private fun buildRecyclerView() {


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

        val list2= "$line1,$line2,$line3,$line4,$line5"
        hh2.add(list2)
        val text = view!!.findViewById<View?>(R.id.text1) as? TextView
        val text1 = view!!.findViewById<View?>(R.id.addcount) as? TextView
        text?.text = hh2.toString()
        for (i in hh2) {
            println(i)
//            println("Total elements in the list by count = "+ hh.size)
            text1!!.text=hh2.size.toString()
        }
    }
    private fun insertItem(line1: String, line2: String, line3: String, line4: String, line5: String) {
        mExampleList!!.add(FragmentBPLOStep4_model(line1, line2, line3, line4,line5))
        mAdapter!!.notifyItemInserted(mExampleList!!.size)

        val list = "$line1,$line2,$line3,$line4,$line5"
        hh.add(list)
       val text = view!!.findViewById<View?>(R.id.text_1) as? TextView
        val text1 = view!!.findViewById<View?>(R.id.count) as? TextView
        text?.text = hh.toString()
        for (i in hh) {
            println(i)
//            println("Total elements in the list by count = "+ hh.size)
            text1!!.text=hh.size.toString()
        }
    }

    private fun removeitem(){
        val index = Random.nextInt(1)
        mExampleList!!.removeAt(index)
        mAdapter!!.notifyItemRemoved(index)
//        notifyDataSetChanged(index,mExampleList!!.size)
        val text = view!!.findViewById<TextView>(R.id.text_1)
        text.text = hh.toString()
        val countitem = view!!.findViewById<TextView>(R.id.count)
            countitem.text = hh.size.toString()
    }


    private fun removeItem2(line1: String, line2: String, line3: String, line4: String, line5: String) {
        mExampleList2!!.remove(FragmentBPLOStep4_model(line1, line2, line3, line4,line5))
        mAdapter2!!.notifyItemRemoved(mExampleList2!!.size)
        val list = "$line1,$line2,$line3,$line4,$line5"
        hh2.remove(list)
        val index = Random.nextInt(1)
        mExampleList2!!.removeAt(index)
        mAdapter2!!.notifyItemRemoved(index)

        val text1 = view!!.findViewById<View?>(R.id.addcount) as? TextView
        val text = view!!.findViewById<View?>(R.id.text1) as? TextView
        text?.text = hh2.toString()
        for (i in hh2) {
            println(i)
//            println("Total elements in the list by count = "+ hh.size)

        }
    }
    private fun removeItem(
        line1: String, line2: String, line3: String, line4: String, line5: String) {
        mExampleList!!.remove(FragmentBPLOStep4_model(line1, line2, line3, line4,line5))
        mAdapter!!.notifyItemRemoved(mExampleList!!.size)
        val list = "$line1,$line2,$line3,$line4,$line5"
        hh.remove(list)
        val index = Random.nextInt(1)
        mExampleList!!.removeAt(index)
        mAdapter!!.notifyItemRemoved(index)
        val text = view!!.findViewById<View?>(R.id.text_1) as? TextView
        val text1 = view!!.findViewById<View?>(R.id.count) as? TextView
        text?.text = hh.toString()
        for (i in hh) {
            println(i)
//            println("Total elements in the list by count = "+ hh.size)
            text1!!.text=hh.size.toString()
        }
    }

    fun getcategory2(){

        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()

        val okHttpClient = OkHttpClient()
        val formBody: RequestBody = FormBody.Builder()
            .build()

        val request = Request.Builder()
            //.method("GET", formBody)
            .url("http://192.168.3.208:8000/api/getCategory")
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
                })
            }

            private fun getbrgyforsub(data: busline_holder) {
                val spinner2id = view!!.findViewById<TextView>(R.id.addbusline_category)
                spinner2id.text = data.buss_category
                val okHttpClient = OkHttpClient()
                val formBody: RequestBody = FormBody.Builder()
                    .build()

                val request = Request.Builder()
                    .method("POST", formBody)
                    .url("http://192.168.3.208:8000/api/getSubcategory/"+spinner2id.text)
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
                            spinner = activity!!.spinner_addsubcate_step4
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
                        val spinnersub = view!!.findViewById<EditText>(R.id.addsubcategory_step4)
                        val code = view!!.findViewById<EditText>(R.id.addb_code_step4)
                        code.setText(data.b_code)
                        spinnersub.setText(data.b_sub_category)

                    }
                })

            }

        })

    }



}


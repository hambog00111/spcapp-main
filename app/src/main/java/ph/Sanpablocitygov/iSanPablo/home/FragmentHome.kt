@file:Suppress("DEPRECATION")

package ph.Sanpablocitygov.iSanPablo.home


import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog

import android.app.ProgressDialog

import android.content.Context
import android.content.DialogInterface


import android.content.Intent
import org.jetbrains.anko.toast
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle

import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat


import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*

import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.home_home_fragment.*

import layout.ph.sanpablocitygov.iSanPablo.goverment.FragmentCityHotline
import okhttp3.*

import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.FragmentBusinessInTheCity
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.FragmentCityEmployeesCorner
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes.FragmentMyTaxes
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.GovernmentOnlineServices.FragmentGovermentOnlineServices
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.FragmentMyAppOnlineRequest
import ph.Sanpablocitygov.iSanPablo.home.newsandupdate.CustomAdapter
import ph.Sanpablocitygov.iSanPablo.home.newsandupdate.newsAdapter
import ph.Sanpablocitygov.iSanPablo.home.newsandupdate.newshandler
import ph.Sanpablocitygov.iSanPablo.links.FragmentFBCIO
import ph.Sanpablocitygov.iSanPablo.links.FragmentHomeCSC
import ph.Sanpablocitygov.iSanPablo.links.FragmentHomePhilGEPS
import ph.Sanpablocitygov.iSanPablo.links.FragmentHomePhilJobNet
import java.io.IOException


@Suppress("PLUGIN_WARNING", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FragmentHome : Fragment(){
    var dataList = ArrayList<HashMap<String, String>>()
    private var permissionsRequired = arrayOf( Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val PERMISSION_CALLBACK_CONSTANT = 100

    private var permissionStatus: SharedPreferences? = null

    private lateinit var viewflipperHome: ViewFlipper

    private lateinit var viewflipperEvents: ViewFlipper

    val image = intArrayOf(R.drawable.lake5, R.drawable.lake1, R.drawable.lake4, R.drawable.lake2)

    val imageevent = intArrayOf(R.drawable.event1, R.drawable.event2, R.drawable.event3)
    //internal lateinit var downloadManager: DownloadManager
    //@SuppressLint("InflateParams")

    @SuppressLint("InflateParams", "RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.home_home_fragment, null)
        newsandupdate()


        permissionStatus = activity!!.getSharedPreferences("permissionStatus", Context.MODE_PRIVATE)
        requestPermission()

        viewflipperHome = view.findViewById<View>(R.id.v_flipper) as ViewFlipper
//          val img =view.findViewById(R.id.img) as LinearLayout
//        viewflipperEvents = view.findViewById<View>(R.id.v_flipperevent) as ViewFlipper
        val linear = view.findViewById<LinearLayout>(R.id.linear_buss)
        linear.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lt = view.findViewById<LinearLayout>(R.id.linear_mytaxes)
        lt.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lmap = view.findViewById<LinearLayout>(R.id.linear_myapp)
        lmap.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lch = view.findViewById<LinearLayout>(R.id.linear_hotlines)
        lch.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val lmy = view.findViewById<LinearLayout>(R.id.linear_myisanpablo)
        lmy.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_transition_animation)
        val  gos = view.findViewById<LinearLayout>(R.id.linear_gos)
        gos.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val cc = view.findViewById<LinearLayout>(R.id.linear_cec)
        cc.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
         val seemore = view.findViewById<TextView>(R.id.see_more)

        seemore.setOnClickListener {

            list()

        }
        val cve  = view.findViewById<CardView>(R.id.cv_events)
        cve.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val cvjo  = view.findViewById<CardView>(R.id.cv_job)
        cvjo.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val cvfl  = view.findViewById<CardView>(R.id.cv_links)
        cvfl.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val cvba  = view.findViewById<CardView>(R.id.cv_bids)
        cvba.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)
        val cveng  = view.findViewById<CardView>(R.id.cv_eng)
        cveng.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)



        for (element in image) {
            flip_imagehome(element)
        }


//        for (element in imageevent) {
//            flip_imageevent(element)
//        }

//        viewPagerEvents = view.findViewById<View>(R.id.events_viewpager) as ViewPager
//        val adapterevents = ViewPagerAdapterEvent(requireContext())
//        viewPagerEvents.adapter = adapterevents
//
//        viewPagerHome = view?.findViewById<View>(R.id.home_viewpager) as ViewPager
//        val adapterhome = ViewPageAdapterHome(requireContext())
//        viewPagerHome.adapter = adapterhome

        val btncsc = view.findViewById<Button>(R.id.btn_home_csc)
        btncsc?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentHomeCSC(), null
            )
                .addToBackStack(null)
                .commit()
        }
        val btnphiljob = view.findViewById<Button>(R.id.btn_home_phil_job)
        btnphiljob?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentHomePhilJobNet(), null
            )
                .addToBackStack(null)
                .commit()
        }


        val btnweb = view.findViewById<Button>(R.id.db_home_logo)
        btnweb?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                Fragmentweb(), null
            )
                .addToBackStack(null)
                .commit()
        }

        val btngeps = view.findViewById<Button>(R.id.btn_home_philgeps)
        btngeps?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentHomePhilGEPS(), null)
                .addToBackStack(null)
                .commit()
        }
        val btncio = view.findViewById<Button>(R.id.btn_fb_cio)
        btncio?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentFBCIO()
                , null
            )
                .addToBackStack(null)
                .commit()
        }
        val textcio = view.findViewById<TextView>(R.id.text_fb_cio)
        textcio?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentFBCIO()
                , null
            )
                .addToBackStack(null)
                .commit()
        }

        val textBITC = view.findViewById<TextView>(R.id.btn_business_in_the_city)
        textBITC.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBusinessInTheCity()
                , null
            )
                .addToBackStack(null)
                .commit()
        }

        val textMT = view.findViewById<TextView>(R.id.btn_my_taxes)
        textMT.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentMyTaxes()
                , null
            )
                .addToBackStack(null)
                .commit()
        }

        val textGOS = view.findViewById<TextView>(R.id.btn_gov_online_service)
        textGOS.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentGovermentOnlineServices()
                , null
            )
                .addToBackStack(null)
                .commit()
        }

        val textMAOR = view.findViewById<TextView>(R.id.btn_online_request)
        textMAOR.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentMyAppOnlineRequest()
                , null
            )
                .addToBackStack(null)
                .commit()
        }

        val textCH = view.findViewById<TextView>(R.id.btn_city_hot_lines)
        textCH.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentCityHotline()
                , null
            )
                .addToBackStack(null)
                .commit()
        }

        val textCEC = view.findViewById<TextView>(R.id.btn_city_employees_corner)
        textCEC.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentCityEmployeesCorner()
                , null
            )
                .addToBackStack(null)
                .commit()
        }

//        fun getData() {
//            val conn: HttpURLConnection =
//                URL("localhost:8000/api/getNews1").openConnection() as HttpURLConnection
//            val res = conn.inputStream.bufferedReader().readText()
//            JSONObject(res).getJSONArray("asd")
//        }
//
//        val imageView = ImageView(context)
//        Glide.with(context).load("").into(imageView)
//        img.addView(imageView)




        setBarChart()

        return view

    }


    private fun newsandupdate(){

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url("http://www.sanpablocitygov.ph/api/getNews")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

                activity!!.runOnUiThread {
                    val image = view!!.findViewById<ImageView>(R.id.image)
                    val refresh = view!!.findViewById<Button>(R.id.refresh)
                    val recycler_view = view!!.findViewById<RecyclerView>(R.id.recycler_view)
//                    toast("Unable to connect to the server please try again later")
                    image.visibility = View.VISIBLE
             refresh.visibility = View.VISIBLE
                    recycler_view.visibility= View.GONE


                    refresh.setOnClickListener {
                        newsandupdate()
                        image.visibility = View.GONE
                        refresh.visibility = View.GONE
                        recycler_view .visibility= View.VISIBLE
                    }
                }
            }

            @SuppressLint("ShowToast")
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()

                val gson = Gson()

                val list = gson.fromJson(body, Array<newshandler>::class.java).toList()
                println(list)

                for (entry in list) {
                    val map = HashMap<String, String>()
                    map["id"] = entry.id
                    map["title"] =  entry.title
                    map["subtitle"] = entry.subtitle
                    dataList.add(map)
                    println(map)
                }

                activity!!.runOnUiThread(java.lang.Runnable {
                    val rerecyclerView = view!!.findViewById<RecyclerView?>(R.id.recycler_view)
                    val layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.HORIZONTAL, false)
                    rerecyclerView!!.layoutManager =layoutManager
                    rerecyclerView.adapter = newsAdapter(dataList)


                })

            }
        })

    }




    private fun setBarChart() {
        val pdLoading = ProgressDialog(requireContext())
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()

        val okHttpClient = OkHttpClient()

        val request = Request.Builder()
            .url("https://coronavirus-19-api.herokuapp.com/countries")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
               pdLoading.dismiss()

                activity!!.runOnUiThread {
                    activity!!.toast("Unable to connect to the server please try again later")

                }
                println(e)
            }
            @SuppressLint("ShowToast")
            override fun onResponse(call: Call, response: Response) {
                pdLoading.dismiss()
                val body = response.body?.string()
                val gson = Gson()
                val list = gson.fromJson(body, Array<mainhandler>::class.java).toList()

                for (student in list) {
                    if(student.country=="Philippines") {
                        activity!!.intent.putExtra("cases",student.cases)
                        activity!!. intent.putExtra("deaths",student.deaths)
                        activity!!.intent.putExtra("recovered",student.recovered)
                    }
                }



                activity!!.runOnUiThread {

                    val ca = (  activity!!.intent as Intent).getStringExtra("cases")


                    val de = (activity!!.intent as Intent).getStringExtra("deaths")

                    val re = (activity!!.intent as Intent).getStringExtra("recovered")

                    val float1: Float? = ca.toFloat()
                    val float2: Float? = de.toFloat()
                    val float3: Float? = re.toFloat()
                    val entries = ArrayList<BarEntry>()
                    float1?.let { BarEntry(it, 0) }?.let { entries.add(it) }
                    float2?.let { BarEntry(it, 1) }?.let { entries.add(it) }
                    float3?.let { BarEntry(it, 2) }?.let { entries.add(it) }
                    val barDataSet = BarDataSet(entries, "")

                    val labels = ArrayList<String>()
                    labels.add("Cases")
                    labels.add("Deaths")
                    labels.add("Recovered")
                    val data = BarData(labels, barDataSet)
                    barChart.data = data // set the data and list of lables into chart

                    barChart.setDescription("")  // set the description

                    //      barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)

                    //    barDataSet.color = resources.getColor(R.color.colorRed)


                    barChart.animateY(300)

                }
            }
        })


    }







    private fun requestPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(requireContext(), permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(requireContext(), permissionsRequired[2]) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permissionsRequired[0])
                || ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permissionsRequired[1])
                || ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permissionsRequired[2])) {
                //Show Information about why you need the permission

            } else if (permissionStatus!!.getBoolean(permissionsRequired[0], false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
//                val builder = AlertDialog.Builder(requireContext())
//                builder.setTitle("Need Multiple Permissions")
//                builder.setMessage("This app needs permissions.")
//                builder.setPositiveButton("Grant") { dialog, which ->
//                    dialog.cancel()
//                    sentToSettings = true
//                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//
//                    startActivityForResult(intent, REQUEST_PERMISSION_SETTING)
//                    Toast.makeText(requireContext(), "Go to Permissions to Grant ", Toast.LENGTH_LONG).show()
//                }
             //   builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
              //  builder.show()
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(requireActivity(), permissionsRequired, PERMISSION_CALLBACK_CONSTANT)
            }

            //   txtPermissions.setText("Permissions Required")

            val editor = permissionStatus!!.edit()
            editor.putBoolean(permissionsRequired[0], true)
            editor.apply()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CALLBACK_CONSTANT) {
            //check if all permissions are granted
            var allgranted = false
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    allgranted = true
                } else {
                    allgranted = false
                    break
                }
            }

            if (!allgranted) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permissionsRequired[0]) && !ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permissionsRequired[1]) && !ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), permissionsRequired[2])) {
                    Toast.makeText(requireContext(), "Unable to get Permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }




                private fun flip_imagehome(i: Int) {
                    val view = ImageView(context)
                    view.setBackgroundResource(i)
                    viewflipperHome.addView(view)
                    viewflipperHome.setFlipInterval(5000)
                    viewflipperHome.isAutoStart = true
                    viewflipperHome.setInAnimation(context, android.R.anim.slide_in_left)
                    viewflipperHome.setOutAnimation(context, android.R.anim.slide_out_right)
                }


//                private fun flip_imageevent(i: Int) {
//                    val view = ImageView(context)
//                    view.setBackgroundResource(i)
//                    viewflipperEvents.addView(view)
//                    viewflipperEvents.flipInterval = 7000
//                    viewflipperEvents.isAutoStart = true
//                    viewflipperEvents.setInAnimation(context, android.R.anim.slide_in_left)
//                    viewflipperEvents.setOutAnimation(context, android.R.anim.slide_out_right)
//                }


    @SuppressLint("InflateParams")
    fun list(){


        val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialognewandupdate, null)

        val mBuilder = AlertDialog.Builder(requireContext())
            .setView(mDialogView)

            .setPositiveButton("Ok", DialogInterface.OnClickListener {
                    dialog, _ ->dialog.cancel()
            })
        val  mAlertDialog = mBuilder.show()
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()


            .url("http://www.sanpablocitygov.ph/api/getNews")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity!!.runOnUiThread {
                    activity!!.toast("Unable to connect to the server please try again later")

                }
            }
            @SuppressLint("ShowToast")
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = Gson()
                val list = gson.fromJson(body, Array<newshandler>::class.java).toList()
                println(list)
                for (entry in list) {
                    val map = HashMap<String, String>()
                    map["id"] = entry.id
                    map["title"] =  entry.title
                    map["subtitle"] = entry.subtitle
                    dataList.add(map)
                    println(map)
                }
                activity!!.runOnUiThread {

                    mAlertDialog.findViewById<ListView>(R.id.listView)!!.adapter = CustomAdapter(requireContext(), dataList)

                }


            }
        })


    }

}


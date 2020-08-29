@file:Suppress("DEPRECATION")

package ph.Sanpablocitygov.iSanPablo

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.Service
import android.net.ConnectivityManager
import android.net.NetworkInfo

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.main_full_disclosure_dialogbox.view.*
import kotlinx.android.synthetic.main.update_fragment.view.*
import ph.Sanpablocitygov.iSanPablo.OurGovernment.FragmentOurGoverment
import ph.Sanpablocitygov.iSanPablo.home.FragmentHome
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOonline
import ph.Sanpablocitygov.iSanPablo.links.*
import ph.Sanpablocitygov.iSanPablo.search.FragmentSearch
import ph.Sanpablocitygov.iSanPablo.tourism.FragmentTourism


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var backPressedTime: Long = 0
    private var backToast: Toast? = null


    var context =this
    var connectivity: ConnectivityManager?=null
    var info: NetworkInfo?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
       //

        val webss: Button = findViewById<Button>(R.id.db_home_logo)
        webss.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentSPCWebsite(), null)
                .addToBackStack(null)
                .commit()
        }



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        val navView: NavigationView = findViewById(R.id.nav_view)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
       supportFragmentManager.beginTransaction().replace(
           R.id.frag_container,
           FragmentHome()
       ).commit()

    }




    fun isConnected() : Boolean
    {

        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)as ConnectivityManager
        if(connectivity!=null)
        {
            info= connectivity!!.activeNetworkInfo
            if(info!=null && info!!.state == NetworkInfo.State.CONNECTED) {
                return true

            }

        }
        return false
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))  {
            drawer_layout.closeDrawer(GravityCompat.START)

        }else{

            super.onBackPressed()
        }

    }

//    override fun onBackPressed() {
//        when {
//            drawer_layout.isDrawerOpen(GravityCompat.START) -> {
//                drawer_layout.closeDrawer(GravityCompat.START)
//
//            }
//            backPressedTime + 2000 > System.currentTimeMillis() -> {
//                backToast!!.cancel()
//                super.onBackPressed()
//                return
//            }
//            else -> {
//                backToast = Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT)
//                backToast!!.show()
//            }
//        }
//        backPressedTime = System.currentTimeMillis()
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }

        return when (item.itemId) {
            R.id.search -> {

                search()

                true
            }
            else -> super.onOptionsItemSelected(item)


        }
    }
////
//
//    val police = "09081930819"
//    val emergency = "09089078124"
//    val fire = "09995784943"
//    val PHONE_REQ = 1
//
    // actions on click menu items
//    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
//
////
////        R.id.police_menu -> {
////
////            if (ActivityCompat.checkSelfPermission(
////                    this,
////                    Manifest.permission.CALL_PHONE
////                ) != PackageManager.PERMISSION_GRANTED
////            ) {
////
////                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), PHONE_REQ)
////
////            } else {
////
////                policecall()
////            }
////
////            true
////        }
//
////        R.id.emergency_menu -> {
////
////            if (ActivityCompat.checkSelfPermission(
////                    this,
////                    Manifest.permission.CALL_PHONE
////                ) != PackageManager.PERMISSION_GRANTED
////            ) {
////
////                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), PHONE_REQ)
////            } else {
////                emergencycall()
////            }
////            true
////        }
//
////        R.id.fire_menu -> {
////
////            if (ActivityCompat.checkSelfPermission(
////                    this,
////                    Manifest.permission.CALL_PHONE
////                ) != PackageManager.PERMISSION_GRANTED
////            ) {
////
////                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), PHONE_REQ)
////            } else {
////
////                firecall()
////            }
////            true
////        }
////
//        R.id.action_search -> {
//
//        search()
//
//            true
//        }
//
//
//        else -> {
//            // If we got here, the user's action was not recognized.
//            // Invoke the superclass to handle it.
//            super.onOptionsItemSelected(item)
//        }

//    }

//    private fun policecall() {
//        val callIntent = Intent(Intent.ACTION_CALL)
//        callIntent.data = Uri.parse("tel:" + police)
//
//        startActivity(callIntent)
//    }
//
////    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
////        if(requestCode == PHONE_REQ)policecall()
////
////    }
//
//
    private fun search(){
    supportFragmentManager.beginTransaction().replace(
        R.id.frag_container,
        FragmentSearch(), null)
        .addToBackStack(null)
        .commit()

}
//
//    private fun firecall(){
//        val callIntent = Intent(Intent.ACTION_CALL)
//        callIntent.data = Uri.parse("tel:" + fire)
//
//        startActivity(callIntent)
//    }
//
//    private fun spc_map(){
//        supportFragmentManager.beginTransaction().replace(
//            R.id.frag_container,
//            FragmentGoogleMap()
//        ).commit()
//
//    }
//
//


//    @SuppressLint("CommitTransaction")
    @SuppressLint("WrongConstant", "ShowToast", "InflateParams")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentHome(), null)
                    .addToBackStack(null)
                .commit()


            }
            R.id.nav_our_city -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentOurCity(), null)
                        .addToBackStack(null)
                .commit()

            }
//            R.id.nav_our_barangay -> {
//                supportFragmentManager.beginTransaction().replace(
//                    R.id.frag_container,
//                    FragmentBarangay(), null)
//                    .addToBackStack(null)
//                    .commit()
//
//            }
            R.id.nav_our_government -> {

                if (isConnected()) {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentOurGoverment(), null)
                        .addToBackStack(null)
                        .commit()
                }
                else{

                    Toast.makeText(context,"Please check your internet connection",4000).show()

                }



            }




            R.id.nav_tourism -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentTourism(), null)
                    .addToBackStack(null)
                    .commit()

            }

            R.id.nav_data_privacy ->{
                supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentDataprivacy(), null)
                    .addToBackStack(null)
                    .commit()
            }

            R.id.nav_department_head -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentDepartment(), null)

                    .addToBackStack(null)
                    .commit()
            }

            R.id.nav_full_disclosure -> {

                    val disView = LayoutInflater.from(this).inflate(R.layout.main_full_disclosure_dialogbox, null)
                    val disBuilder = AlertDialog.Builder(this)
                        .setView(disView)
                disBuilder.setCancelable(false)
                    val disDialog = disBuilder.show()

                disView.disclosure_1.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("4th-quarter-SPP")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(this@MainActivity, DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/4th-qtr-SPP.xls")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }

                disView.disclosure_2.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("20-utilization-2018-4th-Quarter")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/20-Uitlization-2018-4th-Quarter.xls")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }


                disView.disclosure_3.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("APP-2019")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/APP-2019.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }


                disView.disclosure_4.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("BID-RESULTS 2018 4th Quarter")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/BID-RESULTS%202018%204th%20Quarter.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }


                disView.disclosure_5.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("SFC-4THQ")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/SCF-4THQ.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }


                disView.disclosure_6.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("SEF-Utilization-2018")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/SEF-Utilization-2018.xls")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }


                disView.disclosure_7.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("Statement of Debt Service 03.24.14")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/Statement%20of%20Debt%20Services%2003.24.14.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }

                disView.disclosure_8.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("Unliquidated-2018")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/Unliquidated-2018.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }

                disView.disclosure_9.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("CDRRMF-12.31.18")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/CDRRMF-12.31.18.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }

                disView.disclosure_10.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("Manpower Complement(DILG)")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/Manpower%20Complement%20(DILG).xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }

                disView.disclosure_11.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("PDAF UTILIZATION")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/PDAF%20UTILIZATION.xls")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  }
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }

                disView.btn_dis_cancel.setOnClickListener {
                    disDialog.dismiss()
                }

            }


//
//            R.id.nav_webview ->{
//                supportFragmentManager.beginTransaction().replace(
//                    R.id.frag_container,
//                    FragmentBrowser(), null)
//                    .addToBackStack(null)
//                    .commit()
//            }

//            R.id.db_home_logo_1 ->{
//                supportFragmentManager.beginTransaction().replace(
//                    R.id.frag_container,
//                    FragmentSPCWebsite(), null)
//                    .addToBackStack(null)
//                    .commit()
//            }





            R.id.nav_bplo ->{

                val mybus = LayoutInflater.from(this).inflate(R.layout.update_fragment, null)

                val mybusBuilder = AlertDialog.Builder(this)
                    .setView(mybus)
                mybusBuilder.setCancelable(false)
                val mybusDialog = mybusBuilder.show()

                mybus.txt_confirm_update.setOnClickListener {
                    mybusDialog.dismiss()
                }

//                supportFragmentManager.beginTransaction().replace(
//                    R.id.frag_container,
//                    FragmentBPLOonline(), null)
//                    .addToBackStack(null)
//                    .commit()
            }

            R.id.nav_govph ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentGOV(), null)

                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_open_data ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentOpenData(), null)

                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_official_gazette ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentOfficialGazette(), null)
                    .addToBackStack(null)
                    .commit()
            }

            R.id.nav_president ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentOfficePresident(), null)

                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_vice_president ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentOfficeVice(), null)

                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_senate ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentSenate(), null)
                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_representatives ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentRepresentatives(), null)
                    .addToBackStack(null)
                    .commit()
        }
//            R.id.nav_representatives ->{
//                supportFragmentManager.beginTransaction().replace(
//                    R.id.frag_container,
//                    FragmentRepresentatives()
//                    , null)
//                    .addToBackStack(null)
//                    .commit()
//            }
            R.id.nav_supreme_court ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentSupremeCourt(), null)

                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_court ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentCourtAppeals(), null)
                    .addToBackStack(null)
                    .commit()
            }
            R.id.nav_sandiganbayan ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentSandiganbayan(), null)

                    .addToBackStack(null)
                    .commit()
            }

            else -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentHome(), null)
                    .addToBackStack(null)
                    .commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}


package ph.sanpablocitygov.iSanPablo

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import ph.sanpablocitygov.iSanPablo.home.FragmentHome
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.dialog_disclosure.view.*

import ph.sanpablocitygov.iSanPablo.OurGovernment.FragmentOurGoverment
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOonline

import ph.sanpablocitygov.iSanPablo.links.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        fab.setOnClickListener { view ->
//            var show: Any = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

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



    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


//
//
//    val police = "09081930819"
//    val emergency = "09089078124"
//    val fire = "09995784943"
//    val PHONE_REQ = 1
//
    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {

//
//        R.id.police_menu -> {
//
//            if (ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.CALL_PHONE
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//
//                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), PHONE_REQ)
//
//            } else {
//
//                policecall()
//            }
//
//            true
//        }

//        R.id.emergency_menu -> {
//
//            if (ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.CALL_PHONE
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//
//                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), PHONE_REQ)
//            } else {
//                emergencycall()
//            }
//            true
//        }

//        R.id.fire_menu -> {
//
//            if (ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.CALL_PHONE
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//
//                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), PHONE_REQ)
//            } else {
//
//                firecall()
//            }
//            true
//        }
//
//        R.id.admin -> {
//
//        spc_map()
//
//            true
//        }


        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }

    }

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
//    private fun emergencycall(){
//        val callIntent =Intent(Intent.ACTION_CALL)
//        callIntent.data = Uri.parse("tel:" + emergency)
//
//        startActivity(callIntent)
//    }
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
            R.id.nav_our_government -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,

                    FragmentOurGoverment(), null)
                    .addToBackStack(null)
                .commit()


            }
            R.id.nav_tourism -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                FragmentTourism() , null)
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

                    val disView = LayoutInflater.from(this).inflate(R.layout.dialog_disclosure, null)
                    val disBuilder = AlertDialog.Builder(this)
                        .setView(disView)
                    val disDialog = disBuilder.show()

                disView.disclosure_1.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("4th-quarter-SPP")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/4th-qtr-SPP.xls")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

                    alertDialog.show()
                }

                disView.disclosure_2.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("20-utilization-2018-4th-Quarter")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/20-Uitlization-2018-4th-Quarter.xls")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

                    alertDialog.show()
                }


                disView.disclosure_3.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("APP-2019")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/APP-2019.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

                    alertDialog.show()
                }


                disView.disclosure_4.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("BID-RESULTS 2018 4th Quarter")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/BID-RESULTS%202018%204th%20Quarter.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

                    alertDialog.show()
                }


                disView.disclosure_5.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("SFC-4THQ")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/SCF-4THQ.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

                    alertDialog.show()
                }


                disView.disclosure_6.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("SEF-Utilization-2018")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/SEF-Utilization-2018.xls")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

                    alertDialog.show()
                }


                disView.disclosure_7.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("Statement of Debt Service 03.24.14")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/Statement%20of%20Debt%20Services%2003.24.14.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

                    alertDialog.show()
                }

                disView.disclosure_8.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("Unliquidated-2018")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/Unliquidated-2018.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

                    alertDialog.show()
                }

                disView.disclosure_9.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("CDRRMF-12.31.18")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/CDRRMF-12.31.18.xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

                    alertDialog.show()
                }

                disView.disclosure_10.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("Manpower Complement(DILG)")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/Manpower%20Complement%20(DILG).xlsx")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

                    alertDialog.show()
                }

                disView.disclosure_11.setOnClickListener {
                    disDialog.dismiss()
                    var str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((this))
                    with(builder) {
                        setMessage(str)
                        setTitle("PDAF UTILIZATION")

                        setPositiveButton("OK", DialogInterface.OnClickListener
                        { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            this@MainActivity,
                            DownloadManager::class.java) as DownloadManager
                            val uri = Uri.parse("http://www.sanpablocitygov.ph/docs/PDAF%20UTILIZATION.xls")
                            val request = DownloadManager.Request(uri)
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            downloadManager.enqueue(request)  })
                        setNegativeButton("CANCEL", null)
                    }
                    val alertDialog = builder.create()

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

//            R.id.nav_webview ->{
//                supportFragmentManager.beginTransaction().replace(
//                    R.id.frag_container,
//                    FragmentBrowser()
//                ).commit()
//            }


            R.id.nav_bplo ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentBPLOonline(), null)
                    .addToBackStack(null)
                    .commit()
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
            R.id.nav_representatives ->{
                supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentRepresentatives()
                    , null)
                    .addToBackStack(null)
                    .commit()
            }
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


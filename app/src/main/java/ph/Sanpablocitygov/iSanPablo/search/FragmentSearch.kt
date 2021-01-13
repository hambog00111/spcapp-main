package ph.sanpablocitygov.iSanPablo.search

import android.annotation.SuppressLint
import android.app.DownloadManager

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.main_full_disclosure_dialogbox.view.*
import layout.ph.sanpablocitygov.iSanPablo.goverment.FragmentCityHotline
import ph.Sanpablocitygov.iSanPablo.FragmentOurCity
import ph.Sanpablocitygov.iSanPablo.OurGovernment.FragmentOurGoverment
import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.FragmentBusinessInTheCity
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.FragmentCityEmployeesCorner
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes.FragmentMyTaxes
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.GovernmentOnlineServices.FragmentGovermentOnlineServices
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.FragmentMyAppOnlineRequest
import ph.Sanpablocitygov.iSanPablo.links.*
import ph.Sanpablocitygov.iSanPablo.search.ListViewAdapter
import ph.Sanpablocitygov.iSanPablo.search.Model
import ph.Sanpablocitygov.iSanPablo.tourism.FragmentTourism


import java.util.ArrayList


class FragmentSearch:Fragment (){

    private lateinit var searchView : SearchView

    internal lateinit var adapter: ListViewAdapter
    private lateinit var title: Array<String>
    private lateinit var icon: IntArray
    private var arrayList = ArrayList<Model>()

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val view = inflater.inflate(R.layout.fragmentsearch, null)


        title = arrayOf("Business in The City", "My Taxes",
            "My app Online Request", "City Hotlines",
            "Government Online Service","City Employee Corner","Our City",
            "Our Government","Full Disclosure","Tourism","GOV.PH","Open Data Portal","Official Gazette"
            ,"Office of the President","Office of the Vice President"
            ,"Senate of the Philippines","House of Representatives","Supreme Court","Court of the Appeals","Sandiganbayan")
        icon = intArrayOf(R.drawable.home_bc_logo, R.drawable.home_mt_logo,
            R.drawable.home_maor_logo, R.drawable.home_ch_logo,
            R.drawable.home_gos_logo, R.drawable.home_cec_logo,R.drawable.ourcity,R.drawable.ourgovernment,
            R.drawable.disclosure,R.drawable.tourism,R.drawable.aboutgov,R.drawable.aboutgov
            ,R.drawable.aboutgov,R.drawable.govlinks,R.drawable.govlinks,R.drawable.govlinks,R.drawable.govlinks,
            R.drawable.govlinks,R.drawable.govlinks,R.drawable.govlinks)
        searchView = view.findViewById(R.id.searchView1) as SearchView

        val  listView = view.findViewById<ListView>(R.id.listView)

        for (i in title.indices) {
            val model = Model(title[i], icon[i])
            //bind all strings in an array
            arrayList.add(model)
        }

        //pass results to listViewAdapter class
        adapter = ListViewAdapter(requireContext(), arrayList)

        //bind the adapter to the listview
        listView.adapter = adapter
//        holder.mTitleTv!!.text = modellist[position].title
        listView.setOnItemClickListener { _, _, position, _ ->
            searchView.setQuery("", false)
            searchView.clearFocus()

            listView.clearTextFilter()


            if (arrayList[position].title == "Business in The City" ) {




                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentBusinessInTheCity(), null)
                    .addToBackStack(null)
                    .commit()
            }




            if (arrayList[position].title == "My Taxes" ) {



                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentMyTaxes(), null)
                    .addToBackStack(null)
                    .commit()
            }

            if (arrayList[position].title == "My app Online Request") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentMyAppOnlineRequest(), null)
                    .addToBackStack(null)
                    .commit()

            }
            if (arrayList[position].title == "City Hotlines") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentCityHotline(), null)
                    .addToBackStack(null)
                    .commit()

            }
            if (arrayList[position].title == "Government Online Service") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentGovermentOnlineServices(), null)
                    .addToBackStack(null)
                    .commit()
            }
            if (arrayList[position].title == "City Employee Corner") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentCityEmployeesCorner(), null)
                    .addToBackStack(null)
                    .commit()
            }
//new entry
            if (arrayList[position].title == "Our City") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentOurCity(), null)
                    .addToBackStack(null)
                    .commit()
            }
            if (arrayList[position].title == "Our Government") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentOurGoverment(), null)
                    .addToBackStack(null)
                    .commit()
            }
            if (arrayList[position].title == "Full Disclosure") {
                //start NewActivity with title for actionbar and text for textview
                val disView = LayoutInflater.from(requireContext()).inflate(R.layout.main_full_disclosure_dialogbox, null)
                val disBuilder = AlertDialog.Builder(requireContext())
                    .setView(disView)
                disBuilder.setCancelable(false)
                val disDialog = disBuilder.show()

                disView.disclosure_1.setOnClickListener {
                    disDialog.dismiss()
                    val str = "Would you like to download this document?"
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("4th-quarter-SPP")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(requireContext(), DownloadManager::class.java) as DownloadManager
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
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("20-utilization-2018-4th-Quarter")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            requireContext(),
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
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("APP-2019")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            requireContext(),
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
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("BID-RESULTS 2018 4th Quarter")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            requireContext(),
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
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("SFC-4THQ")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            requireContext(),
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
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("SEF-Utilization-2018")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            requireContext(),
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
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("Statement of Debt Service 03.24.14")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            requireContext(),
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
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("Unliquidated-2018")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            requireContext(),
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
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("CDRRMF-12.31.18")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            requireContext(),
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
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("Manpower Complement(DILG)")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            requireContext(),
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
                    val builder = AlertDialog.Builder((requireContext()))
                    with(builder) {
                        setMessage(str)
                        setTitle("PDAF UTILIZATION")

                        setPositiveButton("OK"
                        ) { _, _ ->  val downloadManager: DownloadManager = ContextCompat.getSystemService(
                            requireContext(),
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

            if (arrayList[position].title == "Tourism") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentTourism(), null)
                    .addToBackStack(null)
                    .commit()
            }

            if (arrayList[position].title == "GOV.PH") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentGOV(), null)
                    .addToBackStack(null)
                    .commit()
            }
            if (arrayList[position].title == "Open Data Portal") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentOpenData(), null)
                    .addToBackStack(null)
                    .commit()
            }
            if (arrayList[position].title == "Official Gazette") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentOfficialGazette(), null)
                    .addToBackStack(null)
                    .commit()
            }
            if (arrayList[position].title == "Office of the President") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentOfficePresident(), null)
                    .addToBackStack(null)
                    .commit()
            }
            if (arrayList[position].title == "Office of the Vice President") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentOfficeVice(), null)
                    .addToBackStack(null)
                    .commit()
            }
            if (arrayList[position].title == "Senate of the Philippines") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentSenate(), null)
                    .addToBackStack(null)
                    .commit()
            }
            if (arrayList[position].title == "House of Representatives") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentRepresentatives(), null)
                    .addToBackStack(null)
                    .commit()
            }
            if (arrayList[position].title == "Supreme Court") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentSupremeCourt(), null)
                    .addToBackStack(null)
                    .commit()
            }

            if (arrayList[position].title == "Court of the Appeals") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentCourtAppeals(), null)
                    .addToBackStack(null)
                    .commit()
            }

            if (arrayList[position].title == "Sandiganbayan") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                        R.id.frag_container,
                        FragmentSandiganbayan(), null)
                    .addToBackStack(null)
                    .commit()
            }



        }

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("")
//                    listView.clearTextFilter()
                } else {
                    adapter.filter(s)

                }
                return true
            }
        })

        return view
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_settings) {
            //do your functionality here
            true
        } else super.onOptionsItemSelected(item)
    }


}
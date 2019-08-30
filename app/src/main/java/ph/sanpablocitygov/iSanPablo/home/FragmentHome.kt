package ph.sanpablocitygov.iSanPablo.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.links.*

import android.widget.Button
import android.widget.ImageView
import android.widget.ViewFlipper
import layout.ph.sanpablocitygov.iSanPablo.goverment.FragmentCityHotline
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Loginbplo.FragmentBPLOAccountReg
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.FragmentBusinessInTheCity
import ph.sanpablocitygov.iSanPablo.home.isanpablo.FragmentCityEmployeesCorner
import ph.sanpablocitygov.iSanPablo.home.isanpablo.GovernmentOnlineServices.FragmentGovermentOnlineServices
import ph.sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.FragmentMyAppOnlineRequest
import ph.sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes.FragmentMyTaxes
import ph.sanpablocitygov.iSanPablo.home.isanpablo.test

@Suppress("UNREACHABLE_CODE", "PLUGIN_WARNING")
class FragmentHome : Fragment() {

    internal lateinit var viewflipperHome : ViewFlipper


//    internal lateinit var viewPagerHome: ViewPager
    //internal lateinit var viewPagerEvents: ViewPager
    internal lateinit var viewflipperEvents : ViewFlipper

    val image = intArrayOf(R.drawable.lake5,R.drawable.lake1,R.drawable.lake4,R.drawable.lake2)

    val imageevent = intArrayOf(R.drawable.event1,R.drawable.event2,R.drawable.event3)
    //internal lateinit var downloadManager: DownloadManager
    //@SuppressLint("InflateParams")
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home_layout_2, null)

        viewflipperHome =view.findViewById<View>(R.id.v_flipper) as ViewFlipper

        viewflipperEvents =view.findViewById<View>(R.id.v_flipperevent) as ViewFlipper


        for(i in 0 until image.size) {
            flip_imagehome(image[i])
        }


        for(i in 0 until imageevent.size) {
            flip_imageevent(imageevent[i])
        }

//        viewPagerEvents = view.findViewById<View>(R.id.events_viewpager) as ViewPager
//        val adapterevents = ViewPagerAdapterEvent(requireContext())
//        viewPagerEvents.adapter = adapterevents

//        viewPagerHome = view?.findViewById<View>(R.id.home_viewpager) as ViewPager
//        val adapterhome = ViewPageAdapterHome(requireContext())
//        viewPagerHome.adapter = adapterhome

        val btncsc = view.findViewById<Button>(R.id.btn_home_csc)
        btncsc?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentHomeCSC() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnphiljob = view.findViewById<Button>(R.id.btn_home_phil_job)
        btnphiljob?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentHomePhilJobNet() , null)
                .addToBackStack(null)
                .commit()
        }


        val btnweb = view.findViewById<Button>(R.id.db_home_logo)
        btnweb?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                Fragmentweb() , null)
                .addToBackStack(null)
                .commit()
        }

        val btngeps = view.findViewById<android.widget.Button>(R.id.btn_home_philgeps)
        btngeps?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentHomePhilGEPS()
                , null)
                .addToBackStack(null)
                .commit()
        }
        val btncio = view.findViewById<android.widget.Button>(R.id.btn_fb_cio)
        btncio?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentFBCIO()
                , null)
                .addToBackStack(null)
                .commit()
        }
        val textcio = view.findViewById<TextView>(R.id.text_fb_cio)
        textcio?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentFBCIO()
                , null)
                .addToBackStack(null)
                .commit()
        }

        val textBITC = view.findViewById<TextView>(R.id.btn_business_in_the_city)
        textBITC.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBusinessInTheCity()
                , null)
                .addToBackStack(null)
                .commit()
        }

        val textMT = view.findViewById<TextView>(R.id.btn_my_taxes)
        textMT.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentMyTaxes()
                , null)
                .addToBackStack(null)
                .commit()
        }

        val textGOS = view.findViewById<TextView>(R.id.btn_gov_online_service)
        textGOS.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentGovermentOnlineServices()
                , null)
                .addToBackStack(null)
                .commit()
        }

        val textMAOR = view.findViewById<TextView>(R.id.btn_online_request)
        textMAOR.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentMyAppOnlineRequest()
                , null)
                .addToBackStack(null)
                .commit()
        }

        val textCH = view.findViewById<TextView>(R.id.btn_city_hot_lines)
        textCH.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentCityHotline()
                , null)
                .addToBackStack(null)
                .commit()
        }

        val textCEC = view.findViewById<TextView>(R.id.btn_city_employees_corner)
        textCEC.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentCityEmployeesCorner()
                , null)
                .addToBackStack(null)
                .commit()
        }



        return view

    }


    fun flip_imagehome(i : Int) {
        val view = ImageView(context)
        view.setBackgroundResource(i)
        viewflipperHome.addView(view)
        viewflipperHome.setFlipInterval(4000)
        viewflipperHome.setAutoStart(true)
        viewflipperHome.setInAnimation(context , android.R.anim.slide_in_left)
        viewflipperHome.setOutAnimation(context , android.R.anim.slide_out_right)
    }


    fun flip_imageevent(i : Int) {
        val view = ImageView(context)
        view.setBackgroundResource(i)
        viewflipperEvents.addView(view)
        viewflipperEvents.setFlipInterval(6000)
        viewflipperEvents.setAutoStart(true)
        viewflipperEvents.setInAnimation(context , android.R.anim.slide_in_left)
        viewflipperEvents.setOutAnimation(context , android.R.anim.slide_out_right)
    }


}


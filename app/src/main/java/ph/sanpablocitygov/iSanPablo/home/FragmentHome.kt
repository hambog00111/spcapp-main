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
import layout.ph.sanpablocitygov.iSanPablo.goverment.FragmentCityHotline
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.FragmentBusinessInTheCity
import ph.sanpablocitygov.iSanPablo.home.isanpablo.FragmentCityEmployeesCorner
import ph.sanpablocitygov.iSanPablo.home.isanpablo.GovernmentOnlineServices.FragmentGovermentOnlineServices
import ph.sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.FragmentMyAppOnlineRequest
import ph.sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes


@Suppress("UNREACHABLE_CODE", "PLUGIN_WARNING")
class FragmentHome : Fragment() {


    internal lateinit var viewPagerHome: ViewPager
    internal lateinit var viewPagerEvents: ViewPager
    //internal lateinit var downloadManager: DownloadManager
    //@SuppressLint("InflateParams")
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home_layout_2, null)

        viewPagerEvents = view.findViewById<View>(R.id.events_viewpager) as ViewPager
        val adapterevents = ViewPagerAdapterEvent(requireContext())
        viewPagerEvents.adapter = adapterevents

        viewPagerHome = view?.findViewById<View>(R.id.home_viewpager) as ViewPager
        val adapterhome = ViewPageAdapterHome(requireContext())
        viewPagerHome.adapter = adapterhome

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

        val textCHL = view.findViewById<TextView>(R.id.btn_city_hot_lines)
        textCHL.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentCityHotline()
            ).commit()
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


}

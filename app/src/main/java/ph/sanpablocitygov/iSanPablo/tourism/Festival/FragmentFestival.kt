package ph.sanpablocitygov.iSanPablo.tourism.Festival
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ph.sanpablocitygov.iSanPablo.R


class FragmentFestival : Fragment(){
    private lateinit var viewPagerFestival: ViewPager
    private lateinit var  viewPagerFestivalTilapia: ViewPager
    private lateinit var viewPagerFestivalLenten: ViewPager
    private lateinit var viewPagerFestivalchristmas: ViewPager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.tourism_festivals_fragment, container, false)

        viewPagerFestival = view.findViewById<View>(R.id.festivals_viewpager) as ViewPager
        val adapterevents = ViewPageAdapterFestival(requireContext())
        viewPagerFestival.adapter = adapterevents

        viewPagerFestivalTilapia = view.findViewById<View>(R.id.festivals_tilapia_viewpager) as ViewPager
        val adapterevents1 = ViewPageAdapterFestivalTilapia(requireContext())
        viewPagerFestivalTilapia.adapter = adapterevents1

        viewPagerFestivalLenten = view.findViewById<View>(R.id.festivals_lenten_viewpager) as ViewPager
        val adapterevents2 = ViewPageAdapterFestivalLenten(requireContext())
        viewPagerFestivalLenten.adapter = adapterevents2


        viewPagerFestivalchristmas = view.findViewById<View>(R.id.festivals_christmas_viewpager) as ViewPager
        val adapterevents3 = ViewPageAdapterFestivalchristmas(requireContext())
        viewPagerFestivalchristmas.adapter = adapterevents3




        return view
    }
}
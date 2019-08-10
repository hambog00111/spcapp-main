package ph.sanpablocitygov.iSanPablo.tourism


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.ViewPagerAdapterEvent
import ph.sanpablocitygov.iSanPablo.tourism.ViewPageAdapterFestival


class FragmentFestival : Fragment (){
    internal lateinit var viewPagerFestival: ViewPager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_tourism_festivals, container, false)

        viewPagerFestival = view.findViewById<View>(R.id.festivals_viewpager) as ViewPager
        val adapterevents = ViewPageAdapterFestival(requireContext())
        viewPagerFestival.adapter = adapterevents


        return view
    }
}
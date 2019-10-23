package layout.ph.sanpablocitygov.iSanPablo.goverment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import ph.sanpablocitygov.iSanPablo.home.isanpablo.FragmentCityEmployeesCorner
import layout.ph.sanpablocitygov.iSanPablo.cityhotlines.CityhotlineAdapter
import layout.ph.sanpablocitygov.iSanPablo.cityhotlines.CityhotlineModel


import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.FragmentHome
import ph.sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.FragmentMyAppOnlineRequest
import ph.sanpablocitygov.iSanPablo.search.ListViewAdapter
import ph.sanpablocitygov.iSanPablo.search.Model
import java.util.ArrayList





class FragmentCityHotline : Fragment() {

    internal lateinit var listView: ListView


    internal lateinit var adapter: CityhotlineAdapter
    internal lateinit var ivcityhotline: Array<String>
    internal lateinit var ivcityhotlinephone: Array<String>
    internal var arrayList = ArrayList<CityhotlineModel>()
    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_isanpablo_hotline, null)
       // setupPermissions()
        val titlebc = view.findViewById<TextView>(R.id.txt_title_ch)
        titlebc.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_transition_animation)

        val lnrhot  = view.findViewById<LinearLayout>(R.id.linear_ch_hotlines)
        lnrhot.animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fade_scale_animation)



        ivcityhotline = arrayOf(
            "San Pablo City Government", "San Pablo City Police",

            "Red Cross San Pablo", "San Pablo City Emergency Hospital",
            "San Pablo General Hospital", "City Disaster Risk Reduction Management Office",
            "San Pablo Welfare and Development Office", "Bureau of Fire Protection"
        )

        ivcityhotlinephone = arrayOf(
            "(049)3000-065", "(049)5626-474/(049)5210-610",
            "(049)5624-025", "(049)5031-351/(049)5031-432",
            "  (049)5031-351/(049)5031431", "  (049)8000-405", "(049)5621-575", "(049)5627-654"
        )


        val listView = view.findViewById<ListView>(R.id.listview_cityhotline)

        for (i in ivcityhotline.indices) {
            val cityhotlineModel = CityhotlineModel(ivcityhotline[i], ivcityhotlinephone[i])
            //bind all strings in an array
            arrayList.add(cityhotlineModel)
        }

        adapter = CityhotlineAdapter(requireContext(), arrayList)
        listView.adapter = adapter

        return view
    }




}
package layout.ph.sanpablocitygov.iSanPablo.goverment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import ph.sanpablocitygov.iSanPablo.home.isanpablo.FragmentCityEmployeesCorner
import layout.ph.sanpablocitygov.iSanPablo.cityhotlines.CityhotlineAdapter
import layout.ph.sanpablocitygov.iSanPablo.cityhotlines.CityhotlineModel


import ph.sanpablocitygov.iSanPablo.R

class FragmentCityHotline : Fragment() {

    private lateinit var listView: ListView

    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootview = inflater.inflate(R.layout.fragment_isanpablo_hotline,null)

        listView = rootview.findViewById(R.id.listview_cityhotline)
        genList(listView)

        val btncall= rootview.findViewById<Button>(R.id.btn_call_city_hot_line)
        btncall?.setOnClickListener{
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.frag_container,
                FragmentCityEmployeesCorner()
            ).commit()
        }



        return rootview
    }

    fun genList(ls: ListView) {
        var lists: MutableList<CityhotlineModel> = mutableListOf<CityhotlineModel>()

        for (i in 0..0) {
            var user: CityhotlineModel = CityhotlineModel("San Pablo City Goverment", "(049)3000-065")
            lists.add(user)
        }

        for (i in 0..0) {
            var user: CityhotlineModel = CityhotlineModel("San Pablo City Police", "(049)5626-474/(049)5210-610")
            lists.add(user)
        }


        for (i in 0..0) {
            var user: CityhotlineModel = CityhotlineModel("Red Cross San Pablo", "(049)5624-025")
            lists.add(user)
        }
        for (i in 0..0) {
            var user: CityhotlineModel = CityhotlineModel("San Pablo City Emergency Hospital", "(049)5031-351/(049)5031-432")
            lists.add(user)
        }


        for (i in 0..0) {
            var user: CityhotlineModel = CityhotlineModel("San Pablo General Hospital", "(049)5031-351/(049)5031431")
            lists.add(user) }

        for (i in 0..0) {
            var user: CityhotlineModel = CityhotlineModel("City Disaster Risk Reduction Management Office", "(049)8000-405")
            lists.add(user)
        }


        for (i in 0..0) {
            var user: CityhotlineModel = CityhotlineModel("San Pablo Welfare and Development Office", "(049)5621-575")
            lists.add(user)
        }
        for (i in 0..0) {
            var user: CityhotlineModel = CityhotlineModel("Bureau of Fire Protection", "(049)5627-654")
            lists.add(user)
        }

        val list = CityhotlineAdapter(requireContext(), R.layout.row_cityhot_lines, lists)
        ls.adapter = list

    }


}

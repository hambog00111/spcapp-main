package layout.ph.sanpablocitygov.iSanPablo.goverment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import kotlinx.android.synthetic.main.row_cityhot_lines.*
import layout.ph.sanpablocitygov.iSanPablo.cityhotlines.CityhotlinephoneAdapter
import layout.ph.sanpablocitygov.iSanPablo.cityhotlines.CityhotlinephoneModel
import ph.sanpablocitygov.iSanPablo.R

class FragmentCityHotlinephone : Fragment() {

    private lateinit var listView: ListView
    var cityhotline_phone = arrayOf("(049)3000-065",
        "(049)5626-474/(049)5210-610",
        "(049)5624-025",
        "(049)5031-351/(049)5031-432",
        "(049)5031-351/(049)5031431",
        "(049)8000-405",
        "(049)5621-575",
        "(049)5627-654")



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootview = inflater.inflate(R.layout.fragment_cityhotline_listview, null)

//        val phoneNumber = "000000"
////
////        btn_call_city_hot_line.setOnClickListener {
////
////            val  intent = Intent(Intent.ACTION_DIAL)
////            intent.data= Uri.parse("tel:"+ phoneNumber)
////
////            startActivity(intent)
////
////        }



        listView = rootview.findViewById(R.id.listview_cityhotline)
        genList(listView)


        return rootview



    }

    fun genList(ls: ListView) {
        val lists: MutableList<CityhotlinephoneModel> = mutableListOf<CityhotlinephoneModel>()
        for (i: Int in 0 until cityhotline_phone.size step 1) {
            lists.add(CityhotlinephoneModel(cityhotline_phone[i]))
        }

        val list = CityhotlinephoneAdapter(requireContext(), R.layout.row_cityhot_lines, lists)
        ls.adapter = list

    }




}

package ph.Sanpablocitygov.iSanPablo.tourism.WheretoStayEat

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import layout.ph.sanpablocitygov.iSanPablo.cityhotlines.ResortModel


import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.tourism.WheretoStayEat.TravelTour.TravelTourAdapter
import ph.Sanpablocitygov.iSanPablo.tourism.WheretoStayEat.TravelTour.TravelTourModel

class FragmentWheretoStayEat : Fragment() {

    private lateinit var listViewresort: ListView
    private lateinit var listViewHotel: ListView
    private lateinit var listViewInn: ListView
    private lateinit var listViewTravel: ListView
    private  lateinit var listViewRestaurant: ListView



    @SuppressLint("RestrictedApi", "InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootview = inflater.inflate(R.layout.tourism_wheretostay_fragment, null)

        listViewresort = rootview.findViewById(R.id.listview_tourism_resort)
        genList(listViewresort)
        listViewHotel = rootview.findViewById(R.id.listview_tourism_hotel)
        genList1(listViewHotel)
        listViewInn = rootview.findViewById(R.id.listview_tourism_inn)
        genlist2(listViewInn)
        listViewTravel = rootview.findViewById(R.id.listview_tourism_travel_tour)
        genlist3(listViewTravel)
        listViewRestaurant = rootview.findViewById(R.id.listview_tourism_travel_restaurant)
        genlist4(listViewRestaurant)




        return rootview
    }

    fun  genlist4(ls: ListView)
    {
        val lists: MutableList<ResortModel> = mutableListOf<ResortModel>()
        for (i in 0..0) {
            val user = ResortModel("Samuel's Plate",
                "Brgy. San Roque, San Pablo City", "(049) 5035684")
            lists.add(user)
        }
        for (i in 0..0) {
            val user: ResortModel = ResortModel("Forest City Restaurant",
                "Paseo Escudero St., Brgy. IV-C, San Pablo City", "+63 9988606420")
            lists.add(user)
        }
        val list =  ResortAdapter(requireContext(), R.layout.tourism_wheretogo_row, lists)
        ls.adapter = list

    }


    fun genlist3(ls: ListView){
        val lists = mutableListOf<TravelTourModel>()

        for (i in 0..0){
            val user = TravelTourModel("Guru's Guides Travel and Management",
                "#27 A. Mabini Ext. Brgy. V-A, San Pablo City")
            lists.add(user)
        }
        for (i in 0..0){
            val user = TravelTourModel("Solomon Travel and Tours",
                "2nd flr. Lina Bldg. Rizal Ave., Cor. Mabini St. Brgy. V-B, San Pablo City")
            lists.add(user)
        }

        for (i in 0..0){
            val user = TravelTourModel("Melquisedec Travel and Tours",
                "9023 M. Brion St., Brgy. IV-C, San Pablo City")
            lists.add(user)
        }
        for (i in 0..0){
            val user = TravelTourModel("Sky Quest Travel and Tours",
                "M. Basa st., Brgy. VII-C, San Pablo City")
            lists.add(user)
        }
        for (i in 0..0){
            val user = TravelTourModel("Le Vygr International Travel",
                "M. Almario St., Brgy. III-A, San Pablo City")
            lists.add(user)
        }
        for (i in 0..0){
            val user = TravelTourModel("Sky Quest Travel and Tours",
                "Unit 228, 2nd flr SM City San Pablo, Brgy. San Rafael, San Pablo City")
            lists.add(user)
        }
        for (i in 0..0){
            val user = TravelTourModel("Norpaul Travel and Tours",
                "Unit 7-G Square Barleta St., Cor, Malvar St., Brgy. IV-B, San Pablo City")
            lists.add(user)
        }
        val list = TravelTourAdapter(requireContext(), R.layout.tourism_wheretogo_row2, lists)
        ls.adapter = list
    }

    fun genList(ls: ListView) {
        val lists: MutableList<ResortModel> = mutableListOf<ResortModel>()

        for (i in 0..0) {
            val user = ResortModel("Sanctuario De San Pablo Resort and Spa", "Maharklika Highway, Brgy. San Francisco, San Pablo City", "(049) 5613006")
            lists.add(user)
        }

        for (i in 0..0) {
            val user = ResortModel("Deva Cruz Resort", "Brgy. Sta. Filomena, San Pablo City", "(049) 5624074")
            lists.add(user)
        }
        for (i in 0..0) {
            val user = ResortModel("Maria Paz Royale Garden Resort", "Brgy. Sta. Filomena, San Pablo City", "(049) 5626488")
            lists.add(user)
        }
        for (i in 0..0) {
            val user = ResortModel("Dioko Resort and Eco Tourism Park", "Brgy. San Joaquin, San Pablo City", "(049) 8004542")
            lists.add(user)
        }
        val list = ResortAdapter(requireContext(), R.layout.tourism_wheretogo_row, lists)
        ls.adapter = list

    }
    fun genList1(ls: ListView) {

        val lists: MutableList<ResortModel> = mutableListOf<ResortModel>()
        for (i in 0..0) {
            val user = ResortModel("Auravel Grande Resort and Hotel", "Brgy. San Francisco, San Pablo City", "(049) 5030579")
            lists.add(user)
        }

        val list = ResortAdapter(requireContext(), R.layout.tourism_wheretogo_row, lists)
        ls.adapter = list
    }

    fun genlist2(ls: ListView) {

        val lists: MutableList<ResortModel> = mutableListOf<ResortModel>()
        for (i in 0..0) {
            val user = ResortModel("Sulyap Bed and Breakfast", "Cocoland Compound, Brgy. Del Remedio, San Pablo City", "(049) 5629740")
            lists.add(user)
        }

        val list =  ResortAdapter(requireContext(), R.layout.tourism_wheretogo_row, lists)
        ls.adapter = list

    }

}



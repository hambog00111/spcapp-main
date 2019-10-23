package ph.sanpablocitygov.iSanPablo.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.*
import android.widget.*
import layout.ph.sanpablocitygov.iSanPablo.goverment.FragmentCityHotline
import ph.sanpablocitygov.iSanPablo.FragmentDepartment
import ph.sanpablocitygov.iSanPablo.FragmentOurCity
import ph.sanpablocitygov.iSanPablo.LoadingActivity

import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.FragmentBusinessInTheCity
import ph.sanpablocitygov.iSanPablo.home.isanpablo.FragmentCityEmployeesCorner
import ph.sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes.FragmentMyTaxes
import ph.sanpablocitygov.iSanPablo.home.isanpablo.GovernmentOnlineServices.FragmentGovermentOnlineServices
import ph.sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest.FragmentMyAppOnlineRequest
import java.util.ArrayList


class FragmentSearch:Fragment (){

    private lateinit var searchView : SearchView

    internal lateinit var adapter: ListViewAdapter
    internal lateinit var title: Array<String>
    internal  lateinit var icon: IntArray
    internal  var arrayList = ArrayList<Model>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val view = inflater.inflate(R.layout.fragmentsearch, null)




        title = arrayOf("Business in The City", "My Taxes",
            "My app Online Request", "City Hotlines",
            "Government Online Sevice","City Employee Corner")
        icon = intArrayOf(R.drawable.btn_bitc, R.drawable.btn_mt,
            R.drawable.btn_maro, R.drawable.btn_ch,
            R.drawable.btn_gos, R.drawable.btn_cec)
        searchView = view.findViewById(R.id.searchView) as SearchView

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
        listView.setOnItemClickListener {  parent, view, position, id ->
        //            activity!!.supportFragmentManager.beginTransaction().replace(
//                R.id.frag_container,
//
//                FragmentBusinessInTheCity(), null)
//                .addToBackStack(null)
//                .commit()

            if (arrayList[position].title == "Business in The City" ) {



            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBusinessInTheCity(), null)
                .commit()
        }




            if (arrayList[position].title == "My Taxes" ) {



                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentMyTaxes(), null)
                    .commit()
            }

            if (arrayList[position].title == "My app Online Request") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentMyAppOnlineRequest(), null)
                    .commit()

            }
            if (arrayList[position].title == "City Hotlines") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentCityHotline(), null)
                    .commit()

            }
            if (arrayList[position].title == "Government Online Service") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentGovermentOnlineServices(), null)
                    .commit()
            }
            if (arrayList[position].title == "City Employee Corner") {
                //start NewActivity with title for actionbar and text for textview
                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentCityEmployeesCorner(), null)
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
                    listView.clearTextFilter()
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
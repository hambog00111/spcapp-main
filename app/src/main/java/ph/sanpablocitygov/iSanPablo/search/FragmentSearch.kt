package ph.sanpablocitygov.iSanPablo.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.*
import android.widget.ListView
import android.widget.SearchView

import ph.sanpablocitygov.iSanPablo.R
import java.util.ArrayList


class FragmentSearch:Fragment (){

    private lateinit var searchView : SearchView

    internal lateinit var adapter: ListViewAdapter
    internal lateinit var title: Array<String>
    internal  lateinit var icon: IntArray
    internal  var arrayList = ArrayList<Model>()
    internal  lateinit var mFrag : Fragment
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
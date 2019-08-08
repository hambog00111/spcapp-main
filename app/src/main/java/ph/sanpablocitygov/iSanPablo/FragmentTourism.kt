package ph.sanpablocitygov.iSanPablo


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ph.sanpablocitygov.iSanPablo.links.FragmentHomeCSC
import ph.sanpablocitygov.iSanPablo.tourism.FragmentLandmarks


class FragmentTourism : Fragment (){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_tourism_layout, container, false)
        val btncsc = view.findViewById<Button>(R.id.btn_tourism_gallery)
        btncsc?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentLandmarks() , null)
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}
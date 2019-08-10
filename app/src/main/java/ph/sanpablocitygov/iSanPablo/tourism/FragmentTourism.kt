package ph.sanpablocitygov.iSanPablo.tourism


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ph.sanpablocitygov.iSanPablo.R


class FragmentTourism : Fragment (){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_tourism_layout, container, false)
        val btngallery = view.findViewById<Button>(R.id.btn_tourism_gallery)
        btngallery?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentLandmarks() , null)
                .addToBackStack(null)
                .commit()
        }
        val btngo = view.findViewById<Button>(R.id.btn_tourism_go)
        btngo?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentWheretogo() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnfes = view.findViewById<Button>(R.id.btn_tourism_festivals)
        btnfes?.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentFestival(), null)
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}
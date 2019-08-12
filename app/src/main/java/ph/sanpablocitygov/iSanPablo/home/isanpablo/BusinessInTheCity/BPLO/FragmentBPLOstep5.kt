package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ph.sanpablocitygov.iSanPablo.R

class FragmentBPLOstep5 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bc_bplo_fillup_step5, null)

        val btnnext = view.findViewById<Button>(R.id.txt_bplo_submit)
        btnnext.setOnClickListener {

        }
        val btnback = view.findViewById<Button>(R.id.txt_bplo_back)
        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep4() , null)
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}
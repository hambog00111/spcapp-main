package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import ph.sanpablocitygov.iSanPablo.R

class FragmentBPLOstep1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bc_bplo_fillup_step1_fragment_, null)
        
        // Initializing a String Array
        val asso = arrayOf("Association", "Cooperative", "Corporation", "Partnership", "Single")
        
        // Initializing an ArrayAdapter
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, asso)

        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        // Finally, data bind the spinner object with dapter
    val spnassp = view.findViewById<Spinner>(R.id.spinner_bplo_association)
        spnassp.adapter = adapter




        val btnlogin = view.findViewById<Button>(R.id.txt_bplo_next_page)
        btnlogin.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep2(), null)
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}
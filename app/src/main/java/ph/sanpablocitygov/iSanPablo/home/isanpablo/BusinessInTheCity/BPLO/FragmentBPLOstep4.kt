package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.firebase.ui.auth.AuthUI.getApplicationContext
import ph.sanpablocitygov.iSanPablo.R

class FragmentBPLOstep4 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bc_bplo_fillup_step4, null)


        // Initializing a String Array
        val lineofbusiness = arrayOf("Association", "Cooperative", "Corporation", "Partnership", "Single")


//        val subAsso = arrayOf()

        // Initializing an ArrayAdapter
        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, lineofbusiness)



        // Set the drop down view resource
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item)



        // Finally, data bind the spinner object with dapter
        val spnass = view.findViewById<Spinner>(R.id.spinner_bplo_business_activity_line_business)
        spnass.adapter = adapter1

        spnass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            @SuppressLint("RestrictedApi")
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if(spnass.selectedItem == "Association")
                        {
                            Toast.makeText(getApplicationContext(), "Mobil dipilih",
                                Toast.LENGTH_SHORT).show()

                            val subcat1 = arrayOf("Cooperative", "Corporation")
                            val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, subcat1)
                            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item)
                            val sub1 = view?.findViewById<Spinner>(R.id.spinner_bplo_business_activity_line_business)
                            sub1?.adapter = adapter2
                        }
                else{
                            Toast.makeText(getApplicationContext(), "Mobil dipilihs ",
                                Toast.LENGTH_SHORT).show()
                            val subcat2 = arrayOf("Partnership", "Single")
                            val adapter3 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,subcat2)
                            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item)
                            val sub2 = view?.findViewById<Spinner>(R.id.spinner_bplo_business_activity_sub_category)
                            sub2?.adapter = adapter3

                        }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }


        val btnback = view.findViewById<Button>(R.id.txt_bplo_back)
        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep3() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnpass = view.findViewById<Button>(R.id.txt_bplo_submit)
        btnpass.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOProfile() , null)
                .addToBackStack(null)
                .commit()
        }
        return view
    }

}


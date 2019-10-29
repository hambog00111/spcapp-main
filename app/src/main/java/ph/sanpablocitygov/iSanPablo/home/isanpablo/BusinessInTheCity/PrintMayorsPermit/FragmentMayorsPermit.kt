package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.PrintMayorsPermit

import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.bc_print_mayors_permit_fragment.*

import ph.sanpablocitygov.iSanPablo.R

class FragmentMayorsPermit : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.bc_print_mayors_permit_fragment, null)
//        val mpornumber = view?.findViewById<EditText>(R.id.txt_mp_ornumber)
//        val mpemail = view?.findViewById<EditText>(R.id.txt_mp_email)

        val btnmpclear = view.findViewById<Button>(R.id.btn_mp_clear)
        btnmpclear.setOnClickListener {
            txt_mp_email.setText("")
            txt_mp_ornumber.setText("")
        }
        val btnback = view.findViewById<Button>(R.id.btn_mp_back)
        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentMayorsPermit()).commit()
        }
        val btnsend = view.findViewById<Button>(R.id.btn_mp_send)
        btnsend.setOnClickListener {
//            var mpornum = mpornumber?.text.toString()
//            var mpemailadd = mpemail?.text.toString()


            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentMayorsPermitConfirmation()
                , null)
                .addToBackStack(null)
                .commit()

        }





    return view
}
}
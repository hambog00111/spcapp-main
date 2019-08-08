package ph.sanpablocitygov.iSanPablo.home.isanpablo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.sanpablocitygov.iSanPablo.R

class FragmentGovermentOnlineServices : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_isanpablo_govolser, container, false)

        return view
    }
}

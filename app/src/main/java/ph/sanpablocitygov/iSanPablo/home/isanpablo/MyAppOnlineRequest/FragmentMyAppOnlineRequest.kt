package ph.sanpablocitygov.iSanPablo.home.isanpablo.MyAppOnlineRequest

import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.sanpablocitygov.iSanPablo.R

class FragmentMyAppOnlineRequest : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_isanpablo_myappolreq, container, false)

        return view
    }
}
package ph.sanpablocitygov.iSanPablo.home.isanpablo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ph.sanpablocitygov.iSanPablo.R

open class FragmentMyTaxes : Fragment(){

override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val view: View = inflater.inflate(R.layout.fragment_isanpablo_mytaxes, container, false)

    return view
}
}
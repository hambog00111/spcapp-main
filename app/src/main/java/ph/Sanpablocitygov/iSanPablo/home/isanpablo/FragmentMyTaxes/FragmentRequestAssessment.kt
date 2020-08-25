package ph.Sanpablocitygov.iSanPablo.home.isanpablo.FragmentMyTaxes

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.Sanpablocitygov.iSanPablo.R

class FragmentRequestAssessment : Fragment(){

    @SuppressLint("InflateParams")
    override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.mt_rpt_request_assessment, container, false)

return view
    }
    }
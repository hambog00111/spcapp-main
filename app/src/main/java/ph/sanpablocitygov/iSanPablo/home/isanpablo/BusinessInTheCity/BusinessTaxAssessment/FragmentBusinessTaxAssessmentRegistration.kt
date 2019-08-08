package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BusinessTaxAssessment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.sanpablocitygov.iSanPablo.R

class FragmentBusinessTaxAssessmentRegistration : Fragment() {

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_assessment_registration_1, container, false)

        return view

    }
}
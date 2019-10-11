package ph.sanpablocitygov.iSanPablo.cityhotlines

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import ph.sanpablocitygov.iSanPablo.R

class Fragmenttest1 : Fragment() {
    private var resultTv: TextView? = null
    private var resultTv1: TextView? = null
    private var resultTv2: TextView? = null


    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragmentlayouttest1, container, false)
     //   val rootview = inflater.inflate(R.layout.fragmentlayoutest, null)
     //  val saveBtn = view!!.findViewById<Button>(R.id.saveBtn)

        val intent = activity!!.intent
        val name = "ako lang to"
        val email = intent.getStringExtra("Email")
        val phone: String = intent.getStringExtra("Phone")

         resultTv = view.findViewById<View>(R.id.resultTv) as TextView
         resultTv1 = view.findViewById<View>(R.id.resultTv1)as TextView
        resultTv2 = view.findViewById<View>(R.id.resultTv2)as TextView
        //setText
        resultTv!!.text = ""+name+""
        resultTv1!!.text = ""+email+""
        resultTv2!!.text =""+phone

//        saveBtn.setOnClickListener {
//            //get text from edittexts
//            val name = resultTv.text.toString()
//            val email = resultTv1.text.toString()
//            val phone = resultTv2.text.toString()
//
//            //intent to start activity
//            val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
//            intent.putExtra("Name", name)
//            intent.putExtra("Email", email)
//            intent.putExtra("Phone", phone)
//            startActivity(intent)
//
//        }

        return view
    }
}

package ph.sanpablocitygov.iSanPablo.cityhotlines

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import ph.sanpablocitygov.iSanPablo.R

class fragmenttest : Fragment() {


   private lateinit var nameEt: EditText
    private var emailEt: EditText? = null
    private var phoneEt: EditText? = null
    private var saveBtn: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragmentlayoutest, container, false)
     //  val rootview = inflater.inflate(R.layout.fragmentlayoutest, container,false)
        nameEt = view!!.findViewById<View>(R.id.nameEt) as EditText
         emailEt = view!!.findViewById<View>(R.id.emailEt) as EditText
         phoneEt = view!!.findViewById<View>(R.id.phoneEt)as EditText
         saveBtn = view!!.findViewById<View>(R.id.saveBtn) as Button



        saveBtn!!.setOnClickListener {
            //get text from edittexts
          val name = nameEt!!.text.toString()
            val email = emailEt!!.text.toString()
            val phone = phoneEt!!.text.toString()

            if (TextUtils.isEmpty(name)) {
                nameEt?.error = "Please enter your last name"
                nameEt?.requestFocus()
             return@setOnClickListener
            }

            if (TextUtils.isEmpty(email)) {
                emailEt?.error = "Please enter your first name"
                emailEt?.requestFocus()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(phone)) {
                phoneEt?.error = "Please enter your last name"
                phoneEt?.requestFocus()
                return@setOnClickListener
            }





            //intent to start activity
           activity!!.intent.putExtra("Name", name)
            activity!!. intent.putExtra("Email", email)
            activity!!.  intent.putExtra("Phone", phone)

            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                Fragmenttest1(), null)
                .addToBackStack(null)
                .commit()

//            val intent = Intent(this@MainActivity, SecondActivity::class.java)
//            intent.putExtra("Name", name)
//            intent.putExtra("Email", email)
//            intent.putExtra("Phone", phone)
//            startActivity(intent)
        }

        return view
    }
}



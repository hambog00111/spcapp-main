package ph.sanpablocitygov.iSanPablo.OurBarangay

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ph.sanpablocitygov.iSanPablo.R

class BarangayAdapter(var mcontext: Context , var resource:Int,var items:List<BarangayModel>)
    : ArrayAdapter<BarangayModel>( mcontext , resource , items ){



    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater :LayoutInflater = LayoutInflater.from(mcontext)

        val view : View = layoutInflater.inflate(resource , null )
        val title : TextView = view.findViewById(R.id.title_barangay)
        val district: TextView = view.findViewById(R.id.nav_dept_head_name)
        val code : TextView = view.findViewById(R.id.code_barangay)
        val chairman : TextView = view.findViewById(R.id.chairman_barangay)
        val contact : TextView = view.findViewById(R.id.nav_contact_dept)


        val person : BarangayModel = items[position]

        title.text = person.ivtitle
        district.text = person.ivdistrict
        code.text = person.ivcode
        chairman.text = person.ivchairman
        contact.text = person.ivcontact


        return view
    }

}


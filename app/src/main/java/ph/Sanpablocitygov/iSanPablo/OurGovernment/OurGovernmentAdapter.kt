package ph.Sanpablocitygov.iSanPablo.OurGovernment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ph.Sanpablocitygov.iSanPablo.R


class OurGovernmentAdapter(var mcontext: Context, var resource:Int, var items:List<OurGovernmentModel>)
    : ArrayAdapter<OurGovernmentModel>( mcontext , resource , items ) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val layoutInflater : LayoutInflater = LayoutInflater.from(mcontext)

        val view : View = layoutInflater.inflate(resource , null )
        val office : TextView = view.findViewById(R.id.nav_office)
        val officename: TextView = view.findViewById(R.id.nav_office_name)
        val departmenthead: TextView = view.findViewById(R.id.nav_department_head)

        val person : OurGovernmentModel = items[position]

        office.text = person.ivoffice
        officename.text = person.ivofficename
        departmenthead.text = person.ivdepartment



        return view
    }
}

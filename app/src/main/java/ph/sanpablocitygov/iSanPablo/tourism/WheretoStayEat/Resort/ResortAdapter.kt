package ph.sanpablocitygov.iSanPablo.tourism.WheretoStayEat

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import layout.ph.sanpablocitygov.iSanPablo.cityhotlines.ResortModel
import ph.sanpablocitygov.iSanPablo.R

class ResortAdapter(var mcontext: Context, var resource:Int, var items:List<ResortModel>)
    : ArrayAdapter<ResortModel>(mcontext, resource, items){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mcontext)

        val view: View = layoutInflater.inflate(resource, null)

        var name: TextView = view.findViewById(R.id.txt_name_where)

        var add: TextView = view.findViewById(R.id.txt_address_where)

        var contact: TextView = view.findViewById(R.id.txt_contact_where)

        var resortm : ResortModel = items[position]


        name.text = resortm.ivnamewhere


        add.text = resortm.ivaddwhere

        contact.text = resortm.ivcontactwhere


        return view
    }

}
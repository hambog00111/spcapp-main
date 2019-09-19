package layout.ph.sanpablocitygov.iSanPablo.cityhotlines
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import ph.sanpablocitygov.iSanPablo.FragmentDepartment
import ph.sanpablocitygov.iSanPablo.LoadingActivity
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.FragmentBusinessInTheCity

import java.util.*

class CityhotlineAdapter(internal var mContext: Context, internal var modellist: MutableList<CityhotlineModel>) : BaseAdapter() {
    internal var inflater: LayoutInflater = LayoutInflater.from(mContext)
    internal var arrayList: ArrayList<CityhotlineModel> = ArrayList()

    init {
        this.arrayList.addAll(modellist)
    }

    inner class ViewHolder {
        internal var title: TextView? = null
        internal var phone: TextView? = null
    }

    override fun getCount(): Int {
        return modellist.size
    }

    override fun getItem(i: Int): Any {
        return modellist[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(postition: Int, view: View?, parent: ViewGroup): View {
        var view = view
        val holder: ViewHolder
        if (view == null) {
            holder = ViewHolder()
            view = inflater.inflate(R.layout.row_cityhot_lines, null)

            //locate the views in row.xml
            holder.title = view.findViewById<TextView>(R.id.title_cityhot_line)
            holder.phone = view.findViewById<TextView>(R.id.title_cityhotline_number)


            view.tag = holder

        } else {
            holder = view.tag as ViewHolder
        }
        //set the results into textviews
        holder.title!!.text = modellist[postition].ivcityhotline
        holder.phone!!.text = modellist[postition].ivcityhotlinephone
        //set the result in imageview

        val phoneNumber = "(049)3000-065"
        val phoneNumber1 = "(049)5626-474"
        val phoneNumber2 = "(049)5624-025"
        val phoneNumber3 = "(049)5031-351"
        val phoneNumber4 = "(049)5031-351"
        val phoneNumber5 = " (049)8000-405"
        val phoneNumber6 = "(049)5621-575"
        val phoneNumber7 = "(049)5627-654"
        //listview item clicks
        view!!.setOnClickListener {
            //code later
            if (modellist[postition].ivcityhotline == "San Pablo City Government") {
                //start NewActivity with title for actionbar and text for textview
                val  intent = Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:$phoneNumber")
                mContext.startActivity(intent)
            }
            if (modellist[postition].ivcityhotline == "San Pablo City Police") {
                //start NewActivity with title for actionbar and text for textview
                val  intent = Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:$phoneNumber1")
                mContext.startActivity(intent)
            }
            if (modellist[postition].ivcityhotline == "Red Cross San Pablo") {
                //start NewActivity with title for actionbar and text for textview
                val  intent = Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:$phoneNumber2")
                mContext.startActivity(intent)


            }
            if (modellist[postition].ivcityhotline == "San Pablo City Emergency Hospital") {
                //start NewActivity with title for actionbar and text for textview
                val  intent = Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:$phoneNumber3")
                mContext.startActivity(intent)


            }
            if (modellist[postition].ivcityhotline == "San Pablo General Hospital") {
                //start NewActivity with title for actionbar and text for textview
                val  intent = Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:$phoneNumber4")
                mContext.startActivity(intent)
            }
            if (modellist[postition].ivcityhotline == "City Disaster Risk Reduction Management Office") {
                //start NewActivity with title for actionbar and text for textview
                val  intent = Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:$phoneNumber5")
                mContext.startActivity(intent)
            }
            if (modellist[postition].ivcityhotline ==   "San Pablo Welfare and Development Office") {
                //start NewActivity with title for actionbar and text for textview
                val  intent = Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:$phoneNumber6")
                mContext.startActivity(intent)
            }
            if (modellist[postition].ivcityhotline == "Bureau of Fire Protection") {
                //start NewActivity with title for actionbar and text for textview
                val  intent = Intent(Intent.ACTION_DIAL)
                intent.data= Uri.parse("tel:$phoneNumber7")
                mContext.startActivity(intent)
            }

        }


        return view
    }


}
//    (var mcontext: Context, var resource:Int, var items:List<CityhotlineModel>)
//    : ArrayAdapter<CityhotlineModel>(mcontext, resource, items){
//
//    @SuppressLint("ViewHolder")
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val layoutInflater: LayoutInflater = LayoutInflater.from(mcontext)
//
//        val view: View = layoutInflater.inflate(resource, null)
//
//        var name: TextView = view.findViewById(R.id.title_cityhot_line)
//
//        var phone: TextView = view.findViewById(R.id.title_cityhotline_number)
//
//        var cityh : CityhotlineModel = items[position]
//
//
//        name.text = cityh.ivcityhotline
//
//
//        phone.text = cityh.ivcityhotlinephone
//
//
//
//
//        return view
//    }
//
//
//
//}}
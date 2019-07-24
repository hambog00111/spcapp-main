package layout.ph.sanpablocitygov.iSanPablo.cityhotlines

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ph.sanpablocitygov.iSanPablo.R

class CityhotlineAdapter(var mcontext: Context, var resource:Int, var items:List<CityhotlineModel>)
    : ArrayAdapter<CityhotlineModel>(mcontext, resource, items){

    @SuppressLint("ViewHolder")


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val layoutInflater: LayoutInflater = LayoutInflater.from(mcontext)

        val view: View = layoutInflater.inflate(resource, null)

        var name: TextView = view.findViewById(R.id.title_cityhot_line)

        var phone: TextView = view.findViewById(R.id.title_cityhotline_number)

        var cityh : CityhotlineModel = items[position]


        name.text = cityh.ivcityhotline

        phone.text = cityh.ivcityhotlinephone



        return view
    }



}
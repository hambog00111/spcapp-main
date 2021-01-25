package ph.Sanpablocitygov.iSanPablo.home.newsandupdate

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ph.Sanpablocitygov.iSanPablo.R

class CustomAdapter(private val context: Context, private val dataList: ArrayList<HashMap<String, String>>) : BaseAdapter() {

    private val inflater: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int { return dataList.size }
    override fun getItem(position: Int): Int { return position }
    override fun getItemId(position: Int): Long { return position.toLong() }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val dataitem = dataList[position]




        val rowView = inflater.inflate(R.layout.newsandupdate, parent, false)

        rowView.findViewById<TextView>(R.id.text_view_3).text = dataitem["id"]
        rowView.findViewById<TextView>(R.id.text_view_1).text = dataitem["title"]
        rowView.findViewById<TextView>(R.id.text_view_2).text = dataitem["subtitle"]



//        Picasso.get()
//            .load("http://192.168.3.140:8000/image/"+dataitem.get("thumbnail") )
//            .resize(50, 50)
//            .centerCrop()
//            .into(rowView.findViewById<ImageView>(R.id.row_image))

        rowView.tag = position
        return rowView
    }



}
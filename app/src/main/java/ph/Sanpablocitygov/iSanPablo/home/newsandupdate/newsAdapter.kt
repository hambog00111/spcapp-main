package ph.Sanpablocitygov.iSanPablo.home.newsandupdate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.newsandupdate.view.*
import ph.Sanpablocitygov.iSanPablo.R

class newsAdapter(private val dataList: ArrayList<HashMap<String, String>>) : RecyclerView.Adapter<newsAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.newsandupdate,
            parent, false)
        return ExampleViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = dataList[position]
//        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem["title"]
        holder.textView2.text = currentItem["subtitle"]
        holder.textView3.text = currentItem["id"]
    }
    override fun getItemCount() = dataList.size
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2
        val textView3: TextView = itemView.text_view_3
    }
}
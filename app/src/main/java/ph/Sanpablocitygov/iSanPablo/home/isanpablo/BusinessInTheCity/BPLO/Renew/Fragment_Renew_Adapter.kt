package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Renew

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ph.Sanpablocitygov.iSanPablo.R


internal class Fragment_Renew_Adapter(private var mContext: Context, private val householdList: ArrayList<Fragment_Renew_model>?) :  RecyclerView.Adapter<Fragment_Renew_Adapter.BPLOViewHolder>() {

    internal class BPLOViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextViewLine1: TextView = itemView.findViewById(R.id.textview_line_1)
        var mTextViewLine2: TextView = itemView.findViewById(R.id.textview_line_2)
        var mTextViewLine3: TextView = itemView.findViewById(R.id.textview_line_3)
        var mTextViewLine4: TextView = itemView.findViewById(R.id.textview_line_4)
        var mTextViewLine5: TextView = itemView.findViewById(R.id.textview_line_5)


//        var btnremove: Button = itemView.findViewById(R.id.btn_remove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BPLOViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bplo_step4_item, parent, false)
        return BPLOViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return householdList!!.size
    }

    override fun onBindViewHolder(holder: BPLOViewHolder, position: Int) {
        val currentItem = householdList!![position]
        holder.mTextViewLine1.text = currentItem.line1
        holder.mTextViewLine2.text = currentItem.line2
        holder.mTextViewLine3.text = currentItem.line3
        holder.mTextViewLine4.text = currentItem.line4
        holder.mTextViewLine5.text = currentItem.line5






    }

}
package ph.Sanpablocitygov.iSanPablo.search

import android.annotation.SuppressLint
import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

import android.widget.TextView
import ph.Sanpablocitygov.iSanPablo.R


import java.util.ArrayList
import java.util.Locale

@Suppress("NAME_SHADOWING")
class ListViewAdapter(internal var mContext: Context, internal var modellist: MutableList<Model>): BaseAdapter() {
    internal var inflater: LayoutInflater = LayoutInflater.from(mContext)
    private var arrayList: ArrayList<Model> = ArrayList()


    init {
        this.arrayList.addAll(modellist)
    }

    inner class ViewHolder {
        internal var mTitleTv: TextView? = null
        internal var mIconIv: ImageView? = null
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

    @SuppressLint("InflateParams")
    override fun getView(postition: Int, view: View?, parent: ViewGroup): View? {
        var view = view
        val holder: ViewHolder


        if (view == null) {

            val inflater = LayoutInflater.from(mContext)
            view = inflater.inflate(R.layout.rowsearch, null)

            holder = ViewHolder()
            //locate the views in row.xml
            holder.mTitleTv = view.findViewById <TextView>(R.id.mainTitle) as TextView
            holder.mIconIv = view.findViewById(R.id.mainIcon) as ImageView

            view.tag = holder
        } else {

            holder = view.tag as ViewHolder
        }


        //set the results into textviews
        holder.mTitleTv!!.text = modellist[postition].title
        //set the result in imageview
        holder.mIconIv!!.setImageResource(modellist[postition].icon)



//
       return view
    }

    //filter
    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        modellist.clear()
         if (charText.isEmpty()) {
         modellist.addAll(arrayList)
            }

         else {
                 for (model in arrayList) {
                  if (model.title.toLowerCase(Locale.getDefault())
                                .contains(charText)) {
                    modellist.add(model)


                }

            }
        }
        notifyDataSetChanged()
    }

}
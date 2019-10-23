package ph.sanpablocitygov.iSanPablo.search

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityManagerCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

import android.widget.TextView
import ph.sanpablocitygov.iSanPablo.FragmentDepartment
import ph.sanpablocitygov.iSanPablo.LoadingActivity
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.FragmentBusinessInTheCity

import java.util.ArrayList
import java.util.Locale

class ListViewAdapter(internal var mContext: Context,
                      internal var modellist: MutableList<Model>,
                      internal  var mFrag: Fragment = Fragment(),
                      internal val activity: AppCompatActivity = AppCompatActivity()): BaseAdapter() {
    internal var inflater: LayoutInflater = LayoutInflater.from(mContext)
    internal var arrayList: ArrayList<Model> = ArrayList()




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

    override fun getItem(i: Int): Any {      return modellist[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView( postition: Int, view: View?, parent: ViewGroup): View? {
        var view = view
        val holder: ViewHolder


        if (view == null) {

            val inflater = LayoutInflater.from(mContext)
            view = inflater.inflate(R.layout.rowsearch, null)

            holder = ViewHolder()
            //locate the views in row.xml
            holder.mTitleTv = view.findViewById<TextView>(R.id.mainTitle) as TextView
            holder.mIconIv = view.findViewById(R.id.mainIcon) as ImageView
//
            view.tag = holder

        } else {
            holder = view.tag as ViewHolder
        }


        //set the results into textviews
        holder.mTitleTv!!.text = modellist[postition].title
        //set the result in imageview
        holder.mIconIv!!.setImageResource(modellist[postition].icon)


        //listview item clicks

        holder.mTitleTv!!.setOnClickListener {
            //code later
         val transaction : FragmentManager
            if (modellist[postition].title == "Business in The City" ) {
//                val mFrag: Fragment = Fragment()
                val frag = mFrag
//                if (!isFinishing() && !isDestroyed())
//                //start NewActivity with title for actionbar and text for textview
           activity.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentBusinessInTheCity(), null)
                    .commit()
            }
            if (modellist[postition].title == "My Taxes") {
                //start NewActivity with title for actionbar and text for textview
                val intent = Intent(mContext, FragmentDepartment::class.java)
                mContext.startActivity(intent)
            }
            if (modellist[postition].title == "My home_event_pic1 Online Request") {
                //start NewActivity with title for actionbar and text for textview
                val intent = Intent(mContext, LoadingActivity::class.java)

                mContext.startActivity(intent)
            }
            if (modellist[postition].title == "City Hotlines") {
                //start NewActivity with title for actionbar and text for textview
                val intent = Intent(mContext, FragmentBusinessInTheCity::class.java)

                mContext.startActivity(intent)
            }
            if (modellist[postition].title == "Government Online Service") {
                //start NewActivity with title for actionbar and text for textview
                val intent = Intent(mContext, FragmentDepartment::class.java)

                mContext.startActivity(intent)
            }
            if (modellist[postition].title == "City Employee Corner") {
                //start NewActivity with title for actionbar and text for textview
                val intent = Intent(mContext, FragmentDepartment::class.java)

                mContext.startActivity(intent)
            }

        }

        return view
    }

    //filter
    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        modellist.clear()
         if (charText.length == 0) {
            modellist.addAll(arrayList)
            } else {
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
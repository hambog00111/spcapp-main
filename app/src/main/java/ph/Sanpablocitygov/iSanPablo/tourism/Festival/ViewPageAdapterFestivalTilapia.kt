package ph.Sanpablocitygov.iSanPablo.tourism.Festival

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import ph.Sanpablocitygov.iSanPablo.R

class ViewPageAdapterFestivalTilapia(private val context: Context) : PagerAdapter(){

    private var layoutInflater: LayoutInflater?=null
    private val images = arrayOf(R.drawable.tilapiafrstival3,R.drawable.tilapiafestival2,R.drawable.tilapiafestival)

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 === p1

    }

    override fun getCount(): Int {
        return images.size
    }

    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.tourism_festivals_tilapia_custom, null)
        val image = v.findViewById<View>(R.id.image_view_tilapia_festivals) as ImageView
        image.setImageResource(images[position])


        val vp = container as ViewPager
        vp.addView(v, 0)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}
package ph.sanpablocitygov.iSanPablo.home

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import ph.sanpablocitygov.iSanPablo.R

class ViewPageAdapterHome(private val context: Context) : PagerAdapter(){

    private var layoutInflater: LayoutInflater?=null
    private val images = arrayOf(R.drawable.lake5,R.drawable.lake1,R.drawable.lake4,R.drawable.lake2)

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 === p1

    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.home_custom_gallery_layout, null)
        val image = v.findViewById<View>(R.id.image_view_home) as ImageView
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
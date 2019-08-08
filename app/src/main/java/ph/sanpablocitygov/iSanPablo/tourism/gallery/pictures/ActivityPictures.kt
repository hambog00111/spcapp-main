package ph.sanpablocitygov.iSanPablo.tourism.gallery.pictures

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.GridLayout.VERTICAL
import ph.sanpablocitygov.iSanPablo.R

class ActivityPictures : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures)

        val rview = findViewById<View>(R.id.rview) as RecyclerView
        val place = intArrayOf(
            R.drawable.lake1,
            R.drawable.lake1,
            R.drawable.lake1,
            R.drawable.lake1,
            R.drawable.lake1,
            R.drawable.lake1
        )
        val name = arrayOf("Lake1","Lake2","Lake3","Lake4","Lake5","Lake6")

        val lManager = GridLayoutManager(this, 2 , VERTICAL, false)
        rview.setLayoutManager(lManager)

         rview.adapter = MyAdapater(place, name, this)
    }
}

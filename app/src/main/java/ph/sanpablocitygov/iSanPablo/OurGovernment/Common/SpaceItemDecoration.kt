package ph.sanpablocitygov.iSanPablo.OurGovernment.Common

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpaceItemDecoration(internal var space: Int):RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = space
        outRect.bottom = space
        outRect.right = space
        outRect.left = space

    }
}
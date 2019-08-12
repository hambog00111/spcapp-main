package ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.Loginbplo

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton1 private constructor(context: Context) {
    private var mRequestQueue: RequestQueue

    // applicationContext is key, it keeps you from leaking the
    // Activity or BroadcastReceiver if someone passes one in.
    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(mCtx?.applicationContext)
            }
            return mRequestQueue
        }

    init {
        mCtx = context
        mRequestQueue = requestQueue
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    companion object {
        private var mInstance: VolleySingleton1? = null
        private var mCtx: Context? = null

        @Synchronized
        fun getInstance(context: Context): VolleySingleton1 {
            if (mInstance == null) {
                mInstance = VolleySingleton1(context)
            }
            return mInstance as VolleySingleton1
        }
    }
}
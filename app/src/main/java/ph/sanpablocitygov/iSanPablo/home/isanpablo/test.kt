package ph.sanpablocitygov.iSanPablo.home.isanpablo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import ph.sanpablocitygov.iSanPablo.R

class test: Fragment()  {

    internal var btn_next: Button? = null
    internal var btn_pre: Button? = null
 internal var progressBar: ProgressBar? = null
 private var url = "http://www.sanpablocitygov.ph/?fbclid=IwAR25R1_F3f9gz59nqa9bqAWp7a32RGeGDViejvA23747zW77GhNjZu2v8YU"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
   val view: View = inflater.inflate(R.layout.test, container, false)
//       val view = inflater.inflate(R.layout.test, null)
      var wv: WebView? = null
   //     btn_pre = view.findViewById<View>(R.id.btn_pre) as Button
        //btn_next = view.findViewById<View>(R.id.btn_next) as Button

     //   btn_pre= view.findViewById(R.id.btn_pre)as Button
      btn_next=view.findViewById(R.id.btn_next)as Button

   wv = view.findViewById<View>(R.id.webView) as WebView
        progressBar = view.findViewById<View>(R.id.progressBar) as ProgressBar

        wv.webViewClient = myWebClient()
        wv.settings.javaScriptEnabled = true
        wv.settings.builtInZoomControls = true
        wv.settings.displayZoomControls = false
     wv.loadUrl(url)

//        btn_pre!!.setOnClickListener {
////
//
//            if (wv.canGoBack()) {
//                wv.goBack()
//            }
//        }
        btn_next!!.setOnClickListener {
            //

            if (wv.canGoBack()) {
                wv.goBack()
            }
        }
        return view

    }

//    override fun  onClick(view: View) {
//
//    }

//
    inner class myWebClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

            progressBar!!.visibility = View.VISIBLE
            view.loadUrl(url)
            return true

        }

        override fun onPageFinished(view: WebView, url: String) {

            super.onPageFinished(view, url)
            progressBar!!.visibility = View.GONE
        }

    }

}
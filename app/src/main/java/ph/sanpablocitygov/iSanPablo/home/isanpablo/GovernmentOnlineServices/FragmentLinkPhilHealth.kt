package ph.sanpablocitygov.iSanPablo.home.isanpablo.GovernmentOnlineServices

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import ph.sanpablocitygov.iSanPablo.R

class FragmentLinkPhilHealth : Fragment() {


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(
            R.layout.fragment_home_web, container,
            false)


        val  mWebView = view.findViewById(R.id.home_web_view) as WebView
        mWebView.loadUrl("https://www.philhealth.gov.ph/")

        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true

// Force links and redirects to open in the WebView instead of in a browser
        mWebView.webViewClient = WebViewClient()



        return  view

    }
}

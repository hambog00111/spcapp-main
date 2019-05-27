package com.example.mis_pc_11.spc_app1.Links


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.mis_pc_11.spc_app1.R


class FragmentHomeMarket : Fragment() {


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(
            R.layout.fragment_home_web, container,
            false)


        val  mWebView = view.findViewById(R.id.home_web_view) as WebView
        mWebView.loadUrl("https://epaymentportal.landbank.com/index.php?code=VHQxcGVETU5EOWZiZXVIQnRWQ3NodmtCL2dKMG1HRjBsJTJCZ2k5dlIvclcwPQ")

        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true

// Force links and redirects to open in the WebView instead of in a browser
        mWebView.webViewClient = WebViewClient()

        return  view

    }


}
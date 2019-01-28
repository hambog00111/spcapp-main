package com.example.mis_pc_11.spc_app1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

class FragmentCourtAppeals :
   Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_court_appeals, container,
            false)
        

        val  mWebView = view.findViewById(R.id.web_court_appeals) as WebView
        mWebView.loadUrl("https://ca2.judiciary.gov.ph/caws-war/")

        val webSettings = mWebView.getSettings()
        webSettings.setJavaScriptEnabled(true)

// Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(WebViewClient())



        return  view

    }
}


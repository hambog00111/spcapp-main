package com.example.mis_pc_11.spc_app1

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fill_up.view.*
import java.util.*


class FragmentFillUp : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fill_up, null)
        val progBar: AlertDialog.Builder = AlertDialog.Builder(activity)

        progBar.setCancelable(true)
        progBar.setView(R.layout.layout_progress_loading)

        val alDialog: AlertDialog = progBar.create()
        val btnClick = view.btnSend
        btnClick.setOnClickListener{
            SaveInfo(alDialog).execute("")
        }

        return view
    }

    private class SaveInfo constructor(pBar: AlertDialog): AsyncTask<String, Void, String>() {
        private val pLoading: AlertDialog = pBar

        override fun onPreExecute(){
            super.onPreExecute()
            pLoading.show()
        }

        override fun doInBackground(vararg p0: String?): String {
            for(i in 0..100){
                println(i)
            }
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

        }

    }
}
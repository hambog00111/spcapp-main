package com.example.mis_pc_11.spc_app1

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class FragmentFillUp : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fill_up, null)
    }

    abstract class SaveInfo(ctx: Context): AsyncTask<String, Void, String>() {

        private var mContext: Context

        init {
            this.mContext = ctx.applicationContext
        }
        override fun onPreExecute(){

        }

        override fun doInBackground(vararg p0: String?): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPostExecute(result: String?) {

        }

    }
}
package com.example.mis_pc_11.spc_app1

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_fill_up.view.*


class FragmentFillUp : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fill_up, null)
        val progBar: AlertDialog.Builder = AlertDialog.Builder(activity)

        progBar.setCancelable(true)
        progBar.setView(R.layout.layout_progress_loading)

        val alDialog: AlertDialog = progBar.create()
        val btnClick = view.btnSend

        val radGrp = view.findViewById<RadioGroup>(R.id.opt1)

        btnClick.setOnClickListener{
            //SaveInfo(alDialog).execute("")
            val selRad = radGrp.checkedRadioButtonId
            Log.d("Selected Radio Button", selRad.toString())
            val rBtn = view.findViewById<RadioButton>(selRad)
            Toast.makeText(context, rBtn.text, Toast.LENGTH_LONG).show()
        }

        return view
    }

//    private fun getRadBtnVal(rg: RadioGroup, v: View){
//        val selRad = rg.checkedRadioButtonId
//        val rBtn = v.findViewById(selRad) as RadioButton
//        Toast.makeText(context, rBtn.editableText, Toast.LENGTH_LONG).show()
//    }

    private class SaveInfo constructor(pBar: AlertDialog): AsyncTask<String, Void, String>() {
        private val pLoading: AlertDialog = pBar

        override fun onPreExecute(){
            super.onPreExecute()
            pLoading.show()
        }

        override fun doInBackground(vararg p0: String?): String {
            try{

            } catch(e: Exception) {

            } finally {

            }
            return ""
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pLoading.dismiss()
        }

    }
}
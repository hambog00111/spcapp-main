package layout.ph.sanpablocitygov.iSanPablo.cityhotlines
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import ph.Sanpablocitygov.iSanPablo.R

import java.util.*

@Suppress("NAME_SHADOWING")
class CityhotlineAdapter(private var mContext: Context, private var modellist: MutableList<CityhotlineModel>) : BaseAdapter() {
    internal var inflater: LayoutInflater = LayoutInflater.from(mContext)
    private var arrayList: ArrayList<CityhotlineModel> = ArrayList()



    init {
        this.arrayList.addAll(modellist)
    }

    inner class ViewHolder {
        internal var title: TextView? = null
        internal var phone: TextView? = null

    }

    override fun getCount(): Int {
        return modellist.size
    }

    override fun getItem(i: Int): Any {
        return modellist[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun getView(postition: Int, view: View?, parent: ViewGroup): View {
        var view = view

        val holder: ViewHolder
        if (view == null) {
            holder = ViewHolder()
            view = inflater.inflate(R.layout.home_city_hotlines_row, null)

            //locate the views in row.xml
            holder.title = view.findViewById(R.id.title_cityhot_line) as TextView
            holder.phone = view.findViewById(R.id.title_cityhotline_number)as TextView


            view.tag = holder

        } else {
            holder = view.tag as ViewHolder

        }
        //set the results into textviews
        holder.title!!.text = modellist[postition].ivcityhotline
        holder.phone!!.text = modellist[postition].ivcityhotlinephone
        //set the result in imageview

        val phoneNumber = "(049)3000-065"

        val phoneNumber1a = "(049)5626-474"
        val phoneNumber1b = "(049)5210-610"

        val phoneNumber2 = "(049)5624-025"

        val phoneNumber3a = "(049)5031-351"
        val phoneNumber3b = "(049)5031-432"

        val phoneNumber4a = "(049)5031-351"
        val phoneNumber4b = "(049)5031431"

        val phoneNumber5 = " (049)8000-405"


        val phoneNumber6 = "(049)5621-575"

        val phoneNumber7 = "(049)5627-654"

        //listview item clicks
        view!!.setOnClickListener {
            //code later

            if (modellist[postition].ivcityhotline == "San Pablo City Government") {
                //start NewActivity with title for actionbar and text for textview  

                val Contact = LayoutInflater.from(mContext).inflate(R.layout.home_city_hotlines_call_dialogbox, null)
                //                TextView tv = (TextView) dialog.findViewById(R.id.resultTv);
                val builder = AlertDialog.Builder(mContext)
                    .setView(Contact)
                builder.setCancelable(false)
                val tv = Contact.findViewById(R.id.resultTv) as TextView

                val cl = Contact.findViewById(R.id.call) as TextView
                cl.text = "Call"
                tv.text = "(049)3000-065"


                builder.setPositiveButton("call") { _, _ ->

                        // Do something when user press the positive button
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber")
                        mContext.startActivity(intent)

                    }


                    // Display a neutral button on alert dialog
                    builder.setNeutralButton("Cancel") { _, _ ->
                    }

                    // Finally, make the alert dialog using builder
                    val dialog: AlertDialog = builder.create()
                dialog.setCancelable(false)
                    // Display the alert dialog on home_event_pic1 interface
                    dialog.show()


                }
                if (modellist[postition].ivcityhotline == "San Pablo City Police") {

                    //start NewActivity with title for actionbar and text for textview
                    val Contact = LayoutInflater.from(mContext)
                        .inflate(R.layout.home_city_hotlines_call_dialogbox, null)

//                TextView tv = (TextView) dialog.findViewById(R.id.resultTv);
                    val builder = AlertDialog.Builder(mContext)
                    builder.setCancelable(false)
                        .setView(Contact)

                    val tv = Contact.findViewById(R.id.resultTv) as TextView
                    val cl = Contact.findViewById(R.id.call) as TextView

                    val cl1 = Contact.findViewById(R.id.call1) as TextView
                    val tv1 = Contact.findViewById(R.id.resultTv1) as TextView

                    //button_call number 1 ito yung lalabas sa dialog
                    cl.text = "Call1"
                    tv.text = "(049)5626-474"

                    //button_call number 2 ito yung lalabas sa dialog
                    cl1.text = "Call2"
                    tv1.text = "(049)5210-610"

                    //  holder.resultTv!!.text = ""+number+""
                    //   builder.setMessage(holder.resultTv?.text ?:  ""+number+"")
                    builder.setPositiveButton("Call 2") { _, _ ->
                        // Do something when user press the positive button
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber1b")
                        mContext.startActivity(intent)

                    }

                    // Display a negative button on alert dialog
                    builder.setNegativeButton("Call 1") { _, _ ->

                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber1a")
                        mContext.startActivity(intent)


                    }


                    // Display a neutral button on alert dialog
                    builder.setNeutralButton("Cancel") { _, _ ->
                    }

                    // Finally, make the alert dialog using builder
                    val dialog: AlertDialog = builder.create()
                    dialog.setCancelable(false)
                    // Display the alert dialog on home_event_pic1 interface
                    dialog.show()


                }
                if (modellist[postition].ivcityhotline == "Red Cross San Pablo") {
                    //start NewActivity with title for actionbar and text for textview
                    val Contact = LayoutInflater.from(mContext)
                        .inflate(R.layout.home_city_hotlines_call_dialogbox, null)


//                TextView tv = (TextView) dialog.findViewById(R.id.resultTv);
                    val builder = AlertDialog.Builder(mContext)
                    builder.setCancelable(false)
                        .setView(Contact)

                    val tv = Contact.findViewById(R.id.resultTv) as TextView

                    val cl = Contact.findViewById(R.id.call) as TextView
                    cl.text = "Call"
                    tv.text = "(049)5624-025"
                    //  holder.resultTv!!.text = ""+number+""
                    //   builder.setMessage(holder.resultTv?.text ?:  ""+number+"")
                    builder.setPositiveButton("Call") { _, _ ->
                        // Do something when user press the positive button
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber2")
                        mContext.startActivity(intent)

                    }


                    // Display a neutral button on alert dialog
                    builder.setNeutralButton("Cancel") { _, _ ->
                    }

                    // Finally, make the alert dialog using builder
                    val dialog: AlertDialog = builder.create()
                    dialog.setCancelable(false)
                    // Display the alert dialog on home_event_pic1 interface
                    dialog.show()

                }
                if (modellist[postition].ivcityhotline == "San Pablo City Emergency Hospital") {
                    //start NewActivity with title for actionbar and text for textview
                    val Contact = LayoutInflater.from(mContext)
                        .inflate(R.layout.home_city_hotlines_call_dialogbox, null)



//                TextView tv = (TextView) dialog.findViewById(R.id.resultTv);
                    val builder = AlertDialog.Builder(mContext)
                    builder.setCancelable(false)
                        .setView(Contact)

                    val tv = Contact.findViewById(R.id.resultTv) as TextView
                    val cl = Contact.findViewById(R.id.call) as TextView

                    val cl1 = Contact.findViewById(R.id.call1) as TextView
                    val tv1 = Contact.findViewById(R.id.resultTv1) as TextView

                    //button_call number 1 ito yung lalabas sa dialog
                    cl.text = "Call1"
                    tv.text = "(049)5031-351"

                    //button_call number 2 ito yung lalabas sa dialog
                    cl1.text = "Call2"
                    tv1.text = "(049)5031-432"

                    //  holder.resultTv!!.text = ""+number+""
                    //   builder.setMessage(holder.resultTv?.text ?:  ""+number+"")
                    builder.setPositiveButton("Call 2") { _, _ ->
                        // Do something when user press the positive button
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber3b")
                        mContext.startActivity(intent)

                    }

                    // Display a negative button on alert dialog
                    builder.setNegativeButton("Call 1") { _, _ ->

                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber3a")
                        mContext.startActivity(intent)


                    }


                    // Display a neutral button on alert dialog
                    builder.setNeutralButton("Cancel") { _, _ ->
                    }

                    // Finally, make the alert dialog using builder
                    val dialog: AlertDialog = builder.create()
                    dialog.setCancelable(false)
                    // Display the alert dialog on home_event_pic1 interface
                    dialog.show()

                }
                if (modellist[postition].ivcityhotline == "San Pablo General Hospital") {
                    //start NewActivity with title for actionbar and text for textview
                    val Contact = LayoutInflater.from(mContext)
                        .inflate(R.layout.home_city_hotlines_call_dialogbox, null)


//                TextView tv = (TextView) dialog.findViewById(R.id.resultTv);
                    val builder = AlertDialog.Builder(mContext)
                    builder.setCancelable(false)
                        .setView(Contact)

                    val tv = Contact.findViewById(R.id.resultTv) as TextView
                    val cl = Contact.findViewById(R.id.call) as TextView

                    val cl1 = Contact.findViewById(R.id.call1) as TextView
                    val tv1 = Contact.findViewById(R.id.resultTv1) as TextView

                    //button_call number 1 ito yung lalabas sa dialog
                    cl.text = "Call1"
                    tv.text = "(049)5031-351"

                    //button_call number 2 ito yung lalabas sa dialog
                    cl1.text = "Call2"
                    tv1.text = "(049)5031-431"

                    //  holder.resultTv!!.text = ""+number+""
                    //   builder.setMessage(holder.resultTv?.text ?:  ""+number+"")
                    builder.setPositiveButton("Call 2") { _, _ ->
                        // Do something when user press the positive button
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber4b")
                        mContext.startActivity(intent)

                    }

                    // Display a negative button on alert dialog
                    builder.setNegativeButton("Call 1") { _, _ ->

                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber4a")
                        mContext.startActivity(intent)


                    }


                    // Display a neutral button on alert dialog
                    builder.setNeutralButton("Cancel") { _, _ ->
                    }

                    // Finally, make the alert dialog using builder
                    val dialog: AlertDialog = builder.create()
                    dialog.setCancelable(false)
                    // Display the alert dialog on home_event_pic1 interface
                    dialog.show()
                }
                if (modellist[postition].ivcityhotline == "City Disaster Risk Reduction Management Office") {
                    //start NewActivity with title for actionbar and text for textview
                    val Contact = LayoutInflater.from(mContext)
                        .inflate(R.layout.home_city_hotlines_call_dialogbox, null)


//                TextView tv = (TextView) dialog.findViewById(R.id.resultTv);
                    val builder = AlertDialog.Builder(mContext)
                    builder.setCancelable(false)
                        .setView(Contact)

                    val tv = Contact.findViewById(R.id.resultTv) as TextView

                    val cl = Contact.findViewById(R.id.call) as TextView
                    cl.text = "Call"
                    tv.text = "(049)8000-405"
                    //  holder.resultTv!!.text = ""+number+""
                    //   builder.setMessage(holder.resultTv?.text ?:  ""+number+"")
                    builder.setPositiveButton("Call") { _, _ ->
                        // Do something when user press the positive button
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber5")
                        mContext.startActivity(intent)

                    }


                    // Display a neutral button on alert dialog
                    builder.setNeutralButton("Cancel") { _, _ ->
                    }

                    // Finally, make the alert dialog using builder
                    val dialog: AlertDialog = builder.create()
                    dialog.setCancelable(false)
                    // Display the alert dialog on home_event_pic1 interface
                    dialog.show()
                }
                if (modellist[postition].ivcityhotline == "San Pablo Welfare and Development Office") {
                    //start NewActivity with title for actionbar and text for textview
                    val Contact = LayoutInflater.from(mContext)
                        .inflate(R.layout.home_city_hotlines_call_dialogbox, null)


//                TextView tv = (TextView) dialog.findViewById(R.id.resultTv);
                    val builder = AlertDialog.Builder(mContext)
                    builder.setCancelable(false)
                        .setView(Contact)

                    val tv = Contact.findViewById(R.id.resultTv) as TextView

                    val cl = Contact.findViewById(R.id.call) as TextView
                    cl.text = "Call"
                    tv.text = "(049)5621-575"
                    //  holder.resultTv!!.text = ""+number+""
                    //   builder.setMessage(holder.resultTv?.text ?:  ""+number+"")
                    builder.setPositiveButton("call") { _, _ ->
                        // Do something when user press the positive button
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber6")
                        mContext.startActivity(intent)

                    }


                    // Display a neutral button on alert dialog
                    builder.setNeutralButton("Cancel") { _, _ ->
                    }

                    // Finally, make the alert dialog using builder
                    val dialog: AlertDialog = builder.create()
                    dialog.setCancelable(false)
                    // Display the alert dialog on home_event_pic1 interface
                    dialog.show()
                }
                if (modellist[postition].ivcityhotline == "Bureau of Fire Protection") {
                    //start NewActivity with title for actionbar and text for textview
                    val Contact = LayoutInflater.from(mContext)
                        .inflate(R.layout.home_city_hotlines_call_dialogbox, null)


//                TextView tv = (TextView) dialog.findViewById(R.id.resultTv);
                    val builder = AlertDialog.Builder(mContext)
                    builder.setCancelable(false)
                        .setView(Contact)

                    val tv = Contact.findViewById(R.id.resultTv) as TextView

                    val cl = Contact.findViewById(R.id.call) as TextView
                    cl.text = "Call"
                    tv.text = "(049)5627-654"
                    //  holder.resultTv!!.text = ""+number+""
                    //   builder.setMessage(holder.resultTv?.text ?:  ""+number+"")
                    builder.setPositiveButton("Call") { _, _ ->
                        // Do something when user press the positive button
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phoneNumber7")
                        mContext.startActivity(intent)

                    }


                    // Display a neutral button on alert dialog
                    builder.setNeutralButton("Cancel") { _, _ ->
                    }

                    // Finally, make the alert dialog using builder
                    val dialog: AlertDialog = builder.create()
                    dialog.setCancelable(false)
                    // Display the alert dialog on home_event_pic1 interface
                    dialog.show()
                }

            }


        return view
    }


}



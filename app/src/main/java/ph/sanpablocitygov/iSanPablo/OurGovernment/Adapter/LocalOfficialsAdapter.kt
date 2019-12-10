package ph.sanpablocitygov.iSanPablo.OurGovernment.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import ph.sanpablocitygov.iSanPablo.OurGovernment.Common.Common
import ph.sanpablocitygov.iSanPablo.OurGovernment.Interface.ICardItemClickListener
import ph.sanpablocitygov.iSanPablo.OurGovernment.Model.LocalOfficialsModel
import ph.sanpablocitygov.iSanPablo.R

class LocalOfficialsAdapter (internal var context: Context, internal var localOfficials: List<LocalOfficialsModel>):RecyclerView.Adapter<LocalOfficialsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val localoff = LayoutInflater.from(context)
                             .inflate(R.layout.our_government_officials_row, p0, false)
        return MyViewHolder(localoff)
    }

    override fun getItemCount(): Int {
            return  localOfficials.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
            p0.img_localofficials.setImageResource(localOfficials[p1].ivimglocalofficials)
            p0.name_localofficials.text = localOfficials[p1].ivnamelocalofficials
            p0.post_localofficials.text = localOfficials[p1].ivpositionlocalofiicials  

        p0.setEvent(object:ICardItemClickListener{
            override fun OnCardItemClick(viewl: View, position: Int) {
                Toast.makeText(context, "Clicked" + localOfficials[position],Toast.LENGTH_SHORT).show()

            }

        })

    }

    class MyViewHolder(it:View):RecyclerView.ViewHolder(it),View.OnClickListener{


        internal var img_localofficials : ImageView
        internal var name_localofficials : TextView
        internal var post_localofficials : TextView
        internal lateinit var iCardItemClickListener:ICardItemClickListener

        fun setEvent(iCardItemClickListener: ICardItemClickListener)
        {
                this.iCardItemClickListener = iCardItemClickListener
        }

        init
        {
            img_localofficials = it.findViewById<View>(R.id.local_city_local_officials_row_img) as ImageView
            name_localofficials = it.findViewById(R.id.local_city_local_officials_row_txt_name) as TextView
            post_localofficials = it.findViewById(R.id.local_city_local_officials_row_txt_post) as TextView
        }

        override fun onClick(v: View?) {
                iCardItemClickListener.OnCardItemClick(v!!,adapterPosition)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return  if(localOfficials.size == 1)
            0 // If item size is 1 ,  just return 1 column (full width)
        else{
            if (localOfficials.size % Common.NUM_OF_COLUMN == 0) //size of div for column num == 0 . just return default column
       1
            else
                if(position > 1 && position == localOfficials.size - 1 ) 0 else 1
        }

    }

}
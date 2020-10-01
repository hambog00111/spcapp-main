package ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.FragmentCopyofPayslip

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment


import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.cec_copy_of_payslip_fragment.*
import kotlinx.android.synthetic.main.cec_copy_of_payslip_gov_issued_id_fragment.*
import ph.Sanpablocitygov.iSanPablo.R
import java.io.ByteArrayOutputStream

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class FragmentCopyofPayslipStep2: Fragment(){
    private val CAMERA_REQUEST = 1888
    private var imageView: ImageView? = null
    private val MY_CAMERA_PERMISSION_CODE = 100
      @RequiresApi(Build.VERSION_CODES.M)
      @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { val view = inflater.inflate(
        R.layout.cec_copy_of_payslip_gov_issued_id_fragment, null)
        val btnnext = view.findViewById<Button>(R.id.txt_cec_sr_next)
        val btnback = view.findViewById<Button>(R.id.txt_cec_sr_back)

        val btncamera = view.findViewById<Button>(R.id.cec_sr_btn_government_issued_id)
        val emp_num = (activity!!.intent as Intent).getStringExtra("empnum")
        val emp_email=activity!!.intent.getStringExtra("emp_email")
        val date_from=activity!!.intent.getStringExtra("date_from")
        val date_to=activity!!.intent.getStringExtra("date_to")
        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentCopyofPayslip(), null)
                .addToBackStack(null)
                .commit()

        }

        btnnext.setOnClickListener {
            val gov_issued_id= gov_issued_id.text.toString()


            if (gov_issued_id.trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Please Take a Picture of Any Government Issued ID!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            activity!!.intent.putExtra("empnum", emp_num)
            activity!!.intent.putExtra("emp_email", emp_email)
            activity!!.intent.putExtra("date_from", date_from)
            activity!!.intent.putExtra("date_to", date_to)
            activity!!.intent.putExtra("gov_id", gov_issued_id)
            activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentCopyofPayslipStep3(), null)
                .addToBackStack(null)
                .commit()
        }

        btncamera.setOnClickListener {
            if (activity!!.checkSelfPermission(Manifest.permission.CAMERA) !== PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf<String>(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
            } else {
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }

        return view
    }

    override fun onRequestPermissionsResult(requestCode:Int, @NonNull permissions:Array<String>, @NonNull grantResults:IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(activity, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
            else
            {
                Toast.makeText(activity, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK ) {

          val gid=view!!.findViewById<TextView>(R.id.gov_issued_id)
          val bitmap = data!!.extras!!.get("data") as Bitmap
            cec_sr_img_government_issued_ids!!.setImageBitmap(bitmap)
            val imageData  = imageToString(bitmap)
        gid.text=imageData

//                val inputStream = contentResolver.openInputStream(filePath!!)
//                bitmap = BitmapFactory.decodeStream(inputStream)
//                imageView!!.setImageBitmap(bitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun imageToString(bitmap: Bitmap?): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imageBytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }
}
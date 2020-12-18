package ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.FragmentCopyofPayslip

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.NonNull
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.cec_copy_of_payslip_requester_pic_with_id_fragment.*
import okhttp3.*
import org.jetbrains.anko.toast
import ph.Sanpablocitygov.iSanPablo.R
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.CityEmployeeCornerhandler
import ph.Sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner.FragmentCityEmployeesCorner
import java.io.ByteArrayOutputStream
import java.io.IOException

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class FragmentCopyofPayslipStep3: Fragment(){
    private val CAMERA_REQUEST = 1888
    private val MY_CAMERA_PERMISSION_CODE = 100
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { val view = inflater.inflate(
        R.layout.cec_copy_of_payslip_requester_pic_with_id_fragment, null)
        val btncamera = view.findViewById<Button>(R.id.cec_sr_btn_requester)
        val btnback = view.findViewById<Button>(R.id.txt_cec_sr_back)
         val txt_cec_sr_submit=view.findViewById<Button>(R.id.txt_cec_sr_submit)

        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentCopyofPayslipStep2(), null)
                .addToBackStack(null)
                .commit()

        }
        btncamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CAMERA) !== PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf<String>(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
            } else {
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }

        txt_cec_sr_submit.setOnClickListener {
            val rpwid=view!!.findViewById<TextView>(R.id.rpwid).text.toString()
            if (rpwid.trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(activity, "Please a Take Picture holding Same Government Issued ID you used in Step 2!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Request()

        }



        return view
    }

    fun Request(){

        val pdLoading = ProgressDialog(context)
        pdLoading.setMessage("\tLoading...")
        pdLoading.setCancelable(false)
        pdLoading.show()

//        val imageView = findViewById<ImageView>(R.id.imageView1)
//        imageUpload = findViewById<View>(R.id.imageUpload) as ImageView
//        val gov_id_pic =view!!.findViewById<TextView>(R.id.gov_id_pic).text.toString()
//        val pic_with_id =view!!.findViewById<TextView>(R.id.pic_with_id).text.toString()

        val emp_num = (activity!!.intent as Intent).getStringExtra("empnum")
        val emp_email=activity!!.intent.getStringExtra("emp_email")
        val date_from=activity!!.intent.getStringExtra("date_from")
        val date_to=activity!!.intent.getStringExtra("date_to")
        val gid=activity!!.intent.getStringExtra("gov_id")
        val rpwid=view!!.findViewById<TextView>(R.id.rpwid).text.toString()
        val formBody: RequestBody = FormBody.Builder()
            .add("employee_number",emp_num)
            .add("requestor_email", emp_email)
            .add("pay_period_from",date_from )
            .add("pay_period_to",date_to )
            .add("image_gov_id", "data:image/jpg;base64,$gid")
            .add("image_with_id", "data:image/jpg;base64,$rpwid")

            .build()



        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .method("POST",formBody)
            .url("http://www.sanpablocitygov.ph/api/add_request_hr")
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity!!.runOnUiThread(Runnable {
                    activity!!.toast("unable to connect to the server please try again later")

                })
                pdLoading.dismiss()
                println(e)
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {

                pdLoading.dismiss()
                val body = response.body?.string()
                println(body)
                val gson = Gson()
                val rptrequest = gson.fromJson(body, CityEmployeeCornerhandler::class.java)
                if (rptrequest.message== "Success" ) {

                    activity!!.runOnUiThread(Runnable {

                            val dialogBuilder = AlertDialog.Builder(activity!!)
                            dialogBuilder.setMessage("Request for Copy Payslip successfully Sent.Please wait within 24 hrs and check your email for confirmation. Thank you!")
                                    // if the dialog is cancelable
                                    .setCancelable(false)
                                    // positive button text and action
                                    .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, _ ->

                                        activity!!.supportFragmentManager.beginTransaction().replace(
                                                R.id.frag_container,
                                                FragmentCityEmployeesCorner(), null)
                                            .addToBackStack(null)
                                            .commit()
                                        dialog.dismiss()
                                    })
                            val alert = dialogBuilder.create()
                            alert.show()

                        })



                } else {
                    activity!!.runOnUiThread(Runnable {

                        activity!!.toast("unable to connect to the server please try again later")

                    })
                }

            }
        })
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

            val rpwid=view!!.findViewById<TextView>(R.id.rpwid)
            val bitmap = data!!.extras!!.get("data") as Bitmap
            cec_sr_img_requesters!!.setImageBitmap(bitmap)
            val imageData  = imageToString(bitmap)
            rpwid.text=imageData

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
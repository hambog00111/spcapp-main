package ph.sanpablocitygov.iSanPablo.home.isanpablo.CityEmployeeCorner

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.bc_bplo_fillup_step5_fragment.*
import kotlinx.android.synthetic.main.cec_service_record_dialogbox.view.*
import kotlinx.android.synthetic.main.cec_service_record_fragment.*
import kotlinx.android.synthetic.main.update_fragment.view.*
import ph.sanpablocitygov.iSanPablo.R
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOProfile
import ph.sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO.FragmentBPLOstep4

class FragmentServiceRecord : Fragment() {

    private var rotationDegrees: Int = 0
    //CAMERA
    val requestCode: Int = 0
    val requestCode2: Int = 0
    var image_uri_requester: Uri? = null
    var image_uri: Uri? = null
    private val PERMISSION_CODE_SKETCH = 1000
    private val IMAGE_CAPTURE_CODE_SKETCH = 1001
    private val data :ByteArray? = null
    private val PERMISSION_CODE = 1000
    private val IMAGE_CAPTURE_CODE = 1001
    private var permissionsRequired = android.Manifest.permission.CAMERA
    private var permissionsRequired2 = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    private var permissionsRequired3 = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.cec_service_record_fragment, null)

        val btnback = view.findViewById<Button>(R.id.txt_cec_sr_cancel)
        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentCityEmployeesCorner() , null)

                .commit()
        }
        val btnsubmit = view.findViewById<Button>(R.id.txt_cec_sr_submit)
        btnsubmit.setOnClickListener {
            val mybus = LayoutInflater.from(requireContext()).inflate(R.layout.cec_service_record_dialogbox, null)

            val mybusBuilder = AlertDialog.Builder(requireContext())
                .setView(mybus)
            mybusBuilder.setCancelable(false)
            val mybusDialog = mybusBuilder.show()


            mybus.btn_dialog_sr_confirm.setOnClickListener {
                mybusDialog.dismiss()
                activity!!.supportFragmentManager.beginTransaction().replace(
                    R.id.frag_container,
                    FragmentCityEmployeesCorner() , null)

                    .commit()

            }
        }
        // image capture for DTI Permit

        val btndti = view.findViewById<Button>(R.id.cec_sr_btn_government_issued_id)

        btndti.setOnClickListener {
            cec_sr_img_government_issued_id!!.visibility = View.VISIBLE
            cec_sr_img_government_issued_ids!!.visibility = View.GONE
            // if system os is Marshmallow or Above, we ne to request runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(requireContext(), permissionsRequired)
                    == PackageManager.PERMISSION_DENIED ||
                    ActivityCompat.checkSelfPermission(requireContext(), permissionsRequired2)
                    == PackageManager.PERMISSION_DENIED
                ) {
                    // permission was not enable
                    requestPermissions(permissionsRequired3, PERMISSION_CODE)
                } else {
                    //permission granted
                    openCamera()

                }
            } else {
                //system os is < marshmallow
                openCamera()

            }
        }

        val btnsketch = view.findViewById<Button>(R.id.cec_sr_btn_requester)

        btnsketch.setOnClickListener {
            // if system os is Marshmallow or Above, we need to request runtime permission
            linear_cec_sr_requesters!!.visibility = View.GONE
            cec_sr_img_requester.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(requireContext(), permissionsRequired)
                    == PackageManager.PERMISSION_DENIED ||
                    ActivityCompat.checkSelfPermission(requireContext(), permissionsRequired2)
                    == PackageManager.PERMISSION_DENIED
                ) {
                    // permission was not enable
                    requestPermissions(permissionsRequired3, PERMISSION_CODE)

                } else {
                    //permission granted
                    openCameraRequester()

                }
            } else {
                //system os is < marshmallow
                openCameraRequester()

            }

        }


        return view
    }




    private fun openCameraRequester() {
        val valuesSketch = ContentValues()
        valuesSketch.put(MediaStore.Images.Media.TITLE, "Request")
        image_uri_requester = activity!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valuesSketch)
        //
        val cameraIntentSketch = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntentSketch.putExtra(MediaStore.EXTRA_OUTPUT, image_uri_requester)
        startActivityForResult(cameraIntentSketch,IMAGE_CAPTURE_CODE)


    }

    private fun openCamera() {
        val valuesDTI = ContentValues()
        valuesDTI.put(MediaStore.Images.Media.TITLE, "ID")
        valuesDTI.put(MediaStore.Images.Media.DESCRIPTION, "This image will be send after submitting.")
        image_uri = activity!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valuesDTI)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //called when user presses ALLOW / DENY  from Permission Request Pop Up
        when (requestCode) {

            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup was granted
                    openCamera()
                } else {
                    //permission from popup was denied
                    Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == Activity.RESULT_OK){
            cec_sr_img_government_issued_id!!.setImageURI(image_uri)
            cec_sr_img_requester!!.setImageURI(image_uri_requester)

        }

    }


}

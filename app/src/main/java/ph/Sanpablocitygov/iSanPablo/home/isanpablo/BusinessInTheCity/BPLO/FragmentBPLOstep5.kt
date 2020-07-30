package ph.Sanpablocitygov.iSanPablo.home.isanpablo.BusinessInTheCity.BPLO

import android.annotation.SuppressLint
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


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import kotlinx.android.synthetic.main.bc_bplo_fillup_step5_fragment.*
import ph.Sanpablocitygov.iSanPablo.R



class FragmentBPLOstep5 : Fragment() {
//
//    private var rotationDegrees: Int = 0
//    //CAMERA
//    val requestCode: Int = 0
//    val requestCode2: Int = 0
    var image_uri_sketch: Uri? = null
    var image_uri: Uri? = null
//    private val PERMISSION_CODE_SKETCH = 1000
//    private val IMAGE_CAPTURE_CODE_SKETCH = 1001
//    private val data :ByteArray? = null
    private val PERMISSION_CODE = 1000
    private val IMAGE_CAPTURE_CODE = 1001
    private var permissionsRequired = android.Manifest.permission.CAMERA
    private var permissionsRequired2 = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    private var permissionsRequired3 = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    @SuppressLint("InflateParams")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bc_bplo_fillup_step5_fragment, null)

        val btnback = view.findViewById<Button>(R.id.txt_bplo_back)
        btnback.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOstep4() , null)
                .addToBackStack(null)
                .commit()
        }
        val btnpass = view.findViewById<Button>(R.id.txt_bplo_submit)
        btnpass.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.frag_container,
                FragmentBPLOProfile() , null)
                .addToBackStack(null)
                .commit()
        }
        // image capture for DTI Permit

        val btndti = view.findViewById<Button>(R.id.bplo_step5_dti_btn)

        btndti.setOnClickListener {
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
//                    val imgdti = view.findViewById<ImageView>(R.id.bplo_step5_sketch_img)
                }
            } else {
                //system os is < marshmallow
                openCamera()

            }
        }

            val btnsketch = view.findViewById<Button>(R.id.bplo_step5_sketch_btn)
            val imgsketch = view.findViewById<ImageView>(R.id.bplo_step5_sketch_img)
        imgsketch.matrix.setRotate(0F)
            btnsketch.setOnClickListener {
                // if system os is Marshmallow or Above, we need to request runtime permission

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
                        openCameraSketch()

                    }
                } else {
                    //system os is < marshmallow
                    openCameraSketch()

            }

        }


        return view
    }




    private fun openCameraSketch() {
        val valuesSketch = ContentValues()
        valuesSketch.put(MediaStore.Images.Media.TITLE, "Sketch")
        image_uri_sketch = activity!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valuesSketch)
        //
        val cameraIntentSketch = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntentSketch.putExtra(MediaStore.EXTRA_OUTPUT, image_uri_sketch)
        startActivityForResult(cameraIntentSketch,IMAGE_CAPTURE_CODE)


    }

    private fun openCamera() {
        val valuesDTI = ContentValues()
        valuesDTI.put(MediaStore.Images.Media.TITLE, "DTI Permit")
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
            bplo_step5_dti_img!!.setImageURI(image_uri)
            bplo_step5_sketch_img!!.setImageURI(image_uri_sketch)
        }

    }


}





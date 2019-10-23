package ph.sanpablocitygov.iSanPablo

import android.content.Intent

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.home_terms_and_agreements_dialogbox.view.*
import java.util.*

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()

        val isFirstRun = sharedPreferences.getBoolean("IS_FIRST_RUN", true)
        val isFirstRun2 = sharedPreferences.getBoolean("IS_FIRST_RUN", false)
        if(isFirstRun) {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflaters = this.layoutInflater
            val dialogView = inflaters.inflate(R.layout.home_terms_and_agreements_dialogbox, null)
            dialogBuilder.setView(dialogView)
            dialogBuilder.setCancelable(false)
            val alertDialog = dialogBuilder.create()
//             Update
            dialogView.checkbox_accept.setOnCheckedChangeListener { buttonView, isChecked ->
                editor.putBoolean("IS_FIRST_RUN", false)
                alertDialog.dismiss()
                editor.apply()

                Timer().schedule( object: TimerTask(){
                    override fun run() {
                        val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }, 1200L)
            }
            alertDialog.show()
        }else{

            Timer().schedule( object: TimerTask(){
                override fun run() {
                    val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }, 1200L)
        }


    }
}

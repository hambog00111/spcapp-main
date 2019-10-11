package ph.sanpablocitygov.iSanPablo

import android.content.Intent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.util.*

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        Timer().schedule( object: TimerTask(){
            override fun run() {
                val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 1200L)
    }
}

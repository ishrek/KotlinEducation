package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.commonKotlin.DemoSendData.usingParcelable.PhieuChi
import com.example.myapplication.commonKotlin.DemoSendData.usingSerializable.ObjectIntent
import com.example.myapplication.commonKotlin.Utils.EXTRA_MESSAGE


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val message =  intent.getStringExtra(EXTRA_MESSAGE())
        val  obj = intent.extras?.get("extra_object") as ObjectIntent
        Log.d("Shrek", message)
        Log.d("Shrek", obj.gender)

        //get obj from intent via parcelable
        val args = intent.getBundleExtra("MY_PARAMS")
        val pc = args.getParcelable<PhieuChi>("PHIEU_CHI")
        if (pc != null) {
            Log.d("Shrek", pc.ngayGio)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d("Shrek", "onOptionsItemSelected")
                finish()
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("Shrek", "onBackPressed")
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }
}
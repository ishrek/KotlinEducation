package com.example.myapplication.commonKotlin.demoFragment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.demoFragment.inferfaceFragment.IDemoFragment

/**
 * https://yellowcodebooks.com/2017/12/09/android-bai-32-hien-thi-fragment/
 * https://freetuts.net/fragment-trong-android-2154.html
 * https://dotrinh.com/fragment-va-activity-trong-android-co-ban/
 */

class demoFragmentActivity : AppCompatActivity(), IDemoFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_fragment)
        ButterKnife.bind(this)

//        val parentVC_before = supportFragmentManager.fragments
        Log.d("ishrek", "getListFragments")
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        Log.d("ishrek", "onAttachFragment")
    }

    /**
        Send data between 2 fragment via Activity
        Fm1 -> activity -> fm2
    */
    override fun sendData(name: String?, age: String?) {
        val twoFragment: TwoFragment = supportFragmentManager.findFragmentById(R.id.twoFragment) as TwoFragment
        if (twoFragment != null) {
            if (twoFragment.isInLayout) { // kiem tra fragment can truyen data den co thuc su ton tai va dang hien
                twoFragment.showValue(name)
            } else {
                Toast.makeText(applicationContext, "Fragment is not exist", Toast.LENGTH_LONG).show();
            }
        }
    }

//    // Add dynamic fragment
//    @OnClick(R.id.btn_addFragment)
//    fun addNewFragmet() {
//        //remove fm
//        val  fm = supportFragmentManager
//        val ft_add = fm.beginTransaction()
//        ft_add.add(R.id.frame_layout, ThreeFragment())
//        ft_add.commit()
//        val parentVC_before = supportFragmentManager.fragments
//        Log.d("ishrek", "getListFragments")
//    }
}
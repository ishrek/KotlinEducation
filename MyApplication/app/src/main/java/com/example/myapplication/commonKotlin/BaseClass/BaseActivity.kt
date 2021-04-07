package com.example.myapplication.commonKotlin.BaseClass

import android.app.Fragment
import android.app.FragmentTransaction
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


//https://viblo.asia/p/xay-dung-base-class-activity-fragment-3P0lPeN45ox
abstract class BaseActivity: AppCompatActivity() {

    abstract fun getLayoutID(): Int
    abstract fun onCreateActivity(savedInstanceState: Bundle?)
    abstract fun onDestroyActivity()
    var isNetworkState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        onCreateActivity(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyActivity()
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }

    /**
     * Adds a [Fragment] to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected open fun addFragment(containerViewId: Int, fragment: Fragment?) {
        val fragmentTransaction: FragmentTransaction = this.fragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }
}

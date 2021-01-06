package com.example.myapplication.commonKotlin.demoFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.demoFragment.inferfaceFragment.SendData
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * A simple [Fragment] subclass.
 * Use the [ThreeFragment.newInstance] factory method to
 * - Send data via EventBus library
 * weakness: Tat ca cho nao lang nghe post deu nhan duoc du lieu
 */
class ThreeFragment : Fragment() {

    @BindView(R.id.textView)
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_three, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
//        Log.i("TAG", "onStart ThreeFragment");
        EventBus.getDefault().register(this);
    }

    override fun onStop() {
//        Log.i("TAG", "onStop ThreeFragment");
        EventBus.getDefault().unregister(this);
        super.onStop()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun subscribeData(data: SendData) {
        textView.text = data.user
    }
}
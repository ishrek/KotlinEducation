package com.example.myapplication.commonKotlin.demoFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.demoFragment.inferfaceFragment.IDemoFragment
import com.example.myapplication.commonKotlin.demoFragment.inferfaceFragment.SendData
import org.greenrobot.eventbus.EventBus


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {

    @BindView(R.id.textView1)
    lateinit var text_input: TextView

    var listener: IDemoFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ishrek", "onCreate fragment")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ishrek", "onCreateView")
        val view = inflater.inflate(R.layout.fragment_one, container, false)
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("ishrek", "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("ishrek", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ishrek", "onResume")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("ishrek", "onResume")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("ishrek", "onAttach")
        if (context is demoFragmentActivity) listener =
            context as IDemoFragment // gan listener vao MainActivity
        else throw RuntimeException(
            "$context must implement onViewSelected!"
        )
        Toast.makeText(activity, "Fragment : onAttach", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("ishrek", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ishrek", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("ishrek", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ishrek", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("ishrek", "onDetach")
    }

    @OnClick(R.id.send)
    fun sendTapped() {
        listener?.sendData("Saka", "")
    }

    @OnClick(R.id.SendViaEventBus)
    fun sendViaEventBus() {
        Log.d("ishrek", "sendViaEventBus")
        EventBus.getDefault().post(SendData("Gabriel"))
    }
}
package com.example.myapplication.commonKotlin.sharePreferences

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.myapplication.R


class SharePreferenceActivity : AppCompatActivity() {

    @BindView(R.id.seekBar_sound)
    lateinit var seekBarSound: SeekBar

    @BindView(R.id.seekBar_brightness)
    lateinit var seekBarBrightness: SeekBar

    @BindView(R.id.radioGroup_diffLevel)
    lateinit var radioGroupDiffLevel: RadioGroup

    @BindView(R.id.radioButton_easy)
    lateinit var radioButtonEasy: RadioButton

    @BindView(R.id.radioButton_medium)
    lateinit var radioButtonMedium: RadioButton

    @BindView(R.id.radioButton_hard)
    lateinit var radioButtonHard: RadioButton

    @BindView(R.id.button_save)
    lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_preference)
        ButterKnife.bind(this)
        seekBarSound.max = 100
        seekBarBrightness.max = 100
        loadGameSetting()
    }

    private fun loadGameSetting() {
        val sharedPreferences = getSharedPreferences("gameSetting", MODE_PRIVATE)
        if (sharedPreferences != null) {
            val brightness = sharedPreferences.getInt("brightness", 90)
            val sound = sharedPreferences.getInt("sound", 95)
            val checkedRadioButtonId = sharedPreferences.getInt("checkedRadioButtonId", R.id.radioButton_medium)
            seekBarSound.progress = sound
            seekBarBrightness.progress = brightness
            radioGroupDiffLevel.check(checkedRadioButtonId)
        } else {
            radioGroupDiffLevel.check(R.id.radioButton_medium)
            Toast.makeText(this, "Use the default game setting", Toast.LENGTH_LONG).show()
        }
    }

    // Called when user click to Save button.
    @OnClick(R.id.button_save)
    fun doSave() {
        // The created file can only be accessed by the calling application
        // (or all applications sharing the same user ID).
        val sharedPreferences = getSharedPreferences("gameSetting", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("brightness", seekBarBrightness.progress)
        editor.putInt("sound", seekBarSound.progress)

        // Checked RadioButton ID.
        val checkedRadioButtonId = radioGroupDiffLevel.checkedRadioButtonId
        editor.putInt("checkedRadioButtonId", checkedRadioButtonId)

        // Save.
        editor.apply()
        Toast.makeText(this, "Game Setting saved!", Toast.LENGTH_LONG).show()
    }
}
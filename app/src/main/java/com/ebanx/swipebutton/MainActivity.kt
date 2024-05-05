package com.ebanx.swipebutton

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ebanx.swipebutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.included.swipeBtnEnabled.background = ContextCompat.getDrawable(this, R.drawable.shape_button2)
        binding.included.swipeBtnEnabled.setSlidingButtonBackground(ContextCompat.getDrawable(this, R.drawable.shape_rounded2))

        binding.included.swipeBtnEnabled.setOnStateChangeListener { active ->
            Toast.makeText(this@MainActivity, "State: " + active, Toast.LENGTH_SHORT).show()
            if (active) {
                binding.included.swipeBtnEnabled.setButtonBackground(ContextCompat.getDrawable(this@MainActivity, R.drawable.shape_button))
            } else {
                binding.included.swipeBtnEnabled.setButtonBackground(ContextCompat.getDrawable(this@MainActivity, R.drawable.shape_button3))
            }
        }

//        swipeBtnDisabled.setDisabledStateNotAnimated()
        binding.included.swipeBtnEnabled.setEnabledStateNotAnimated()

        binding.included.swipeNoState.setOnActiveListener { Toast.makeText(this@MainActivity, "Active!", Toast.LENGTH_SHORT).show() }

        binding.included.toggleBtn.setOnClickListener {
            if (!binding.included.swipeBtnEnabled.isActive) {
                binding.included.swipeBtnEnabled.toggleState()
            }
        }

    }

}

package com.example.fragmentresultsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonRecreate.setOnClickListener {
            recreate()
        }

        if (supportFragmentManager.findFragmentByTag("main_fragment") == null) {
            supportFragmentManager.commit {
                add<MainFragment>(R.id.fragmentContainer, "main_fragment")
            }
        }
    }
}
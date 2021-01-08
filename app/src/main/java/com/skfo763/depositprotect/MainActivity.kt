package com.skfo763.depositprotect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skfo763.depositprotect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var testBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(testBinding.root)

        testBinding.test.setOnClickListener {
            throw IllegalStateException("test crash")
        }

    }
}
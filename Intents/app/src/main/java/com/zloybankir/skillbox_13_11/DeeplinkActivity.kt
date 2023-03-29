package com.zloybankir.skillbox_13_11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zloybankir.skillbox_13_11.databinding.ActivityDeeplinkBinding

class DeeplinkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeeplinkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDeeplinkBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleIntentData()
    }

    private fun handleIntentData() {
        binding.textViewDomainName.text = intent.data?.host
        binding.textViewPageName.text = intent.data?.lastPathSegment
    }
}
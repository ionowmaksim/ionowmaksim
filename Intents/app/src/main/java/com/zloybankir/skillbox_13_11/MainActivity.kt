package com.zloybankir.skillbox_13_11

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.zloybankir.skillbox_13_11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private val dialActivityLauncher = registerForActivityResult(DialActivityContract()) { result ->
        if (result == Activity.RESULT_OK) {
            showToast("The call was successful")
        } else {
            showToast("Call canceled")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializationUI()
    }

    private fun initializationUI() {
        binding.buttonCall.setOnClickListener { openActionDial() }
    }

    private fun openActionDial() {
        val phoneNumber = binding.editTextPhoneNumber.text.toString()
        val isNumberValid = Patterns.PHONE.matcher(phoneNumber).matches()
        val isCallPhonePermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED

        if (!isCallPhonePermissionGranted) {
            getCallPhonePermission()
        } else if (!isNumberValid) {
            showToast("Invalid number")
        } else {
//            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
//                data = Uri.parse("tel:$phoneNumber")
//            }
//            if (dialIntent.resolveActivity(packageManager) != null) {
//                startActivityForResult(dialIntent, DIAL_REQUEST_CODE)
//            }
            DebugLogger.d(tag, "-----------------")
            dialActivityLauncher.launch(phoneNumber)
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun getCallPhonePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CALL_PHONE),
            DIAL_PERMISSION_CODE
        )
    }

    companion object {
        private const val DIAL_PERMISSION_CODE = 123
        private const val DIAL_REQUEST_CODE = 455
    }
}
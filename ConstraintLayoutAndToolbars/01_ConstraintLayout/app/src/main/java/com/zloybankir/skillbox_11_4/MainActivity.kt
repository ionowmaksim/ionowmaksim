package com.zloybankir.skillbox_11_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.zloybankir.skillbox_11_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkFillingFields()
            }
        }

        binding.editTextEmail.addTextChangedListener(watcher)
        binding.editTextPassword.addTextChangedListener(watcher)
        binding.checkBoxLicense.setOnCheckedChangeListener { _, _ -> checkFillingFields() }
        binding.buttonLogin.setOnClickListener { showProgressBar() }
    }

    private fun checkFillingFields() {
        binding.buttonLogin.isEnabled =
            binding.editTextEmail.text.isNotBlank() && binding.editTextPassword.text.isNotBlank() &&
                    binding.checkBoxLicense.isChecked
    }

    private fun showProgressBar() {
        val progressBar = layoutInflater.inflate(R.layout.progress_bar, binding.root, false)

        val constraintSet = ConstraintSet() //TODO не работает привязка progressBar
        constraintSet.clone(binding.root)
        constraintSet.connect(progressBar.id, ConstraintSet.TOP, binding.buttonLogin.id, ConstraintSet.BOTTOM)
        constraintSet.applyTo(binding.root)


        binding.root.addView(progressBar)
        Handler().postDelayed({
            changeEnabledParam(true)
            Toast.makeText(this, R.string.toast_loginComplete, Toast.LENGTH_SHORT).show()
            binding.root.removeView(progressBar)
        }, 2000)
    }

    private fun changeEnabledParam(status: Boolean) {
        binding.apply {
            editTextEmail.isEnabled = status
            editTextPassword.isEnabled = status
            checkBoxLicense.isEnabled = status
            buttonLogin.isEnabled = status
        }
    }
}
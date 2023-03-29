package com.zloybankir.skillbox_12_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import com.zloybankir.skillbox_12_12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var authStatusState: AuthorizationStatusState

    override fun onCreate(savedInstanceState: Bundle?) {
        DebugLogger.d(tag, "onCreate was called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializationUI()

        if (savedInstanceState != null) {
            authStatusState =
                savedInstanceState.getParcelable<AuthorizationStatusState>(KEY_AUTH_STATUS)
                    ?: error("Unexpected state")
        } else {
            authStatusState =
                AuthorizationStatusState(resources.getString(R.string.textView_status_false))
        }
        binding.textViewAutorizationStatus.text = authStatusState.message
    }

    override fun onStart() {
        super.onStart()
        DebugLogger.d(tag, "onStart was called")
    }

    override fun onResume() {
        super.onResume()
        DebugLogger.d(tag, "onResume was called")
    }

    override fun onPause() {
        super.onPause()
        DebugLogger.d(tag, "onPause was called")
    }

    override fun onStop() {
        super.onStop()
        DebugLogger.d(tag, "onStop was called")
    }

    override fun onDestroy() {
        super.onDestroy()
        DebugLogger.d(tag, "onDestroy was called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_AUTH_STATUS, authStatusState)
        DebugLogger.d(tag, "onSaveInstanceState was called")
    }

    private fun initializationUI() {
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
        binding.buttonLongOperation.setOnClickListener { longOperation() }
    }

    private fun checkFillingFields() {
        binding.buttonLogin.isEnabled =
            binding.editTextEmail.text.isNotBlank() && binding.editTextPassword.text.isNotBlank() &&
                    binding.checkBoxLicense.isChecked
    }

    private fun checkAutorization() {
        authStatusState =
            AuthorizationStatusState(resources.getString(R.string.textView_status_true))
        binding.textViewAutorizationStatus.text = authStatusState.message
    }

    private fun longOperation() {
        DebugLogger.w(tag, "Long operation started")
        Thread.sleep(10000)
        DebugLogger.w(tag, "Long operation stopped")
    }

    private fun showProgressBar() {
        changeEnabledParam(false)
        val progressBar =
            layoutInflater.inflate(R.layout.progress_bar, binding.scrolingContainer, false)
        progressBar.id = View.generateViewId()
        binding.scrolingContainer.addView(progressBar)

        val constraintSet = ConstraintSet()
        constraintSet.clone(binding.scrolingContainer)
        constraintSet.connect(
            progressBar.id, ConstraintSet.TOP, binding.buttonLongOperation.id, ConstraintSet.BOTTOM
        )
        constraintSet.connect(
            progressBar.id,
            ConstraintSet.START,
            binding.guidelineVerticalLeft.id,
            ConstraintSet.START
        )
        constraintSet.connect(
            progressBar.id, ConstraintSet.END, binding.guidelineVerticalRight.id, ConstraintSet.END
        )
        constraintSet.applyTo(binding.scrolingContainer)

        Handler().postDelayed({
            changeEnabledParam(true)
            Toast.makeText(this, R.string.toast_loginComplete, Toast.LENGTH_SHORT).show()
            binding.scrolingContainer.removeView(progressBar)
            checkAutorization()
        }, 2000)
    }

    private fun changeEnabledParam(status: Boolean) {
        binding.apply {
            editTextEmail.isEnabled = status
            editTextPassword.isEnabled = status
            checkBoxLicense.isEnabled = status
            buttonLogin.isEnabled = status
        }

        if (status) {
            DebugLogger.i(tag, "Views is enabled")
        } else {
            DebugLogger.i(tag, "Views is disabled")
        }
    }

    companion object {
        private const val KEY_AUTH_STATUS = "auth_status"
    }
}

object DebugLogger {
    fun d(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg)
        }
    }
}
package com.github.fernandospr.livedataapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActionActivity1 : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ActionActivity1::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_1)
    }
}

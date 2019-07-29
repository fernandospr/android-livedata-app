package com.github.fernandospr.livedataapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_action_2.*

class ActionActivity2 : AppCompatActivity() {

    companion object {
        private const val EXTRA_PARAM1 = "EXTRA_PARAM1"
        private const val EXTRA_PARAM2 = "EXTRA_PARAM2"

        fun newIntent(context: Context, param1: String, param2: String): Intent {
            return Intent(context, ActionActivity2::class.java).apply {
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_2)
        val param1 = intent.getStringExtra(EXTRA_PARAM1)
        val param2 = intent.getStringExtra(EXTRA_PARAM2)
        textView.text = "These are the params: [$param1, $param2]"
    }
}

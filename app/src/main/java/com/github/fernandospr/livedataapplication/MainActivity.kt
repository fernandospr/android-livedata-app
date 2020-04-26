package com.github.fernandospr.livedataapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this)[SampleViewModel::class.java]
        viewModel.getAction().observe(this, Observer {
            handleAction(it)
        })
        viewModel.getResource().observe(this, Observer {
            handleResource(it)
        })

        buttonAction1.setOnClickListener {
            viewModel.operationResultingInAction(1)
        }
        buttonAction2.setOnClickListener {
            viewModel.operationResultingInAction(2)
        }
        buttonLoadWithSuccess.setOnClickListener {
            viewModel.fetchResource(1)
        }
        buttonLoadWithError.setOnClickListener {
            viewModel.fetchResource(2)
        }

        progressBar.visibility = View.GONE
        textViewError.visibility = View.GONE
        textViewSuccess.visibility = View.GONE
    }

    private fun handleResource(lce: SampleResource) {
        when (lce) {
            is SampleResource.Loading -> showLoading()
            is SampleResource.Error -> showError(lce.message)
            is SampleResource.SuccessWithParams -> showSuccess(lce.myResource)
        }
    }

    private fun showSuccess(resource: MyResource) {
        progressBar.visibility = View.GONE
        textViewError.visibility = View.GONE
        textViewSuccess.visibility = View.VISIBLE
        textViewSuccess.text = "Hello ${resource.name}"
    }

    private fun showError(message: String) {
        progressBar.visibility = View.GONE
        textViewError.visibility = View.VISIBLE
        textViewSuccess.visibility = View.GONE
        textViewError.text = message
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        textViewError.visibility = View.GONE
        textViewSuccess.visibility = View.GONE
    }

    private fun handleAction(it: SampleAction) {
        when (it) {
            is SampleAction.Action1 -> performAction1()
            is SampleAction.Action2WithParams -> performAction2(it.param1, it.param2)
        }
    }

    private fun performAction1() {
        startActivity(ActionActivity1.newIntent(this))
    }

    private fun performAction2(param1: String, param2: String) {
        startActivity(ActionActivity2.newIntent(this, param1, param2))
    }
}

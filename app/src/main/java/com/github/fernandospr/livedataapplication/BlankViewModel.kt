package com.github.fernandospr.livedataapplication

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BlankViewModel : ViewModel() {
    val first = MutableLiveData(false)
    val second = MutableLiveData(false)

    val enabled = MediatorLiveData<Boolean>().apply {
        addSource(first) { value = validateAll() }
        addSource(second) { value = validateAll() }
    }

    private fun validateAll(): Boolean {
        return first.value == true && second.value == true
    }
}

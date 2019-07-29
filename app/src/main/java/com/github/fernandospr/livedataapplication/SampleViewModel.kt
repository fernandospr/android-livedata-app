package com.github.fernandospr.livedataapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SampleViewModel(private val sleepSampleMillis: Long = 5000) : ViewModel() {

    private val _resource = MutableLiveData<SampleResource>()
    private val _action = SingleLiveEvent<SampleAction>()

    private val composite = CompositeDisposable()

    override fun onCleared() = composite.clear()

    fun getAction(): LiveData<SampleAction> = _action
    fun getResource(): LiveData<SampleResource> = _resource

    fun fetchResource(value: Int) {
        _resource.value = SampleResource.Loading
        composite.add(
            expensiveOperation(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = { _resource.value = SampleResource.Error(it.message ?: "Generic error") },
                    onSuccess = { _resource.value = SampleResource.SuccessWithParams(it) }
                )
        )
    }

    private fun expensiveOperation(value: Int): Single<MyResource> {
        return Single.fromCallable {
            Thread.sleep(sleepSampleMillis)
            if (value > 1) {
                throw Exception("This is an exception")
            }
            MyResource("Fernando")
        }
    }

    fun operationResultingInAction(value: Int) {
        if (complexLogic(value)) {
            _action.value = SampleAction.Action1
        } else {
            _action.value = SampleAction.Action2WithParams("Hello", "World")
        }
    }

    private fun complexLogic(value: Int) = value == 1
}
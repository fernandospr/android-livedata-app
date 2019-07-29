package com.github.fernandospr.livedataapplication

sealed class SampleResource {
    object Loading : SampleResource()
    data class Error(val message: String) : SampleResource()
    data class SuccessWithParams(val myResource: MyResource) : SampleResource()
}

data class MyResource(val name: String)
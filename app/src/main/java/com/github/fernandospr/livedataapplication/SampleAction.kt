package com.github.fernandospr.livedataapplication

sealed class SampleAction {
    object Action1 : SampleAction()
    data class Action2WithParams(val param1: String, val param2: String) : SampleAction()
}
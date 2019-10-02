package com.github.fernandospr.livedataapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BlankViewModelUnitTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: BlankViewModel

    @Before
    fun setup() {
        viewModel = BlankViewModel()
        viewModel.enabled.observeForever {  }
    }

    @Test
    fun `Setting first and second to true should enable`() {
        viewModel.first.value = true
        viewModel.second.value = true

        Assert.assertTrue(viewModel.enabled.value!!)
    }

    @Test
    fun `Setting first to true and second to false should not enable`() {
        viewModel.first.value = true
        viewModel.second.value = false

        Assert.assertFalse(viewModel.enabled.value!!)
    }

    @Test
    fun `Setting first to false and second to true should not enable`() {
        viewModel.first.value = false
        viewModel.second.value = true

        Assert.assertFalse(viewModel.enabled.value!!)
    }

    @Test
    fun `Setting first and second to false should not enable`() {
        viewModel.first.value = false
        viewModel.second.value = false

        Assert.assertFalse(viewModel.enabled.value!!)
    }
}

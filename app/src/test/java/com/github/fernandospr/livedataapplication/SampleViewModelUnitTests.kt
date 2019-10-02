package com.github.fernandospr.livedataapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SampleViewModelUnitTests {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val rxRule = RxSchedulerRule()

    private lateinit var viewModel: SampleViewModel

    private lateinit var actionObserver: Observer<SampleAction>
    private lateinit var resourceObserver: Observer<SampleResource>

    @Before
    fun setup() {
        actionObserver = mock()
        resourceObserver = mock()

        viewModel = SampleViewModel(0L)
        viewModel.getAction().observeForever(actionObserver)
        viewModel.getResource().observeForever(resourceObserver)
    }

    @Test
    fun `Fetch resource should show loading`() {
        viewModel.fetchResource(1)

        verify(resourceObserver).onChanged(eq(SampleResource.Loading))
    }

    @Test
    fun `Fetch resource should show success`() {
        viewModel.fetchResource(1)

        val expected = SampleResource.SuccessWithParams(MyResource("Fernando"))
        verify(resourceObserver).onChanged(eq(expected))
    }

    @Test
    fun `Fetch resource should show error`() {
        viewModel.fetchResource(2)

        val expected = SampleResource.Error("This is an exception")
        verify(resourceObserver).onChanged(eq(expected))
    }

    @Test
    fun `Operation should result in Action1`() {
        viewModel.operationResultingInAction(1)

        val expected = SampleAction.Action1
        verify(actionObserver).onChanged(eq(expected))
    }

    @Test
    fun `Operation should result in Action2`() {
        viewModel.operationResultingInAction(2)

        val expected = SampleAction.Action2WithParams("Hello", "World")
        verify(actionObserver).onChanged(eq(expected))
    }
}

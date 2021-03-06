package com.mupper.reactiveelements

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * Created by jesus.medina on 12/2019.
 * Insulet Corporation
 * Andromeda
 */
class ReactiveElementsGetOnIoTest {
    @SpyK
    var spyObservable = Observable.just(Unit)

    @SpyK
    var spySingle = Single.just(Unit)

    @SpyK
    var spyFlowable = Flowable.just(Unit)

    @SpyK
    var spyCompletable = Completable.complete()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `getOnIo should call subscribeOn with IO scheduler on observable element`() {
        // Given

        // When
        spyObservable.getOnIo()

        // Then
        verify {
            spyObservable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnIo should call observeOn with IO scheduler on observable element`() {
        // Given
        every { spyObservable.subscribeOn(Schedulers.io()) } returns spyObservable

        // When
        spyObservable.getOnIo()

        // Then
        verify {
            spyObservable.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnIo should call subscribeOn with IO scheduler on single element`() {
        // Given

        // When
        spySingle.getOnIo()

        // Then
        verify {
            spySingle.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnIo should call observeOn with IO scheduler on single element`() {
        // Given
        every { spySingle.subscribeOn(Schedulers.io()) } returns spySingle

        // When
        spySingle.getOnIo()

        // Then
        verify {
            spySingle.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnIo should call subscribeOn with IO scheduler on flowable element`() {
        // Given
        // When
        spyFlowable.getOnIo()

        // Then
        verify {
            spyFlowable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnIo should call observeOn with IO scheduler on flowable element`() {
        // Given
        every { spyFlowable.subscribeOn(Schedulers.io()) } returns spyFlowable

        // When
        spyFlowable.getOnIo()

        // Then
        verify {
            spyFlowable.observeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnIo should call subscribeOn with IO scheduler on completable element`() {
        // Given
        // When
        spyCompletable.getOnIo()

        // Then
        verify {
            spyCompletable.subscribeOn(Schedulers.io())
        }
    }

    @Test
    fun `getOnIo should call observeOn with IO scheduler on completable element`() {
        // Given
        every { spyCompletable.subscribeOn(Schedulers.io()) } returns spyCompletable

        // When
        spyCompletable.getOnIo()

        // Then
        verify {
            spyCompletable.observeOn(Schedulers.io())
        }
    }
}

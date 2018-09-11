package io.petros.movies.presentation.feature.movies.subscriber

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import io.petros.movies.domain.model.movie.MoviesResultPage
import io.petros.movies.test.domain.TestMoviesProvider.Companion.provideMoviesResultPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesSubscriberTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val moviesResultPage = provideMoviesResultPage()

    private lateinit var testedClass: MoviesSubscriber
    private val moviesResultPageObservableMock = mock<Observer<MoviesResultPage>>()

    @Before
    fun setUp() {
        testedClass = MoviesSubscriber(MutableLiveData())
        testedClass.moviesObservable.observeForever(moviesResultPageObservableMock)
    }

    @Test
    fun `When load movies succeeds, then the movies result page is posted`() {
        testedClass.onSuccess(moviesResultPage)

        verify(moviesResultPageObservableMock).onChanged(moviesResultPage)
    }

    @Test
    fun `When load movies fails, then the movies result page is not posted`() {
        testedClass.onError(Exception())

        verifyZeroInteractions(moviesResultPageObservableMock)
    }

}

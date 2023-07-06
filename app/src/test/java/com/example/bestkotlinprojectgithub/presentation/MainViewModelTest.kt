package com.example.bestkotlinprojectgithub.presentation

import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.example.bestkotlinprojectgithub.data.GitHubServiceApi
import com.example.bestkotlinprojectgithub.data.ListUserRepositoryUseCase
import com.example.bestkotlinprojectgithub.data.Repository
import com.example.bestkotlinprojectgithub.data.RepositoryImpl
import com.example.bestkotlinprojectgithub.model.Item
import com.example.bestkotlinprojectgithub.model.Owner
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.lang.Exception


@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = MainDispatcherRule()

    // Cria um despachante de teste para executar corrotinas de maneira síncrona
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val userCase: ListUserRepositoryUseCase = mockk()

    private lateinit var viewModel: MainViewModel

    private val observer = mockk<Observer<MainViewModel.State>>(relaxed = true)

    private val pagingDataCharacters = PagingData.from(
        listOf(
            Item(id = 0, name = "test", 12, 12, Owner(login = "12", "name.com.br")),
            Item(id = 1, name = "test2", 14, 14, Owner(login = "12", "name.com.br"))
        )
    )

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel(userCase)
        viewModel.list.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewModel call userCase get Success then sets pagingDataCharacters`() {

        coEvery { userCase.execute("") } returns flow { emit(pagingDataCharacters) }
        viewModel.initViewModelGetRepositories()

        verifyOrder {
            observer.onChanged(MainViewModel.State.Loading)
            observer.onChanged(MainViewModel.State.Success(pagingDataCharacters))
        }

    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : InstantTaskExecutorRule() {
    override fun starting(description: Description) {
        super.starting(description)

        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)

        Dispatchers.resetMain()
    }
}


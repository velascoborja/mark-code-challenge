package com.medtronic.surgery.app.presentation.viewmodel.procedures

import app.cash.turbine.test
import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepository
import com.medtronic.surgery.app.presentation.viewmodel.procedure.ProceduresListViewModel
import com.medtronic.surgery.app.support.CoroutineTestRule
import com.medtronic.surgery.app.utils.support.Fixtures.mockProceduresList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ProceduresListViewModelTest {

    @get:Rule
    val rule: TestRule = CoroutineTestRule(StandardTestDispatcher())

    private lateinit var viewModel: ProceduresListViewModel
    private val repository: ProcedureRepository = mockk()

    @Before
    fun setUp() {
        viewModel = ProceduresListViewModel(repository)
    }

    @Test
    fun `fetchProcedures should set loading state then success`() = runTest {
        coEvery { repository.fetchProcedures() } returns mockProceduresList
        viewModel = ProceduresListViewModel(repository)

        viewModel.proceduresListState.test {
            assertEquals(ProceduresListViewModel.ProceduresListState.Loading, awaitItem())
            assertEquals(
                ProceduresListViewModel.ProceduresListState.Success(mockProceduresList),
                awaitItem()
            )
        }

        coVerify { repository.fetchProcedures() }
    }

    @Test
    fun `fetchProcedures should handle empty response`() = runTest {
        coEvery { repository.fetchProcedures() } returns emptyList()
        viewModel = ProceduresListViewModel(repository)

        viewModel.proceduresListState.test {
            assertEquals(ProceduresListViewModel.ProceduresListState.Loading, awaitItem())
            assertEquals(
                ProceduresListViewModel.ProceduresListState.Error("No procedures found"),
                awaitItem()
            )
        }
    }

    @Test
    fun `fetchProcedures should handle error`() = runTest {
        coEvery { repository.fetchProcedures() } throws Exception("Network Error")
        viewModel = ProceduresListViewModel(repository)

        viewModel.proceduresListState.test {
            assertEquals(ProceduresListViewModel.ProceduresListState.Loading, awaitItem())
            assertEquals(
                ProceduresListViewModel.ProceduresListState.Error("Network Error"),
                awaitItem()
            )
        }
    }

    @Test
    fun `refreshProcedures should update isRefreshing state`() = runTest {
        coEvery { repository.fetchProcedures() } returns mockProceduresList
        viewModel = ProceduresListViewModel(repository)

        viewModel.isRefreshing.test {
            assertEquals(false, awaitItem()) // Initial state
            viewModel.refreshProcedures()
            assertEquals(true, awaitItem()) // Refresh started
            assertEquals(false, awaitItem()) // Refresh ended
        }

        coVerify { repository.fetchProcedures() }
    }

    @Test
    fun `toggleFavorite should update favorite status and refresh procedures`() = runTest {
        coEvery { repository.toggleFavoriteStatus("1") } returns Unit
        coEvery { repository.fetchProcedures() } returns mockProceduresList
        viewModel = ProceduresListViewModel(repository)

        viewModel.toggleFavorite("1")
        advanceUntilIdle()
        coVerify(exactly = 1) { repository.toggleFavoriteStatus("1") }
    }
}
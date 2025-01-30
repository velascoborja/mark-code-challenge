package com.medtronic.surgery.app.presentation.viewmodel.details

import app.cash.turbine.test
import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepository
import com.medtronic.surgery.app.support.CoroutineTestRule
import com.medtronic.surgery.app.utils.support.Fixtures.mockProcedureDetails
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

class ProcedureDetailsViewModelTest {

    @get:Rule
    val rule: TestRule = CoroutineTestRule(StandardTestDispatcher())

    private lateinit var viewModel: ProcedureDetailsViewModel
    private val repository: ProcedureRepository = mockk()

    @Before
    fun setUp() {
        viewModel = ProcedureDetailsViewModel(repository)
    }

    @Test
    fun `fetchProcedureDetails should set loading state then success`() = runTest {
        coEvery { repository.fetchProcedureDetails("1") } returns mockProcedureDetails
        viewModel = ProcedureDetailsViewModel(repository)

        viewModel.procedureDetailsState.test {
            viewModel.fetchProcedureDetails("1")
            assertEquals(ProcedureDetailsViewModel.ProcedureDetailsState.Loading, awaitItem())
            assertEquals(
                ProcedureDetailsViewModel.ProcedureDetailsState.Success(mockProcedureDetails),
                awaitItem()
            )
            coVerify { repository.fetchProcedureDetails("1") }
        }
    }

    @Test
    fun `fetchProcedureDetails should handle error`() = runTest {
        coEvery { repository.fetchProcedureDetails("1") } throws Exception("Network Error")
        viewModel = ProcedureDetailsViewModel(repository)

        viewModel.procedureDetailsState.test {
            viewModel.fetchProcedureDetails("1")
            assertEquals(ProcedureDetailsViewModel.ProcedureDetailsState.Loading, awaitItem())
            assertEquals(
                ProcedureDetailsViewModel.ProcedureDetailsState.Error("Network Error"),
                awaitItem()
            )
        }
    }

    @Test
    fun `toggleFavorite should update favorite status `() = runTest {
        coEvery { repository.toggleFavoriteStatus("1") } returns Unit
        viewModel = ProcedureDetailsViewModel(repository)

        viewModel.toggleFavorite("1")
        advanceUntilIdle()
        coVerify(exactly = 1) { repository.toggleFavoriteStatus("1") }
    }
}
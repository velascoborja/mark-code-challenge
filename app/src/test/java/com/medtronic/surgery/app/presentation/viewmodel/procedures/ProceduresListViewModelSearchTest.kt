package com.medtronic.surgery.app.presentation.viewmodel.procedures

import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepository
import com.medtronic.surgery.app.presentation.ui.filter.FilterProcedureType
import com.medtronic.surgery.app.presentation.viewmodel.procedure.ProceduresListViewModel
import com.medtronic.surgery.app.support.CoroutineTestRule
import com.medtronic.surgery.app.utils.support.Fixtures.mockProcedure
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ProceduresListViewModelSearchTest {

    @get:Rule
    val rule: TestRule = CoroutineTestRule(StandardTestDispatcher())

    private lateinit var viewModel: ProceduresListViewModel
    private val repository: ProcedureRepository = mockk()

    @Before
    fun setUp() {
        viewModel = ProceduresListViewModel(repository)
    }

    private val mockProcedures = listOf(
        mockProcedure.copy(uuid = "1", name = "Cemented Hip Cup", duration = 180),
        mockProcedure.copy(uuid = "2", name = "Lateral Approach to the Hip", duration = 75, isFavorite = true),
        mockProcedure.copy(uuid = "3", name = "IMA Harvesting", duration = 90)
    )

    @Test
    fun `filterProcedures should return all procedures when searchQuery is empty and filter is NONE`() {
        val result = viewModel.filterProcedures(mockProcedures, "", FilterProcedureType.NONE) { true }
        assertEquals(3, result.size)
    }

    @Test
    fun `filterProcedures should return only favorite procedures`() {
        val result = viewModel.filterProcedures(mockProcedures, "", FilterProcedureType.NONE) { it.isFavorite }
        assertEquals(1, result.size)
        assertEquals("Lateral Approach to the Hip", result.first().name)
    }

    @Test
    fun `filterProcedures should return matching procedures based on search query`() {
        val result = viewModel.filterProcedures(mockProcedures, "Lateral", FilterProcedureType.NONE) { true }
        assertEquals(1, result.size)
        assertEquals("Lateral Approach to the Hip", result.first().name)
    }

    @Test
    fun `filterProcedures should return empty when search query has no match`() {
        val result = viewModel.filterProcedures(mockProcedures, "Brain", FilterProcedureType.NONE) { true }
        assertEquals(0, result.size)
    }

    @Test
    fun `filterProcedures should sort by duration ascending`() {
        val result = viewModel.filterProcedures(mockProcedures, "", FilterProcedureType.DURATION_ASCENDING) { true }
        assertEquals(listOf("Lateral Approach to the Hip", "IMA Harvesting", "Cemented Hip Cup"), result.map { it.name })
    }

    @Test
    fun `filterProcedures should sort by duration descending`() {
        val result = viewModel.filterProcedures(mockProcedures, "", FilterProcedureType.DURATION_DESCENDING) { true }
        assertEquals(listOf("Cemented Hip Cup", "IMA Harvesting", "Lateral Approach to the Hip"), result.map { it.name })
    }

    @Test
    fun `filterProcedures should sort alphabetically ascending`() {
        val result = viewModel.filterProcedures(mockProcedures, "", FilterProcedureType.ALPHABETICAL_ASCENDING) { true }
        assertEquals(listOf("Cemented Hip Cup", "IMA Harvesting", "Lateral Approach to the Hip"), result.map { it.name })
    }

    @Test
    fun `filterProcedures should sort alphabetically descending`() {
        val result = viewModel.filterProcedures(mockProcedures, "", FilterProcedureType.ALPHABETICAL_DESCENDING) { true }
        assertEquals(listOf("Lateral Approach to the Hip", "IMA Harvesting", "Cemented Hip Cup"), result.map { it.name })
    }

    @Test
    fun `filterProcedures should apply search, favorite filter, and sorting together`() {
        val result = viewModel.filterProcedures(mockProcedures, "Lateral", FilterProcedureType.ALPHABETICAL_ASCENDING) { it.isFavorite }
        assertEquals(1, result.size)
        assertEquals("Lateral Approach to the Hip", result.first().name)
    }
}
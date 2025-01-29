package com.medtronic.surgery.app.data.repository.procedure

import com.medtronic.surgery.app.data.local.dao.ProcedureDao
import com.medtronic.surgery.app.data.local.dao.ProcedureDetailsDao
import com.medtronic.surgery.app.data.service.procedure.ProcedureService
import com.medtronic.surgery.app.utils.support.Fixtures.mockProceduresList
import com.medtronic.surgery.app.utils.support.Fixtures.mockProcedureDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProcedureRepositoryTest {

    private lateinit var repository: ProcedureRepositoryImpl
    private val procedureDao: ProcedureDao = mockk(relaxed = true)
    private val procedureDetailsDao: ProcedureDetailsDao = mockk(relaxed = true)
    private val procedureService: ProcedureService = mockk()

    @Before
    fun setup() {
        repository = ProcedureRepositoryImpl(
            procedureDao = procedureDao,
            procedureDetailsDao = procedureDetailsDao,
            procedureService = procedureService
        )
    }

    @Test
    fun `fetchProcedures should fetch from service and store in dao`() = runTest {
        // Mock service call success
        coEvery { procedureService.getProcedures() } returns mockProceduresList
        coEvery { procedureDao.insertProcedures(any()) } returns Unit

        val result = repository.fetchProcedures()

        // Verify database save and expected result
        coVerify { procedureDao.insertProcedures(mockProceduresList) }
        assertEquals(mockProceduresList, result)
    }

    @Test
    fun `fetchProcedures should return cached data when service fails`() = runTest {
        // Simulate API failure, fallback to DB
        coEvery { procedureService.getProcedures() } throws Exception("Network Error")
        coEvery { procedureDao.getAllProcedures() } returns flowOf(mockProceduresList)

        val result = repository.fetchProcedures()

        // Verify cache usage
        assertEquals(mockProceduresList, result)
    }

    @Test
    fun `fetchProcedureDetails should fetch from service and store in dao`() = runTest {
        // Mock service success
        coEvery { procedureService.getProcedureDetails("1") } returns mockProcedureDetails
        coEvery { procedureDetailsDao.insertProcedureDetails(any()) } returns Unit

        val result = repository.fetchProcedureDetails("1")

        // Verify database save and expected result
        coVerify { procedureDetailsDao.insertProcedureDetails(mockProcedureDetails) }
        assertEquals(mockProcedureDetails, result)
    }

    @Test
    fun `fetchProcedureDetails should return cached data when service fails`() = runTest {
        // Simulate API failure, fallback to DB
        coEvery { procedureService.getProcedureDetails("1") } throws Exception("Network Error")
        coEvery { procedureDetailsDao.getProcedureDetailsByUuid("1") } returns flowOf(
            mockProcedureDetails
        )

        val result = repository.fetchProcedureDetails("1")

        // Verify cache usage
        assertEquals(mockProcedureDetails, result)
    }

    @Test
    fun `toggleFavoriteStatus should update favorite status in both dao`() = runTest {
        // Mock DAO response
        coEvery { procedureDao.getProcedureByUuid("1") } returns mockProceduresList[0]
        coEvery { procedureDetailsDao.getProcedureDetailsByUuid("1") } returns flowOf(
            mockProcedureDetails
        )

        // Call function
        repository.toggleFavoriteStatus("1")

        // Verify favorite status toggled
        coVerify { procedureDao.updateFavoriteStatus("1", true) }
        coVerify { procedureDetailsDao.updateFavoriteStatus("1", true) }
    }
}
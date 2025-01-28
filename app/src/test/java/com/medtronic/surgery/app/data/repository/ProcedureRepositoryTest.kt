package com.medtronic.surgery.app.data.repository

import com.medtronic.surgery.app.data.local.dao.ProcedureDao
import com.medtronic.surgery.app.data.local.dao.ProcedureDetailsDao
import com.medtronic.surgery.app.data.model.image.ImageMetadata
import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetailsPhase
import com.medtronic.surgery.app.data.repository.procedure.ProcedureRepositoryImpl
import com.medtronic.surgery.app.data.service.procedure.ProcedureService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProcedureRepositoryTest {

    private lateinit var repository: ProcedureRepositoryImpl
    private val procedureDao: ProcedureDao = mockk()
    private val procedureDetailsDao: ProcedureDetailsDao = mockk()
    private val procedureService: ProcedureService = mockk()

    private val mockImageData = ImageMetadata(
        uuid = "uuid",
        url = "url",
        version = "version"
    )

    private val mockProcedures = listOf(
        Procedure(
            uuid = "uuid",
            name = "name",
            thumbnail = mockImageData,
            phases = listOf("phase1", "phase2"),
            duration = 2480,
            isFavorite = false,
            publishedAt = "2015-04-14T10:00:51.978180",
        )
    )

    private val mockProceduresDetails = ProcedureDetails(
        uuid = "uuid",
        phases = listOf(
            ProcedureDetailsPhase(
                uuid = "uuid",
                name = "name",
                icon = mockImageData,
            )
        ),
        icon = mockImageData,
        card = mockImageData,
        duration = 2480,
        isFavorite = false,
        publishedAt = "2015-04-14T10:00:51.978180",
    )

    @Before
    fun setup() {
        repository = ProcedureRepositoryImpl(
            procedureDao = procedureDao,
            procedureDetailsDao = procedureDetailsDao,
            procedureService = procedureService
        )
    }

    @Test
    fun `refreshProcedures should fetch procedures from service and save to dao`() = runBlocking {
        // Mock service behavior
        coEvery { procedureService.getProcedures() } returns mockProcedures
        coEvery { procedureDao.insertProcedures(any()) } returns Unit
        val result = repository.refreshProcedures()
        // Verify dao insertions
        coVerify { procedureDao.insertProcedures(mockProcedures) }
        assertEquals(Unit, result.value())
    }

    @Test
    fun `watchProcedures should return flow of procedures from dao`() = runBlocking {
        // Mock dao behavior
        coEvery { procedureDao.getAllProcedures() } returns flowOf(mockProcedures)
        // Call watchProcedures
        val result = repository.watchProcedures()
        // Collect result and verify
        result.collect { procedures ->
            assertEquals(mockProcedures, procedures)
        }
    }

    @Test
    fun `refreshProcedureDetails should fetch procedure details from service and save to dao`() = runBlocking {
        // Mock service behavior
        coEvery { procedureService.getProcedureDetails("1") } returns mockProceduresDetails
        coEvery { procedureDetailsDao.insertProcedureDetails(any()) } returns Unit

        // Call refreshProcedureDetails
        val result = repository.refreshProcedureDetails("1")

        // Verify dao insertion and assert response
        coVerify { procedureDetailsDao.insertProcedureDetails(mockProceduresDetails) }
        assertEquals(Unit, result.value())
    }

    @Test
    fun `watchProcedureDetails should return flow of procedure details from dao`() = runBlocking {
        // Mock dao behavior
        coEvery { procedureDetailsDao.getProcedureDetailsByUuid("1") } returns flowOf(mockProceduresDetails)
        // Call watchProcedureDetails
        val result = repository.watchProcedureDetails("1")
        // Collect result and verify
        result.collect { procedureDetails ->
            assertEquals(mockProceduresDetails, procedureDetails)
        }
    }

    @Test
    fun `toggleFavoriteStatus should update favorite status in both dao`() = runBlocking {

        // Mock dao behavior
        coEvery { procedureDao.getProcedureByUuid("1") } returns mockProcedures[0]
        coEvery { procedureDao.updateFavoriteStatus("1", true) } returns Unit
        coEvery { procedureDetailsDao.getProcedureDetailsByUuid("1") } returns flowOf(mockProceduresDetails)
        coEvery { procedureDetailsDao.updateFavoriteStatus("1", true) } returns Unit

        // Call toggleFavoriteStatus
        repository.toggleFavoriteStatus("1")

        // Verify favorite status updated
        coVerify { procedureDao.updateFavoriteStatus("1", true) }
        coVerify { procedureDetailsDao.updateFavoriteStatus("1", true) }
    }
}
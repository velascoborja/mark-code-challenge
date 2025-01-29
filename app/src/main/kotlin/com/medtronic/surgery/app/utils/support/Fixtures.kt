package com.medtronic.surgery.app.utils.support

import com.medtronic.surgery.app.data.model.image.ImageMetadata
import com.medtronic.surgery.app.data.model.procedure.Procedure
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetails
import com.medtronic.surgery.app.data.model.procedure_details.ProcedureDetailsPhase

object Fixtures {
    val mockImageData = ImageMetadata(
        uuid = "uuid",
        url = "url",
        version = "version"
    )

    val mockProcedure = Procedure(
        uuid = "uuid",
        name = "name",
        thumbnail = mockImageData,
        phases = listOf("phase1", "phase2"),
        duration = 2480,
        isFavorite = false,
        publishedAt = "2015-04-14T10:00:51.978180",
    )

    val mockProceduresList = listOf(
        mockProcedure
    )

    val mockProcedureDetails = ProcedureDetails(
        uuid = "uuid",
        name = "name",
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
}
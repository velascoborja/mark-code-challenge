package com.medtronic.surgery.app.presentation.ui.procedures

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import com.medtronic.surgery.app.presentation.ui.filter.FilterProcedureType
import com.medtronic.surgery.app.presentation.viewmodel.procedures.FakeProceduresListViewModel
import com.medtronic.surgery.app.utils.support.Fixtures.mockProcedure
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProceduresListScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val mockProcedures = listOf(
        mockProcedure.copy(uuid = "1", name = "Heart Surgery", duration = 90, isFavorite = false),
        mockProcedure.copy(uuid = "2", name = "Knee Replacement", duration = 120, isFavorite = true),
        mockProcedure.copy(uuid = "3", name = "Appendectomy", duration = 60, isFavorite = false)
    )

    @Test
    fun proceduresListScreen_displaysProcedures() {
        composeTestRule.setContent {
            ProceduresListScreen(
                proceduresListViewModel = FakeProceduresListViewModel(mockProcedures),
                type = ProceduresListType.ALL
            )
        }

        composeTestRule.onNodeWithText("Heart Surgery").assertExists()
        composeTestRule.onNodeWithText("Knee Replacement").assertExists()
        composeTestRule.onNodeWithText("Appendectomy").assertExists()
    }

    @Test
    fun search_procedure_updatesList() {
        composeTestRule.setContent {
            ProceduresListScreen(
                proceduresListViewModel = FakeProceduresListViewModel(mockProcedures),
                type = ProceduresListType.ALL
            )
        }

        composeTestRule.onNodeWithTag("test_search_bar").performTextInput("Knee")
        composeTestRule.onNodeWithText("Knee Replacement").assertExists()
        composeTestRule.onNodeWithText("Heart Surgery").assertDoesNotExist()
        composeTestRule.onNodeWithText("Appendectomy").assertDoesNotExist()
    }

    @Test
    fun click_filterIcon_opensFilterDialog() {
        composeTestRule.setContent {
            ProceduresListScreen(
                proceduresListViewModel = FakeProceduresListViewModel(mockProcedures),
                type = ProceduresListType.ALL
            )
        }

        composeTestRule.onNodeWithTag("test_filter_button").performClick()
        composeTestRule.onNodeWithText(context.getString(FilterProcedureType.DURATION_ASCENDING.filterName)).assertExists()
        composeTestRule.onNodeWithText(context.getString(FilterProcedureType.DURATION_DESCENDING.filterName)).assertExists()
        composeTestRule.onNodeWithText(context.getString(FilterProcedureType.ALPHABETICAL_ASCENDING.filterName)).assertExists()
        composeTestRule.onNodeWithText(context.getString(FilterProcedureType.ALPHABETICAL_DESCENDING.filterName)).assertExists()
        composeTestRule.onNodeWithText(context.getString(FilterProcedureType.NONE.filterName)).assertExists()
    }
}
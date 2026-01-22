package com.and.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.and.domain.model.type.InterestCategory
import com.and.presentation.screen.preinvestigation.InvestigationInterestItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InvestigationInterestList(
    selectedInterests: Set<InterestCategory>,
    onInterestClick: (InterestCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = InterestCategory.entries

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = categories,
            ) { interest ->
                InvestigationInterestItem(
                    interest = interest,
                    isSelected = interest in selectedInterests,
                    onClick = onInterestClick
                )
            }
        }
    }
}
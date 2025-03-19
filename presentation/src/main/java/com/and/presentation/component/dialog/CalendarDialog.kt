@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.and.presentation.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.and.presentation.R
import com.and.presentation.screen.login.LoginTheme
import com.and.presentation.ui.Background_System
import com.and.presentation.ui.Blue100
import com.and.presentation.ui.Blue900
import com.and.presentation.ui.Body1Normal
import com.and.presentation.ui.Body1Reading
import com.and.presentation.ui.Body2Normal
import com.and.presentation.ui.Caption_Heavy
import com.and.presentation.ui.Error_Caption
import com.and.presentation.ui.Gray400
import com.and.presentation.ui.Gray700
import com.and.presentation.ui.Gray800
import com.and.presentation.ui.Gray900
import com.and.presentation.ui.Primary_Normal
import com.and.presentation.ui.SUCCESS_NORMAL
import com.and.presentation.util.removeRippleEffect
import java.lang.ref.WeakReference
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarDialog(
    onDismiss: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    initialDate: LocalDate = LocalDate.now()
) {
    val today = LocalDate.now()
    var currYearMonth: YearMonth by remember {
        mutableStateOf(YearMonth.of(initialDate.year, initialDate.monthValue))
    }
    var selectedDay by remember { mutableStateOf(initialDate) }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 60.dp)
        ) {
            Surface(
                shape = MaterialTheme.shapes.small,
                shadowElevation = 8.dp,
                color = Color.White,
            ) {
                Column {
                    MonthHeader(
                        yearMonth = currYearMonth,
                        onHideRequested = {
                            onDismiss()
                        },
                        onLeftClick = {
                            currYearMonth = currYearMonth.minusMonths(1)
                        },
                        onRightClick = {
                            currYearMonth = currYearMonth.plusMonths(1)
                        }
                    )
                    DayOfWeekHeader()
                    CalendarView(
                        currentYearMonth = currYearMonth,
                        selectedDate = selectedDay,
                        onDayClick = { date ->
                            if (selectedDay != date) {
                                selectedDay = date
                                onDateSelected(date)
                            }
                        },
                        eventDays = setOf(
                            LocalDate.now().minusDays(1),
                            LocalDate.now().minusDays(2),
                            LocalDate.now().minusDays(3),
                            LocalDate.now().minusDays(4),
                            LocalDate.now().minusDays(5),
                            LocalDate.now().plusDays(1),
                            LocalDate.now().plusDays(11),
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Button(
                onClick = {
                    selectedDay = today
                    onDateSelected(today)
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                contentPadding = PaddingValues(vertical = 0.dp, horizontal = 14.dp),
                modifier = Modifier
                    .height(32.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 60.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_line_reload),
                        contentDescription = null,
                        tint = Primary_Normal,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = "오늘",
                        style = Body2Normal,
                        fontWeight = FontWeight.Medium,
                        color = Primary_Normal
                    )
                }
            }
        }
    }
}

@Composable
private fun MonthHeader(
    yearMonth: YearMonth,
    onHideRequested: () -> Unit,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onLeftClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_line_left),
                        contentDescription = null,
                        tint = Gray800,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                Text(
                    text = "${yearMonth.year}년 ${yearMonth.monthValue}월",
                    style = Body2Normal,
                    fontWeight = FontWeight.Medium,
                    color = Gray800
                )
                IconButton(
                    onClick = onRightClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_line_right),
                        contentDescription = null,
                        tint = Gray800,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = onHideRequested
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_line_close),
                    contentDescription = stringResource(id = R.string.close),
                    tint = Gray800,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Background_System
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}

@Composable
private fun DayOfWeekHeader(
    modifier: Modifier = Modifier,
) {
    val daysOfWeek = listOf(
        DayOfWeek.SUNDAY,
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN),
                style = Body2Normal,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = when (dayOfWeek) {
                    DayOfWeek.SUNDAY -> Error_Caption
                    DayOfWeek.SATURDAY -> SUCCESS_NORMAL
                    else -> Gray700
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun CalendarView(
    currentYearMonth: YearMonth,
    selectedDate: LocalDate,
    onDayClick: (LocalDate) -> Unit,
    eventDays: Set<LocalDate> = emptySet(),
    modifier: Modifier = Modifier
) {
    val days: List<LocalDate?> = remember(currentYearMonth) {
        getDaysByYearMonth(currentYearMonth)
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
    ) {
        items(days) { day ->
            DayGridItem(
                date = day,
                isSelected = selectedDate == day,
                hasEvent = day in eventDays,
                onDayClick = onDayClick
            )
        }
    }
}

@Composable
private fun DayGridItem(
    date: LocalDate?,
    isSelected: Boolean,
    hasEvent: Boolean,
    onDayClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (date == null) {
        Box(modifier = modifier)
        return
    }

    val today = LocalDate.now()
    val isPastDay = date <= today
    val textColor = when {
        isSelected -> Color.White
        isPastDay -> Gray900
        else -> Gray400
    }
    val backgroundColor = if (isSelected) Primary_Normal else Color.White

    Box(
        modifier = modifier
            .removeRippleEffect {
                if (isPastDay) {
                    onDayClick(date)
                }
            }
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .size(40.dp)
                    .border(
                        width = 1.dp,
                        color = when {
                            isSelected -> Primary_Normal
                            today == date -> Blue900
                            else -> Color.White
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(backgroundColor)
                    .padding(vertical = 8.dp, horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date.dayOfMonth.toString(),
                    style = Body1Reading,
                    color = textColor,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            hasEvent && (date <= today) -> Primary_Normal
                            hasEvent && (date > today) -> Blue100
                            else -> Color.White
                        }
                    ),
            )
        }

    }
}

private fun getDaysByYearMonth(
    yearMonth: YearMonth
): List<LocalDate?> {
    val lengthOfMonth = yearMonth.lengthOfMonth() // 총 일수
    val firstDayOfMonth = yearMonth.atDay(1) // 첫째 날
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
    val prevMonthDays = if (firstDayOfWeek == 0) 0 else firstDayOfWeek
    val daysOfMonth = (1..lengthOfMonth).map { day -> yearMonth.atDay(day) }

    return (1..prevMonthDays).map { null } + daysOfMonth
}

@Preview(
    name = "CalendarDialog Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
private fun CalendarDialogPreview() {
    LoginTheme {
//        DayGridItem(
//            date = LocalDate.now(),
//            isSelected = true,
//            hasEvent = true,
//            onDayClick = { date ->
//
//            },
//        )
        CalendarDialog(
            onDismiss = {

            },
            onDateSelected = { date ->

            }
        )
    }
}
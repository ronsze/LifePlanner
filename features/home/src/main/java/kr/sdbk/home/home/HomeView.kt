package kr.sdbk.home.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kr.sdbk.core_common.R
import kr.sdbk.core_common.compose.BaseText
import kr.sdbk.core_common.compose.Loading
import kr.sdbk.core_common.compose.dashedBorder
import kr.sdbk.core_common.compose.dpToSp
import model.schedule.Schedule
import model.schedule.ScheduleState
import model.schedule.Time
import java.time.DayOfWeek

@Composable
fun HomeView(
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    mainNavController: NavController
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    when (viewState) {
        HomeViewModel.HomeViewState.Loading -> Loading()
        HomeViewModel.HomeViewState.View -> View(viewModel, mainNavController)
    }
}

@Composable
fun View(
    viewModel: HomeViewModel,
    mainNavController: NavController
) {
    Box {
        ScheduleList(
            viewModel = viewModel,
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun ScheduleList(
    viewModel: HomeViewModel,
    modifier: Modifier
) {
    val data by viewModel.scheduleList.collectAsStateWithLifecycle()

    LazyColumn(
        contentPadding = PaddingValues(
            top = 40.dp,
            start = 20.dp,
            end = 20.dp
        ),
        modifier = modifier
    ) {
        items(data) { schedule ->
            ScheduleItem(
                item = schedule
            )
        }
        item {
            ContentContainer(false) {
                ScheduleAddItem(
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun ContentContainer(
    enableShadow: Boolean = true,
    content: @Composable () -> Unit
) {
    val elevation = if (enableShadow) 5.dp else 0.dp
    Card(
        shape = RoundedCornerShape(35.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.transparent)),
        elevation = CardDefaults.cardElevation(elevation),
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
    ) {
        content()
    }
    Spacer(modifier = Modifier.height(25.dp))
}

@Composable
fun ScheduleItem(
    item: Schedule
) {
    Column {
        TimeText(
            time = item.time,
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(10.dp))
        ContentContainer {
            Content(
                item = item
            )
        }
    }
}

@Composable
fun TimeText(
    time: Time,
    modifier: Modifier
) {
    BaseText(
        text = time.toText(),
        fontSize = dpToSp(21.dp),
        color = colorResource(id = R.color.black),
        fontWeight = FontWeight.Medium,
        modifier = modifier
            .padding(end = 7.dp)
    )
}

@Composable
fun Content(
    item: Schedule
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        Row {
            TextPart(
                title = item.title,
                detail = item.detail,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_hello),
                contentDescription = "",
                modifier = Modifier
                    .size(75.dp)
                    .padding(15.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun TextPart(
    title: String,
    detail: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 17.dp, vertical = 10.dp)
    ) {
        BaseText(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = dpToSp(17.dp)
        )
        BaseText(
            text = detail,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = dpToSp(15.dp),
            modifier = Modifier
                .padding(top = 2.dp)
        )
    }
}

@Composable
fun ScheduleAddItem(
    navigateToAddSchedule: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .dashedBorder(
                width = 2f,
                size = 15f,
                cap = 10f,
                color = colorResource(id = R.color.gray),
                radius = 35.dp
            )
            .clickable { navigateToAddSchedule() }
    ) {
        Image(
            imageVector = Icons.Filled.Add,
            contentDescription = "",
            modifier = Modifier
                .size(55.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
@Preview
fun ScheduleItemPreview() {
    Content(
        item = Schedule("", "tt", "Hello\nhello\nww", Time(15, 22), DayOfWeek.entries[0], ScheduleState.DISABLED)
    )
}

@Composable
@Preview
fun ScheduleAddItemPreview() {
    ScheduleAddItem(modifier = Modifier)
}
package kr.sdbk.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.sdbk.core_common.R
import kr.sdbk.core_common.compose.dpToSp


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleList(fragmentViewModel: HomeViewModel) {
    val data = remember { fragmentViewModel.list }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 40.dp, bottom = 25.dp)
    ) {
        items(data) {
            ScheduleItem(it, Modifier.animateItemPlacement())
        }
    }
}

@Composable
fun ScheduleItem(
    item: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(top = 15.dp, bottom = 10.dp)
    ) {
        TimeText(Modifier.align(Alignment.End))
        Content(item, Modifier)
    }
}

@Composable
fun TimeText(modifier: Modifier) {
    Text(
        text = "16:44",
        fontSize = dpToSp(21.dp),
        modifier = modifier
            .padding(end = 7.dp)
    )
}

@Composable
fun Content(
    item: String,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(35.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = modifier
            .wrapContentHeight()
            .padding(top = 7.dp)
    ) {
        Row {
            TextPart(item, Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_hello),
                contentDescription = "",
                modifier = Modifier
                    .size(75.dp)
                    .padding(15.dp)
            )
        }
    }
}

@Composable
fun TextPart(
    item: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 17.dp, vertical = 10.dp)
    ) {
        Text(
            text = item,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = dpToSp(17.dp)
        )
        Text(
            text = "detaildetaildetaildetaildetaildedetaildetaildetaildetaildetaildetailtaildetaildetaildetaildetaildetaildetail",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = dpToSp(15.dp),
            modifier = Modifier
                .padding(top = 2.dp)
        )
    }
}

@Composable
@Preview
fun ScheduleItemPreview() {
    ScheduleItem("1", Modifier)
}
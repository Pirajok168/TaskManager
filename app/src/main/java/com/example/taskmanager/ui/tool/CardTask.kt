package com.example.taskmanager.ui.tool

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.R
import com.example.taskmanager.ui.theme.TaskManagerTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardTask() {
    val backgroundColor = Color.Yellow
    val listTag = listOf("School", "Everyday")
    val label = "Пойти в школу"
    val date = "7 октября 2022"
    val time = "17:00"
    Surface(
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row() {
                    listTag.forEach {
                            tag ->
                        Chip(
                            onClick = {  },
                            border = BorderStroke(1.dp, Color.Gray),
                            colors = ChipDefaults.chipColors(backgroundColor = backgroundColor),
                            modifier = Modifier.padding(end = 10.dp)
                        ) {
                            Text(
                                text = tag,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }

                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "")
                }
            }
            

            
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(text = date)
            }
            
            Spacer(modifier = Modifier.size(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_access_time_24),
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = time)
                }


                Done(){

                }

            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Done(
    onDone: () -> Unit
) {
    val check = remember{ mutableStateOf(false) }
    val tint by animateColorAsState(
        if (check.value) Color(0xFF000000) else Color.Transparent
    )

    Surface(
        onClick = {
            check.value = !check.value
            onDone()
        },
        modifier = Modifier
            .size(30.dp),
        border = BorderStroke(1.dp, Color.Black),
        color = tint,
        shape = CircleShape
    ) {

    }

}


@Preview
@Composable
fun PreviewCardTask() {
    TaskManagerTheme {
        CardTask()
    }
}
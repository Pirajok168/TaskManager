package com.example.taskmanager.ui.screen


import android.app.DatePickerDialog
import android.app.DialogFragment
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.ui.theme.TaskManagerTheme
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import com.example.taskmanager.R


@Composable
fun CreateTask(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CreateTaskTopBar(onBack)
        }) {
            paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            ColorTask(
                Modifier.padding(20.dp),
                onChangeColor = {

                }
            )

            Deadline(
                modifier = Modifier.padding(20.dp),
                changeDate = {

                }
            )
        
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColorTask(
    modifier: Modifier,
    onChangeColor:(color: Color) -> Unit
) {
    val listColors = listOf(
        Color(0xFF03DAC5),
        Color(0xFFF44336),
        Color(0xFFE91E63),
        Color(0xFF9C27B0),
        Color(0xFF3F51B5),
        Color(0xFF00BCD4),
        Color(0xFFFFEB3B),
        Color(0xFFFF5722),
    )

    Text(
        text = "Цвет задачи",
        color = Color.LightGray,
        fontSize = 16.sp,
        modifier = modifier
    )

    

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(20.dp)
    ){
        items(listColors){
                color ->
            Surface(
                color = color,
                modifier = Modifier.size(30.dp),
                shape = CircleShape,
                onClick = {
                    onChangeColor(color)
                }
            ) {

            }
        }
    }


    Divider(modifier = modifier)
}


@Composable
fun Deadline(
    modifier: Modifier,
    changeDate: (date: Date) -> Unit
) {
    var date by remember {
        mutableStateOf(LocalDate.now())
    }
    val context = LocalContext.current

    Text(
        text = "Время выполнения",
        color = Color.LightGray,
        fontSize = 16.sp,
        modifier = modifier
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "${date.dayOfMonth} ${date.month} ${date.year}",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )

        IconButton(onClick = {
            showDateDialog(
                changeDate = {
                    year, month, dayOfMonth ->
                    date = LocalDate.of(year, month, dayOfMonth)
                },
                context = context,
                date = date
            )
        }) {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24)
                , contentDescription = "")
        }
    }
}

fun showDateDialog(
    context: Context,
    changeDate: (year: Int,
                  month: Int,
                  dayOfMonth: Int) -> Unit,
    date: LocalDate,
){
    val picker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        changeDate(year, month+1, dayOfMonth)
    }
    DatePickerDialog(context, picker, date.year, date.monthValue-1, date.dayOfMonth).show()
}


@Composable
fun CreateTaskTopBar(
    onBack: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = { Text(text = "Новая задача") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}


@Preview
@Composable
fun PreviewCreateTask() {
    TaskManagerTheme {
        CreateTask(){

        }
    }
}
package com.example.attendease.core.attendies.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.attendease.core.attendies.model.entity.Student
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendiesStudentList(
    students: List<Student>,
){
    var searchQuery by remember { mutableStateOf("") }
    val checkedStates = remember { mutableStateListOf<Boolean>().apply { addAll(List(students.size) { false }) } }

    // Filter students based on search input
    val filteredStudents = remember(students, searchQuery) {
        students.filter { it.fullName.contains(searchQuery, ignoreCase = true) }
    }

    Column {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = {
                Text(
                    text = "Search for a student by name",
                    style = LocalCustomTypographyScheme.current.p_small,
                    color = LocalCustomColorScheme.current.default500,
                )
            },
            leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "Search", tint = LocalCustomColorScheme.current.default900) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = LocalCustomColorScheme.current.default400,
                unfocusedBorderColor = LocalCustomColorScheme.current.default300
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(filteredStudents) { student ->
                val index = students.indexOf(student)
                val checked = checkedStates[index]

                StudentItem(
                    student = student,
                    checked = checked,
                    onClick = { checkedStates[index] = !checked }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Confirm",
                        style = LocalCustomTypographyScheme.current.p_mediumSemiBold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
package com.example.attendease.core.attendies.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.attendease.core.attendies.model.entity.Student
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme

@Composable
fun StudentItem(
    student: Student,
    checked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = student.fullName,
                style = LocalCustomTypographyScheme.current.p_medium,
                color = LocalCustomColorScheme.current.default900
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = student.absenceCount.toString() + " Absence" + if (student.absenceCount > 1) "s" else "",
                style = LocalCustomTypographyScheme.current.p_small,
                color = if (student.absenceCount == 0) {
                    LocalCustomColorScheme.current.default400
                } else if (student.absenceCount < 3) {
                    LocalCustomColorScheme.current.basePrimary
                } else if (student.absenceCount == 3) (
                    LocalCustomColorScheme.current.baseWarning
                ) else {
                    LocalCustomColorScheme.current.baseError
                }
            )
        }

        Checkbox(
            checked = checked,
            onCheckedChange = {onClick()},
            colors = CheckboxDefaults.colors(
                checkedColor = LocalCustomColorScheme.current.basePrimary,
                uncheckedColor = LocalCustomColorScheme.current.default300
            )
        )
    }
}
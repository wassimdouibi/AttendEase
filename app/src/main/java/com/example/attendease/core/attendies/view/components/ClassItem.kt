package com.example.attendease.core.attendies.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.attendease.core.attendies.model.entity.ProgressStep
import com.example.attendease.core.attendies.model.entity.Session

@Composable
fun ClassItem(
    classItem: Session,
    onStateChange: (ProgressStep) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .clickable(onClick = {onStateChange(ProgressStep.Attendance)}),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = classItem.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {

            Text(
                text = "${classItem.startHour} - ${classItem.endHour}",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = classItem.salle,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
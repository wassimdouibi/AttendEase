package com.example.attendease.core.attendies.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.attendease.R
import com.example.attendease.core.attendies.model.entity.ProgressStep
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme

@Composable
fun ProgressBar(
    currentStep: ProgressStep,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Render steps
            ProgressStep.entries.forEachIndexed { index, step ->
                // Determine step state
                val isCompleted = step.ordinal < currentStep.ordinal
                val isCurrent = step == currentStep

                // Circle Box
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        color = when {
                            isCompleted -> LocalCustomColorScheme.current.primary500
                            isCurrent -> LocalCustomColorScheme.current.primary50
                            else -> LocalCustomColorScheme.current.primary50
                        },
                        contentColor = when {
                            isCompleted -> LocalCustomColorScheme.current.baseWhite
                            isCurrent -> LocalCustomColorScheme.current.basePrimary
                            else -> LocalCustomColorScheme.current.baseWhite
                        },
                        border = BorderStroke(
                            width = 1.dp,
                            color = when {
                                isCompleted -> LocalCustomColorScheme.current.basePrimary
                                isCurrent -> LocalCustomColorScheme.current.basePrimary
                                else -> LocalCustomColorScheme.current.primary300
                            }
                        )
                    ) {}

                    // Content of the circle (check or step number)
                    if (isCompleted) {
                        Icon(
                            painter = painterResource(R.drawable.checkmark),
                            modifier = Modifier.size(18.dp),
                            contentDescription = "$step Completed",
                            tint = Color.White
                        )
                    } else if (isCurrent) {
                        Icon(
                            painter = painterResource(R.drawable.dot),
                            modifier = Modifier.size(9.dp),
                            contentDescription = "$step In Progress",
                            tint = Color.Blue
                        )
                    }
                }

                // Line between circles (except after the last circle)
                if (index < ProgressStep.entries.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 2.5.dp,
                        color = when {
                            isCompleted -> LocalCustomColorScheme.current.basePrimary
                            isCurrent -> LocalCustomColorScheme.current.primary300
                            else -> LocalCustomColorScheme.current.primary300
                        }
                    )
                }
            }
        }

        Row(
            modifier = modifier.fillMaxWidth().padding(start = 23.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProgressStep.entries.forEachIndexed { index, step ->
                Text(
                    text = step.name,
                    style = LocalCustomTypographyScheme.current.p_tiny,
                    color = LocalCustomColorScheme.current.default800,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.then(
                        if (index == 1) Modifier.padding(start = 19.dp) else Modifier
                    )
                )
            }
        }
    }
}
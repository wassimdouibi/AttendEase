package com.example.attendease.core.HelpSupport.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme
import com.example.attendease.R


@Composable
fun FAQSection() {
    val faqs = listOf(
        stringResource(R.string.faq_q1) to stringResource(R.string.faq_a1),
        stringResource(R.string.faq_q2) to stringResource(R.string.faq_a2),
        stringResource(R.string.faq_q3) to stringResource(R.string.faq_a3)
    )

    Column {
        faqs.forEach { (question, answer) ->
            FAQItem(question, answer)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray.copy(alpha = 0.5f))
            )
        }
    }
}

@Composable
fun FAQItem(question: String, answer: String) {
    var isExpanded = remember { mutableStateOf(false) } // État pour gérer l'affichage

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded.value = !isExpanded.value } // Inversion de l'état au clic
            .padding(vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = question,
                style = LocalCustomTypographyScheme.current.p_medium,
                modifier = Modifier.padding(bottom = 2.dp, start = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = if (isExpanded.value) Icons.Rounded.Remove else Icons.Rounded.Add,
                contentDescription = if (isExpanded.value) "Click to fold" else "Click to expand",
                tint = LocalCustomColorScheme.current.default900,
                modifier = Modifier.size(20.dp)
            )
        }

        // Afficher la réponse si `isExpanded` est vrai
        if (isExpanded.value) {
            Text(
                text = answer,
                style = LocalCustomTypographyScheme.current.p_small,
                color = LocalCustomColorScheme.current.default600,
                modifier = Modifier.padding(top = 6.dp, start = 8.dp, end = 8.dp)
            )
        }
    }
}
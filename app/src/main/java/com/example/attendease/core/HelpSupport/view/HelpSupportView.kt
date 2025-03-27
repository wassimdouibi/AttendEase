package com.example.attendease.core.HelpSupport.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.attendease.core.HelpSupport.viewModel.HelpSupportViewModel

@Composable
fun HelpSupportView(
    navController: NavHostController,
    helpSupportViewModel: HelpSupportViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Help & Support",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        // General Instructions
        Text(
            text = "General Instructions",
            fontSize = 18.sp,
            color = Color.Gray.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(14.dp))

        GeneralInstructions()

        Spacer(modifier = Modifier.height(28.dp))

        // FAQ Section
        Text(
            text = "Frequently Asked Questions",
            fontSize = 18.sp,
            color = Color.Gray.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(14.dp))

        FAQSection()

        Spacer(modifier = Modifier.height(28.dp))

        // Contact Support
        Text(
            text = "Contact Support",
            fontSize = 18.sp,
            color = Color.Gray.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(14.dp))

        ContactSupport()
    }
}


@Composable
fun GeneralInstructions() {
    Column {
        InstructionItem("Do", true)
        Spacer(modifier = Modifier.height(6.dp))
        InstructionItem("Do", true)
        Spacer(modifier = Modifier.height(6.dp))
        InstructionItem("Do", true)
        Spacer(modifier = Modifier.height(6.dp))
        InstructionItem("Don't do", false)
        Spacer(modifier = Modifier.height(6.dp))
        InstructionItem("Don’t do", false)
    }
}

@Composable
fun InstructionItem(text: String, isPositive: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = if (isPositive) Icons.Outlined.CheckCircle else Icons.Outlined.Cancel,
            contentDescription = null,
            tint = if (isPositive) Color(0xFF4CAF50) else Color(0xFFF44336),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
fun FAQSection() {
    val faqs = listOf(
        "How to use the app?" to "You can use the app by following these steps...",
        "How to reset my password?" to "To reset your password, go to the settings...",
        "How to report an issue?" to "If you encounter an issue, please contact support..."
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
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = if (isExpanded.value) Icons.Outlined.Remove else Icons.Outlined.Add,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        // Afficher la réponse si `isExpanded` est vrai
        if (isExpanded.value) {
            Text(
                text = answer,
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 6.dp, start = 8.dp, end = 8.dp)
            )
        }
    }
}


@Composable
fun ContactSupport() {
    Column {
        ContactItem(Icons.Outlined.Email, "Email", "support@example.com")
        ContactItem(Icons.Outlined.Phone, "Phone", "+1 234 567 890")
        ContactItem(Icons.Outlined.AccessTime, "Office hours", "09:00 - 17:00")
    }
}

@Composable
fun ContactItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF2196F3),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$label (", color = Color.Black, fontSize = 16.sp)
        Text(text = value, color = Color(0xFF2196F3), fontSize = 16.sp)
        Text(text = ")", color = Color.Black, fontSize = 16.sp)
    }
}



package com.example.attendease.onboarding.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material.icons.outlined.Visibility


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    labelValue: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        label = { Text(text = labelValue, color = Color.Gray) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = Color.LightGray,
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            cursorColor = Color.Black // Black cursor
        ),
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    )
    Spacer(modifier = Modifier.height(12.dp))
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun passwordTextField(
    labelValue: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    val passwordVisible = remember {
        mutableStateOf(false) // Password not visible initially
    }

    OutlinedTextField(
        label = { Text(text = labelValue, color = Color.Gray) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedLabelColor = Color.LightGray,
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.LightGray,
            cursorColor = Color.Black // Black cursor
        ),
        value = value, // External value
        onValueChange = onValueChange, // External state updater
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }
            val description = if (passwordVisible.value) {
                "Hide password"
            } else {
                "Show password"
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}
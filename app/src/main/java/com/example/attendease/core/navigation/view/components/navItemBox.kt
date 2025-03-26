package com.example.attendease.core.navigation.view.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.attendease.core.navigation.model.NavItem

@Composable
fun RowScope.NavItemBox(
    item: NavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,

        label = { Text(text = item.label) },
        icon = {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.label,
            )
        },
        colors = NavigationBarItemDefaults.colors(
            unselectedTextColor = Color.Gray,
            selectedTextColor = Color.Blue,
            selectedIconColor = Color.Blue,
            unselectedIconColor = Color.Gray,
            indicatorColor = Color.Transparent
        )
    )
}
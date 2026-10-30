package hr.foi.popapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import hr.foi.popapp.models.Role

@Composable
fun RoleSelector(
    roles: List<Role>,
    selected: Role,
    onRoleSelected: (Role) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        roles.forEach { role ->
            FilterChip(
                label = { Text(role.displayName) },
                onClick = { onRoleSelected(role) },
                selected = role == selected
            )
        }
    }
}
package com.mxtgames.gymtrack.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mxtgames.gymtrack.ui.theme.GymTrackTheme

@Composable
fun InputFieldComponent(
    modifier: Modifier = Modifier,
    label: String = "",
    defaultValue: String = ""
) {
    TextField(
        modifier =  modifier,
        value = defaultValue,
        label = {
            Text(label)
        },
        onValueChange = {}
    )
}

@Preview(showBackground = true)
@Composable
fun InputFieldComponentPreview() {
    GymTrackTheme {
        Box(
            modifier = Modifier.padding(GymTrackTheme.spaces.XL)
        ) {
            InputFieldComponent(
                label = "Test label",
            )
        }
    }
}
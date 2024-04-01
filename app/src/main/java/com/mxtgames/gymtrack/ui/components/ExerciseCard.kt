package com.mxtgames.gymtrack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.common.collect.ImmutableList
import com.mxtgames.gymtrack.data.ExerciseData
import com.mxtgames.gymtrack.data.SetData
import com.mxtgames.gymtrack.data.TargetMuscle
import com.mxtgames.gymtrack.ui.theme.GymTrackTheme

@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    exerciseData: ExerciseData = ExerciseData()
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Row {
                exerciseData.targetMuscles.forEach {
                    Text(text = it.name)
                }
            }
            Text(text = exerciseData.name)
            exerciseData.note?.let { note ->
                Text(text = note)
            }
            SetHeaderRow()
            exerciseData.setData.forEach { setData ->
                SetRow(setData = setData)
            }
        }
    }
}

@Composable
fun SetHeaderRow(
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = "Weight")
        Text(text = "Reps")
    }
}

@Composable
fun SetRow(
    modifier: Modifier = Modifier,
    setData: SetData
) {
    Divider()
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = setData.weigh.toString())
        Text(text = setData.reps.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseCardPreview() {
    GymTrackTheme {
        Box(
            modifier = Modifier
                .background(Color.Magenta)
                .padding(GymTrackTheme.spaces.XL)
        ) {
            ExerciseCard(
                exerciseData = ExerciseData(
                    name = "Test Exercise Name",
                    targetMuscles = ImmutableList.of(
                        TargetMuscle(
                            name = "Bicep",
                        )
                    ),
                    note = "Some note here",
                    setData = ImmutableList.of(
                        SetData(
                            weigh = 15.0,
                            reps = 10
                        ),
                        SetData(
                            weigh = 15.0,
                            reps = 9
                        )
                    )
                )
            )
        }
    }
}
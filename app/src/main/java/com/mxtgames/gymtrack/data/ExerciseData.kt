package com.mxtgames.gymtrack.data

import com.google.common.collect.ImmutableList

data class ExerciseData(
    val name: String = "",
    val targetMuscles: ImmutableList<TargetMuscle> = ImmutableList.of(),
    val note: String? = null,
    val setData: ImmutableList<SetData> = ImmutableList.of(),
)

data class TargetMuscle(
    val name: String = "",
    val id: Int = 0,
)

data class SetData(
    val weigh: Double = 0.0,
    val reps: Int = 0,
)

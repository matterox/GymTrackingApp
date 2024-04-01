package com.mxtgames.gymtrack

sealed class Destination(val route: String) {
    data object Auth: Destination("authentication")
    data object Main: Destination("main")
}
package com.rayadams.navigationexample.navigation

import kotlinx.serialization.Serializable

object NavRoutes {
    @Serializable
    object MainScreen

    @Serializable
    object Screen1

    @Serializable
    object Screen2

    @Serializable
    object Screen3

    @Serializable
    data class ScreenWithParameter(val parameter: String)
}

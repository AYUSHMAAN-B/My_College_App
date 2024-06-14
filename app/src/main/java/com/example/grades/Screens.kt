package com.example.grades

sealed class Screens (val title : String, val route : String)
{
    data object Home : Screens("HOME", "home")
    data object QuickLinks : Screens("QUICK LINKS", "quick_links")
    data object TimeTable : Screens("TIME TABLE", "time_table")
    data object ToDo : Screens("TODO", "todo")
    data object Grades : Screens("GRADES", "grades")
    data object WebScreen : Screens("WEBSCREEN", "webscreen/{url}")

    companion object {
        fun fromRoute(route: String?): Screens =
            when (route?.substringBefore("/")) {
                Home.route -> Home
                QuickLinks.route -> QuickLinks
                TimeTable.route -> TimeTable
                ToDo.route -> ToDo
                Grades.route -> Grades
                "webscreen" -> WebScreen
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
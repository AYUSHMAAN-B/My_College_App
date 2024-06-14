package com.example.grades

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.grades.Grade_Screen.Grades
import com.example.grades.Home_Screen.Home
import com.example.grades.QuickLinks_Screen.QuickLinks
import com.example.grades.QuickLinks_Screen.WebScreen
import com.example.grades.TimeTable_Screen.TimeTable
import com.example.grades.ToDo_Screen.ToDo

@Composable
fun Navigation(
    navController: NavController,
    padding: PaddingValues,
    viewModel: MainViewModel,
    currentScreen: MutableState<Screens>
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.Home.route
    ) {
        composable(Screens.Home.route) {
            Home(
                onGradesClicked = {
                    currentScreen.value = Screens.Grades
                    navController.navigate("grades")
                },
                onQuickLinksClicked = {
                    viewModel.setScreen(Screens.QuickLinks)
                    navController.navigate(Screens.QuickLinks.route)
                },
                onTimeTableClicked = {
                    viewModel.setScreen(Screens.TimeTable)
                    navController.navigate(Screens.TimeTable.route)
                },
                onToDoClicked = {
                    viewModel.setScreen(Screens.ToDo)
                    navController.navigate(Screens.ToDo.route)
                }
            )
        }
        composable(Screens.Grades.route) {
            Grades(viewModel)
        }
        composable(Screens.QuickLinks.route) {
            QuickLinks(navController = navController)
        }
        composable(Screens.TimeTable.route) {
            TimeTable()
        }
        composable(Screens.ToDo.route) {
            ToDo()
        }
        composable(Screens.WebScreen.route) {
            val url = it.arguments?.getString("url") ?: ""
            WebScreen(url)
        }
    }
}
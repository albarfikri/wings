package com.example.wings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wings.ui.navigation.CheckoutPage
import com.example.wings.ui.navigation.Login
import com.example.wings.ui.navigation.ProductDetail
import com.example.wings.ui.navigation.ProductDetail.itemIdArg
import com.example.wings.ui.navigation.ProductList
import com.example.wings.ui.pages.CheckoutConfirmationPage
import com.example.wings.ui.pages.LoginPage
import com.example.wings.ui.pages.ProductDetail
import com.example.wings.ui.pages.ProductListsPage
import com.example.wings.ui.viewmodel.AppViewModelProvider
import com.example.wings.ui.viewmodel.CheckoutViewModel
import com.example.wings.ui.viewmodel.ProductViewModel
import com.example.wings.ui.viewmodel.WingsViewModel

@Composable
fun WingsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: WingsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    productViewModel: ProductViewModel = viewModel(factory = AppViewModelProvider.Factory),
    checkoutViewModel: CheckoutViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    NavHost(
        navController = navController, startDestination = Login.route, modifier = modifier
    ) {
        composable(
            route = Login.route
        ) {
            val loginState = viewModel.loginUiState
            LoginPage(loginState = loginState.isLogin, userValidation = {
                viewModel.loginUser(it.user, it.password)
            }, onHeadingToProductList = {
                navController.navigateSingleTopTo(ProductList.route)
            }, modifier
            )
        }

        composable(
            route = ProductList.route,
        ) {
            val productUiState = productViewModel.productUiState.collectAsState()
            val coroutineScope = rememberCoroutineScope()
            ProductListsPage(
                productList = productUiState.value.productlist,
                onProductClicked = {
                    navController.navigate("${ProductDetail.route}/$it")
                },
                onBuyClicked = {
                    productViewModel.insertBoughtProduct(it)

                },
                onCheckoutClicked = {
                    navController.navigate(CheckoutPage.route)
                }
            )

        }
        composable(
            route = ProductDetail.routeWithArgs,
            arguments = listOf(navArgument(itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ProductDetail(
                modifier = modifier
            )
        }

        composable(
            route = CheckoutPage.route,
        ) {
            val checkoutUiState = checkoutViewModel.checkoutState.collectAsState().value.checkoutList
            CheckoutConfirmationPage(
                checkoutViewModel,
                checkoutUiState,
                modifier = modifier
            )
        }
    }
}


// Helper for launching single top
fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}


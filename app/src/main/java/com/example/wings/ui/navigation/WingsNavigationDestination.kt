package com.example.wings.ui.navigation

interface WingsNavigationDestination {
    val route: String
    val contentDescription: String
}

object Login : WingsNavigationDestination {
    override val route = "Login"
    override val contentDescription = "Login Page"
}

object ProductList : WingsNavigationDestination {
    override val route = "ProductList"
    override val contentDescription = "Product List Page"
}

object ProductDetail: WingsNavigationDestination {
    override val route = "ProductDetail"
    override val contentDescription = "Product Detail Page"
    const val itemIdArg = "productId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

object CheckoutPage : WingsNavigationDestination {
    override val route = "Checkout"
    override val contentDescription = "Checkout Page"
}


package com.example.wings.ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wings.R
import com.example.wings.data.dataclass.Product


@Composable
fun ProductListsPage(
    productList: List<Product>,
    onProductClicked: (Int) -> Unit,
    onBuyClicked: (Product) -> Unit,
    onCheckoutClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(floatingActionButton = {
        WingsFloatingActionButton(
            isVisible = true,
            onCheckoutClicked = onCheckoutClicked
        )
    }) {
        ProductList(
            productList = productList,
            onProductItemClicked = { onItemClicked ->
                onProductClicked(onItemClicked)

            },
            onBuyClicked = { dataProduct ->
                onBuyClicked(dataProduct)
            },
            modifier.padding(it)
        )
    }
}

@Composable
fun ProductList(
    productList: List<Product>,
    onProductItemClicked: (Int) -> Unit,
    onBuyClicked: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(22.dp)
    ) {
        items(productList) {
            ProductListItem(
                itemProduct = it,
                onProductClicked = { idProduct ->
                    onProductItemClicked(idProduct)
                },
                onBuyClicked = { productData ->
                    onBuyClicked(productData)
                }
            )
            Spacer(modifier = modifier.padding(12.dp))
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductListItem(
    itemProduct: Product,
    modifier: Modifier = Modifier,
    onProductClicked: (Int) -> Unit,
    onBuyClicked: (Product) -> Unit
) {
    Card(
        modifier = modifier.padding(vertical = 4.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        onClick = { onProductClicked(itemProduct.productCode) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            productImage(itemProduct.productName, modifier.weight(1f))
            Spacer(modifier = modifier.padding(4.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = itemProduct.productName, style = MaterialTheme.typography.h5
                )
                Text(
                    text = "Rp. ${itemProduct.price},-",
                    style = MaterialTheme.typography.h6,
                )
                Button(onClick = {
                    onBuyClicked(itemProduct)
                }) {
                    Text(text = "Buy")
                }
            }
        }
    }
}

@Composable
fun productImage(
    description: String,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.avatar_parcel),
        contentDescription = description,
        modifier = modifier.clip(CircleShape)
    )
}

@Composable
fun WingsFloatingActionButton(
    isVisible: Boolean,
    onCheckoutClicked: () -> Unit
) {
    AnimatedVisibility(visible = isVisible) {
        FloatingActionButton(
            onClick = {
                onCheckoutClicked()
            }, backgroundColor = MaterialTheme.colors.primary
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check, contentDescription = null
                )
                // Toggle the visibility of the content with animation.

                Text(
                    text = "Checkout", modifier = Modifier.padding(start = 8.dp, top = 3.dp)
                )
            }
        }
    }
}
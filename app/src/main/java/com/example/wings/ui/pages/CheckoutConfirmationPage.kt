package com.example.wings.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wings.data.dataclass.Product
import com.example.wings.data.dataclass.TransactionDetailWithProducts
import com.example.wings.ui.viewmodel.CheckoutViewModel

@Composable
fun CheckoutConfirmationPage(
    checkoutViewModel: CheckoutViewModel,
    checkoutList: List<TransactionDetailWithProducts>,
    modifier: Modifier = Modifier
) {
    val total = rememberSaveable {
        mutableStateOf(0)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Checkout",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(bottom = 12.dp)
        )
        Row(modifier = modifier.padding(bottom = 12.dp)) {
            Text(
                text = "Total: ",
                fontSize = 18.sp,
            )
            Text(
                text = "Rp. ${total.value},-", fontSize = 18.sp
            )
        }
        CheckoutList(
            checkoutViewModel = checkoutViewModel,
            productList = checkoutList,
            onProductItemClicked = {},
            onBuyClicked = {},
            total = {
                total.value += it
                if (it == 0) {
                    total.value -= it
                }

            })
    }
}


@Composable
fun CheckoutList(
    checkoutViewModel: CheckoutViewModel,
    productList: List<TransactionDetailWithProducts>,
    onProductItemClicked: (Int) -> Unit,
    onBuyClicked: (Product) -> Unit,
    total: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(productList) {
            Log.d("Data", it.toString())
            //checkoutViewModel.getProductNameById(it.productCode.toInt())
//            it.products.forEach{product ->
            CheckoutItem(
                checkoutViewModel = checkoutViewModel,
                checkoutProduct = it,
                onProductClicked = { idProduct ->
                    onProductItemClicked(idProduct)
                }, onBuyClicked = { productData ->
                    onBuyClicked(productData)
                }, total = total
            )
//            }
            Spacer(modifier = modifier.padding(12.dp))
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckoutItem(
    checkoutViewModel: CheckoutViewModel,
    checkoutProduct: TransactionDetailWithProducts,
    modifier: Modifier = Modifier,
    onProductClicked: (Int) -> Unit,
    onBuyClicked: (Product) -> Unit,
    total: (Int) -> Unit
) {
    val quantity = rememberSaveable {
        mutableStateOf("")
    }

    val productName = checkoutViewModel.uiState.collectAsState().value.productName
    Card(modifier = modifier.padding(vertical = 4.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        onClick = {
            onProductClicked(checkoutProduct.transactionDetail.quantity)
        }) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            productImage(checkoutProduct.transactionDetail.quantity.toString(), modifier.weight(1f))
            Spacer(modifier = modifier.padding(4.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.Center
            ) {
                checkoutProduct.products?.forEach {
                    Text(
                        text = it.productName, style = MaterialTheme.typography.h5
                    )
                }

//
//                Text(
//                    text = "data", style = MaterialTheme.typography.h5
//                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(top = 8.dp)
                ) {
//                    quantity.value = checkoutProduct.unit
                    OutlinedTextField(
                        value = quantity.value,
                        onValueChange = {
                            if (it.isEmpty()) {
                                total.invoke(0 * checkoutProduct.transactionDetail.price.toInt())
                            } else
                                total.invoke(
                                    it.toInt() * checkoutProduct.transactionDetail.price.toInt()
                                )
                            quantity.value = it
                        }, modifier.weight(1f)
                    )
                    Text(
                        text = " PCS", fontSize = 14.sp, fontWeight = FontWeight.Bold
                    )
                }

                Row(modifier.padding(top = 8.dp)) {
                    Text(
                        text = "Sub Total: ",
                        fontSize = 14.sp,
                    )
                    Text(
                        text = "Rp. ${checkoutProduct.transactionDetail.price},-", fontSize = 14.sp
                    )
                }
            }
        }
    }

}

package com.example.wings.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.convertTo
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wings.R
import com.example.wings.data.dataclass.Product
import com.example.wings.ui.viewmodel.AppViewModelProvider
import com.example.wings.ui.viewmodel.ProductDetailViewModel

@Composable
fun ProductDetail(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val productDetailState = viewModel.productDetailState.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        productDetailImage(description = productDetailState.productName)
        Text(
            text = productDetailState.productName, fontWeight = FontWeight.Bold, fontSize = 24.sp,
            modifier = modifier.padding(top = 14.dp, bottom = 14.dp)
        )
        detailList(productState = productDetailState, modifier = modifier)
    }
}


@Composable
fun detailList(productState: Product, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(id = R.string.Price),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Rp. ${productState.price},-", fontSize = 18.sp)
    }
    Row(
        modifier = modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.Dimension),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = productState.dimension, fontSize = 18.sp)
    }
    Row(
        modifier = modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.Price_Unit),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = productState.unit, fontSize = 18.sp)
    }
}

@Composable
fun productDetailImage(
    description: String,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.avatar_parcel),
        contentDescription = description,
        modifier = modifier.clip(CircleShape)
    )
}
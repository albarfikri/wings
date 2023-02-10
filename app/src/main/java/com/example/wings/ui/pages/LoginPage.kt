package com.example.wings.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wings.R
import com.example.wings.data.dataclass.Login
import kotlinx.coroutines.delay

@Composable
fun LoginPage(
    loginState: Boolean,
    userValidation: (Login) -> Unit,
    onHeadingToProductList: () -> Unit,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize()
    ) {

        if (loginState) {
            LaunchedEffect(Unit) {
                delay(3000L)
                onHeadingToProductList()
            }
        }

        val usernameValue = rememberSaveable {
            mutableStateOf("")
        }

        val passwordValue = rememberSaveable {
            mutableStateOf("")
        }

        Text(text = stringResource(id = R.string.login), fontSize = 28.sp)


        Spacer(modifier = Modifier.padding(22.dp))

        OutlinedTextField(
            value = usernameValue.value,
            onValueChange = {
                usernameValue.value = it.trim()
            }, label = {
                Text(
                    text = stringResource(id = R.string.Username),
                    color = colorResource(id = R.color.black)
                )
            }, placeholder = {
                Text(text = "Enter Username", color = MaterialTheme.colors.primary)
            }, singleLine = true
        )

        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(value = passwordValue.value, onValueChange = {
            passwordValue.value = it.trim()
        }, label = {
            Text(
                text = stringResource(id = R.string.Password),
                color = colorResource(id = R.color.black)
            )
        }, placeholder = {
            Text(text = "Enter Password", color = colorResource(id = R.color.black))
        }, singleLine = true, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ), visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Button(
            onClick = {
                if (usernameValue.value.isNotEmpty() && passwordValue.value.isNotEmpty()) {
                    userValidation(
                        Login(
                            user = usernameValue.value, password = passwordValue.value
                        )
                    )
                }
            }, modifier = modifier.width(240.dp), shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = stringResource(id = R.string.login))
        }

        Spacer(modifier = Modifier.padding(12.dp))

        Text(
            text = if (loginState) "Login Berhasil !" else "",
            color = colorResource(id = R.color.teal_700),
            fontWeight = FontWeight.Medium
        )

    }
}

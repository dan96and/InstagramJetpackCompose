package com.example.jetpackcomposeinstagram

import android.app.Activity
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
        Body(modifier = Modifier.align(Alignment.Center))
        Footer(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        modifier = modifier.clickable { activity.finish() },
        imageVector = Icons.Default.Close,
        contentDescription = "Close app"
    )
}

@Composable
fun Body(modifier: Modifier) {
    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    var isLoginEnable: Boolean by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        ImageLogo(modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        EditTextEmail(email = email) {
            email = it
            isLoginEnable = enableButtonLogin(email, password)
        }
        Spacer(modifier = Modifier.size(8.dp))
        EditTextPassword(password = password) {
            password = it
            isLoginEnable = enableButtonLogin(email, password)
        }
        ForgotPassword(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.End)
        )
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnable)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F))
                .height(1.dp)
        )
        Spacer(modifier = modifier.size(24.dp))
        SignUp()
        Spacer(modifier = modifier.size(24.dp))
    }
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "Logo of Instagram"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextEmail(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = { onTextChanged(it) },
        maxLines = 1,
        singleLine = true,
        placeholder = {
            Text(text = "Phone number, username or email", color = Color(0xFFB2B2B2))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFFAFAFA),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}

fun enableButtonLogin(email: String, password: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextPassword(password: String, onTextChanged: (String) -> Unit) {

    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = { onTextChanged(it) },
        maxLines = 1,
        singleLine = true,
        placeholder = {
            Text(text = "Password", color = Color(0xFFB2B2B2))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFFAFAFA),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val imagen = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }

            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = imagen, contentDescription = "Icon show the password")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "Forgot de password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9)
    )
}

@Composable
fun LoginButton(loginEnable: Boolean) {
    Button(
        onClick = { /*TODO*/ },
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(4.dp)

    ) {
        Text(text = "Login")
    }
}

@Composable
fun LoginDivider() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F))
                .height(1.dp)
                .weight(1F)
        )
        Text(
            modifier = Modifier.padding(horizontal = 18.dp),
            text = "OR",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F))
                .height(1.dp)
                .weight(1F)
        )
    }
}

@Composable
fun SocialLogin() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(32.dp)
                .padding(end = 8.dp),
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Logo Facebook"
        )
        Text(
            text = "Continue as Daniel Andres",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun SignUp() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = "Don't have an account?",
            fontSize = 12.sp,
            color = Color(0xFFB5B5B5)
        )
        Text(
            text = "Sign up.",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9)
        )
    }
}


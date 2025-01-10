package com.example.uilogintest.ui


import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uilogintest.R


@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    val customColor = Color(0xFF0CBC8B)
    val grayColor = Color(0xFF666666)
    val yellowColor = Color(0xFFFFA210)

    fun validateEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }

    fun onLoginClick() {
        dialogMessage = if (validateEmail(email) && validatePassword(password)) {
            "Login Successful"
        } else {
            "Invalid Email or Password"
        }
        showDialog = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "立即登录以访问更快的互联网\n" +
                        "超过120+个位置。",
                color = grayColor,
                modifier = Modifier.align(Alignment.Start)
                    .padding(bottom = 32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("请输入ID/邮箱") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = customColor,
                    unfocusedBorderColor = customColor,
                    cursorColor = customColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("请输入密码") },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (showPassword) R.drawable.visibility_24dp_e8eaed_fill0_wght400_grad0_opsz24 else R.drawable.visibility_off_24dp_e8eaed_fill0_wght400_grad0_opsz24
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Image(painter = painterResource(id = image), contentDescription = "Toggle Password Visibility")
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = customColor,
                    unfocusedBorderColor = customColor,
                    cursorColor = customColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.align(Alignment.End)
                    .padding(bottom = 32.dp)
            ) {
                Text(text = "忘记密码?", color = grayColor)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "（通过邮箱找回）", color = yellowColor)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onLoginClick() },
                colors = ButtonDefaults.buttonColors(containerColor = customColor),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("登录", color = Color.White)
            }


            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Login Result") },
                    text = { Text(dialogMessage) },
                    confirmButton = {
                        Button(
                            onClick = { showDialog = false },
                            colors = ButtonDefaults.buttonColors(containerColor = customColor)
                        ) {
                            Text("OK", color = Color.White)
                        }// Hex color #0CBC8B
                    }
                )
            }
        }


        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {

        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
        ) {
            Button(
                onClick = { /* TODO: Right Scanner Action */ },
                colors = ButtonDefaults.buttonColors(containerColor = customColor),
                shape = CircleShape
            ) {
                Text("扫码登录", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}


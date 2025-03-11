package com.example.bt_lttbdd_tuan2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgeClassificationScreen()
        }
    }
}

@Composable
fun AgeClassificationScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "THỰC HÀNH 01",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            AgeChecker()
        }
    }
}

@Composable
fun AgeChecker() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hộp chứa phần nhập liệu
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Họ và tên") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Tuổi") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút Kiểm tra
        Button(
            onClick = {
                val ageInt = age.toIntOrNull()
                result = if (ageInt == null) {
                    "⚠️ Vui lòng nhập tuổi hợp lệ!"
                } else {
                    when {
                        ageInt > 65 -> "👴 $name thuộc nhóm: Người già"
                        ageInt in 6..65 -> "🧑‍💼 $name thuộc nhóm: Người lớn"
                        ageInt in 2..5 -> "👶 $name thuộc nhóm: Trẻ em"
                        ageInt <= 2 -> "🍼 $name thuộc nhóm: Em bé"
                        else -> "❓ Không xác định"
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue, shape = RoundedCornerShape(8.dp)), // Thay đổi màu nền
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue) // Áp dụng màu xanh dương
        ) {
            Text("Kiểm tra", color = Color.White) // Đổi màu chữ thành trắng để dễ đọc
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = result,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
    }
}

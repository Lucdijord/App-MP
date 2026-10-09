package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AdicionarEnderecoScreen(
    onVoltar: () -> Unit
) {
    var rua by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var complemento by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Adicionar Endereço",
                style = MaterialTheme.typography.headlineSmall
            )

            OutlinedTextField(
                value = rua,
                onValueChange = { rua = it },
                label = { Text("Rua") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = numero,
                onValueChange = { numero = it },
                label = { Text("Número") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = bairro,
                onValueChange = { bairro = it },
                label = { Text("Bairro") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cidade,
                onValueChange = { cidade = it },
                label = { Text("Cidade") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = complemento,
                onValueChange = { complemento = it },
                label = { Text("Complemento (opcional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (
                        rua.isBlank() ||
                        numero.isBlank() ||
                        bairro.isBlank() ||
                        cidade.isBlank()
                    ) {
                        Toast.makeText(
                            context,
                            "Preencha os campos obrigatórios!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        DadosEnderecos.adicionar(
                            rua.trim(),
                            numero.trim(),
                            bairro.trim(),
                            cidade.trim(),
                            complemento.trim()
                        )

                        Toast.makeText(
                            context,
                            "Endereço cadastrado!",
                            Toast.LENGTH_SHORT
                        ).show()

                        onVoltar()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9D2438),
                    contentColor = Color.White
                )
            ) {
                Text("Salvar Endereço")
            }
        }
    }
}
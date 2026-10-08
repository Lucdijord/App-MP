
package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Endereço") },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

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
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Endereço")
            }
        }
    }
}

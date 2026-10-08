
package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdicionarProdutoScreen(
    onVoltar: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Produto") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome do produto") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = preco,
                onValueChange = { preco = it },
                label = { Text("Preço") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = categoria,
                onValueChange = { categoria = it },
                label = { Text("Categoria") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val valor = preco.replace(",", ".").toDoubleOrNull()

                    if (nome.isBlank() ||
                        categoria.isBlank() ||
                        valor == null ||
                        !valor.isFinite() ||
                        valor <= 0
                    ) {
                        Toast.makeText(
                            context,
                            "Preencha os campos corretamente!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {

                        DadosProdutos.adicionar(
                            nome.trim(),
                            valor,
                            categoria.trim()
                        )

                        Toast.makeText(
                            context,
                            "Produto cadastrado!",
                            Toast.LENGTH_SHORT
                        ).show()

                        onVoltar()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Produto")
            }
        }
    }
}

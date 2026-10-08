
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheEnderecoScreen(
    enderecoId: Int,
    onVoltar: () -> Unit
) {

    val endereco = DadosEnderecos.enderecos.find {
        it.id == enderecoId
    }

    var complemento by remember(enderecoId) {
        mutableStateOf(endereco?.complemento ?: "")
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes do Endereço") },
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

            if (endereco == null) {

                Text("Endereço não encontrado.")

                Button(onClick = onVoltar) {
                    Text("Voltar")
                }

            } else {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            text = endereco.rua,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        HorizontalDivider()

                        Text(
                            text = "Número: ${endereco.numero}"
                        )

                        Text(
                            text = "Bairro: ${endereco.bairro}"
                        )

                        Text(
                            text = "Cidade: ${endereco.cidade}"
                        )

                        Text(
                            text = "Complemento: " +
                                    endereco.complemento.ifBlank {
                                        "Não informado"
                                    }
                        )
                    }
                }

                Text(
                    text = "Editar Complemento",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = complemento,
                    onValueChange = { complemento = it },
                    label = {
                        Text("Complemento do endereço")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2
                )

                Button(
                    onClick = {

                        DadosEnderecos.atualizarComplemento(
                            enderecoId,
                            complemento.trim()
                        )

                        Toast.makeText(
                            context,
                            "Endereço atualizado!",
                            Toast.LENGTH_SHORT
                        ).show()

                        onVoltar()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA62A2A)
                    )
                ) {
                    Text("Salvar Alteração")
                }
            }
        }
    }
}

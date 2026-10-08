
package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnderecosScreen(
    onAdicionarClick: () -> Unit,
    onEnderecoClick: (Int) -> Unit,
    onVoltar: () -> Unit
) {

    Scaffold() { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                "Endereços cadastrados",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onAdicionarClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Adicionar Endereço")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (DadosEnderecos.enderecos.isEmpty()) {

                Text("Nenhum endereço cadastrado.")

            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = DadosEnderecos.enderecos,
                        key = { it.id }
                    ) { endereco ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onEnderecoClick(endereco.id)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF5F5F5)
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {

                                    Text(
                                        text = endereco.rua,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = "Nº ${endereco.numero}"
                                    )

                                    Text(
                                        text = endereco.bairro
                                    )

                                    Text(
                                        text = endereco.cidade,
                                        color = Color.Gray
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        DadosEnderecos.remover(endereco)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Remover endereço",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

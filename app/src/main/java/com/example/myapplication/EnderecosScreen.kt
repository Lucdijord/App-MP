package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun EnderecosScreen(
    onAdicionarClick: () -> Unit,
    onEnderecoClick: (Int) -> Unit,
    onVoltar: () -> Unit
) {
    val vinho = Color(0xFF9D2438)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Endereços cadastrados",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAdicionarClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = vinho,
                contentColor = Color.White
            )
        ) {
            Text("Adicionar Endereço")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (DadosEnderecos.enderecos.isEmpty()) {

            Text(
                text = "Nenhum endereço cadastrado.",
                color = Color.Gray
            )

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(
                    items = DadosEnderecos.enderecos,
                    key = { it.id }
                ) { endereco ->

                    Card(
                        onClick = {
                            onEnderecoClick(endereco.id)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
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
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(4.dp))

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
                                    tint = vinho
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalheProdutoScreen(
    produtoId: Int,
    onVoltar: () -> Unit,
    onAdicionarCarrinho: (Produto, Int) -> Unit
) {
    val produto = DadosProdutos.produtos.find {
        it.id == produtoId
    }

    var quantidade by remember(produtoId) {
        mutableStateOf(1)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes do Produto") },
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

        if (produto == null) {
            Column(
                modifier = Modifier.padding(padding).padding(16.dp)
            ) {
                Text("Produto não encontrado")
                Button(onClick = onVoltar) {
                    Text("Voltar")
                }
            }
        } else {

            val total = produto.preco * quantidade

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

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
                            text = produto.nome,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Categoria: ${produto.categoria}",
                            color = Color.Gray
                        )

                        Text(
                            text = String.format(
                                Locale.forLanguageTag("pt-BR"),
                                "R$ %.2f",
                                produto.preco
                            ),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFA62A2A)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                onAdicionarCarrinho(produto, quantidade)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFA62A2A)
                            )
                        ) {
                            Text("Adicionar ao Carrinho")
                        }
                    }
                }

                Text(
                    text = "Quantidade",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    OutlinedButton(
                        onClick = { quantidade-- },
                        enabled = quantidade > 1
                    ) {
                        Text("-")
                    }

                    Text(
                        text = quantidade.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedButton(
                        onClick = { quantidade++ }
                    ) {
                        Text("+")
                    }
                }

                HorizontalDivider()

                Text(
                    text = "Valor total",
                    fontSize = 18.sp
                )

                Text(
                    text = String.format(
                        Locale.forLanguageTag("pt-BR"),
                        "R$ %.2f",
                        total
                    ),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFA62A2A)
                )
            }
        }
    }
}

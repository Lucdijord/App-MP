
package com.example.myapplication

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
import java.util.Locale

@Composable
fun CarrinhoScreen(
    onCatalogoClick: () -> Unit
) {
    val itens = DadosCarrinho.itens
    val formatoBrasil = Locale.forLanguageTag("pt-BR")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Meu Carrinho",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (itens.isEmpty()) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Seu carrinho está vazio!")

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onCatalogoClick) {
                    Text("Ver produtos")
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = itens,
                    key = { it.produto.id }
                ) { item ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
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
                                    text = item.produto.nome,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )

                                Text(
                                    text = "Quantidade: ${item.quantidade}"
                                )

                                Text(
                                    text = String.format(
                                        formatoBrasil,
                                        "R$ %.2f",
                                        item.produto.preco * item.quantidade
                                    ),
                                    color = Color(0xFFA62A2A),
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            IconButton(
                                onClick = {
                                    DadosCarrinho.remover(
                                        item.produto.id
                                    )
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remover do carrinho",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }

            HorizontalDivider()

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = String.format(
                        formatoBrasil,
                        "R$ %.2f",
                        DadosCarrinho.calcularTotal()
                    ),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFA62A2A)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onCatalogoClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continuar comprando")
            }
        }
    }
}

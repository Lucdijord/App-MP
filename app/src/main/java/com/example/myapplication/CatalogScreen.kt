
package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import androidx.compose.foundation.clickable

@Composable
fun CatalogScreen(
    onAdicionarClick: () -> Unit,
    onProdutoClick: (Int) -> Unit
) {
    var pesquisa by remember { mutableStateOf("") }

    val produtosFiltrados = DadosProdutos.produtos.filter {
        it.nome.contains(pesquisa, ignoreCase = true) ||
                it.categoria.contains(pesquisa, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Catálogo de Produtos",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pesquisa,
            onValueChange = { pesquisa = it },
            label = { Text("Buscar produtos") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAdicionarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar produto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Produtos disponíveis",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = produtosFiltrados,
                key = { it.id }
            ) { produto ->

                Card(
                    modifier = Modifier.fillMaxWidth().clickable{ onProdutoClick(produto.id)},
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
                                text = produto.nome,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = produto.categoria,
                                color = Color.Gray
                            )

                            Text(
                                text = String.format(
                                    Locale.forLanguageTag("pt-BR"),
                                    "R$ %.2f",
                                    produto.preco
                                ),
                                color = Color(0xFFA62A2A),
                                fontWeight = FontWeight.Bold
                            )
                        }

                        IconButton(
                            onClick = {
                                DadosProdutos.remover(produto)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Remover produto",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}

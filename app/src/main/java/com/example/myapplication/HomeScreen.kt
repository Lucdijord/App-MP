
package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun HomeScreen(
    onCatalogoClick: () -> Unit,
    onPesquisarClick: (String) -> Unit,
    onCategoriaClick: (String) -> Unit,
    onProdutoClick: (Int) -> Unit
) {
    var pesquisa by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFFFFEBEE)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "MP",
                color = Color(0xFFA62A2A),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pesquisa,
            onValueChange = { pesquisa = it },
            label = { Text("Buscar produtos...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onPesquisarClick(pesquisa.trim())
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                onPesquisarClick(pesquisa.trim())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        PromoBanner(onCatalogoClick)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Categorias",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            CategoryItem(
                title = "Eletro",
                modifier = Modifier.weight(1f),
                onClick = {
                    onCategoriaClick("Eletrônicos")
                }
            )

            CategoryItem(
                title = "Casa",
                modifier = Modifier.weight(1f),
                onClick = {
                    onCategoriaClick("Casa")
                }
            )

            CategoryItem(
                title = "Beleza",
                modifier = Modifier.weight(1f),
                onClick = {
                    onCategoriaClick("Beleza")
                }
            )

            CategoryItem(
                title = "Mais",
                modifier = Modifier.weight(1f),
                onClick = {
                    onCategoriaClick("Todos")
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Destaques para você",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        val destaques = DadosProdutos.produtos.take(3)

        if (destaques.isEmpty()) {
            Text("Nenhum produto disponível.")
        } else {
            destaques.forEach { produto ->

                ProdutoDestaque(
                    nome = produto.nome,
                    preco = String.format(
                        Locale.forLanguageTag("pt-BR"),
                        "R$ %.2f",
                        produto.preco
                    ),
                    onClick = {
                        onProdutoClick(produto.id)
                    }
                )
            }
        }
    }
}

@Composable
fun PromoBanner(onCatalogoClick: () -> Unit) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFCDD2)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Ofertas imperdíveis",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text("Confira nossos produtos!")

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onCatalogoClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                )
            ) {
                Text("Ver ofertas")
            }
        }
    }
}

@Composable
fun CategoryItem(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(70.dp)
            .clickable { onClick() },
        color = Color(0xFFF0F0F0),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProdutoDestaque(
    nome: String,
    preco: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = nome,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = preco,
                    color = Color(0xFFA62A2A)
                )
            }

            Button(onClick = onClick) {
                Text("Ver")
            }
        }
    }
}

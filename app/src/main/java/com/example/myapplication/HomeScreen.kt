
package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(onCatalogoClick: () -> Unit) {

    var pesquisa by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Logo
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

        // Pesquisa
        OutlinedTextField(
            value = pesquisa,
            onValueChange = { pesquisa = it },
            label = { Text("Buscar produtos...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

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
                "Eletro",
                Modifier.weight(1f),
                onCatalogoClick
            )
            CategoryItem(
                "Casa",
                Modifier.weight(1f),
                onCatalogoClick
            )
            CategoryItem(
                "Beleza",
                Modifier.weight(1f),
                onCatalogoClick
            )
            CategoryItem(
                "Mais",
                Modifier.weight(1f),
                onCatalogoClick
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

        ProdutoDestaque(
            "Fone de Ouvido",
            "R$ 159,90",
            onCatalogoClick
        )

        ProdutoDestaque(
            "Smartwatch",
            "R$ 299,90",
            onCatalogoClick
        )

        ProdutoDestaque(
            "Perfume",
            "R$ 89,90",
            onCatalogoClick
        )
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

            Text("Descontos especiais para você!")

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
            Column {
                Text(nome, fontWeight = FontWeight.Bold)
                Text(preco, color = Color(0xFFA62A2A))
            }

            Button(onClick = onClick) {
                Text("Ver")
            }
        }
    }
}

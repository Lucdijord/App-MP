
package com.example.myapplication

import androidx.compose.runtime.mutableStateListOf

data class Produto(
    val id: Int,
    val nome: String,
    val preco: Double,
    val categoria: String
)

object DadosProdutos {

    val produtos = mutableStateListOf(
        Produto(1, "Fone de Ouvido", 159.90, "Eletrônicos"),
        Produto(2, "Smartwatch", 299.90, "Eletrônicos"),
        Produto(3, "Perfume", 89.90, "Beleza")
    )

    private var proximoId = 4

    fun adicionar(nome: String, preco: Double, categoria: String) {
        produtos.add(
            Produto(proximoId, nome, preco, categoria)
        )
        proximoId++
    }

    fun remover(produto: Produto) {
        produtos.remove(produto)
        DadosCarrinho.remover(produto.id)
    }
}

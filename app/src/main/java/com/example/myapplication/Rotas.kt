package com.example.myapplication

object Rotas {
    const val INICIO = "inicio"
    const val CATALOGO = "catalogo"
    const val CARRINHO = "carrinho"
    const val PERFIL = "perfil"

    const val ADICIONAR_PRODUTO = "adicionar_produto"
    const val DETALHE_PRODUTO = "detalhe_produto/{id}"

    fun detalheProduto(id: Int): String {
        return "detalhe_produto/$id"
    }

    const val ENDERECOS = "enderecos"
    const val ADICIONAR_ENDERECO = "adicionar_endereco"
    const val DETALHE_ENDERECO = "detalhe_endereco/{id}"

    fun detalheEndereco(id: Int): String {
        return "detalhe_endereco/$id"
    }
}
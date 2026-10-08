
package com.example.myapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.*

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val telaAtual by navController.currentBackStackEntryAsState()
    val rotaAtual = telaAtual?.destination?.route

    var pesquisaCatalogo by remember {
        mutableStateOf("")
    }

    var categoriaCatalogo by remember {
        mutableStateOf("Todos")
    }

    fun abrirCatalogo(
        pesquisa: String = "",
        categoria: String = "Todos"
    ) {
        pesquisaCatalogo = pesquisa
        categoriaCatalogo = categoria

        navController.navigate(Rotas.CATALOGO)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {

                val abas = listOf(
                    Triple("Início", Rotas.INICIO, Icons.Default.Home),
                    Triple("Catálogo", Rotas.CATALOGO, Icons.Default.List),
                    Triple("Carrinho", Rotas.CARRINHO, Icons.Default.ShoppingCart),
                    Triple("Perfil", Rotas.PERFIL, Icons.Default.Person)
                )

                abas.forEach { (nome, rota, icone) ->

                    NavigationBarItem(
                        selected = rotaAtual == rota,
                        onClick = {

                            if (rotaAtual != rota) {

                                if (rota == Rotas.CATALOGO) {
                                    pesquisaCatalogo = ""
                                    categoriaCatalogo = "Todos"
                                }

                                navController.navigate(rota) {
                                    popUpTo(Rotas.INICIO) {
                                        inclusive = false
                                    }
                                    launchSingleTop = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = icone,
                                contentDescription = nome
                            )
                        },
                        label = {
                            Text(nome)
                        }
                    )
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Rotas.INICIO,
            modifier = Modifier.padding(padding)
        ) {

            composable(Rotas.INICIO) {

                HomeScreen(
                    onCatalogoClick = {
                        abrirCatalogo()
                    },

                    onPesquisarClick = { pesquisa ->
                        abrirCatalogo(
                            pesquisa = pesquisa
                        )
                    },

                    onCategoriaClick = { categoria ->
                        abrirCatalogo(
                            categoria = categoria
                        )
                    },

                    onProdutoClick = { id ->
                        navController.navigate(
                            Rotas.detalheProduto(id)
                        )
                    }
                )
            }


            composable(Rotas.CATALOGO) {

                CatalogScreen(
                    pesquisaInicial = pesquisaCatalogo,
                    categoriaInicial = categoriaCatalogo,

                    onAdicionarClick = {
                        navController.navigate(
                            Rotas.ADICIONAR_PRODUTO
                        )
                    },

                    onProdutoClick = { id ->
                        navController.navigate(
                            Rotas.detalheProduto(id)
                        )
                    }
                )
            }

            composable(Rotas.ADICIONAR_PRODUTO) {

                AdicionarProdutoScreen(
                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = Rotas.DETALHE_PRODUTO,
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val id = backStackEntry.arguments
                    ?.getInt("id") ?: -1

                DetalheProdutoScreen(
                    produtoId = id,

                    onVoltar = {
                        navController.popBackStack()
                    },

                    onAdicionarCarrinho = { produto, quantidade ->

                        DadosCarrinho.adicionar(
                            produto,
                            quantidade
                        )

                        navController.navigate(Rotas.CARRINHO) {
                            popUpTo(Rotas.INICIO) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(Rotas.CARRINHO) {

                CarrinhoScreen(
                    onCatalogoClick = {
                        pesquisaCatalogo = ""
                        categoriaCatalogo = "Todos"

                        navController.navigate(Rotas.CATALOGO) {
                            popUpTo(Rotas.INICIO) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(Rotas.PERFIL) {

                ProfileScreen(
                    onEnderecosClick = {
                        navController.navigate(
                            Rotas.ENDERECOS
                        )
                    },

                    onCatalogoClick = {
                        abrirCatalogo()
                    }
                )
            }

            composable(Rotas.ENDERECOS) {

                EnderecosScreen(
                    onAdicionarClick = {
                        navController.navigate(
                            Rotas.ADICIONAR_ENDERECO
                        )
                    },

                    onEnderecoClick = { id ->
                        navController.navigate(
                            Rotas.detalheEndereco(id)
                        )
                    },

                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Rotas.ADICIONAR_ENDERECO) {

                AdicionarEnderecoScreen(
                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = Rotas.DETALHE_ENDERECO,
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val id = backStackEntry.arguments
                    ?.getInt("id") ?: -1

                DetalheEnderecoScreen(
                    enderecoId = id,

                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

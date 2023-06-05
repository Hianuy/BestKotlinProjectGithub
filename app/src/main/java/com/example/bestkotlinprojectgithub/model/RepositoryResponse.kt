package com.example.bestkotlinprojectgithub.model


data class RepositoryResponse(
    val items: List<Item>
)


data class Item(
    val id: Long,
    val name: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val owner: Owner
)

data class Owner(
    val login: String,
    val avatar_url: String
)



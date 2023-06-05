#  BestKotlinProjectGithub
# Introdução 
Fazer um aplicativo que consuma API do Github.

# O que o aplicativo faz?
Criar um app para consultar a API do Github e trazer os repositórios com mais estrelas em
Kotlin, que contenha:
- Lista de Repositórios
- exemplo de
chamada: https://api.github.com/search/repositories?q=language:kotlin&sort=stars&page
=1
- Exibir nome do repo, quantidade de estrelas, quantidade de fork, foto e nome do autor
- Scroll infinito
- Testes unitários
Seria legal se tivesse:
- Kotlin
- Android Architecture Components
- Testes de UI usando Espresso
- Rx ou Coroutines
- Cache de imagens e da API
- Suportar mudanças de orientação das telas sem perder o estado


# O que foi usado na construção do Aplicativo?
- Kotlin
- Extensions
- Xml para construção dos layouts
- Material Design
- RecyclerView
- Adapters 
- Glide para carregamento de imagens
- ViewBinding
- MVVM + CLEAN ARCHITECTURE 
- Coroutines
- Injeção de Dependência via Koin
- Padrões de projeto como Repository com utilização de Interface

Aplicativo para consultar API do Github e trazer os repositórios mais populare ou Kotlin

Lista de repositórios. Exemplo de chamada na API: https://api.github.com/search/repositories?q=language:Kotlin&sort=stars&page=1; ✔️

Paginação na tela de lista, com endless scroll; ✔️

Cada repositório deve exibir: Nome do repositório, Descrição do Repositório, Nome/Foto do autor, Número de Stars, Número de Forks; ✔️

Cache de imagens; ✔️

Coroutines para comunicação com a API;  ✔️

Cache da API;  ✔️

Testes no projeto (unitários e por tela); ❌

Suportar mudanças de orientação das telas sem perder estado;  ✔️

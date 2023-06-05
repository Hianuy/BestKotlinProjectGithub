#  BestKotlinProjectGithub
# Introdução 
Criar um app para consultar a API do Github e trazer alguns dados.

# O que o aplicativo faz?

O app precisa consultar a API do Github e trazer os repositórios com mais estrelas em
Kotlin, que contenha:
- Lista de Repositórios ✔️

- Exibir nome do repo, quantidade de estrelas, quantidade de fork, foto e nome do autor ✔️
- Scroll infinito ✔️
- Testes unitários ❌

Seria legal se tivesse:
- Kotlin ✔️
- Android Architecture Components(Paging, ViewModel e etc.) ✔️
- Testes de UI usando Espresso ❌
- Rx ou Coroutines ✔️
- Cache de imagens e da API(Incompleto cache de API) ✔️
- Suportar mudanças de orientação das telas sem perder o estado(MVVM)  ✔️

Exemplo de chamada -> https://api.github.com/search/repositories?q=language:kotlin&sort=stars&page
=1

# O que foi usado na construção do Aplicativo?
- Kotlin
- Extensions
- Xml para construção dos layouts
- Material Design
- RecyclerView
- Adapters 
- Glide para carregamento de imagens
- ViewBinding
- Paging
- MVVM + CLEAN ARCHITECTURE 
- Coroutines
- Injeção de Dependência via Koin
- Padrões de projeto como Repository com utilização de Interface


https://github.com/Hianuy/BestKotlinProjectGithub/assets/30296650/6e7e24d5-8ff1-46df-8a22-6ca0b8ca6efe


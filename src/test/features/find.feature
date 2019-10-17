# language: pt
Funcionalidade: Procurar pessoa por ID

    Cenário de Fundo:
        Dado que exista uma pessoa cadastrada

    Cenário: 01 - procurando por uma pessoa existente
        Dado que uma pessoa de id "1"
        Então deveria retornar uma pessoa de nome "Pessoa Teste"

    Cenário: 02 - procurando por uma pessoa não existente
        Dado que uma pessoa de id "2"
        Então deveria retornar uma exception not found

// A DIO possui 'Formacoes' incríveis que têm como objetivo oferecer um conjunto de 'ConteudosEducacionais' 
// voltados para uma stack tecnológica específica, preparando profissionais de TI para o mercado de trabalho. 
// 'Formacoes' possuem algumas características importantes, como 'nome', 'nivel' e seus respectivos 'conteudosEducacionais'. 
// Além disso, tais experiências educacionais têm um comportamento relevante ao nosso domínio, 
// definido pela capacidade de 'matricular' um ou mais 'Alunos'.


enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val id: Int, var nome: String)

data class ConteudoEducacional(val id: Int, var nome: String, var duracao: Int)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>, var nivel: Nivel) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        var teste = false;
        when (nivel) {
            Nivel.BASICO -> if (inscritos.count() < 3) { teste = true}
            Nivel.INTERMEDIARIO -> if (inscritos.count() < 2) { teste = true }
            Nivel.DIFICIL -> if (inscritos.count() < 1) { teste = true }
        }
        
        if (teste) {
            inscritos.add(usuario)
            println("Matricula de ${usuario.nome} efetuada com sucesso!")            
        }
        else {
            println("${usuario.nome}, Não há mais vagas em aberto!!!")
        }
               
        println("")
    }
    
    fun relatorio() {
        println("--- Relatório Formação $nome ---")
        println("Conteudos: ")
        val ordenar = conteudos.sortedBy {it.id}
        ordenar.forEach(){
            it -> println("- ${it.nome}")
        }
        
        println("Matriculados Quantidade(${inscritos.count()}) :")
        inscritos.sortBy {it.id}
        inscritos.forEach(){
            it -> println("- ${it.nome}")
        }      
        
        var quant = when (nivel) {
            Nivel.BASICO -> 3 - inscritos.count()
            Nivel.INTERMEDIARIO -> 2 - inscritos.count()
            Nivel.DIFICIL -> 1 - inscritos.count()
        }
        println("Quantidade vaga(s) em aberto: $quant")
        println("--------------------------------")
        println("")
    }
}

fun main() {
    var conteudosprimeiraformacao = listOf(ConteudoEducacional(1,"Fundamentos",120),ConteudoEducacional(3,"Programação",240),ConteudoEducacional(2,"Projetos",40))
    var primeiraformacao = Formacao("Kotlin", conteudosprimeiraformacao, Nivel.INTERMEDIARIO)
    
    var conteudossegundaformacao = listOf(ConteudoEducacional(1,"Estrutura de Dados",120),ConteudoEducacional(3,"SQL",240),ConteudoEducacional(2,"PSQL",40))
    var segundaformacao = Formacao("Banco de dados", conteudossegundaformacao, Nivel.BASICO)
    
    var conteudosterceiraformacao = listOf(ConteudoEducacional(1,"Estrutura",120),ConteudoEducacional(3,"linux",240),ConteudoEducacional(2,"Protocolos",40))
    var terceiraformacao = Formacao("Redes de Computadores", conteudosterceiraformacao, Nivel.DIFICIL)

   

    primeiraformacao.matricular(Usuario(1,"Guilherme"))
    primeiraformacao.matricular(Usuario(3,"Vinicius"))
    primeiraformacao.matricular(Usuario(2,"Lilian"))
    
    
    segundaformacao.matricular(Usuario(1,"Guilherme"))
    segundaformacao.matricular(Usuario(2,"Vinicius"))
    
    terceiraformacao.matricular(Usuario(1,"Guilherme"))
    terceiraformacao.matricular(Usuario(2,"João"))
    
    
    primeiraformacao.relatorio()    
    segundaformacao.relatorio() 
    terceiraformacao.relatorio()
    
}

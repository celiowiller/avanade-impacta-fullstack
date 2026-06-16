// aqui, temos de importar o recurso necessario para que seja possivel criar a injeção de dependencia para as requisições HTTP - GET, POST, PUT, DELETE
import { HttpClient } from '@angular/common/http';

import { Injectable, inject, signal, computed } from '@angular/core'; // este é o recurso necessario para o uso do decorator @Injectable

import { Observable, tap } from 'rxjs'; // recursos neessarios para trabalhar com nossas tarefas assincronas

import { jwtDecode } from 'jwt-decode'; // recurso que vai auxiliar na "decodificação" do token para a obtenção da role, especifica, do usuario

import { UsuarioModel } from '../models/usuario.model'; // este é o nosso model domain do usuario

// @Injectable permite que, se necessario for, esta classe seja passivel de receber uma injeção de dependencia
@Injectable({
  providedIn: 'root' // este trecho é fundamental para as aplicações angular "modernas"; porque, aqui, temos aqui que vamos chamar  de "gerenciamento de singletons"; estamos, dessa forma, dizendo que: este service deve ser uma instancia única que será "compartilhada" por todo e qualquer componente que quiser fazer uso das instruções, aqui, descritas.
})
// este service é responsavel por processar os dados de cadastro do usuario.
export class UsuarioService {
/*
========================================================================================================
  1º BLOCO: IMPLEMENTAR OS RECURSOS NECESSARIOS PARA AS OPERAÇÕES DE DADOS
========================================================================================================
*/

// // 1º passo: definir uma prop que receberá como valor o método inject(); com a class HttpClient definindo, dessa forma, a injeção de dependencia para as requisições http
private http = inject(HttpClient) // agora, podemos usar a prop http como injeção de dependencia para fazer as requisições http

// 2º passo: agora, vamos definir uma nova propriedade que receberá como valor a URL base para a INTEGRAÇÃO DO FRONT COM A API - adequada - DEFINIDA NO BACKEND
private readonly apiURL: string = 'http://localhost:8080' // neste momento, "integramos" o nosso front com nosos backend 


// 3º passo: aqui, vamos utilizar o conceito e o recurso SIGNAL DE ESTADO/STATE -> ESTE RECURSO vai nos auxiliar na observação do estado dos dados do usuario: por exemplo -> se o usuario está, ou não autenticado; para este proposito, podemos verificar a existencia do token e se este mesmo token está armazenado no localStorage
private _estaLogado = signal<boolean>(this.verificarToken())

// 4º passo: neste passo, precisamos "expor" o signal; porque, dessa forma, podemos acessar o estado dos dados do usuario - a partir de outros componentes, se assim for necessario
public estaLogado = computed(() => this._estaLogado())


  constructor() { }

/*
========================================================================================================
  2º BLOCO: IMPLEMENTAR AS OPERAÇÕES DE DADOS USANDO OS RECURSOS DEFINIDOS NO 1º BLOCO
========================================================================================================
*/

// 1º operação assincrona: definir o método/tarefa asincrona/promise para o cadastro de registro de um usuario
cadastrar(usuario: UsuarioModel): Observable<void>{
  // agora, precisamos definir a expressão de retorno do metodo
  return this.http.post<void>(`${this.apiURL}/usuarios`, usuario)

  /*
    Observable<void>: padrão classico - no Angular - para a comunicação assincrona com APIs REST. Como "comportamento" padrão, o método não tem "compromisso" de retornar  um contexto de "corpo de requisição" em formato JSON - os dados que foram cadastrados, simplesmente, cumprem sua tarefa - serem registrado na base -pela PAI backend; portanto não se faz necessario qualquer retorno com os dados de registro


    http: esta é a injeção de dependencia para a construção da requisição http para a API e seu respectivo endepoint


    post<void>: requisição HTTP d eenvio de dados 

    (`${this.apiURL}/usuarios`, usuario): aqui, estamos fazendo a requisição HTTP POST para a API de cadastro de usuario - java springboot. o endpoint /usuarios é ponto especifico da chamada.
  */
}


// 2º operação assincrona: definir o método/tarefa asincrona/promise para o login de um usuario
login(credenciais: Partial<UsuarioModel>): Observable<{token: string}>{
  return this.http.post<{token: string}>(`${this.apiURL}/auth/login`, credenciais)
  .pipe(
    tap(
      resposta => {
        localStorage.setItem('token', resposta.token)

        this._estaLogado.set(true)
      }
    )
  )
}

/*
  Partial<UsuarioModel>: aqui, temos a indicação que "nao precisamos" de todos os dados do usuario; precisamos, apenas, dos dados que dever ser registrados na base -> por exemplo, não precimos ID do usuario; quem nos esta possibilidade de lidar com apenas "parte", necessaria dos dados é o recurso Partial

  .pipe(): método que nos auxilia no estabelecimento de uma "comunicação assincrona direta" com a API para que, caso ocorra alguma interrupção, ao ser reestabelecida, podemos concluir a requisição  

  tap(): método que obtem o fluxo dos dados do Observable e armazena este token no localStorage - no front -para que, dessa forma, o usuario possa "navegar" pelas areas da aplicação - desde que tenha a role adequada

  this._estaLogado.set(true): o signal, que esta sendo referencia com o proposito de "observar" se há alguma alteração no estado dos dados do usuario 
  */

// 3º operação - SINCRONA: definir o método para verificar se existe o token armazenado no localStorage
private verificarToken(): boolean{
  return !!localStorage.getItem('token')
  // !!localStorage: o uso do operador !! transforma qualquer num valor boolean 
  // "HHASAnçsdfnçasdfks765" - TRUE
  // ! INVERSOR LOGICO
  // !! - INVERTE MAIS UMA VEZ
} 

// 4º operação - SINCRONA: obter o token a partir do storage
obterToken(): string | null{
  return localStorage.getItem('token')
}


// 5º operação SINCRONA: recuperar o nivel de acesso de usuario armazenado no processo de login: para este proposito vamos definir um método chamado obterRole
obterRole(): string | null{
  const token = localStorage.getItem('token')
  if(!token) return null

  const decode: any = jwtDecode(token)

  return decode.role || null
}

// 6ª operação: definir o método/tarefa assincrona/promise para o logout de um usuario
logout(): void{
    localStorage.removeItem('token')// aqui, para fazer o logout do usuario estamos "limpando" o localStorage e, portanto, "matando" a sessão que foi estabelecida quando o usuario fez seu login e o token foi armazenado

    // precisamos referenciar o signal para que ocorra a "notificação" de estado dos dados de usuario: de true (usuario logado) para false(usuario "deslogado")
    this._estaLogado.set(false)
  }
}

import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SensorModel } from '../models/sensor.model';

@Injectable({
  providedIn: 'root'
})
export class SensorService {

  /*
========================================================================================================
  1º BLOCO: IMPLEMENTAR OS RECURSOS NECESSARIOS PARA AS OPERAÇÕES DE DADOS
========================================================================================================
*/
// 1º passo: definir uma prop que receberá como valor o método inject(); com a class HttpClient definindo, dessa forma, a injeção de dependencia para as requisições http
private http = inject(HttpClient) // agora, podemos usar a prop http como injeção de dependencia para fazer as requisições http

// 2º passo: agora, vamos definir uma nova propriedade que receberá como valor a URL base para a INTEGRAÇÃO DO FRONT COM A API - adequada - DEFINIDA NO BACKEND
private readonly apiURL: string = 'http://localhost:8080/sensores' // neste momento, "integramos" o nosso front com nosos backend

  constructor() { }

  /*
==========================================================================================
  2º BLOCO: IMPLEMENTAR  AS OPERAÇÕES DE DADOS
==========================================================================================
*/

// 1º operação assincrona: listar todos os sensores
buscarTodos(): Observable<SensorModel[]>{
  return this.http.get<SensorModel[]>(this.apiURL)
}

// 2º operação assincrona: buscar um sensor por Id
buscarPorId(id: string): Observable<SensorModel>{
  return this.http.get<SensorModel>(`${this.apiURL}/${id}`)
}

// 3º operação assincrona: criar/registrar/armazenar um novo sensor(ADMIN)
salvar(sensor: Partial<SensorModel>){
  return this.http.post<void>(this.apiURL, sensor)
}

// 4º operação assincrona: atualizar/alterar nome do sensor (ADMIN)
atualizarNome(id: string, nome: string): Observable<void>{
  return this.http.put<void>(`${this.apiURL}/${id}`, {nome})
}

// 5º operação assincrona: atualizar/alterar a localização de um sensor (ADMIN)
atualizarLocalizacao(id: string, localizacao: string): Observable<void>{
  return this.http.put<void>(`${this.apiURL}/${id}/localizacao`, {localizacao})
}

// 6º operação assincrona: excluir um sensor (ADMIN)
deletar(id: string): Observable<void>{
  return this.http.delete<void>(`${this.apiURL}/${id}`)
}

// 7º operação assincrona: buscar um sensor com leituras
buscarComLeituras(): Observable<any[]>{
  return this.http.get<any[]>(`${this.apiURL}/com-leituras`)
}
}

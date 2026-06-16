import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SensorComLeituras } from '../models/leitura.model';

@Injectable({
  providedIn: 'root'
})
export class LeituraService {

/*
=====================================================================================
  1º BLOCO: IMPLEMENTAR OS RECURSOS NECESSARIOS PARA AS OPERAÇÕES DE DADOS
====================================================================================
*/
// 1º passo: definir uma prop que receberá como valor o método inject(); com a class HttpClient definindo, dessa forma, a injeção de dependencia para as requisições http
private http = inject(HttpClient) // agora, podemos usar a prop http como injeção de dependencia para fazer as requisições http

// 2º passo: agora, vamos definir uma nova propriedade que receberá como valor a URL base para a INTEGRAÇÃO DO FRONT COM A API - adequada - DEFINIDA NO BACKEND
private readonly apiURL: string = 'http://localhost:8080/sensores' // neste momento, "integramos" o nosso front com nosos backend

private readonly apiURLLeitura: string = 'http://localhost:8080/leituras'

  constructor() { }

  /*
==========================================================================================
  2º BLOCO: IMPLEMENTAR  AS OPERAÇÕES DE DADOS
==========================================================================================
*/

  // 1º operação assincrona: buscar todos os registros de sensores com seus historicos de medição 
  obterDashboardCompleto(): Observable<SensorComLeituras[]>{
    return this.http.get<SensorComLeituras[]>(`${this.apiURL}/com-leituras`)
  }

  // 2º operação assincrona: registrar uma nova medição leitura 
  registrarLeitura(leitura: any): Observable<void>{
    return this.http.post<void>(this.apiURLLeitura, leitura)
  }
}
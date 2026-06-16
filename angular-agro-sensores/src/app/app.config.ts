// este é o arquivo responsavel pela configuração de alguns recursos "globais" da aplicação
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';

// então, precisamos adicionar/configurar os seguintes recursos
import { provideHttpClient, withInterceptors } from '@angular/common/http'; // provê todos os recursos ncessarios para a construção das requisições HTTP

// configuração do interceptor: este é um recurso de fundamental importancia par ao processo de navegação do usuario! uma vez que ele - usuario - esteja autenticado - para este proposito
import { authInterceptor } from './interceptors/auth.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [ // este array, providers, define o registro de sigletons para a aplicação
    provideZoneChangeDetection({ eventCoalescing: true }), 
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([
        authInterceptor
      ])
    )
  ]
};

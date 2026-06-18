// este é o "protetor" de rotas de componentes que queremos manter com acesso restrito; este elemento também uma estrtura funcional
import { inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';

// o nosso "protetor" vai verificar se o usuario possui o token valido e suas credenciais estão adequadas para o acesso restrito
// UrlTree: auxilia no contexto de redirecionamento do usuario - a depender de suas credenciais e a requisição que este fazendo
export const authGuard: CanActivateFn = (route, state) : boolean | UrlTree => {

  const usuarioService: UsuarioService = inject(UsuarioService) // esta é nossa DI de dados do usuario 
  const router: Router = inject(Router) // aqui, temos a DI de roteamento

  // verificação para observar se o usuario esta logado
  if(usuarioService.estaLogado()){
    return true
  }

  // caso o usuario não esteja logado, podemos redireciona-lo para o rota de componente de login
  return router.parseUrl('/login')
};

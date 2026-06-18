// este é o "protetor" de rotas de componentes que queremos manter com acesso restrito; este elemento também uma estrtura funcional
import { inject } from '@angular/core';
import { CanActivateFn, Router, UrlTree } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';
import { UserRoleModel } from '../models/user-role.model';

// este é o nosso "protetor" admin; significa que as ações masi "sensiveis" - criar, atualziar, excluir - são apenas responsabilidade deste perfil
export const adminGuard: CanActivateFn = (route, state) : boolean | UrlTree => {

  const usuarioService: UsuarioService = inject(UsuarioService) // esta é nossa DI de dados do usuario 
  const router: Router = inject(Router) // aqui, temos a DI de roteamento

  const perfilUsuario = usuarioService.obterRole()

  // verificação para observar se o usuario esta logado
  if(usuarioService.estaLogado() && perfilUsuario === UserRoleModel.ADMIN){
    return true
  }
   // notificação de barreira de segurança
   alert('ACESSO NEGADO: Você não possui privilégios de Administrador da rede de sensores')
  // caso o usuario não esteja logado, podemos redireciona-lo para o rota de componente de login
  return router.parseUrl('/login')
};

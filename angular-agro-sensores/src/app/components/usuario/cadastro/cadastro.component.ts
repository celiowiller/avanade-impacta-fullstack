import { Component, inject } from '@angular/core'; // aqui, o recurso inject deve se fazer presente para criarmo a injeção de dependencia necessaria para executar o cadastro do usuario

import { CommonModule } from '@angular/common'; // auxilia no uso de elementos essencias do angular core

import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms'; // recursos fundamentais para a criação do formulario de cadastro do usuario

import { UserRoleModel } from '../../../models/user-role.model';

import { UsuarioService } from '../../../services/usuario.service'; // aqui, estamos importando o service que implementa as tarefas assincronas referentes aos dados de usuario

import { Router } from '@angular/router'; // recurso que irá auxiliar na definição de roteamento - a partir da estrutura logica do componente

@Component({
  selector: 'app-cadastro',
  imports: [CommonModule, ReactiveFormsModule], // array indicado para o "registro" dos recursos de módulo
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})
export class CadastroComponent {

  /*
  ========================================================================
    1º BLOCO: DEFINIÇÃO DE RECURSOS PARA AS OPERAÇÕES DO COMPONENTE
  ========================================================================
  */

  // 1º passo: titulo do componente
  public tituloComp: string =  'Cadastro do usuario'

  public subtituloComp: string  = 'Insira seus dados no formulario'

  // 2º passo: criar as DIs a partir do recursos definidos nas importações 
  private usuarioService: UsuarioService = inject(UsuarioService)
  private fb: FormBuilder = inject(FormBuilder)
  private router: Router = inject(Router)

  // 3º passo: definir uma prop que receberá como valor o model UsuarioRoleModel
  public perfisDisponiveis = Object.values(UserRoleModel) // aqui, estamos "pegando" todos os valores descritos na enum - UserRoleModel - e transformando num array; este é o resultado da transformação -> ['ADMIN', 'USER'] - dessa forma podemos, qunado vincular dados na view, usar, por exemplo, na estrutura select option

  /*
  ========================================================================
    2º BLOCO: DEFINIÇÃO DAS OPERAÇÕES DO COMPONENTE
  ========================================================================
  */

  // 1ª operação: definir a prop que receberá como valor os apres que serão atribuidos dos dados obtidos pelo formulario **** aqui, estamos trabalhando com a modalidade - do angular -  model-driven form: pois o comportamento do fomulario é "controlado" pela camada lógica! 
  cadastroFormulario = this.fb.group({
    login: ['', [Validators.required, Validators.email]] , // ...@t.com
    senha: ['', [Validators.required, Validators.minLength(6)]],
    role: [UserRoleModel.USER, Validators.required]
  })


  // 2ª operação: definir o método que irá "enviar" os dados de cadastro do usuario
  aoCadastrar(): void{
    // verificar se os dados de cadstro estão em conformidade para envio
    if(this.cadastroFormulario.valid){
      // se a avaliação for TRUE
      // aqui, abaixo, neste momento, a tarefa assincrona/promise será, então executada
      // "quem" executa a Promise é o método subscribe. Ele é o responsavel por execução - tarefas assincronas a partir do Observable
      this.usuarioService.cadastrar(this.cadastroFormulario.value as any).subscribe({
        next: () => this.router.navigate(['/login'])
      })
    }
  }


  // 3ª operação: definir o método que cancela, caso seja necessario, o cadstro e redireciona o usuario para a tela de login
  cancelar(): void{
    this.router.navigate(['/login'])
  }

}

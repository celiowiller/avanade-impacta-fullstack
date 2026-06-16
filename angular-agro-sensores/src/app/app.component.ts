// 1. aqui, SEMPRE SERÁ NECESSARIO FAZER IMPORTAÇÕES DOS RECURSOS QUE CADA COMPONENTE DEVERÁ USAR PARA SEU PLENO FUNCIONAMENTO

import { Component } from '@angular/core'; // este é o recurso necessário para "transformar" uma classe/arquivo .ts - comum - num componente Angular 
// 2. este é o recurso que permite/possibilita, se necessario, estabelecer "rotas/navegação" para que o usuario posso "transitar" entre os componentes da aplicação  - este recurso expõe todas as rotas do projeto - a partir do componente principal
import { RouterOutlet } from '@angular/router';

@Component({ // **** este é o decorator (que, na maioria das vezes, é indicado pelo simbolo @ - at); este decorator diz que: "agora, a classe - comum - .ts" é parte de um componente Angular! 
  selector: 'app-root', // 3. esta é a propriedade - selector - que dá ao componente; seu nome, então (nome do componente), é app-root! Portanto,o "conteudo" que esta sendo "injetado" dentro de index.html tem origem aqui, neste componente 

  // 4. além de importar os recursos, para o pleno funcionamento do componente, alguns destes recursos precisam, tambem, serem "registrado" - geralmente os recursos de modulo - como "disponibilizados" para uso; o array imports é que possui esta responsabilidade - de registrar e "dar vida" para recursos que o componente irá usar 
  imports: [RouterOutlet],

  // 5. aqui, temos o "endereço" do arquivo .html/template/view que está diretamente vinculada a camada lógica deste componente
  templateUrl: './app.component.html',

  // 6. aqui, temos o "endereço" do arquivo .css/estilos/atributos de estilo que está diretamente vinculada a camada lógica deste componente e, também, a view que compõe o componente 
  styleUrl: './app.component.css'
})
export class AppComponent {
  // toda a vez que criamos um projeto Angular o componente app.component - com os seus respectivos arquivos - é criado por padrão! E, na "camada" lógica - arquio .ts  - do componente - sempre há um var padrão que recebe como valor o nome da aplicação
  title = 'angular-agro-sensores';
}

// este, sendo o "componente principal" do projeto é necesario que, de forma direta ou indireta, TODOS OS COMPONENTES  "CONVERSEM" COM ESTE AQUI!!!!! 

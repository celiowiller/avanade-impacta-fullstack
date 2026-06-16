// este é o recurso necessario para que as rotas, aqui, implementadas possam funcionar adequadamente
import { Routes } from '@angular/router';
import { CadastroComponent } from './components/usuario/cadastro/cadastro.component';
import { LoginComponent } from './components/usuario/login/login.component';

// esta é a const (constante) - nada mais é do que um array - que irá "abrigar" todas as rotas que definiremos para os componente
export const routes: Routes = [
    // ---- ROTAS PÚBLICAS -----
    // aqui, teremos uma rota "estruturada" da seguinte forma: http://localhost:4200/cadstro
    {path: 'cadastro', component: CadastroComponent},
    {path: 'login', component: LoginComponent}
];

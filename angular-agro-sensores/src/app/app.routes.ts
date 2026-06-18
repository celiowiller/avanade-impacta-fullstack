// este é o recurso necessario para que as rotas, aqui, implementadas possam funcionar adequadamente
import { Routes } from '@angular/router';
import { CadastroComponent } from './components/usuario/cadastro/cadastro.component';
import { LoginComponent } from './components/usuario/login/login.component';
// guards
import { authGuard } from './guards/auth.guard';
import { SensorListComponent } from './components/sensores/sensor-list/sensor-list.component';
import { SensorDetailComponent } from './components/sensores/sensor-detail/sensor-detail.component';
import { DashboardComponent } from './components/sensores/dashboard/dashboard.component';
import { SensorLeituraFormComponent } from './components/sensores/sensor-leitura-form/sensor-leitura-form.component';
import { adminGuard } from './guards/admin.guard';
import { SensorFormComponent } from './components/sensores/sensor-form/sensor-form.component';

// esta é a const (constante) - nada mais é do que um array - que irá "abrigar" todas as rotas que definiremos para os componente
export const routes: Routes = [
    // ---- ROTAS PÚBLICAS -----
    // definir a rota de comportamento padrão da aplicação
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    // aqui, teremos uma rota "estruturada" da seguinte forma: http://localhost:4200/cadstro
    {path: 'cadastro', component: CadastroComponent},
    {path: 'login', component: LoginComponent},

    // ---- ROTAS RESTRITAS (Qualquer usaurio logado) ----
    {
     // localhost:4200/sensores
        path: 'sensores',
        canActivate: [authGuard],
        children:[
            {path: '', component: SensorListComponent},
            // localhost:4200/sensores/detalhes/1
            {path: 'detalhes/:id', component: SensorDetailComponent}
        ]
    },

    {
     // localhost:4200/telemetria/dashboard
        path: 'telemetria',
        canActivate: [authGuard],
        children:[
            // aqui, temos uma visão geral de tods os sensores com leituras (com origem no usecase de agregação)
            {path: 'dashboard', component: DashboardComponent},
            // localhost:4200/telemetria/simulador
            {path: 'simulador', component: SensorLeituraFormComponent}
        ]
    },

    
    // ---- ROTAS RESTRITAS (Nivel: APENAS ADMIN) ----
    {
     // localhost:4200/configuracoes/novo-sensor
        path: 'configuracoes',
        canActivate: [authGuard, adminGuard],
        children:[
            {path: 'novo-sensor', component: SensorFormComponent},
            // localhost:4200/configuracoes/editar-sensor/1
            {path: 'editar-sensor/:id', component: SensorFormComponent}
        ]
    },

    // rota de fallback para evitar erros de navegação
    {path: '**', redirectTo: 'login'}
];

// ESTE ARQUIVO É UM "CENTRALIZADOR SECUNDARIO" DE RECURSOS DA APLICAÇÃO 
// est arquivo "centralizará" todos os recursos necessarios para a aplicação - a partir da dependencia material design 
import { NgModule } from '@angular/core'; // este é o recurso necessario para "transformar" esta classe num angular module: seu proposito é -> centralizar recursos semelhantes - para um determinado objetivo
//import { CommonModule } from '@angular/common';

// ------------------------------------------------------------------------

// agora, vamos importar os modulos do material
import { MatToolbarModule } from '@angular/material/toolbar'; // auxilia na construção das barras superiores de componentes
import { MatButtonModule} from '@angular/material/button'; // auxilia na cosntrução buttons nas views
import { MatIconModule } from '@angular/material/icon'; // auxilia na construção de iconização da view
import { MatCardModule } from '@angular/material/card'; // auxilia na construçãod e cards
import { MatFormFieldModule } from '@angular/material/form-field'; // auxilia na construção de estruturas de formulario
import { MatInputModule } from '@angular/material/input'; // auxilia na construção de inputs e formulario
import { MatSnackBarModule } from '@angular/material/snack-bar'; // auxilia na construção de feedback visual de operações
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatMenuModule } from '@angular/material/menu';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatSlideToggleModule } from '@angular/material/slide-toggle'

@NgModule({
  /*declarations: [],
  imports: [
    CommonModule
  ]*/

    exports:[
      MatToolbarModule,
      MatIconModule,
      MatButtonModule,
      MatCardModule,
      MatFormFieldModule,
      MatInputModule,
      MatSnackBarModule,
      MatProgressSpinnerModule,
      MatMenuModule,
      MatListModule,
      MatSelectModule,
      MatTableModule,
      MatSlideToggleModule
    ]
})
export class MaterialModule { }

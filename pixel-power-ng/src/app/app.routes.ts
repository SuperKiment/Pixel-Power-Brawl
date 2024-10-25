import { Routes } from '@angular/router';
import { FormulaireComponent } from './formulaire/formulaire.component';
import { KimentComponent } from './kiment/kiment.component';

export const routes: Routes = [
  { path: 'app-formulaire', component: FormulaireComponent },
  { path: 'app-kiment', component: KimentComponent },
];

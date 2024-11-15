import { Routes } from '@angular/router';
import { FormulaireComponent } from './formulaire/formulaire.component';
import { KimentComponent } from './kiment/kiment.component';
import { TestApiComponent } from './test-api/test-api.component';

export const routes: Routes = [
  { path: 'app-formulaire', component: FormulaireComponent },
  { path: 'app-kiment', component: KimentComponent },
  { path: 'app-test-api', component: TestApiComponent },
];

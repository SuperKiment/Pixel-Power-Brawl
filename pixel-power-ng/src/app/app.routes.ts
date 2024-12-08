import { Routes } from '@angular/router';
import { FormulaireComponent } from './formulaire/formulaire.component';
import { KimentComponent } from './kiment/kiment.component';
import { TestApiComponent } from './test-api/test-api.component';
import { TeamChoosingComponent } from './team-choosing/team-choosing.component';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';
import { LoginRegisterComponent } from './login-register/login-register.component';

export const routes: Routes = [
  { path: 'app-formulaire', component: FormulaireComponent },
  { path: 'app-kiment', component: KimentComponent },
  { path: 'app-test-api', component: TestApiComponent },
  { path: 'team-choosing', component: TeamChoosingComponent },
  { path: 'matchmaking', component: MatchmakingComponent },
  { path: 'login', component: LoginRegisterComponent },
];

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CoinListComponent } from './components/coin-list/coin-list.component';

const routes: Routes = [
  {
    path: 'coinList',
    component: CoinListComponent
  },
  {
    path: '',
    redirectTo: 'coinList',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AboutComponent } from './components/core/about/about.component';
import { AuthorComponent } from './components/core/author/author.component';
import { HomeComponent } from './components/core/home/home.component';
import { ObrazovanjeComponent } from './components/model/obrazovanje/obrazovanje.component';
import { PreduzeceComponent } from './components/model/preduzece/preduzece.component';
import { RadnikComponent } from './components/model/radnik/radnik.component';
import { SektorComponent } from './components/model/sektor/sektor.component';

const routes: Routes = [
  {path: 'obrazovanje', component: ObrazovanjeComponent },
  {path: 'preduzece', component: PreduzeceComponent},
  {path: 'sektor', component: SektorComponent},
  {path: 'radnik', component: RadnikComponent},
  {path: 'about', component: AboutComponent},
  {path: 'author', component: AuthorComponent},
  {path: 'home', component: HomeComponent},
  {path: '', redirectTo: '/home', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

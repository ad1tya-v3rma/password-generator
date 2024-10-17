import { TypewriterComponent } from './typewriter/typewriter.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MainComponent } from './main/main.component';

const routes: Routes =
  [
    {
      path: "typewriter",
      // redirectTo: 'typewriter',
      //pathMatch: 'full',
      component: TypewriterComponent
    },
    {
      path: "home",
      // redirectTo: '/home',
      // pathMatch: 'full',
      component: HomeComponent
    },
    {
      path: 'main',
      pathMatch: 'full',
      component: MainComponent
    },
    { path: '', redirectTo: '/home', pathMatch: 'full' }, // Add other routes as needed
    // { path: '**', redirectTo: '/home' } // Handle unknown routes
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

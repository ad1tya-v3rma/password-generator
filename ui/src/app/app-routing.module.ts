import { TypewriterComponent } from './typewriter/typewriter.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';

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

    // { path: '', redirectTo: '/home', pathMatch: 'full' }, // Add other routes as needed
    // { path: '**', redirectTo: '/home' } // Handle unknown routes
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatButtonModule } from '@angular/material/button';
import { GlitchTextComponent } from './glitch-text/glitch-text.component';
import { TypewriterComponent } from './typewriter/typewriter.component';
import { HomeComponent } from './home/home.component';
import { MainComponent } from './main/main.component'
@NgModule({
  declarations: [
    AppComponent,
    GlitchTextComponent,
    TypewriterComponent,
    HomeComponent,
    MainComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

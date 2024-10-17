import { Component } from '@angular/core';
import { Router } from '@angular/router'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  styles: [':host{background-color :white}']
})
export class AppComponent {


  title = 'ui';

  constructor(private router: Router) { }

  redirect() {
    this.router.navigate(['/typewriter'])
    // this.router.navigateByUrl('/typewriter', { skipLocationChange: true }).then(() => {
    //   this.router.navigate(['TypewriterComponent']);
    // });
  }

  shouldShowNavbar(): boolean {
    return this.router.url !== '/typewriter'; // Adjust routes as needed
  }

  isHiddenRoute(): boolean {
    return this.router.url === '/typewriter' || this.router.url === '/login'; // Adjust paths as needed
  }
}

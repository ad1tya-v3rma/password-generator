import { Component} from '@angular/core';
import { Router } from '@angular/router'
import { AppUtilServiceService } from './services/app-util-service.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  styles: [':host{background-color :#e9cccc}']
})
export class AppComponent {


  title = 'ui';

  constructor(private router: Router, private appUtilService: AppUtilServiceService) { }

  ngOnInit(): void {
    this.appUtilService.getCsrfToken();
  }
  redirect() {
    this.router.navigate(['/typewriter'])
  }

  shouldShowNavbar(): boolean {
    return this.router.url !== '/typewriter'; // Adjust routes as needed
  }

  isHiddenRoute(): boolean {
    return this.router.url === '/typewriter' || this.router.url === '/login'; // Adjust paths as needed
  }
}

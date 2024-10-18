import { Component } from '@angular/core';
import { PasswordService } from '../services/password.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent {
  generatedPass: string = '';
  isHovered: boolean = false;
  constructor(private passwordService: PasswordService) {
  }

  toggle() {
    this.isHovered = !this.isHovered;
  }

  generatePass() {
    this.generatedPass = this.passwordService.getSecurePassword();
  }

}

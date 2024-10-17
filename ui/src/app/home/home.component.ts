import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Route, Router } from '@angular/router';
import { every, isEmpty } from 'rxjs';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements AfterViewInit {


  @ViewChild('input') textArea: any;
  text: string = "Secure Password Generator";
  containsNewline: boolean = false;
  user: string = '';
  pass: string = '';
  cache: string = '';
  constructor(private router: Router) { }

  ngAfterViewInit(): void {
    this.setText(">>>");
  }

  setText(value: string) {
    this.textArea.nativeElement.value += value;
    if (this.containsNewline) {

    }
  }

  checkLogin(arg0: string, arg1: string) {
    if (this.user === 'admin' && this.pass === 'pass') {
      this.router.navigate(['/main']);
    }
    else {
      this.setText("error : Invalid Credentials\n>>>");
      this.user = this.pass = '';
      console.log('user : ' + this.user + '\npass : ' + this.pass)
    }
  }

  appendText(event: KeyboardEvent | string) {

    if (event instanceof KeyboardEvent) {
      if (event.key === 'Enter') {
        event.preventDefault();
        this.setText("\n>>>")
        this.containsNewline = true;
        console.log(this.cache);

      }
      if (this.containsNewline) {
        const lines = this.textArea.nativeElement.value.split('\n')

        if (lines[lines.length - 2].replaceAll(">>>", "").trim() === "clear") {
          this.reset();
          return;
        }

        if (this.user === '') {
          this.user = lines[lines.length - 2].replaceAll(">>>", '').trim();

          //this.user = this.textArea.nativeElement.value.replaceAll('>>>', '').trim();
          console.log(this.user);
          this.containsNewline = false;
          //this.user.replace(">>>", "")
        }
        else if (this.pass === '') {
          this.pass = lines[lines.length - 2].replaceAll(">>>", '').trim();
          //this.pass = this.textArea.nativeElement.value.replaceAll('>>>', '').replaceAll(this.user, '').trim();
          console.log(this.pass);
          this.checkLogin(this.user, this.pass);
          this.containsNewline = false;
          //this.user.replace(">>>", "")
        }
      }
    }
  }

  reset() {
    this.user = this.pass = this.textArea.nativeElement.value = '';
    this.setText('>>>')
  }

}

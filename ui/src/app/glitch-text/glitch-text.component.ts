import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'glitch-text',
  templateUrl: './glitch-text.component.html',
  styleUrls: ['./glitch-text.component.scss']
})
export class GlitchTextComponent {
  @Input() customAtb: string = '';
  text: any = "hello";
  temp: any;
  ngOnInit() {
    console.log(this.customAtb)
    this.temp = this.customAtb;

  }
}

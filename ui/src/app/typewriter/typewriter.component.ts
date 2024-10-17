import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'typewriter',
  templateUrl: 'typewriter.component.html',
  styleUrls: ['typewriter.component.scss']
})
export class TypewriterComponent implements OnInit {
  @Input() customText: string = '';
  text = '';
  displayedText = '';
  index = 0;
  isBlinking = true;

  toggleVisibility: boolean = false;

  ngOnInit() {
    this.text = this.customText;
    setTimeout(() => this.toggleVisibility = true, 2000);
    setInterval(() => {
      this.isBlinking = !this.isBlinking; // Toggle cursor visibility
    }, 500); // Adjust blink speed here
    setTimeout(() => this.type(), 2000); // Start typing after 1 second
    //this.type();
  }

  type() {
    if (this.index < this.text.length) {
      setTimeout(() => this.type(), this.generateRandomDelay());
      this.displayedText += this.text[this.index];
      this.index++;
      // Adjust typing speed here
    }
  }

  generateRandomDelay(): number {
    return Math.floor(Math.random() * (50 - 10 + 1)) + 100; // Generates a number between 100 and 500
  }
}

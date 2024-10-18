import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PasswordService {

  constructor() { }
  passwordList: string[] = [];
  upper: String[] =
    [
      'A', 'B', 'C', 'D', 'E',
      'F', 'G', 'H', 'I', 'J', 'K', 'L',
      'M', 'N', 'O', 'P', 'Q', 'R', 'S',
      'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    ];
  lower: String[] =
    [
      'a', 'b', 'c', 'd', 'e',
      'f', 'g', 'h', 'i', 'j', 'k', 'l',
      'm', 'n', 'o', 'p', 'q', 'r', 's',
      't', 'u', 'v', 'w', 'x', 'y', 'z',
    ];
  numbers: String[] = ['0', '1', '2', '3', '4',
    '5', '6', '7', '8', '9'];
  special: String[] = ['!', '@', '#', '$', '%',
    '^', '&', '*', '(', ')', '-', '+', '=',
    '[', ']', '{', '}', '|', ';', ':', '"',
    '<', '>', ',', '.', '?', '/'];
  all: String[] = [...this.lower, ...this.upper, ...this.numbers, ...
    this.special];
  password: string = '';

  getSecurePassword() {
    this.password = '';
    while (this.password.length < 19) {
      var num = this.getRandomNumber();
      var letter: any;
      letter = this.upper[num];
      //this.password = this.password + (this.password.includes(letter) ? continue : letter);
      if (this.password.includes(letter)) {
        continue;
      }
      else {
        this.password = this.password + letter;
      }
      letter = this.special[num];
      if (this.password.includes(letter)) {
        continue;
      }
      else {
        this.password = this.password + letter;
      }
      letter = this.lower[num];
      if (this.password.includes(letter)) {
        continue;
      }
      else {
        this.password += letter;
      }
      if (num > this.numbers.length - 1) {
        while (num > this.numbers.length - 1) {
          num = this.getRandomNumber();
        }
        letter = this.numbers[num];
        if (this.password.includes(letter)) {
          continue;
        }
        else {
          this.password += letter;
        }
      }
    }
    if (this.passwordList.includes(this.password)) {
      this.getSecurePassword();
    }
    else {
      this.passwordList.push(this.password);
    }

    return this.password;

  }

  //create a function that generates random number between 0-18
  getRandomNumber() {
    return Math.floor(Math.random() * 19);;
  }
}

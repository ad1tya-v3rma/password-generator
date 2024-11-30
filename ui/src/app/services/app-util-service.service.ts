import { Injectable } from '@angular/core';
import { AppUtils } from '../entity/AppUtils';

@Injectable({
  providedIn: 'root'
})
export class AppUtilServiceService {

  constructor() { this.appUtil = new AppUtils(); }

  appUtil: AppUtils;

  setCsrf(csrfToken: string) {
    if (csrfToken) {
      this.appUtil.crsfToken = csrfToken;
      console.log("csrf token generated : " + csrfToken);
    }
  }

  async getCsrfToken() {
    await fetch('http://localhost:7070/generator/csrf',
      {
        'method': 'GET',
        'headers':
        {
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': '*'
        },
        'mode': 'cors',
        credentials: 'same-origin',
      }

    )
      .then(response => response.json())
      .then(data => this.setCsrf(data.token))
      .catch(error => console.error(error))
  }

}


// async savePair(array: Pair[]) {
//   await fetch("http://localhost:7070/saveList",
//     {
//       "method": "POST",
//       "mode": "cors",
//       credentials: 'same-origin',
//       headers:
//       {
//         "Content-Type": "application/json"
//       },
//       body: JSON.stringify(array)
//     }
//   )
// }

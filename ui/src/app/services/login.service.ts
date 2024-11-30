import { Injectable } from '@angular/core';
import { AppUtilServiceService } from './app-util-service.service';
import { UserDetails } from '../entity/UserDetails'
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private appUtils: AppUtilServiceService) {

  }

  async validateCredentials(uname: string, pass: string
  ) {
    const userDetails = new UserDetails();
    userDetails.username = uname;
    userDetails.password = pass;
    try {
      const response = await fetch('http://localhost:7070/generator/login', {
        method: 'POST',
        mode: 'cors',
        credentials: 'same-origin',  // Keeps credentials for same-origin requests
        headers: {
          "Content-Type": "application/json", // Content type as JSON
          //"X-CSRFToken": 'aDW5rB60kXS_VnvsTAFNtZHirHusfABYTNsHySKOwyW9vy74CgzfmniGohaSNEPUfyx5hqmGgRmeTTR1f-I28Ua7oB3Zj03A' // CSRF token in header
          //"Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGFtYWFyIiwiaWF0IjoxNzMyMjIwODk4LCJleHAiOjE3MzIyMjEwMDZ9.e6LN1nuBW5E9oPWhS-_hT_TeOxDTTdYd2cy-YRWiv8Q"
        },
        body: JSON.stringify(userDetails) // JSON stringified userDetails
      });

      // Check if the response is successful
      if (response.ok) {
        const data = await response.json();
        if (data != null) {
          return data.result; // Return the result if available
        } else {
          return false; // Return false if data is null or doesn't contain the expected result
        }
      } else {
        console.error(response)
        console.error('Login failed:', response.status);
        console.log(response.headers)
        console.log(response.type)
        console.log(response.body)
        return false;
      }

    }catch (error)
    {
      console.error(error);
      return false
    }
  }

}

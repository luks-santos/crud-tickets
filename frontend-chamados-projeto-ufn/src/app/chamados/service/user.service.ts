import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly API = 'api/auth';

  constructor(private httpClient: HttpClient) { }

  create(user: any) {
    return this.httpClient.post(this.API + '/register', user);
  }
}

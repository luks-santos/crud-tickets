import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, map } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor(private httpClient: HttpClient, private authService: AuthService) { }
  private tokenName = environment.TOKEN_NAME;

  private createHeaders(): HttpHeaders {
    const token = this.authService.getKey(this.tokenName); 
    
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    });
    return headers;
  }
}

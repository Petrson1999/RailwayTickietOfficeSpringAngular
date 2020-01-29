import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class StationService {

  readonly rootUrl = 'http://localhost:8085/api/stations';

  constructor(private http: HttpClient) {
  }

  getAllStantion() {
    let reqHeader = new HttpHeaders({'No-Auth': 'True'});
    return this.http.get(this.rootUrl, {headers: reqHeader});
  }

}

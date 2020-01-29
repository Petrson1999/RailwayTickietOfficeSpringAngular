import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {FlightSearchModel} from "../models/flight-search.model";

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  readonly rootUrl = 'http://localhost:8085/api/flights';

  constructor(private http: HttpClient) {
  }

  searchFlights(serchForm: FlightSearchModel) {
    let reqHeader = new HttpHeaders({'No-Auth': 'True'});
    return this.http.post(this.rootUrl + '/search', serchForm, {headers: reqHeader});
  }

  getFlightWagonDto(flightId: number) {
    let reqHeader = new HttpHeaders({'No-Auth': 'True'});
    return this.http.get(this.rootUrl + '/' + flightId + '/seats' , {headers: reqHeader});
  }

}

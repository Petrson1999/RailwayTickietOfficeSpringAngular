import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TrainService {

  readonly rootUrl = 'http://localhost:8085/api/trains';

  constructor(private http: HttpClient) {
  }

  getAllTrains() {
    return this.http.get(this.rootUrl);
  }

  addTrain(trainName: String){
    const body = {
      trainName: trainName
    };
    return this.http.put(this.rootUrl, body);
  }

}

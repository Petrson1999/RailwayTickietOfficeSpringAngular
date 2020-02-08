import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AddWagonTypeModel} from "../models/add-wagon-type.model";
import {AddWagonModel} from "../models/add-wagon.model";

@Injectable({
  providedIn: 'root'
})
export class WagonService {

  readonly rootUrl = 'http://localhost:8085/api/wagons';

  constructor(private http: HttpClient) {
  }

  getAllWagons() {
    return this.http.get(this.rootUrl);
  }

  getAllWagonTypes() {
    return this.http.get(this.rootUrl + '/types');
  }

  addWagon(addWagonModel: AddWagonModel){
    return this.http.put(this.rootUrl, addWagonModel);
  }

  addWagonType(addWagonType: AddWagonTypeModel){
    return this.http.put(this.rootUrl+ '/types', addWagonType);
  }

}

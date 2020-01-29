import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {OrderFormModel} from "../models/order-form.model";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  readonly rootUrl = 'http://localhost:8085/api/tickets';

  constructor(private http: HttpClient) {
  }

  orderTicket(order: OrderFormModel) {
    return this.http.post(this.rootUrl + '/order', order);
  }

}

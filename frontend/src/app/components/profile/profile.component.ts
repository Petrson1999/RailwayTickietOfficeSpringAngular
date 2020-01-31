import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../models/user.model";
import {UserTicketsModel} from "../../models/user-tickets.model";

@Component({
  selector: 'app-proffile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor() {
    this.user = {
      id: 0,
      login: '',
      role: '',
      name: '',
      surname: '',
      funds: 0
    };
    this.actualUserTickets = [];
  }

  user: UserModel;
  actualUserTickets: UserTicketsModel[];
  deprecatedUserTickets: UserTicketsModel[];

  ngOnInit() {
  }

}

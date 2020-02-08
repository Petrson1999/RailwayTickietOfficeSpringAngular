import {Component, OnInit} from '@angular/core';
import {UserModel} from "../../models/user.model";
import {UserTicketsModel} from "../../models/user-tickets.model";
import {HttpErrorResponse} from "@angular/common/http";
import {UserService} from "../../services/user.service";
import {ToastrService} from "ngx-toastr";
import {TicketService} from "../../services/ticket.service";
import {WagonSeatsModel} from "../../models/wagon-seats.model";

@Component({
  selector: 'app-proffile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private userService: UserService,
              private toastr: ToastrService, private ticketService: TicketService) {
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
    this.initUserProfile();
    this.initActualUserTickets();
    this.initDeprecatedUserTickets();
  }

  initUserProfile() {
    let userId = localStorage.getItem('userId');
    this.userService.getUserById(userId).subscribe(
      (data: UserModel) => {
        this.user = data;
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      }
    )
  }

  initActualUserTickets() {
    let userId = localStorage.getItem('userId');
    this.ticketService.getActualUserTickets(userId).subscribe(
      (data: any) => {
        if(data.succeeded){
          this.actualUserTickets = data.ticketDtos as UserTicketsModel[];
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      }
    )
  }

  initDeprecatedUserTickets() {
    let userId = localStorage.getItem('userId');
    this.ticketService.getDeprecatedUserTickets(userId).subscribe(
      (data: any) => {
        if(data.succeeded){
          this.deprecatedUserTickets = data.ticketDtos as UserTicketsModel[];
        }
      },
      (error: HttpErrorResponse) => {
        this.toastr.error(error.error.message);
      }
    )
  }

}

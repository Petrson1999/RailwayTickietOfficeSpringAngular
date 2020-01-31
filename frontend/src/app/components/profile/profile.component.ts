import {Component, OnInit} from '@angular/core';
import {UserModel} from "../../models/user.model";
import {UserTicketsModel} from "../../models/user-tickets.model";
import {HttpErrorResponse} from "@angular/common/http";
import {UserService} from "../../services/user.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-proffile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private userService: UserService,
              private toastr: ToastrService) {
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

}

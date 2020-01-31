import {Component, TemplateRef, OnInit} from '@angular/core';
import {BsModalService, BsModalRef} from 'ngx-bootstrap/modal';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

import {HttpErrorResponse} from '@angular/common/http';
import {NgForm} from '@angular/forms';
import {UserService} from "../../services/user.service";
import {RegistrationUser} from "../../models/registration.model";
import {SecurityService} from "../../services/security.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  ngOnInit() {
  }

  constructor(private modalService: BsModalService,
              private router: Router, private userService: UserService,
              private toastr: ToastrService) {
    this.user = new RegistrationUser();
    this.securityService = new SecurityService();
    this.isLoginError = false;
  }

  securityService: SecurityService;
  modalRef: BsModalRef;

  user: RegistrationUser;

  isLoginError: boolean;

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  logOut() {
    localStorage.removeItem("userToken");
    localStorage.removeItem("userId");
    localStorage.removeItem('userRoles');
    this.router.navigate([""]);
  }

  OnLoginFormSubmit(Login: string, Password: string) {
    this.userService.userAuthentification(Login, Password).subscribe(
      (data: any) => {
        localStorage.setItem("userToken", data.accessToken);
        localStorage.setItem("userId", data.userId);
        localStorage.setItem('userRole', data.userRoles[0].authority);
        this.router.navigate(["/profile/" + data.userId]);
        this.modalRef.hide();
      },
      (error: HttpErrorResponse) => {
        this.isLoginError = true;
        this.toastr.error(error.error.message);
      }
    )
  }

  resetForm(form?: NgForm) {
    if (form != null)
      form.reset();
    this.user = {
      login: '',
      password: '',
      name: '',
      surname: ''
    }
  }

  OnSingUpFormSubmit(form: NgForm) {
    this.userService.registerUser(form.value)
      .subscribe(
        (data: any) => {
          if (data.succeeded) {
            this.resetForm(form);
            this.toastr.success(data.message);
            this.modalRef.hide();
          }
        },
        (error: HttpErrorResponse) => {
          this.toastr.error(error.error.message);
        });
  }

  goToProfile() {
    let userId = localStorage.getItem('userId');
    this.router.navigate(["/profile/" + userId]);
  }

}

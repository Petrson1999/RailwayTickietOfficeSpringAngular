import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor() { }

  isLogined() {
    let isShow: Boolean;
    isShow = localStorage.getItem('userId') !== null;
    return isShow;
  }
}

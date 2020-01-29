import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RegistrationUser } from '../models/registration.model';
import { LoginModel } from '../models/login.model.model'

@Injectable({
    providedIn: 'root'
})
export class UserService {

    readonly rootUrl = 'http://localhost:8085/api';

    constructor(private http: HttpClient) { }

    registerUser(user: RegistrationUser) {
        let reqHeader = new HttpHeaders({ 'No-Auth': 'True' });
        return this.http.post(this.rootUrl + '/account/registration', user, { headers: reqHeader });
    }

    userAuthentification(login, password) {
        let data = new LoginModel();
        data.password = password;
        data.login = login;
        let reqHeader = new HttpHeaders({ 'No-Auth': 'True' });
        return this.http.post(this.rootUrl + "/auth/sign-in", data, { headers: reqHeader });
    }

    /*getUserById(id: string) {
        return this.http.get(this.rootUrl + "api/Account/" + id);
    }*/

}

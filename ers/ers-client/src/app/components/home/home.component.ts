import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';
import {Reimbursement} from '../../models/Reimbursement';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    user1 = 'Kara Simons';
    user2 = 'Andrew Dickinson';

    userNavs: Array<{title: string}>;

    constructor(private authService: AuthService) {}

    ngOnInit() {
        if (this.authService.hasRoles(['Manager'])) {
            this.userNavs = environment.managerNav;
        } else {
            this.userNavs = environment.employeeNav;
        }
    }
}

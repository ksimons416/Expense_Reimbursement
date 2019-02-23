import { Component, OnInit } from '@angular/core';
import { Reimbursement} from "../../models/Reimbursement";
import {ReimbursementService} from "../../services/reimbursement.service";
import { environment } from 'src/environments/environment';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-view-all-reimbursements',
  templateUrl: './view-all-reimbursements.component.html',
  styleUrls: ['./view-all-reimbursements.component.css']
})
export class ViewAllReimbursementsComponent implements OnInit {
  userNavs: Array<{title: string}>;
  reimbursements: Reimbursement[];
  constructor(private reimbursementService: ReimbursementService, private authService: AuthService) { }
  authorName1 = 'Andrew Dickinson';
  type1 = 'Lodging';
  type2 = 'Food';
  type3 = 'Travel';
  type4 = 'Other';
  status1 = 'Open';
  status2 = 'Approved';
  status3 = 'Rejected';
  ngOnInit() {

    if (this.authService.hasRoles(['Manager'])) {
      this.userNavs = environment.managerNav;
  } else {
      this.userNavs = environment.employeeNav;
  }
    this.reimbursementService.getAllReimbursements()
      .subscribe(reimbursements => {
        this.reimbursements = reimbursements;
      })
  }
}

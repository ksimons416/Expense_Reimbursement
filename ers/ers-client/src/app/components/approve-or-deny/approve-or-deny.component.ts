import {Component, OnInit} from '@angular/core';
import {ReimbursementService} from "../../services/reimbursement.service";
import {AuthService} from "../../services/auth.service";
import {Reimbursement} from "../../models/Reimbursement";
import {NgForm} from "@angular/forms";
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-approve-or-deny',
  templateUrl: './approve-or-deny.component.html',
  styleUrls: ['./approve-or-deny.component.css']
})
export class ApproveOrDenyComponent implements OnInit {

  userNavs: Array<{title: string}>;

  reimbursements: Reimbursement[];
  showApprovedReimbursements: boolean = true;
  showDeniedReimbursements: boolean = true;
  showPendingReimbursements: boolean = true;
  authorName1 = 'Andrew Dickinson';
  type1 = 'Lodging';
  type2 = 'Food';
  type3 = 'Travel';
  type4 = 'Other';
  status1 = 'Open';
  status2 = 'Approved';
  status3 = 'Rejected';
  constructor(private reimbursementService: ReimbursementService, private authService: AuthService) {
  }
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
  submitRequest(form: NgForm, reimbursementId: number) {
    let reimbursement = new Reimbursement();
    reimbursement.id = reimbursementId;
    reimbursement.resolverId = this.authService.getLoggedInUser().id;
    reimbursement.dateResolved = new Date();
    if (form.value['status'] === 'approve') {
      reimbursement.statusId = 2;
    } else if (form.value['status'] === 'deny') {
      reimbursement.statusId = 3;
    } else {
      reimbursement.statusId = 1;
    }
    this.reimbursementService.updateReimbursement(reimbursement)
      .subscribe(() => console.log('success'),
        () => console.log('failure'));
      window.location.reload();
  }
  fShowAllReimbursements() {
    this.showApprovedReimbursements = true;
    this.showDeniedReimbursements = true;
    this.showPendingReimbursements = true;
  }
  fShowApprovedReimbursements() {
    this.showApprovedReimbursements = true;
    this.showDeniedReimbursements = false;
    this.showPendingReimbursements = false;

  }
  fShowDeniedReimbursements() {
    this.showApprovedReimbursements = false;
    this.showDeniedReimbursements = true;
    this.showPendingReimbursements = false;
  }
  fShowPendingReimbursements() {
    this.showApprovedReimbursements = false;
    this.showDeniedReimbursements = false;
    this.showPendingReimbursements = true;
  }
}

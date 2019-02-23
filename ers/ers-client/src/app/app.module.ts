import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AuthGuardService } from './services/auth-guard.service';
import { LoginComponent } from './components/login/login.component';
import { NavComponent } from './components/nav/nav.component';
import { ViewReimbursementsComponent } from './components/view-reimbursements/view-reimbursements.component';
import { ReimbursementRequestComponent } from './components/reimbursement-request/reimbursement-request.component';
import { ApproveOrDenyComponent } from './components/approve-or-deny/approve-or-deny.component';
import { ViewAllReimbursementsComponent } from './components/view-all-reimbursements/view-all-reimbursements.component';
import { HeaderComponent } from './components/header/header.component';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'ers',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'reimbursementrequest',
    component: ReimbursementRequestComponent
  },
  {
    path: 'showreimbursementsemp',
    component: ViewReimbursementsComponent
  },
  {
    path: 'showreimbursementsmanager',
    component: ViewAllReimbursementsComponent
  },
  {
    path: 'approveordenyreimbursements',
    component: ApproveOrDenyComponent
  },
  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    NavComponent,
    ViewReimbursementsComponent,
    ReimbursementRequestComponent,
    ApproveOrDenyComponent,
    ViewAllReimbursementsComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule
  ],
  providers: [AuthGuardService, {provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }

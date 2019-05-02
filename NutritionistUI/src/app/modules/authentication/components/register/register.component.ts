import { Component, OnInit } from '@angular/core';
import { User } from '../../User';
import { AuthenticationService } from '../../authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'authentication-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User = new User();
  message: String= "";
  val :boolean = false;

  constructor(private authService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
  }
  disableButton(){
    if(this.newUser.firstName==null || this.newUser.lastName==null ||this.newUser.userId==null ||this.newUser.password==null ){
      return true;
    }
    else{return false;}
  }
  registerUser() {
    console.log('Register User data:', this.newUser);
    if(this.newUser.password){}
    this.authService.registerUser(this.newUser).subscribe(data => {
      console.log('User registered', data);
      this.router.navigate(['/login']);
    });
  }
}


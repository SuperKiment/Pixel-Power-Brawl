import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ApiCallService } from '../api-call-service/api-call.service';
import { Router } from '@angular/router';

@Component({
  selector: 'login-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  providers: [ApiCallService],
  templateUrl: './login-register.component.html',
  styleUrl: './login-register.component.css',
})
export class LoginRegisterComponent {
  private fb = inject(FormBuilder);

  isLoginMode = true;
  authForm: FormGroup;

  constructor(
    private httpClient: HttpClient,
    private apiCall: ApiCallService,
    private router: Router
  ) {
    if (localStorage.getItem('username')) {
      router.navigate(['/profil']);
    }

    this.authForm = this.fb.group({
      username: ['', []],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: [''],
    });

    this.toggleConfirmPassword();
  }

  toggleMode() {
    this.isLoginMode = !this.isLoginMode;
    this.toggleConfirmPassword();
  }

  toggleConfirmPassword() {
    if (this.isLoginMode) {
      this.authForm.removeControl('confirmPassword');
    } else {
      this.authForm.addControl(
        'confirmPassword',
        this.fb.control('', [
          Validators.required,
          this.matchPassword.bind(this),
        ])
      );
    }
  }

  matchPassword(control: any) {
    return control.value === this.authForm.get('password')?.value
      ? null
      : { mismatch: true };
  }

  onSubmit() {
    if (this.authForm.invalid) return;

    if (this.isLoginMode) {
      console.log('Logging in with:', this.authForm.value);
      this.apiCall.login(this.authForm.value).subscribe((data) => {
        console.log(data);
        if (data.status == 200) {
          // localStorage.setItem('token', data.body);
          localStorage.setItem(
            'username',
            this.authForm.get('username')?.value
          );
          this.router.navigate(['/team-choosing']);
        } else {
        }
      });
    } else {
      console.log('Registering with:', this.authForm.value);
    }
  }
}

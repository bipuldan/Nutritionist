import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent
 } from '@angular/common/http';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable()
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private authService: AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if(request.url.startsWith("https://api.nal.usda.gov")) {
            return next.handle(request);
        }
        request = request.clone({
            setHeaders: {
                Authorization: `Bearer ${this.authService.getToken()}`
            }
        });
        return next.handle(request);
    }
}


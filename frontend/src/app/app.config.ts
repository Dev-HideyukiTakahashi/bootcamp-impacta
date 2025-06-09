import { provideHttpClient, withInterceptors} from '@angular/common/http';
import { authInterceptor } from './core/interceptors/auth-interceptor';
import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideNgxMask } from 'ngx-mask';

// Garantindo que o HttpClientModule seja provido globalmente
export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(withInterceptors([authInterceptor])), // Fornecendo HttpClient para toda a aplicação
    provideRouter(routes), // Fornecendo as rotas
    provideNgxMask()
  ],
};

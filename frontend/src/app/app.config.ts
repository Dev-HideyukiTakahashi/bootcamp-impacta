import { provideHttpClient } from '@angular/common/http';
import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';

// Garantindo que o HttpClientModule seja provido globalmente
export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(), // Fornecendo HttpClient para toda a aplicação
    provideRouter(routes), // Fornecendo as rotas
  ],
};

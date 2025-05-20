import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UserRole } from '../../model/enum/user-role.enum';
import { AuthService } from '../../service/authService.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // verifica tem usuario logado
  const isLoggedIn = authService.isLoggedIn();

  if (!isLoggedIn) {
    return router.createUrlTree(['/login']);
  }

  // verifica se ong ou voluntario esta logado
  const userRole = authService.isOng() ? UserRole.Ong : UserRole.Voluntario;

  // verifica as rotas autorizada de acordo com parametro que veio do guard
  const allowedRoles = route.data?.['roles'] as UserRole[] | undefined;

  // se nao for autorizado redireciona de acordo com o 'role'
  if (allowedRoles && (!userRole || !allowedRoles.includes(userRole))) {
    const redirectUrl =
      userRole === UserRole.Ong ? '/home-ong' : '/home-voluntario';

    return router.createUrlTree([redirectUrl]);
  }

  return true;
};

import { CanActivateFn } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {

	const authService = inject(AuthService);

	if (authService.isAuthenticated()) {
		const expectedRole = route.data['expectedRole'];
		console.log('guard', expectedRole);
		
		if (!expectedRole || authService.hasRole(expectedRole)) {
			return true;
		} 
		else {
			return false;
		}
	} 
	else {
		return false;
	}
};

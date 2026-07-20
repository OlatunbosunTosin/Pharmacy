package ng.Pharmacy.controllers;

import ng.Pharmacy.dtos.requests.LoginRequests;
import ng.Pharmacy.dtos.requests.LogoutRequests;
import ng.Pharmacy.dtos.requests.RegisterUserRequests;
import ng.Pharmacy.dtos.responses.LoginResponses;
import ng.Pharmacy.dtos.responses.LogoutResponses;
import ng.Pharmacy.dtos.responses.RegisterUserResponses;
import ng.Pharmacy.services.AuthService;
import ng.Pharmacy.services.AuthServiceImpl;

public class AuthController {

    private AuthService authService = new AuthServiceImpl();

    public RegisterUserResponses registerResponse(RegisterUserRequests request){
        return authService.registerChemist(request);
    }

    public LoginResponses loginResponse(LoginRequests request){
        return authService.login(request);
    }

    public LogoutResponses logoutResponse(LogoutRequests request){
        return authService.logout(request);
    }

}

package ng.Pharmacy.services;

import ng.Pharmacy.dtos.requests.LoginRequests;
import ng.Pharmacy.dtos.requests.LogoutRequests;
import ng.Pharmacy.dtos.requests.RegisterUserRequests;
import ng.Pharmacy.dtos.responses.LoginResponses;
import ng.Pharmacy.dtos.responses.LogoutResponses;
import ng.Pharmacy.dtos.responses.RegisterUserResponses;

public interface AuthService {

    RegisterUserResponses registerChemist(RegisterUserRequests request);
    LoginResponses login(LoginRequests loginRequest);
    LogoutResponses logout(LogoutRequests logoutRequest);
}

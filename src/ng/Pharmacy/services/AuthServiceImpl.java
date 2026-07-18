package ng.Pharmacy.services;

import ng.Pharmacy.data.model.User;
import ng.Pharmacy.data.repositories.UserRepository;
import ng.Pharmacy.data.repositories.UserRepositoryImpl;
import ng.Pharmacy.dtos.requests.LoginRequests;
import ng.Pharmacy.dtos.requests.LogoutRequests;
import ng.Pharmacy.dtos.requests.RegisterUserRequests;
import ng.Pharmacy.dtos.responses.LoginResponses;
import ng.Pharmacy.dtos.responses.LogoutResponses;
import ng.Pharmacy.dtos.responses.RegisterUserResponses;
import ng.Pharmacy.utils.Mapper;

public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public RegisterUserResponses registerChemist(RegisterUserRequests request) {
        if (userRepository.findByUsername(request.getUsername()) != null){
            throw new IllegalArgumentException("Username " +request.getUsername() + " already exists");
        }
        userRepository.save(Mapper.mapToUser(request));
        return null;
    }

    @Override
    public LoginResponses login(LoginRequests loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user != null){
            if(user.getPassword().equals(loginRequest.getPassword())){
                user.setLoggedIn(true);
                userRepository.save(user);
                return null;
            }throw new IllegalArgumentException("wrong password");
        }throw new IllegalArgumentException("user does not exist");
    }

    @Override
    public LogoutResponses logout(LogoutRequests logoutRequest){
        User user = userRepository.findByUsername(logoutRequest.getUsername());
        if (user != null){
            user.setLoggedIn(false);
            userRepository.save(user);
            return null;
        }
        throw new IllegalArgumentException("user does not exist");

    }

}


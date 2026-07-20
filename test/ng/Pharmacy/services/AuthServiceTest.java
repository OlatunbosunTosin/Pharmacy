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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {

    private AuthService authService;
    private UserRepository user;

    @BeforeEach
    public void setUp(){
        authService = new AuthServiceImpl();
        user = new UserRepositoryImpl();
        user.deleteAll();
    }

    @Test
    public void register1Chemist_countIs1(){
        RegisterUserRequests request = new RegisterUserRequests();
        request.setFullname("fullname");
        request.setPassword("password");
        request.setUsername("username");
        authService.registerChemist(request);
        assertEquals(1, user.count());
    }

    @Test
    public void registerUser_returnUserFullname(){
        RegisterUserRequests request = new RegisterUserRequests();
        request.setFullname("fullname");
        request.setPassword("password");
        request.setUsername("username");
        authService.registerChemist(request);
        RegisterUserResponses response = new RegisterUserResponses();
        response.setFullname(request.getFullname());
        response.setUsername(request.getUsername());
        assertEquals("fullname", response.getFullname());
    }

    @Test
    public void registerAlreadyExistingUser_throwsException(){
        RegisterUserRequests request = new RegisterUserRequests();
        request.setFullname("fullname");
        request.setPassword("password");
        request.setUsername("username");
        authService.registerChemist(request);
        RegisterUserRequests requestTwo = new RegisterUserRequests();
        requestTwo.setFullname("myfullname");
        requestTwo.setPassword("mypassword");
        requestTwo.setUsername("username");
        assertThrows(IllegalArgumentException.class, () -> authService.registerChemist(requestTwo));
    }

    @Test
    public void loginWithValidPassword(){
        RegisterUserRequests request = new RegisterUserRequests();
        request.setFullname("fullname");
        request.setPassword("password");
        request.setUsername("username");
        authService.registerChemist(request);

        User registeredUser = user.findByUsername(request.getUsername());
        LoginRequests userLogin = new LoginRequests();
        userLogin.setUsername("username");
        userLogin.setPassword("password");

        authService.login(userLogin);

        LoginResponses response = new LoginResponses();
        response.setLoggedIn(registeredUser.isLoggedIn());

        assertTrue(response.isLoggedIn());
    }

    @Test
    public void loginWithInvalidPassword(){
        RegisterUserRequests request = new RegisterUserRequests();
        request.setFullname("fullname");
        request.setPassword("password");
        request.setUsername("username");
        authService.registerChemist(request);

        LoginRequests userLogin = new LoginRequests();
        userLogin.setUsername("username");
        userLogin.setPassword("wrongpassword");

        assertThrows(IllegalArgumentException.class, () -> authService.login(userLogin));
    }

    @Test
    public void loginWithValidPassword_logout(){
        RegisterUserRequests request = new RegisterUserRequests();
        request.setFullname("fullname");
        request.setPassword("password");
        request.setUsername("username");
        authService.registerChemist(request);

        User registeredUser = user.findByUsername(request.getUsername());
        LoginRequests userLogin = new LoginRequests();
        userLogin.setUsername("username");
        userLogin.setPassword("password");

        authService.login(userLogin);
        LoginResponses response = new LoginResponses();
        response.setLoggedIn(registeredUser.isLoggedIn());

        assertTrue(response.isLoggedIn());

        LogoutRequests systemLogout = new LogoutRequests();
        systemLogout.setUsername("username");

        authService.logout(systemLogout);
        LogoutResponses logoutResponse = new LogoutResponses();
        logoutResponse.setLoggedIn(registeredUser.isLoggedIn());

        assertFalse(logoutResponse.isLoggedIn());

    }
}

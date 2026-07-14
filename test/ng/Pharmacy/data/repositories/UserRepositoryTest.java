package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryTest {

    private UserRepository userRepository;
    private User newUser;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepositoryImpl();
        newUser = new User();
        newUser.setUsername("Tos");
        newUser.setPassword("1234");
        newUser.setFullName("Ola Tosin");
    }

    @Test
    public void newRepositoryIsEmptyTest(){
        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveNewUser_countIsOneTest(){
        userRepository.save(newUser);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void saveTwoNewUsers_findOneUserById_returnFullName(){
        userRepository.save(newUser);
        User newUserTwo = new User();
        newUserTwo.setUsername("Ayo");
        newUserTwo.setPassword("2345");
        newUserTwo.setFullName("Ayo Ola");
        User savedUser = userRepository.save(newUserTwo);
        User foundUser = userRepository.findById(savedUser.getId());
        assertEquals(savedUser.getFullName(), foundUser.getFullName());
    }

    @Test
    public void updateUserUsername_findByIdReturnsUpdatedUsername(){
        userRepository.save(newUser);
        newUser.setFullName("Tosin Ola");
        User foundUser = userRepository.findById(newUser.getId());
        assertEquals("Tosin Ola", foundUser.getFullName());
    }

    @Test
    public void saveNewUser_countIs1_deleteById_countIs0Test(){
        userRepository.save(newUser);
        assertEquals(1,userRepository.count());
        userRepository.deleteById(newUser.getId());
       assertEquals(0,userRepository.count());
    }

    @Test
    public void saveNewUser_countIs1_delete_countIs0Test(){
        userRepository.save(newUser);
        assertEquals(1,userRepository.count());
        userRepository.delete(newUser);
        assertEquals(0,userRepository.count());
    }

    @Test
    public void save2NewUsers_countIs2_deleteAll_countIs0Test(){
        userRepository.save(newUser);
        User newUserTwo = new User();
        newUserTwo.setUsername("Ayo");
        newUserTwo.setPassword("2345");
        newUserTwo.setFullName("Ayo Ola");
        userRepository.save(newUserTwo);
        assertEquals(2,userRepository.count());
        userRepository.deleteAll();
        assertEquals(0,userRepository.count());
    }

    @Test
    public void saveNewUser_userExistsByIdTest(){
        userRepository.save(newUser);
        assertTrue(userRepository.existsById(newUser.getId()));
    }
}

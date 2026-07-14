package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{

    private int count;
    private List<User> users;

    public UserRepositoryImpl(){
        users = new ArrayList<>();
    }

    public User save(User user){
        if(user.getId() == 0) {
            user.setId(++count);
            users.add(user);
            return user;
        }throw new IllegalArgumentException("user already exists");

    }

    public long count() {
        return users.size();
    }

    public User findById(int id) {
        for(User user : users){
            if (user.getId() == id)
                return user;
        }throw new IllegalArgumentException("Invalid id");
    }

    @Override
    public void deleteById(int id) {
        User user = findById(id);
        delete(user);
    }

    @Override
    public void delete(User user) {
        if (user != null)
            users.remove(user);
        else throw new IllegalArgumentException("user is null");
    }

    @Override
    public void deleteAll() {
        users.clear();
    }

    @Override
    public boolean existsById(int id) {
        for(User user : users) {
            if (user.getId() == id)
                return true;
        }return false;
    }
}

package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{

    private static int count;
    private static List<User> users = new ArrayList<>();

    public User save(User user){
        if(isNew(user)){
            user.setId(++count);
            users.add(user);
            return user;
        }
        update(user);
        return user;

    }

    private boolean isNew(User user){
        return user.getId() == 0;
    }

    public void update(User user) {
        user.setLoggedIn(user.isLoggedIn());
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

    @Override
    public User findByUsername(String username){
        for(User user : users){
            if(user.getUsername().equals(username))
                return user;
        }return null;
    }


}

package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.User;

public interface UserRepository {
    long count();

    User save(User user);

    User findById(int id);

    void deleteById(int id);

    void delete(User user);

    void deleteAll();

    boolean existsById(int id);

    User findByUsername(String username);

    void update(User user);

}

package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.DispensedDrugs;

public interface DispensedDrugsRepository {
    long count();

    DispensedDrugs save(DispensedDrugs drug);

    DispensedDrugs findById(int id);

    void deleteById(int id);

    void delete(DispensedDrugs drug);

    void deleteAll();

    boolean existsById(int id);
}

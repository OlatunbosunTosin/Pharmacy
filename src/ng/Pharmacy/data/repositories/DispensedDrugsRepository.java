package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.DispensedDrug;

public interface DispensedDrugsRepository {
    long count();

    DispensedDrug save(DispensedDrug drug);

    DispensedDrug findById(int id);

    void deleteById(int id);

    void delete(DispensedDrug drug);

    void deleteAll();

    boolean existsById(int id);
}

package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.Drug;

public interface DrugRepository {

        long count();

        Drug save(Drug drug);

        Drug findById(int id);

        void deleteById(int id);

        void delete(Drug drug);

        void deleteAll();

        boolean existsById(int id);

        Drug findByName(String name);


}

package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryImpl implements DrugRepository{

    private int count;
    private List<Drug> drugs;

    public DrugRepositoryImpl(){
        drugs = new ArrayList<>();
    }

    public Drug save(Drug drug){
        if(drug.getId() == 0) {
            drug.setId(++count);
            drugs.add(drug);
            return drug;
        }throw new IllegalArgumentException("drug is already saved");
    }

    public long count() {
        return drugs.size();
    }

    public Drug findById(int id) {
        for(Drug drug : drugs){
            if (drug.getId() == id)
                return drug;
        }throw new IllegalArgumentException("Invalid id");
    }

    @Override
    public void deleteById(int id) {
        Drug drug = findById(id);
        delete(drug);
    }

    @Override
    public void delete(Drug drug) {
        if (drug != null)
            drugs.remove(drug);
        else throw new IllegalArgumentException("user is null");
    }

    @Override
    public void deleteAll() {
        drugs.clear();
    }

    @Override
    public boolean existsById(int id) {
        for(Drug drug : drugs) {
            if (drug.getId() == id)
                return true;
        }return false;
    }

}

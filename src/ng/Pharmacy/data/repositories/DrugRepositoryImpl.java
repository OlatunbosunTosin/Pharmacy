package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryImpl implements DrugRepository{

    private static int count;
    private static List<Drug> drugs;

    public DrugRepositoryImpl(){
        drugs = new ArrayList<>();
    }

    public Drug save(Drug drug){
        if(isNew(drug)){
            drug.setId(++count);
            drugs.add(drug);
            return drug;
        }
        update(drug);
        return drug;
    }

    private boolean isNew(Drug drug){
        return drug.getId() == 0;
    }

    public void update(Drug drug) {
        delete(drug);
        drugs.add(drug);
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
        if (drug == null) throw new IllegalArgumentException("user is null");
        Drug removedDrug = findById(drug.getId());
        drugs.remove(removedDrug);
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

    public Drug findByName(String name){
        for(Drug drug : drugs){
            if(drug.getName().equals(name))
                return drug;
        }return null;
    }

}

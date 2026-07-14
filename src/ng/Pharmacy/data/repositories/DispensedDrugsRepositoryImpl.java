package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.DispensedDrug;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugsRepositoryImpl implements DispensedDrugsRepository {

    private int count;
    private List<DispensedDrug> drugs;

    public DispensedDrugsRepositoryImpl(){
        drugs = new ArrayList<>();
    }

    public DispensedDrug save(DispensedDrug purchasedDrug){
        if(purchasedDrug.getId() == 0) {
            purchasedDrug.setId(++count);
            drugs.add(purchasedDrug);
            return purchasedDrug;
        }
        DispensedDrug oldSaved = findById(purchasedDrug.getId());
        drugs.remove(oldSaved);
        drugs.add(purchasedDrug);
        return purchasedDrug;

    }

    public long count() {
        return drugs.size();
    }

    public DispensedDrug findById(int id) {
        for(DispensedDrug drug : drugs){
            if (drug.getId() == id)
                return drug;
        }throw new IllegalArgumentException("Invalid id");
    }

    @Override
    public void deleteById(int id) {
        DispensedDrug drug = findById(id);
        delete(drug);
    }

    @Override
    public void delete(DispensedDrug drug) {
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
        for(DispensedDrug drug : drugs) {
            if (drug.getId() == id)
                return true;
        }return false;
    }
}

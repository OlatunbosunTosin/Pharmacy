package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.DispensedDrugs;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugsRepositoryImpl implements DispensedDrugsRepository {

    private static int count;
    private static List<DispensedDrugs> drugs;

    public DispensedDrugsRepositoryImpl(){
        drugs = new ArrayList<>();
    }

    public DispensedDrugs save(DispensedDrugs purchasedDrug){
        if(purchasedDrug.getId() == 0) {
            purchasedDrug.setId(++count);
            drugs.add(purchasedDrug);
            return purchasedDrug;
        }
        DispensedDrugs oldSaved = findById(purchasedDrug.getId());
        drugs.remove(oldSaved);
        drugs.add(purchasedDrug);
        return purchasedDrug;

    }

    public long count() {
        return drugs.size();
    }

    public DispensedDrugs findById(int id) {
        for(DispensedDrugs drug : drugs){
            if (drug.getId() == id)
                return drug;
        }throw new IllegalArgumentException("Invalid id");
    }

    @Override
    public void deleteById(int id) {
        DispensedDrugs drug = findById(id);
        delete(drug);
    }

    @Override
    public void delete(DispensedDrugs drug) {
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
        for(DispensedDrugs drug : drugs) {
            if (drug.getId() == id)
                return true;
        }return false;
    }
}

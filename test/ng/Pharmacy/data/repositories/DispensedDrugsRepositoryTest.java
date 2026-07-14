package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.DispensedDrug;
import ng.Pharmacy.data.model.Drug;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DispensedDrugsRepositoryTest {

    private DispensedDrugsRepository dispensedDrugsRepository;
    private DispensedDrug drugPurchased;
    private Drug drug;

    @BeforeEach
    public void setUp(){
        dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        drug = new Drug();
        drugPurchased = new DispensedDrug();
        drugPurchased.setDrug(drug);
        drugPurchased.setQuantity(2);
        drugPurchased.setTotalPrice(2000);

    }

    @Test
    public void newRepositoryIsEmptyTest(){
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void saveNewDrug_countIsOneTest(){
        dispensedDrugsRepository.save(drugPurchased);
        assertEquals(1, dispensedDrugsRepository.count());
    }

    @Test
    public void saveTwoNewDrugs_findOneDrugById_returnFullName(){
        dispensedDrugsRepository.save(drugPurchased);
        Drug drugTwo = new Drug();
        DispensedDrug drugPurchasedTwo = new DispensedDrug();
        drugPurchasedTwo.setDrug(drugTwo);
        drugPurchasedTwo.setQuantity(2);
        drugPurchasedTwo.setTotalPrice(2000);
        DispensedDrug savedDrug = dispensedDrugsRepository.save(drugPurchasedTwo);
        DispensedDrug foundDrug = dispensedDrugsRepository.findById(drugPurchasedTwo.getId());
        assertEquals(savedDrug.getDrug().getName(), foundDrug.getDrug().getName());
    }

    @Test
    public void updateDrugName_findByIdReturnsUpdatedName(){
        dispensedDrugsRepository.save(drugPurchased);
        drugPurchased.setQuantity(3);
        DispensedDrug foundDrug = dispensedDrugsRepository.findById(drugPurchased.getId());
        assertEquals(3, foundDrug.getQuantity());
    }

    @Test
    public void saveNewDrug_countIs1_deleteById_countIs0Test(){
        dispensedDrugsRepository.save(drugPurchased);
        assertEquals(1, dispensedDrugsRepository.count());
        dispensedDrugsRepository.deleteById(drugPurchased.getId());
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void saveNewDrug_countIs1_delete_countIs0Test(){
        dispensedDrugsRepository.save(drugPurchased);
        assertEquals(1, dispensedDrugsRepository.count());
        dispensedDrugsRepository.delete(drugPurchased);
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void save2NewDrugs_countIs2_deleteAll_countIs0Test(){
        dispensedDrugsRepository.save(drugPurchased);
        Drug drugTwo = new Drug();
        DispensedDrug drugPurchasedTwo = new DispensedDrug();
        drugPurchasedTwo.setDrug(drugTwo);
        drugPurchasedTwo.setQuantity(2);
        drugPurchasedTwo.setTotalPrice(2000);
        DispensedDrug savedDrug = dispensedDrugsRepository.save(drugPurchasedTwo);
        assertEquals(2, dispensedDrugsRepository.count());
        dispensedDrugsRepository.deleteAll();
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void saveNewDrug_drugExistsByIdTest(){
        dispensedDrugsRepository.save(drugPurchased);
        assertTrue(dispensedDrugsRepository.existsById(drugPurchased.getId()));
    }
}

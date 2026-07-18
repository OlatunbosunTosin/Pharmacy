package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.DispensedDrugs;
import ng.Pharmacy.data.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DispensedDrugsRepositoryTest {

    private DispensedDrugsRepository dispensedDrugsRepository;
    private DispensedDrugs drugPurchased;
    private User user;

    @BeforeEach
    public void setUp(){
        dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        user = new User();
        drugPurchased = new DispensedDrugs();
        drugPurchased.setDispensedBy(user);

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
        user.setFullName("Ayo mide");
        DispensedDrugs drugPurchasedTwo = new DispensedDrugs();
        drugPurchasedTwo.setDispensedBy(user);
        DispensedDrugs savedDrug = dispensedDrugsRepository.save(drugPurchasedTwo);
        DispensedDrugs foundDrug = dispensedDrugsRepository.findById(drugPurchasedTwo.getId());
        assertEquals(savedDrug.getDispensedBy().getFullName(), foundDrug.getDispensedBy().getFullName());
    }

//    @Test
//    public void updateDrugName_findByIdReturnsUpdatedName(){
//        dispensedDrugsRepository.save(drugPurchased);
//        DispensedDrugs foundDrug = dispensedDrugsRepository.findById(drugPurchased.getId());
//        assertEquals(3, foundDrug.getQuantity());
//    }

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
        DispensedDrugs drugPurchasedTwo = new DispensedDrugs();
        dispensedDrugsRepository.save(drugPurchasedTwo);
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

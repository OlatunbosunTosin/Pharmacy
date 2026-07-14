package ng.Pharmacy.data.repositories;

import ng.Pharmacy.data.model.Drug;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrugRepositoryTest {

    private DrugRepository drugRepository;
    private Drug newDrug;
    private LocalDate expiryDate;

    @BeforeEach
    public void setUp(){
        drugRepository = new DrugRepositoryImpl();
        expiryDate = LocalDate.now();
        newDrug = new Drug();
        newDrug.setBrand("Emzor");
        newDrug.setExpiryDate(expiryDate);
        newDrug.setName("Panadol");
        newDrug.setPrice(1000);
    }

    @Test
    public void newRepositoryIsEmptyTest(){
        assertEquals(0, drugRepository.count());
    }

    @Test
    public void saveNewDrug_countIsOneTest(){
        drugRepository.save(newDrug);
        assertEquals(1, drugRepository.count());
    }

    @Test
    public void saveTwoNewDrugs_findOneDrugById_returnFullName(){
        drugRepository.save(newDrug);
        Drug drugTwo = new Drug();
        drugTwo.setBrand("Advil");
        drugTwo.setExpiryDate(expiryDate);
        drugTwo.setName("Ibuprofen");
        drugTwo.setPrice(1000);
        Drug savedDrug = drugRepository.save(drugTwo);
        Drug foundDrug = drugRepository.findById(drugTwo.getId());
        assertEquals(savedDrug.getName(), foundDrug.getName());
    }

    @Test
    public void updateDrugName_findByIdReturnsUpdatedName(){
        drugRepository.save(newDrug);
        newDrug.setName("Acetaminophen");
        Drug foundDrug = drugRepository.findById(newDrug.getId());
        assertEquals("Acetaminophen", foundDrug.getName());
    }

    @Test
    public void saveNewDrug_countIs1_deleteById_countIs0Test(){
        drugRepository.save(newDrug);
        assertEquals(1,drugRepository.count());
        drugRepository.deleteById(newDrug.getId());
        assertEquals(0,drugRepository.count());
    }

    @Test
    public void saveNewDrug_countIs1_delete_countIs0Test(){
        drugRepository.save(newDrug);
        assertEquals(1,drugRepository.count());
        drugRepository.delete(newDrug);
        assertEquals(0,drugRepository.count());
    }

    @Test
    public void save2NewDrugs_countIs2_deleteAll_countIs0Test(){
        drugRepository.save(newDrug);
        Drug drugTwo = new Drug();
        drugTwo.setBrand("Advil");
        drugTwo.setExpiryDate(expiryDate);
        drugTwo.setName("Ibuprofen");
        drugTwo.setPrice(1000);
        Drug savedDrug = drugRepository.save(drugTwo);
        assertEquals(2,drugRepository.count());
        drugRepository.deleteAll();
        assertEquals(0,drugRepository.count());
    }

    @Test
    public void saveNewDrug_drugExistsByIdTest(){
        drugRepository.save(newDrug);
        assertTrue(drugRepository.existsById(newDrug.getId()));
    }
}

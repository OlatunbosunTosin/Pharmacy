package ng.Pharmacy.services;

import ng.Pharmacy.data.model.DispensedDrug;
import ng.Pharmacy.data.model.Drug;
import ng.Pharmacy.data.model.User;
import ng.Pharmacy.data.repositories.*;
import ng.Pharmacy.dtos.requests.AddDrugRequests;
import ng.Pharmacy.dtos.requests.DispenseDrugsRequests;
import ng.Pharmacy.dtos.requests.RegisterUserRequests;
import ng.Pharmacy.dtos.responses.AddDrugResponses;
import ng.Pharmacy.dtos.responses.DrugsDispensedResponses;
import ng.Pharmacy.dtos.responses.RegisterUserResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChemistServicesTest {

    private ChemistServices chemistService;
    private DrugRepository drug;
    private DispensedDrugsRepository dispensedDrugs;

    @BeforeEach
    public void setUp(){

        chemistService = new ChemistServicesImpl();
        drug = new DrugRepositoryImpl();
        dispensedDrugs = new DispensedDrugsRepositoryImpl();
//        drug.deleteAll();

    }

    @Test
    public void add1Drug_countIs1Test(){
        AddDrugRequests request = new AddDrugRequests();
        request.setName("Paracetamol");
        request.setBrand("Emzor");
        request.setPrice(1000);
        chemistService.addDrug(request);

        assertEquals(1, drug.count());
    }

    @Test
    public void addAlreadyExistingDrug_throwsException(){
        AddDrugRequests request = new AddDrugRequests();
        request.setName("Paracetamol");
        request.setBrand("Emzor");
        request.setPrice(1000);
        request.setExpiryDate("13/12/2027");
        chemistService.addDrug(request);

        AddDrugRequests requestTwo = new AddDrugRequests();
        requestTwo.setName("Paracetamol");
        requestTwo.setBrand("Emzor");
        requestTwo.setPrice(1000);
        requestTwo.setExpiryDate("13/12/2028");
        assertThrows(IllegalArgumentException.class, () -> chemistService.addDrug(requestTwo));
    }

    @Test
    public void addADrug_UpdateDrugPrice(){
        AddDrugRequests request = new AddDrugRequests();
        request.setName("Paracetamol");
        request.setBrand("Emzor");
        request.setPrice(1000);
        request.setExpiryDate("13/12/2027");
        chemistService.addDrug(request);

        Drug addedDrug = drug.findByName(request.getName());

        request.setPrice(1100);
        chemistService.updateDrug(request);

        AddDrugResponses response = new AddDrugResponses();
        response.setPrice(addedDrug.getPrice());

        assertEquals(1100, response.getPrice());
    }

    @Test
    public void UpdateDrugPriceOfDrugThatDoesNotExist_throwsException(){
        AddDrugRequests request = new AddDrugRequests();
        drug.findByName(request.getName());
        request.setPrice(1100);

        assertThrows(IllegalArgumentException.class, () -> chemistService.updateDrug(request));

    }

    @Test
    public void add1Drug_countIs1_deleteDrug_countIs0Test() {
        AddDrugRequests request = new AddDrugRequests();
        request.setName("Paracetamol");
        request.setBrand("Emzor");
        request.setPrice(1000);

        AddDrugResponses response = chemistService.addDrug(request);

        chemistService.deleteDrug(response.getId());

        assertEquals(0, drug.count());
    }

    @Test
    public void setDispensedDrugs(){
        AuthService authService = new AuthServiceImpl();
        RegisterUserRequests request = new RegisterUserRequests();
        request.setFullname("fullname");
        request.setPassword("password");
        request.setUsername("username");
        authService.registerChemist(request);

        UserRepository repository = new UserRepositoryImpl();
        User user = repository.findByUsername(request.getUsername());

        AddDrugRequests requestDrug = new AddDrugRequests();
        requestDrug.setName("Paracetamol");
        requestDrug.setBrand("Emzor");
        requestDrug.setPrice(1000);
        requestDrug.setExpiryDate("13/12/2027");
        chemistService.addDrug(requestDrug);

        AddDrugRequests requestTwo = new AddDrugRequests();
        requestTwo.setName("Ibuprofen");
        requestTwo.setBrand("Advil");
        requestTwo.setPrice(1200);
        requestTwo.setExpiryDate("13/12/2028");
        chemistService.addDrug(requestTwo);

        Drug drugOne = drug.findByName("Paracetamol");
        Drug drugTwo = drug.findByName("Ibuprofen");

        DispensedDrug drugBoughtOne = new DispensedDrug();
        drugBoughtOne.setDrug(drugOne);
        drugBoughtOne.setQuantity(1);
        drugBoughtOne.calculateTotalPrice();

        DispensedDrug drugBoughtTwo = new DispensedDrug();
        drugBoughtTwo.setDrug(drugTwo);
        drugBoughtTwo.setQuantity(2);
        drugBoughtTwo.calculateTotalPrice();

        List <DispensedDrug> drugs = new ArrayList<>();
        drugs.add(drugBoughtOne);
        drugs.add(drugBoughtTwo);

        DispenseDrugsRequests drugsDispensed = new DispenseDrugsRequests();
        drugsDispensed.setDispensedDrugs(drugs);
        drugsDispensed.setDispensedBy(user);
        drugsDispensed.setDispensedDateTime();

        DrugsDispensedResponses dispensed = chemistService.dispenseDrugs(drugsDispensed);

        assertEquals(2, dispensed.getDispensedDrugs().size());
    }
}

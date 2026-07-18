package ng.Pharmacy.utils;

import ng.Pharmacy.data.model.DispensedDrugs;
import ng.Pharmacy.data.model.Drug;
import ng.Pharmacy.data.model.User;
import ng.Pharmacy.dtos.requests.AddDrugRequests;
import ng.Pharmacy.dtos.requests.DispenseDrugsRequests;
import ng.Pharmacy.dtos.requests.RegisterUserRequests;
import ng.Pharmacy.dtos.responses.AddDrugResponses;
import ng.Pharmacy.dtos.responses.DrugsDispensedResponses;


public class Mapper {

    public static User mapToUser(RegisterUserRequests request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullname());
        return user;
    }

    public static Drug mapToDrug(AddDrugRequests request){
        Drug drug = new Drug();
        drug.setName(request.getName());
        drug.setBrand(request.getBrand());
        drug.setPrice(request.getPrice());
        drug.setId(request.getId());
        return drug;
    }

    public static AddDrugResponses mapToAddDrugResponse(Drug drug){
        AddDrugResponses response = new AddDrugResponses();
        response.setId(drug.getId());
        response.setPrice(drug.getPrice());
        response.setBrand(drug.getBrand());
        response.setName(drug.getName());
        response.setExpiryDate(drug.getExpiryDate());
        return response;
    }

    public static DrugsDispensedResponses mapToDrugsDispensedResponse(DispensedDrugs drug){
        DrugsDispensedResponses response = new DrugsDispensedResponses();
        response.setDispensedDrugs(drug.getDispensedDrugs());
        response.setDispensedDateTime(drug.getDispensedDateTime());
        response.setId(drug.getId());
        return response;
    }

    public static DispensedDrugs mapToDispensedDrugs(DispenseDrugsRequests drugsDispensedRequest){
        DispensedDrugs drugs = new DispensedDrugs();
        drugs.setDispensedBy(drugsDispensedRequest.getDispensedBy());
        drugs.setId(drugsDispensedRequest.getId());
        drugs.setDispensedDateTime(drugsDispensedRequest.getDispensedDateTime());
        drugs.setDispensedDrugs(drugsDispensedRequest.getDispensedDrugs());
        return drugs;
    }
}

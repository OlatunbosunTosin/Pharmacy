package ng.Pharmacy.controllers;

import ng.Pharmacy.dtos.requests.AddDrugRequests;
import ng.Pharmacy.dtos.requests.DispenseDrugsRequests;
import ng.Pharmacy.dtos.responses.AddDrugResponses;
import ng.Pharmacy.dtos.responses.DrugsDispensedResponses;
import ng.Pharmacy.services.ChemistServices;
import ng.Pharmacy.services.ChemistServicesImpl;

public class ChemistController {

    private ChemistServices chemistService = new ChemistServicesImpl();

    public AddDrugResponses response (AddDrugRequests request){
        return chemistService.addDrug(request);
    }

    public DrugsDispensedResponses dispenseDrugsResponse(DispenseDrugsRequests drugsDispensedRequest){
        return chemistService.dispenseDrugs(drugsDispensedRequest);
    }

}

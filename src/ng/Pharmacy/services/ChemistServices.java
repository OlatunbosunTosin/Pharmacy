package ng.Pharmacy.services;

import ng.Pharmacy.data.model.DispensedDrugs;
import ng.Pharmacy.dtos.requests.AddDrugRequests;
import ng.Pharmacy.dtos.requests.DispenseDrugsRequests;
import ng.Pharmacy.dtos.responses.AddDrugResponses;
import ng.Pharmacy.dtos.responses.DrugsDispensedResponses;

import java.util.List;

public interface ChemistServices {

    AddDrugResponses addDrug(AddDrugRequests request);
    AddDrugResponses updateDrug(AddDrugRequests request);
    void deleteDrug(int id);
    DrugsDispensedResponses dispenseDrugs(DispenseDrugsRequests drugsDispensedRequest);
}

package ng.Pharmacy.services;

import ng.Pharmacy.data.model.DispensedDrug;
import ng.Pharmacy.data.model.DispensedDrugs;
import ng.Pharmacy.data.model.Drug;
import ng.Pharmacy.data.model.User;
import ng.Pharmacy.data.repositories.DispensedDrugsRepository;
import ng.Pharmacy.data.repositories.DispensedDrugsRepositoryImpl;
import ng.Pharmacy.data.repositories.DrugRepository;
import ng.Pharmacy.data.repositories.DrugRepositoryImpl;
import ng.Pharmacy.dtos.requests.AddDrugRequests;
import ng.Pharmacy.dtos.requests.DispenseDrugsRequests;
import ng.Pharmacy.dtos.responses.AddDrugResponses;
import ng.Pharmacy.dtos.responses.DrugsDispensedResponses;
import ng.Pharmacy.utils.Mapper;

import java.util.List;

public class ChemistServicesImpl implements ChemistServices {

    private final DrugRepository drugRepository = new DrugRepositoryImpl();
    private final DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();

    @Override
    public AddDrugResponses addDrug(AddDrugRequests request) {
        if (drugRepository.findByName(request.getName()) != null){
            throw new IllegalArgumentException(request.getName() + " already exists");
        }
        Drug drug = drugRepository.save(Mapper.mapToDrug(request));
        return Mapper.mapToAddDrugResponse(drug);
    }

    @Override
    public AddDrugResponses updateDrug(AddDrugRequests request) {
        Drug drug = drugRepository.findByName(request.getName());
        if (drug != null){
            drug.setPrice(request.getPrice());
            drugRepository.save(drug);
            return Mapper.mapToAddDrugResponse(drug);
        }throw new IllegalArgumentException("drug does not exist");

    }

    @Override
    public void deleteDrug(int id) {
        drugRepository.deleteById(id);
    }

    @Override
    public DrugsDispensedResponses dispenseDrugs(DispenseDrugsRequests drugsDispensedRequest) {

        DispensedDrugs drug = dispensedDrugsRepository.save(Mapper.mapToDispensedDrugs(drugsDispensedRequest));
        return Mapper.mapToDrugsDispensedResponse(drug);

    }
}

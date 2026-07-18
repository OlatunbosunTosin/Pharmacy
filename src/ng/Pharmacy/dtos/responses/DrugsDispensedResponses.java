package ng.Pharmacy.dtos.responses;

import ng.Pharmacy.data.model.DispensedDrug;
import ng.Pharmacy.data.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class DrugsDispensedResponses {

    private int id;
    private User dispensedBy;
    private List<DispensedDrug> dispensedDrugs;
    private LocalDateTime dispensedDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getDispensedBy() {
        return dispensedBy;
    }

    public void setDispensedBy(User dispensedBy) {
        this.dispensedBy = dispensedBy;
    }

    public List<DispensedDrug> getDispensedDrugs() {
        return dispensedDrugs;
    }

    public void setDispensedDrugs(List<DispensedDrug> dispensedDrugs) {
        this.dispensedDrugs = dispensedDrugs;
    }

    public LocalDateTime getDispensedDateTime() {
        return dispensedDateTime;
    }

    public void setDispensedDateTime(LocalDateTime dispensedDateTime) {
        this.dispensedDateTime = dispensedDateTime;
    }
}

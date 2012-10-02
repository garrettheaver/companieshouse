package ie.hjkl.companieshouse.data.appointment.appointees;

import ie.hjkl.companieshouse.data.utils.DateUtils;
import org.joda.time.LocalDate;

public class SnapshotIndividualAppointee extends SnapshotAppointee {

    public SnapshotIndividualAppointee(String record) {
        super(record);
    }

    public String getTitle() {
        return getVariableData()[0];
    }

    public String[] getForenames() {
        return getVariableData()[1].split(" ");
    }

    public String[] getLastnames() {
        return getVariableData()[2].split(" ");
    }

    public String getHonors() {
        return getVariableData()[3];
    }

    public LocalDate getBornOn() {
        return DateUtils.parse(record.substring(56, 64));
    }

    public String getOccupation() {
        return getVariableData()[11];
    }

    public String getNationality() {
        return getVariableData()[12];
    }

    public String getUsualResidentialCountry() {
        return getVariableData()[13];
    }

}

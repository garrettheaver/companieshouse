package ie.hjkl.companieshouse.data.appointment.appointees;

public class SnapshotCorporateAppointee extends SnapshotAppointee {

    public SnapshotCorporateAppointee(String record) {
        super(record);
    }

    public String getName() {
        return getVariableData()[2];
    }

}

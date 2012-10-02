package ie.hjkl.companieshouse.data.appointment;

import ie.hjkl.companieshouse.data.appointment.appointees.SnapshotAppointee;
import ie.hjkl.companieshouse.data.appointment.appointees.SnapshotCorporateAppointee;
import ie.hjkl.companieshouse.data.appointment.appointees.SnapshotIndividualAppointee;

public class SnapshotAppointeeFactory {

    public static SnapshotAppointee getAppointee(String record) {
        if (record.substring(24, 25).equals("Y"))
            return new SnapshotCorporateAppointee(record);
        else
            return new SnapshotIndividualAppointee(record);
    }

}

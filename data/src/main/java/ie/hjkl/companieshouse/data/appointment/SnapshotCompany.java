package ie.hjkl.companieshouse.data.appointment;

import ie.hjkl.companieshouse.data.appointment.appointees.SnapshotAppointee;

public class SnapshotCompany {

    private String record;
    private SnapshotAppointee[] appointees;

    public SnapshotCompany(String record) {
        this(record, null);
    }

    public SnapshotCompany(String record, SnapshotAppointee[] appointees) {
        this.record = record;
        this.appointees = appointees;
    }

    public String getNumber() {
        return record.substring(0, 8);
    }

    public String getStatus() {
        String status = record.substring(9, 10).trim();
        return status.isEmpty() ? null : status;
    }

    public String getName() {
        String name = record.substring(40, record.length()).trim();
        return name.substring(0, name.length() - 1);
    }

    public SnapshotAppointee[] getAppointees() {
        return appointees;
    }

}

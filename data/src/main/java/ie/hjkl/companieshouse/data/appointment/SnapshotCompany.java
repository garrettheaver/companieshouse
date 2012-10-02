package ie.hjkl.companieshouse.data.appointment;

public class SnapshotCompany {

    private String record;

    public SnapshotCompany(String record) {
        this.record = record;
    }

    public String getNumber() {
        return record.substring(0, 8);
    }

    public String getStatus() {
        String status = record.substring(9, 10).trim();
        return status.isEmpty() ? null : status;
    }

    public int getNumberOfOfficers() {
        return Integer.parseInt(record.substring(32, 36));
    }

    public String getName() {
        String name = record.substring(40, record.length()).trim();
        return name.substring(0, name.length() - 1);
    }

}

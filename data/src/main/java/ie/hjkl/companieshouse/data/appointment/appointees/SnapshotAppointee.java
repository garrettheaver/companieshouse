package ie.hjkl.companieshouse.data.appointment.appointees;

import ie.hjkl.companieshouse.data.utils.DateUtils;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public abstract class SnapshotAppointee {

    protected String record;
    private String[] variableData;

    public SnapshotAppointee(String record) {
        this.record = record;
    }

    public String getCompanyNumber() {
        return record.substring(0, 8);
    }

    public String getAppointeeNumber() {
        return record.substring(12, 24);
    }

    public String getAppointmentDateOrigin() {
        return record.substring(9, 10);
    }

    public int getAppointmentType() {
        return Integer.parseInt(record.substring(10, 12));
    }

    public LocalDate getAppointedOn() {
        return DateUtils.parse(record.substring(32, 40));
    }

    public LocalDate getResignedOn() {
        return DateUtils.parse(record.substring(40, 48));
    }

    public String getPostcode() {
        return record.substring(48, 56).trim();
    }

    public String[] getAddress() {
        List<String> address = new ArrayList<String>();
        String[] variableData = getVariableData();

        for (int i = 4; i <= 10; i++)
            if (!(variableData[i].isEmpty()))
                address.add(variableData[i]);

        return address.toArray(new String[address.size()]);
    }

    protected String[] getVariableData() {
        if (variableData == null)
            variableData = record.substring(68).split("<");

        return variableData;
    }

}

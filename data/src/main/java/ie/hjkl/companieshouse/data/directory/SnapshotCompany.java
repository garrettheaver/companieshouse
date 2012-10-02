package ie.hjkl.companieshouse.data.directory;

import ie.hjkl.companieshouse.data.utils.DateUtils;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class SnapshotCompany {

    private String record;

    public SnapshotCompany(String record) {
        this.record = record;
    }

    public String getNumber() {
        return record.substring(0, 8);
    }

    public LocalDate getIncorporatedOn() {
        return DateUtils.parse(record.substring(12, 20));
    }

    public LocalDate getAccountsMadeUpTo() {
        return DateUtils.parse(record.substring(20, 28));
    }

    public LocalDate getAnnualReturnMadeUpTo() {
        return DateUtils.parse(record.substring(28, 36));
    }

    public String getStatus() {
        return record.substring(36, 38).trim();
    }

    public int getAccountingReferenceDay() {
        return Integer.parseInt(record.substring(38, 40));
    }

    public int getAccountingReferenceMonth() {
        return Integer.parseInt(record.substring(40, 42));
    }

    public String getAccountsType() {
        return record.substring(66, 68).trim();
    }

    public String getInspectionMarker() {
        String marker = record.substring(68, 69).trim();
        return marker.isEmpty() ? null : marker;
    }

    public String getPostcode() {
        String postcode = record.substring(70, 78).trim();
        return postcode.isEmpty() ? null : postcode;
    }

    public String getPostcodeStatus() {
        String status = record.substring(78, 79).trim();
        return status.isEmpty() ? null : status;
    }

    public String getName() {
        return record.substring(90, 250).trim();
    }

    public String getAlphaKey() {
        return record.substring(250, 310).trim();
    }

    public int getJurisdiction() {
        return Integer.parseInt(record.substring(310, 311));
    }

    public String[] getAddress() {
        List<String> address = new ArrayList<String>();

        for (String part : getVariableData())
            if (!(part.isEmpty()))
                address.add(part);

        return address.toArray(new String[address.size()]);
    }

    private String[] getVariableData() {
        return record.substring(311).split("<");
    }

}

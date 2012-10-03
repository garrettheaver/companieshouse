package ie.hjkl.companieshouse.files;

import ie.hjkl.companieshouse.data.appointment.SnapshotCompany;
import ie.hjkl.companieshouse.data.appointment.appointees.SnapshotAppointee;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AppointmentSnapshotTest {

    private String resource0 = "/appointmentSnapshot0.dat";
    private AppointmentSnapshot snapshot0;

    @Before
    public void setUp() throws Exception {
        String path = getClass().getResource(resource0).getFile();
        snapshot0 = new AppointmentSnapshot(path);
    }

    @Test
    public void getRunNumber() {
        assertEquals(9938, snapshot0.getRunNumber());
    }

    @Test
    public void getProducedOn() {
        assertEquals(new LocalDate(2009, 11, 28), snapshot0.getProducedOn());
    }

    @Test
    public void getNumberOfRecords() {
        assertEquals(228, snapshot0.getNumberOfRecords());
    }

    @Test
    public void iteratorReturnsCorrectFirstCompany() {
        SnapshotCompany company0 = snapshot0.iterator().next();
        assertEquals("02334784", company0.getNumber());
    }

    @Test
    public void iteratorReturnsCorrectFirstAppointee() {
        SnapshotCompany company0 = snapshot0.iterator().next();
        SnapshotAppointee appointee0 = company0.getAppointees()[0];
        assertEquals("018240940001", appointee0.getAppointeeNumber());
    }

    @Test
    public void iteratorReturnsCorrectNumberOfCompanies() {
        List<SnapshotCompany> companies = new ArrayList<SnapshotCompany>();

        for (SnapshotCompany company : snapshot0)
            companies.add(company);

        assertEquals(44, companies.size());
    }

    @Test
    public void iteratorReturnsCorrectNumberOfAppointees() {
        SnapshotCompany company0 = snapshot0.iterator().next();
        assertEquals(3, company0.getAppointees().length);
    }

    @Test
    public void iteratorReturnsCorrectFinalCompany() {
        SnapshotCompany company44 = null;

        for (SnapshotCompany company : snapshot0)
            company44 = company;

        assertEquals("02602769", company44.getNumber());
    }

    @Test
    public void iteratorReturnsCorrectFinalAppointee() {
        SnapshotCompany company44 = null;

        for (SnapshotCompany company : snapshot0)
            company44 = company;

        SnapshotAppointee appointee2 = company44.getAppointees()[2];
        assertEquals("040362430004", appointee2.getAppointeeNumber());
    }

}

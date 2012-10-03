package ie.hjkl.companieshouse.files;

import ie.hjkl.companieshouse.data.directory.SnapshotCompany;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DirectorySnapshotTest {

    private String resource0 = "/directorySnapshot0.dat";
    private DirectorySnapshot snapshot0;

    @Before
    public void setUp() throws Exception {
        String path = getClass().getResource(resource0).getFile();
        snapshot0 = new DirectorySnapshot(path);
    }

    @Test
    public void getRunNumber() {
        assertEquals(425, snapshot0.getRunNumber());
    }

    @Test
    public void getProducedOn() {
        assertEquals(new LocalDate(2011, 10, 20), snapshot0.getProducedOn());
    }

    @Test
    public void getNumberOfRecords() {
        assertEquals(681, snapshot0.getNumberOfRecords());
    }

    @Test
    public void iteratorReturnsCorrectFirstObject() {
        SnapshotCompany company0 = snapshot0.iterator().next();
        assertEquals("00000182", company0.getNumber());
    }

    @Test
    public void iteratorReturnsCorrectNumberOfRecords() {
        List<SnapshotCompany> companies = new ArrayList<SnapshotCompany>();

        for (SnapshotCompany company : snapshot0)
            companies.add(company);

        assertEquals(681, companies.size());
    }

    @Test
    public void iteratorReturnsCorrectFinalObject() {
        SnapshotCompany company681 = null;

        for (SnapshotCompany company : snapshot0)
            company681 = company;

        assertEquals("00196631", company681.getNumber());
    }

}

package ie.hjkl.companieshouse.data.appointment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SnapshotCompanyTest {

    private String example0 = "023347841                       00030028FLOW SYSTEMS DESIGN LIMITED<                                                                                                                                     ";
    private String example1 = "023348491D                      00000037PHOENIX PAINTING CONTRACTORS LIMITED<                                                                                                                            ";

    private SnapshotCompany record0, record1;

    @Before
    public void setUp() {
        record0 = new SnapshotCompany(example0);
        record1 = new SnapshotCompany(example1);
    }

    @Test
    public void getNumber() {
        assertEquals("02334784", record0.getNumber());
    }

    @Test
    public void getStatusWhenPresent() {
        assertEquals("D", record1.getStatus());
    }

    @Test
    public void getStatusWhenBlank() {
        assertNull(record0.getStatus());
    }

    @Test
    public void getName() {
        assertEquals("FLOW SYSTEMS DESIGN LIMITED", record0.getName());
    }

}

package ie.hjkl.companieshouse.data.appointment.appointees;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SnapshotCorporateAppointeeTest {

    private String example0 = "023349932102120682720001Y       2007060420070604EC4M 6YH        0067<<BWB SECRETARIAL LIMITED<<<<2-6 CANNON STREET<<LONDON<<<<BRITISH<<";

    private SnapshotCorporateAppointee record0;

    @Before
    public void setUp() {
        record0 = new SnapshotCorporateAppointee(example0);
    }

    @Test
    public void getName() {
        assertEquals("BWB SECRETARIAL LIMITED", record0.getName());
    }

}

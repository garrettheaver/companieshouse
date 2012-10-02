package ie.hjkl.companieshouse.data.appointment.appointees;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SnapshotIndividualAppointeeTest {

    private String example0 = "023347842200018240940001        19911031        S11 8RW 195308140113MR<GEOFFREY HUGH<PRIESTMAN<OBE<<<49 ENDCLIFFE GLEN ROAD<<SHEFFIELD<SOUTH YORKSHIRE<<LECTURER<BRITISH<UNITED KINGDOM<";

    private SnapshotIndividualAppointee record0;

    @Before
    public void setUp() {
        record0 = new SnapshotIndividualAppointee(example0);
    }

    @Test
    public void getTitle() {
        assertEquals("MR", record0.getTitle());
    }

    @Test
    public void getForenames() {
        String[] expected = new String[]{"GEOFFREY", "HUGH"};
        assertArrayEquals(expected, record0.getForenames());
    }

    @Test
    public void getLastnames() {
        String[] expected = new String[]{"PRIESTMAN"};
        assertArrayEquals(expected, record0.getLastnames());
    }

    @Test
    public void getHonors() {
        assertEquals("OBE", record0.getHonors());
    }

    @Test
    public void getBornOn() {
        assertEquals(new LocalDate(1953, 8, 14), record0.getBornOn());
    }

    @Test
    public void getOccupation() {
        assertEquals("LECTURER", record0.getOccupation());
    }

    @Test
    public void getNationality() {
        assertEquals("BRITISH", record0.getNationality());
    }

    @Test
    public void getUsualResidentialCountry() {
        assertEquals("UNITED KINGDOM", record0.getUsualResidentialCountry());
    }

}

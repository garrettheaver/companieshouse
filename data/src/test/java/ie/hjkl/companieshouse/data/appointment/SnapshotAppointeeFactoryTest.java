package ie.hjkl.companieshouse.data.appointment;

import ie.hjkl.companieshouse.data.appointment.appointees.SnapshotAppointee;
import ie.hjkl.companieshouse.data.appointment.appointees.SnapshotCorporateAppointee;
import ie.hjkl.companieshouse.data.appointment.appointees.SnapshotIndividualAppointee;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SnapshotAppointeeFactoryTest {

    private String example0 = "023347842200018240940001        19911031        S11 8RW 195308140113MR<GEOFFREY HUGH<PRIESTMAN<OBE<<<49 ENDCLIFFE GLEN ROAD<<SHEFFIELD<SOUTH YORKSHIRE<<LECTURER<BRITISH<UNITED KINGDOM<";
    private String example1 = "023349932102120682720001Y       2007060420070604EC4M 6YH        0067<<BWB SECRETARIAL LIMITED<<<<2-6 CANNON STREET<<LONDON<<<<BRITISH<<";

    private SnapshotAppointee record0, record1;

    @Before
    public void setUp() {
        record0 = SnapshotAppointeeFactory.getAppointee(example0);
        record1 = SnapshotAppointeeFactory.getAppointee(example1);
    }

    @Test
    public void returnsIndividual() {
        assertEquals(SnapshotIndividualAppointee.class, record0.getClass());
    }

    @Test
    public void returnsCorporate() {
        assertEquals(SnapshotCorporateAppointee.class, record1.getClass());
    }

    @Test
    public void getCompanyNumber() {
        assertEquals("02334784", record0.getCompanyNumber());
    }

    @Test
    public void getAppointeeNumber() {
        assertEquals("018240940001", record0.getAppointeeNumber());
    }

    @Test
    public void getAppointmentDateOrigin() {
        assertEquals("2", record0.getAppointmentDateOrigin());
    }

    @Test
    public void getAppointmentType() {
        assertEquals(0, record0.getAppointmentType());
    }

    @Test
    public void getAppointedOn() {
        assertEquals(new LocalDate(1991, 10, 31), record0.getAppointedOn());
    }

    @Test
    public void getResignedOnWhenPresent() {
        assertEquals(new LocalDate(2007, 6, 4), record1.getResignedOn());
    }

    @Test
    public void getResignedOnWhenBlank() {
        assertNull(record0.getResignedOn());
    }

    @Test
    public void getPostcode() {
        assertEquals("S11 8RW", record0.getPostcode());
    }

    @Test
    public void getAddress() {
        String[] expected = new String[]{"49 ENDCLIFFE GLEN ROAD", "SHEFFIELD", "SOUTH YORKSHIRE"};
        assertArrayEquals(expected, record0.getAddress());
    }

}

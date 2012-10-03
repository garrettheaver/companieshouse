package ie.hjkl.companieshouse.data.directory;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SnapshotCompanyTest {

    private String example0 = "00000182    186301152010123120110925 23112                         1  RH12 1XLO           UNION MARINE AND GENERAL INSURANCE COMPANY LIMITED,(THE)                                                                                                        UNIONMARINEANDGENERALINSURANCECOMPANYLIMITEDTHE             1<ST MARK'S COURT<CHART WAY<HORSHAM<WEST SUSSEX<<RH12 1XL<<<<";
    private String example1 = "00093076    000000000000000000000000 20309                         0L                     UNITED SALT LIMITED                                                                                                                                             UNITEDSALT                                                  1<CEROBOS HOUSE<VICTORIA ROAD<WILLESDEN<LONDON NW10<<<<<<";

    private SnapshotCompany record0, record1;

    @Before
    public void setUp() {
        record0 = new SnapshotCompany(example0);
        record1 = new SnapshotCompany(example1);
    }

    @Test
    public void getNumber() {
        assertEquals("00000182", record0.getNumber());
    }

    @Test
    public void getIncorporatedOnWhenPresent() {
        assertEquals(new LocalDate(1863, 1, 15), record0.getIncorporatedOn());
    }

    @Test
    public void getIncorporatedOnWhenBlank() {
        assertNull(record1.getIncorporatedOn());
    }

    @Test
    public void getAccountsMadeUpToWhenPresent() {
        assertEquals(new LocalDate(2010, 12, 31), record0.getAccountsMadeUpTo());
    }

    @Test
    public void getAccountsMadeUpToWhenBlank() {
        assertNull(record1.getAccountsMadeUpTo());
    }

    @Test
    public void getAnnualReturnMadeUpToWhenPresent() {
        assertEquals(new LocalDate(2011, 9, 25), record0.getAnnualReturnMadeUpTo());
    }

    @Test
    public void getAnnualReturnMadeUpToWhenBlank() {
        assertNull(record1.getAnnualReturnMadeUpTo());
    }

    @Test
    public void getStatus() {
        assertEquals("2", record0.getStatus());
    }

    @Test
    public void getAccountingReferenceDay() {
        assertEquals(31, record0.getAccountingReferenceDay());
    }

    @Test
    public void getAccountingReferenceMonth() {
        assertEquals(12, record0.getAccountingReferenceMonth());
    }

    @Test
    public void getAccountsType() {
        assertEquals("1", record0.getAccountsType());
    }

    @Test
    public void getInspectionMarkerWhenPresent() {
        assertEquals("L", record1.getInspectionMarker());
    }

    @Test
    public void getInspectionMarkerWhenBlank() {
        assertNull(record0.getInspectionMarker());
    }

    @Test
    public void getPostcodeWhenPresent() {
        assertEquals("RH12 1XL", record0.getPostcode());
    }

    @Test
    public void getPostcodeWhenBlank() {
        assertNull(record1.getPostcode());
    }

    @Test
    public void getPostcodeStatusWhenPresent() {
        assertEquals("O", record0.getPostcodeStatus());
    }

    @Test
    public void getPostcodeStatusWhenBlank() {
        assertNull(record1.getPostcodeStatus());
    }

    @Test
    public void getName() {
        assertEquals("UNION MARINE AND GENERAL INSURANCE COMPANY LIMITED,(THE)", record0.getName());
    }

    @Test
    public void getAlphaKey() {
        assertEquals("UNIONMARINEANDGENERALINSURANCECOMPANYLIMITEDTHE", record0.getAlphaKey());
    }

    @Test
    public void getJurisdiction() {
        assertEquals(1, record0.getJurisdiction());
    }

    @Test
    public void getAddress() {
        String[] expected = new String[]{"ST MARK'S COURT", "CHART WAY", "HORSHAM", "WEST SUSSEX", "RH12 1XL"};
        assertArrayEquals(expected, record0.getAddress());
    }
}

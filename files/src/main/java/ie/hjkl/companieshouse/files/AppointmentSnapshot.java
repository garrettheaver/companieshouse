package ie.hjkl.companieshouse.files;

import ie.hjkl.companieshouse.data.appointment.SnapshotAppointeeFactory;
import ie.hjkl.companieshouse.data.appointment.SnapshotCompany;
import ie.hjkl.companieshouse.data.appointment.appointees.SnapshotAppointee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppointmentSnapshot extends Snapshot<SnapshotCompany> {

    public AppointmentSnapshot(String path) throws IOException {
        super(path);
    }

    @Override
    public Iterator<SnapshotCompany> iterator() {
        try {
            FileReader reader = new FileReader(new File(path));
            BufferedReader buffer = new BufferedReader(reader);
            return new AppointmentSnapshotIterator(buffer);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

class AppointmentSnapshotIterator extends SnapshotIterator<SnapshotCompany> {

    private static final String APPOINTEE_RECORD_MARKER = "2";

    public AppointmentSnapshotIterator(BufferedReader reader) throws IOException {
        super(reader);
        this.nextRecord = reader.readLine();
    }

    @Override
    public SnapshotCompany next() {
        String currentCompany = nextRecord;
        List<SnapshotAppointee> list = new ArrayList<SnapshotAppointee>();

        try {
            while ((nextRecord = reader.readLine()) != null) {
                if (nextRecord.substring(8, 9).equals(APPOINTEE_RECORD_MARKER))
                    list.add(SnapshotAppointeeFactory.getAppointee(nextRecord));
                else
                    break;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        SnapshotAppointee[] array = list.toArray(new SnapshotAppointee[list.size()]);
        return new SnapshotCompany(currentCompany, array);
    }
}

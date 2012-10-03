package ie.hjkl.companieshouse.files;

import ie.hjkl.companieshouse.data.directory.SnapshotCompany;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class DirectorySnapshot extends Snapshot implements Iterable<SnapshotCompany> {

    public DirectorySnapshot(String path) throws IOException {
        super(path);
    }

    @Override
    public Iterator<SnapshotCompany> iterator() {
        try {
            FileReader reader = new FileReader(new File(path));
            BufferedReader buffer = new BufferedReader(reader);
            return new DirectorySnapshotIterator(buffer);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

class DirectorySnapshotIterator extends SnapshotIterator<SnapshotCompany> {

    public DirectorySnapshotIterator(BufferedReader reader) throws IOException {
        super(reader);
        this.nextLine = reader.readLine();
    }

    @Override
    public SnapshotCompany next() {
        String currentLine = nextLine;

        try {
            nextLine = reader.readLine();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return new SnapshotCompany(currentLine);
    }

}

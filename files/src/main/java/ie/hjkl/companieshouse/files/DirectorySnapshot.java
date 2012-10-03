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

    @Override
    public int getNumberOfRecords() {
        return Integer.parseInt(finalLine.substring(8, 16));
    }
}

class DirectorySnapshotIterator implements Iterator<SnapshotCompany> {

    private BufferedReader reader;
    private String nextLine;

    public DirectorySnapshotIterator(BufferedReader reader) throws IOException {
        this.reader = reader;

        reader.readLine();
        this.nextLine = reader.readLine();
    }

    @Override
    public boolean hasNext() {
        return nextLine != null && false == nextLine.startsWith("99999999");
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

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

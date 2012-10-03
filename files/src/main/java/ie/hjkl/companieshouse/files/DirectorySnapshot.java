package ie.hjkl.companieshouse.files;

import ie.hjkl.companieshouse.data.directory.SnapshotCompany;
import ie.hjkl.companieshouse.data.utils.DateUtils;
import org.joda.time.LocalDate;

import java.io.*;
import java.util.Iterator;

public class DirectorySnapshot implements Iterable<SnapshotCompany> {

    private String path, firstLine, finalLine;
    public static final int FINAL_LINE_LENGTH = 14 + 1;

    public DirectorySnapshot(String path) throws IOException {
        this.path = path;
        RandomAccessFile file = new RandomAccessFile(new File(path), "r");

        try {
            firstLine = file.readLine();
            file.seek(file.length() - FINAL_LINE_LENGTH);
            finalLine = file.readLine();
        } finally {
            file.close();
        }
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

    public int getRunNumber() {
        return Integer.parseInt(firstLine.substring(8, 12));
    }

    public LocalDate getProducedOn() {
        return DateUtils.parse(firstLine.substring(12, 20));
    }

    public int getNumberOfRecords() {
        return Integer.parseInt(finalLine.substring(8, 14));
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

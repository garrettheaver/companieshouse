package ie.hjkl.companieshouse.files;

import ie.hjkl.companieshouse.data.utils.DateUtils;
import org.joda.time.LocalDate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

public abstract class Snapshot {

    protected String path, firstLine, finalLine;

    public Snapshot(String path) throws IOException {
        this.path = path;
        RandomAccessFile file = new RandomAccessFile(new File(path), "r");

        try {
            readMetaDataLines(file);
        } finally {
            file.close();
        }
    }

    public int getRunNumber() {
        return Integer.parseInt(firstLine.substring(8, 12));
    }

    public LocalDate getProducedOn() {
        return DateUtils.parse(firstLine.substring(12, 20));
    }

    public int getNumberOfRecords() {
        return Integer.parseInt(finalLine.substring(8, 16));
    }

    private void readMetaDataLines(RandomAccessFile file) throws IOException {
        int seekOffset = 0;

        file.seek(seekOffset);
        firstLine = file.readLine();

        do {
            file.seek(file.length() - seekOffset++);
            finalLine = file.readLine();
        } while (finalLine == null || false == finalLine.startsWith("99999999"));
    }

}

abstract class SnapshotIterator<T> implements Iterator<T> {

    protected BufferedReader reader;
    protected String nextLine;

    public SnapshotIterator(BufferedReader reader) throws IOException {
        this.reader = reader;
        this.nextLine = reader.readLine();
    }

    @Override
    public boolean hasNext() {
        return nextLine != null && false == nextLine.startsWith("99999999");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}

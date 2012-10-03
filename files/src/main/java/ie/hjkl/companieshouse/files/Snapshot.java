package ie.hjkl.companieshouse.files;

import ie.hjkl.companieshouse.data.utils.DateUtils;
import org.joda.time.LocalDate;

import java.io.*;
import java.util.Iterator;

public abstract class Snapshot<T> implements Iterable<T> {

    protected String path, firstRecord, finalRecord;
    public static final String FINAL_RECORD_MARKER = "99999999";

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
        return Integer.parseInt(firstRecord.substring(8, 12));
    }

    public LocalDate getProducedOn() {
        return DateUtils.parse(firstRecord.substring(12, 20));
    }

    public int getNumberOfRecords() {
        return Integer.parseInt(finalRecord.substring(8, 16));
    }

    private void readMetaDataLines(RandomAccessFile file) throws IOException {
        int seekOffset = 0;

        file.seek(seekOffset);
        firstRecord = file.readLine();

        do {
            file.seek(file.length() - seekOffset++);
            finalRecord = file.readLine();
        } while (finalRecord == null || false == finalRecord.startsWith(FINAL_RECORD_MARKER));
    }
}

abstract class SnapshotIterator<T> implements Iterator<T> {

    protected BufferedReader reader;
    protected String nextRecord;

    public SnapshotIterator(BufferedReader reader) throws IOException {
        this.reader = reader;
        this.nextRecord = reader.readLine();
    }

    @Override
    public boolean hasNext() {
        return nextRecord != null && false == nextRecord.startsWith(Snapshot.FINAL_RECORD_MARKER);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

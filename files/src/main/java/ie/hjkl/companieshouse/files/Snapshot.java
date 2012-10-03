package ie.hjkl.companieshouse.files;

import ie.hjkl.companieshouse.data.utils.DateUtils;
import org.joda.time.LocalDate;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

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

    public abstract int getNumberOfRecords();

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

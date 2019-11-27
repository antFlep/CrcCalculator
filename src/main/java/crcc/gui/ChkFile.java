package crcc.gui;

import crcc.checksum.ChecksumCalculator;

import java.io.File;

public class ChkFile {
    private final File file;
    private final String fileName;
    private final String filePath;
    private String crc32 = "Not Calculated";
    private Status status = Status.UNCHECKED;

    public ChkFile(File file) {
        this.file = file;
        fileName = file.getName();
        filePath = file.getAbsolutePath();
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getCrc32() {
        return crc32;
    }

    public void setCrc32() {
        this.crc32 = ChecksumCalculator.crc32Checksum(file);
        setStatus(Status.CHECKED);
    }

    public Status getStatus() {
        return status;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

}

package crcc.gui;

import crcc.checksum.ChecksumCalculator;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChkFile {
    private File file;
    private String fileName;
    private String filePath;
    private String crc32 = null;
    private Status status = Status.UNCHECKED;

    public ChkFile(File file) {
        setFile(file);
    }

    private void setFile(File file) {
        this.file = file;
        this.fileName = file.getName();
        this.filePath = file.getAbsolutePath();
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

    public void setFileName() {
        if (status == Status.CHECKED && crc32 != null) {
            int extensionStart = fileName.lastIndexOf('.');

            String directory = file.getParentFile().getAbsolutePath();

            String start = fileName.substring(0,extensionStart);
            String middle = " - [" + crc32 + "]";
            String end = fileName.substring(extensionStart, fileName.length());
            String newFileName = start + middle + end;

            System.out.println(
                    "\nRenaming file" +
                    "\n Location: " + directory +
                    "\n Old name: " + fileName +
                    "\n New name: " + newFileName);

            File newFile = new File(directory, newFileName);
            boolean renameResult = file.renameTo(newFile);

            if (!renameResult)
                System.out.println("Error renaming file");
            else {
                setFile(newFile);
                setStatus(Status.OK);
            }
        }
    }

    public String getCrc32() {
        return crc32;
    }

    public void setCrc32() {
        String pattern = "[^a-fA-F0-9][a-fA-F0-9]{8}[^a-fA-F0-9]";
        Pattern crcPattern = Pattern.compile(pattern);
        Matcher crcMatcher = crcPattern.matcher(fileName);

        crc32 = ChecksumCalculator.crc32Checksum(file);
        if (fileName.contains(crc32))
            setStatus(Status.OK);
        else if (crcMatcher.find())
            setStatus(Status.BROKEN);
        else
            setStatus(Status.CHECKED);
    }

    public Status getStatus() {
        return status;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

}

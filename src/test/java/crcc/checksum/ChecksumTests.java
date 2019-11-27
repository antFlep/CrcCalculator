package crcc.checksum;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ChecksumTests {

    @Test
    public void testChecksumCalculator() {
        File testFile1 = new File("src/test/resources/testFile1.txt");
        File testFile2 = new File("src/test/resources/testFile2.txt");

        Assert.assertEquals("14795ADB", ChecksumCalculator.crc32Checksum(testFile1));
        Assert.assertEquals("C6BA6F5B", ChecksumCalculator.crc32Checksum(testFile2));
    }

}

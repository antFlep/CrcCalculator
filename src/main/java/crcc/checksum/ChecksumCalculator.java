package crcc.checksum;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

public class ChecksumCalculator {

    // ChunkSize for ByteBuffer
    private static final int BUFFER_SIZE = 1024;

    public static String crc32Checksum(File file) {
        CRC32 crc = new CRC32();

        try {
            // Read file using FileChannel
            FileInputStream fileStream = new FileInputStream(file);
            FileChannel fileChannel = fileStream.getChannel();

            while (true) {
                // read files in chunks
                ByteBuffer file_Chunk = ByteBuffer.allocate(BUFFER_SIZE);
                int read = fileChannel.read(file_Chunk);

                // reset ByteBuffer position to 0
                file_Chunk.flip();

                if (read == -1)
                    break;

                // update our checksum sum
                crc.update(file_Chunk);
            }
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long crcNum = crc.getValue();
        return Long.toHexString(crcNum).toUpperCase();
    }
}

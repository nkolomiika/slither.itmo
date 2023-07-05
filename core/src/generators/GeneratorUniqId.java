package generators;

import java.math.BigInteger;
import java.util.UUID;

import static java.lang.Long.parseLong;

public class GeneratorUniqId {

    public static long generate() {
        String generateUUIDNo = String.format("%010d", new BigInteger(UUID.randomUUID()
                .toString()
                .replace("-", ""), 16));
        long id = parseLong(generateUUIDNo.substring(generateUUIDNo.length() - 10), 10);
        return id;
    }

}

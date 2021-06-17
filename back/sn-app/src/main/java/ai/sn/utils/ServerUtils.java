package ai.sn.utils;

import static ai.sn.constants.Constants.PROFILE_IMAGES;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerUtils {


    public static Path getProfileImagesPath() {
        return Paths.get(".").resolve(PROFILE_IMAGES);
    }
}


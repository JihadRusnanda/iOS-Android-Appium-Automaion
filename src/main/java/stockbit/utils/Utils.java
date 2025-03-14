package stockbit.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static stockbit.utils.Config.TEST_DATA;
import static stockbit.utils.Config.TEST_DATA_FILE_NAME;

public class Utils {
    public static Properties ELEMENTS;

    public static final Dotenv env = Dotenv.load();

    public static void loadElementProperties(String directory) {
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        ELEMENTS = new Properties();

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].toString().contains(".properties")) {
                try {
                    ELEMENTS.load(new FileInputStream(directory + listOfFiles[i].getName()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void printError(String error) {
        throw new AssertionError(error);
    }

    public static String testData(String data) {
        Dotenv testData = Dotenv.configure().directory(TEST_DATA).filename(TEST_DATA_FILE_NAME).load();
        try {
            return Objects.requireNonNull(testData.get(data.replaceAll(" ", "_").toUpperCase()));
        } catch (NullPointerException e) {
            return data;
        }
    }

    public static String env(String config) {
        String resolveEnvVariable = env.get(config.replace(" ", "_").toUpperCase());
        if (resolveEnvVariable == null) {
            printError(String.format("Env '%s' data not found! Please check your env file.", config));
        }
        return resolveEnvVariable;
    }

    public static String envOptional(String key) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String value = dotenv.get(key.replace(" ", "_").toUpperCase());
        return Objects.requireNonNullElse(value, "");
    }
}

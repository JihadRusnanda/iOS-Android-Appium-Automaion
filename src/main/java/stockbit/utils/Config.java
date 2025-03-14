package stockbit.utils;

public class Config {

    private Config() {
        throw new UnsupportedOperationException("Utility class tidak boleh diinstansiasi");
    }

    public static final String ANDROID = "Android";
    public static final String IOS = "iOS";
    public static final String SRC_TEST_RESOURCE = System.getProperty("user.dir") + "/src/test/resources";
    public static final String ELEMENTS = SRC_TEST_RESOURCE + "/elements/";
    public static final Integer TIMEOUT = 15;
    public static final String APPIUM_LOCAL_URL = "http://127.0.0.1:4723";

    public static final String TEST_DATA = SRC_TEST_RESOURCE + "/test_data";

    public static final String TEST_DATA_FILE_NAME = ".test_data";
}

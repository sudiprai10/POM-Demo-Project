package com.hdfclife.insurance.utility;

/**
 * Utilities that help set up or interact with the browser
 */
public class BrowserUtils {
    private static final String DRIVERS_FOLDER = "browser-drivers";

    /**
     * Obtain the path to the Chrome web driver for the current operative system
     *
     * @return Path to the Chrome web driver file
     */
    public static String getChromeDriverPath() {
        String path = DRIVERS_FOLDER + "/<OS>/chromedriver";
        String osFamily = Environment.getOSFamilyName();
        String driverFolder = osFamily;

        // Special cases
        if (osFamily.equals(Environment.WINDOWS)) {
            path += ".exe";
            driverFolder += "/32bits";

        } else if (osFamily.equals(Environment.MAC)) {
            driverFolder += "/32bits";

        } else if (osFamily.equals(Environment.LINUX)) {
            driverFolder += "/" + Environment.getArchitectureBits() + "bits";

        }

        path = path.replace("<OS>", driverFolder);

        return path;
    }

    /**
     * Obtain the path to the Internet Explorer web driver
     *
     * @return Path to the IE web driver file
     */
    public static String getIEDriverPath() {
        return DRIVERS_FOLDER + "/windows/32bits/iedriver.exe";
    }
}

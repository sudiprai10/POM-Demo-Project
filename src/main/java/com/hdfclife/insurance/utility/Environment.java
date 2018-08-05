package com.hdfclife.insurance.utility;

/**
 * Extracts environment information, such as the OS and architecture
 */
public class Environment {

    // OS family names
    public final static String WINDOWS = "windows";
    public final static String LINUX = "linux";
    public final static String MAC = "mac";
    public final static String SOLARIS = "solaris";

    /**
     * Retrieves the OS family name. Not the full OS name, but one of the class constants
     *
     * @return Name of the operative system, one of these class' constants.
     */
    public static String getOSFamilyName() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.indexOf("win") >= 0) {
            return WINDOWS;
        } else if (osName.indexOf("mac") >= 0) {
            return MAC;
        } else if (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0) {
            return LINUX;
        } else if (osName.indexOf("sunos") >= 0) {
            return SOLARIS;
        } else {
            return "Unable to identify OS";
        }
    }

    /**
     * Identifies the number of bits the JVM is using
     *
     * @return 32 or 64
     */
    public static int getArchitectureBits() {
        String architecture = System.getProperty("os.arch");

        /*
         * 64-bit architecture names feature this number on the name (x64, x86_64, amd64, IA64N, ppc64)
         * and therefore the choice is made based on that assumption
         */
        if (architecture.indexOf("64") >= 0) {
            return 64;
        } else {
            return 32;
        }
    }
}

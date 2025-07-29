package com.github.rhys_h_walker.models.general_utilities;

public class FileUtilities {
    
    /**
     * Get the extension of a file from its filepath
     * @param filePath filepath as a String
     * @return The extension as a string (.mp3, .jpg, .webp, etc..)
     */
    public static String getExtension(String filePath) {
        int index = filePath.lastIndexOf(".");
        return filePath.substring(index, filePath.length());
    }

}

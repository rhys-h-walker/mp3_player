package com.github.rhys_h_walker.models.general_utilities;

import com.github.rhys_h_walker.Logger;

public class FileUtilities {
    
    /**
     * Get the extension of a file from its filepath
     * @param filePath filepath as a String
     * @return The extension as a string (.mp3, .jpg, .webp, etc..)
     */
    public static String getExtension(String filePath) {
        Logger.logdebug("Getting file extension");
        int index = filePath.lastIndexOf(".");
        return filePath.substring(index, filePath.length());
    }

}

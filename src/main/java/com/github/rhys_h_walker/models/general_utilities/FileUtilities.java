package com.github.rhys_h_walker.models.general_utilities;

public class FileUtilities {
    
    public static String getExtension(String filePath) {
        int index = filePath.lastIndexOf(".");
        return filePath.substring(index, filePath.length());
    }

}

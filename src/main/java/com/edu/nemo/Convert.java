package com.edu.nemo;

import java.io.File;

public class Convert {
    public static void main(String[] args) throws Exception{
        Convert convert = new Convert();
        File file = new File("/home/nemo/Desktop/1.Original");
        Utils.listFilesForFolder(file);
    }
}

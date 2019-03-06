package com.edu.nemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.MessageFormat;

public class Utils {

    private static final String EXTENDED_FILE = "mp3";
    private static final String IGNORE_MAC_OS = "__MACOSX";
    private static final String PATH_IMAGE_DROP = "/home/nemo/Desktop/rock-over-sky.jpg";
    private static final String TEMPLATE_COMMAND = "ffmpeg -loop 1 -framerate 2 -i \"{0}\" -i \"{1}\" -c:v libx264 -preset slower -tune stillimage -crf 18 -c:a copy -shortest -pix_fmt yuv420p \"{2}.mkv\"";

    public static String stripExtension (String str) {
        // Handle null case specially.
        if (str == null) return null;

        // Get position of last '.'.
        int pos = str.lastIndexOf(".");

        // If there wasn't any '.' just return the string as is.
        if (pos == -1) return str;

        // Otherwise return the string, up to the dot.
        return str.substring(0, pos);
    }

    public static void listFilesForFolder(final File folder) throws Exception{
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if(!fileEntry.getPath().contains(IGNORE_MAC_OS)) {
                    if (fileEntry.getName().endsWith(EXTENDED_FILE) || fileEntry.getName().endsWith(EXTENDED_FILE.toUpperCase())) {
                        convertVideo(fileEntry.getPath());
                    }
                }
            }
        }
    }

    public static void exeCmd(String command) throws Exception{
        ProcessBuilder builder = new ProcessBuilder("bash", "-c", command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }

    public static void convertVideo(String pathFile) throws Exception{
        if(pathFile.endsWith(EXTENDED_FILE) || pathFile.endsWith(EXTENDED_FILE.toUpperCase())){
            String command = MessageFormat.format(TEMPLATE_COMMAND, PATH_IMAGE_DROP, pathFile, stripExtension(pathFile));
            System.out.println(command);
//            exeCmd(command);
        }
    }
}

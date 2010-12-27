package org.cvut.MMN.view.dialog;

import java.io.File;

/**
 *
 * @author matej
 */
public class Validator {

    public static boolean isEmpty(String input) {
        return (input == null) || (input.trim().isEmpty());
    }

    public static boolean fileExists(String path) {
        File f = new File(path);
        return f.isFile();
    }
}

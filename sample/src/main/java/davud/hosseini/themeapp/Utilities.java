package davud.hosseini.themeapp;

import android.content.res.ColorStateList;

import androidx.annotation.ColorInt;
import androidx.databinding.BindingConversion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import davud.hosseini.themeapp.theme.Theme;

/**
 * Created by Davud. ThemeApp project.
 */
public class Utilities {

    private final static Pattern pattern = Pattern.compile("[\\-0-9]+");

    public static int parseInt(String value) {
        if (value == null) {
            return 0;
        }
        int val = 0;
        try {
            Matcher matcher = pattern.matcher(value);
            if (matcher.find()) {
                String num = matcher.group(0);
                val = Integer.parseInt(num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    public static String getAssetsDir() {
        return ApplicationLoader.applicationContext.getFilesDir().getPath();
    }


    public static void copyFile(InputStream sourceFile, File destFile) throws IOException {
        OutputStream out = new FileOutputStream(destFile);
        byte[] buf = new byte[4096];
        int len;
        while ((len = sourceFile.read(buf)) > 0) {
            Thread.yield();
            out.write(buf, 0, len);
        }
        out.close();
    }

    @BindingConversion
    @ColorInt
    public static int stringToColorInt(String colorKey) {

        return Theme.getColor(colorKey);
    }

    @BindingConversion
    public static ColorStateList stringToColorStateList(String colorKey) {

        return ColorStateList.valueOf(Theme.getColor(colorKey));
    }
}

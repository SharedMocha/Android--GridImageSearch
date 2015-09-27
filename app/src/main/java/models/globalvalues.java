package models;

/**
 * Created by htammare on 9/26/2015.
 */
public class globalvalues {
    private static int Offset;
    private static String URL;

    public static String getURL() {
        return URL;
    }
    public static int getOffset() {
        return Offset;
    }

    public static int setOffset(int offset)
    {
        Offset = offset;
        return Offset;
    }
    public static String setURL(String url) {
        URL = url;
        return URL;
    }
}

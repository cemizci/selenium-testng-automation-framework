package utilities;

import java.util.regex.Pattern;

public final class ContentSanity {

    private ContentSanity(){}

    private static final Pattern METHOD_CALL_LIKE = Pattern.compile("\\b[A-Za-z_][A-Za-z0-9_]*\\s*\\([^)]*\\)");

    public static boolean looksLikeCode(String text){
        if (text == null) return false;
        String t = text.trim();
        if (t.isEmpty()) return false;
        return METHOD_CALL_LIKE.matcher(t).find();
    }
}

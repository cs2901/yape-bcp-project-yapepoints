package YapeCoupons.helpers;

public class helper {
    public static String getRandomCode (int length) {
        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (length-- > 0) {
            int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static String getExtension (String file_name) {
        String extension = "";
        int i = file_name.lastIndexOf('.');
        int p = Math.max(file_name.lastIndexOf('/'), file_name.lastIndexOf('\\'));
        if (i > p) {
            extension = file_name.substring(i + 1);
        }
        return extension;
    }

}

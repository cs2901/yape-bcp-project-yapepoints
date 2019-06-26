package YapeCoupons.mail;

public class Mail {

    public static String PasswordRectification(String userMail ) throws Exception {
        String code = getRandomCode(6);
        YapeCoupons.MailUtil.sendMail(userMail, "<h1> ¡Hola Yapero! </h1> <h2>Cambio de contraseña</h2> Su código es " + code + ". Conozca más de nosotros <a href=\"linker.zone/rVJRS\">aquí.</a>");
        return code;
    }

    public static String getRandomCode (int length) {
        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while (length-- > 0) {
            int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}

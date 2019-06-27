package YapeCoupons.mail;

import static YapeCoupons.helpers.helper.getRandomCode;

public class Mail {

    public static String PasswordRectification(String userMail ) throws Exception {
        String code = getRandomCode(6);
        YapeCoupons.MailUtil.sendMail(userMail, "<h1> ¡Hola Yapero! </h1> <h2>Cambio de contraseña</h2> Su código es " + code + ". Conozca más de nosotros <a href=\"linker.zone/rVJRS\">aquí.</a>");
        return code;
    }

}

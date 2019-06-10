package YapeCoupons;


public class Mail{
    public static void PasswordRectification(String userMail ) throws Exception {
        YapeCoupons.MailUtil.sendMail(userMail, "<h1> ¡Hola Yapero! </h1> <h2>Cambio de contraseña</h2> Su solicitud esta siendo procesada. Conozca más de nosotros <a href=\"linker.zone/rVJRS\">aquí.</a>");
    }
}

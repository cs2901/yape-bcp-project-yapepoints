package YapeCoupons.middleware;

import javax.servlet.http.HttpServletRequest;

public class Middleware {

    static public boolean isLogged (HttpServletRequest request) {
        String given_name = (String) request.getSession().getAttribute("given_name");
        return given_name != null;
    }

}

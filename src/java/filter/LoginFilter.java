package filter;

import entity.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();

        User k = (User) req.getSession().getAttribute("valid_user");

        if (k == null) {
            if (url.contains("logout")||url.contains("Admin") ) {
                res.sendRedirect(req.getContextPath() + "/index.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if (k.getAdmin() == 0) {
                if (url.contains("Admin")) {
                    res.sendRedirect(req.getContextPath() + "/front/front.xhtml");
                } else if (url.contains("logout")) {
                    req.getSession().invalidate();
                    res.sendRedirect(req.getContextPath() + "/index.xhtml");
                } else {
                    chain.doFilter(request, response);
                }
            }else{
                if (url.contains("register") || url.contains("login")) {
                    res.sendRedirect(req.getContextPath() + "/front/front.xhtml");
                } else if (url.contains("logout")) {
                    req.getSession().invalidate();
                    res.sendRedirect(req.getContextPath() + "/index.xhtml");
                } else {
                    chain.doFilter(request, response);
                }
            }

        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}

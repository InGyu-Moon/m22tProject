package m22t.ansdlsrb.m22tProject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
@Slf4j
public class LoginCheckFilter implements Filter {

    //접근 가능한 주소 설정
    private static final String[] whitelist = {"/", "/members/new", "/login", "/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        try{
            if(isLoginCheckPath(requestURI)){
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute("memberId") == null){
                    //미인증 사용자
                    //httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    httpResponse.sendRedirect("/login");
                    return;
                }
            }
            chain.doFilter(request,response);
        } catch (Exception e){
            throw e;
        }

    }

    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }


}

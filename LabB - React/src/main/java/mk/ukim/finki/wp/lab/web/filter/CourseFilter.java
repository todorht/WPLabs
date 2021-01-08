package mk.ukim.finki.wp.lab.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class CourseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        String courseId = (String) request.getSession().getAttribute("courseId");
        if(("/AddStudent".equals(path) || "/CreateStudent".equals(path) || "/StudentEnrollmentSummary".equals(path)) && courseId==null ){
            response.sendRedirect("/courses?error=PleaseSelectCourse");
        }else if("/".equals(path)) {
            response.sendRedirect("/courses");
        }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
    }

}



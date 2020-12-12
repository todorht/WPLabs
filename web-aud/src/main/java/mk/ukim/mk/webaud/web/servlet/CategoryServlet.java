package mk.ukim.mk.webaud.web.servlet;

import mk.ukim.mk.webaud.model.Category;
import mk.ukim.mk.webaud.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "category-servlet", urlPatterns = "/servlet/category")
public class CategoryServlet  extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");

        PrintWriter out = resp.getWriter();
        out.println("<html><head></head>");
        out.println("<h3>User info</h3>");
        out.format("IP address: %s</br>",ipAddress);
        out.format("Client agent: %s",clientAgent);
        out.println("<body><h3>Category list</h3>");
        out.println("<ul>");
        categoryService.listCategories().forEach(r->
                out.format("<li>%s (%s)</li>",r.getName(),r.getDescription()));
        out.println("</ul>");
        out.println("<h3>Add category</h3>");
        out.println("<form method='POST' action='/servlet/category'>");
        out.println("<label for='name'>Name:</label>");
        out.println("<input id='name' type='text' name='name'/>");
        out.println("<label for='desc'>Description:</label>");
        out.println("<input id='desc' type='text' name='desc'/>");
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String categoryName = req.getParameter("name");
        String categoryDesc = req.getParameter("desc");
        categoryService.create(categoryName, categoryDesc);
        resp.sendRedirect("/servlet/category");

    }
}

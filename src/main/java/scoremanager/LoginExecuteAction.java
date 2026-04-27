package scoremanager;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {
    public String execute(
            HttpServletRequest request, HttpServletResponse response
        ) throws Exception {

            HttpSession session = request.getSession();

            String login = request.getParameter("id");
            System.out.println(login);
            String password = request.getParameter("password");
            TeacherDao dao = new TeacherDao();
            // id と パスワードを照合
            Teacher customer = dao.search(login, password);

            // 検索にヒットしたらログイン
            if (customer != null) {
                //session.setAttribute("customer", customer);
                System.out.println("ログイン成功：" + customer.getName());
                customer.setAuthenticated(true);
                System.out.println(customer.isAuthenticated());
                session.setAttribute("user", customer);
                session.setAttribute("teacher", customer);
                return "./main/menu.jsp";

            }

            System.out.println("ログイン失敗");
            System.out.println(login + ":" + password);
            request.setAttribute("notlogin", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
            return "login.jsp";
        }

}

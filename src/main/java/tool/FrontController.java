package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(urlPatterns = {"*.action"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            // パスを取得
            String path = req.getServletPath().substring(1);
            // ファイル名を基にしたクラス名を取得
            String name = path.replace(".action", "").replace("/", ".");
            name = name + "Action";  // ★ これを追加


            System.out.println("★ servlet path → " + req.getServletPath());
            System.out.println("★ class name → " + name);

            // アクションクラスのインスタンスを生成
            Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();

            String url = action.execute(req, res);
            
            // String url = action.execute(req, res);
            // req.getRequestDispatcher(url).forward(req, res);
            req.getRequestDispatcher(url).forward(req, res);
        } catch (Exception e) {
        	System.out.println(e);
            e.printStackTrace();
            // エラーページへリダイレクト
            req.setAttribute("errors", e);
            req.getRequestDispatcher("/scoremanager/main/TestRegist.action").forward(req, res);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
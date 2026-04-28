package scoremanager.main;
 
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
 
public class SubjectDeleteExecuteAction extends Action {
    @Override
    public String execute(
            HttpServletRequest request,HttpServletResponse response
    ) throws Exception {
        //リクエストパラメータから科目コードを取得
        String cd = request.getParameter("cd");
        //DAOを使って削除処理を実行
        SubjectDao dao = new SubjectDao();
        boolean result = dao.delete(cd);
        // 削除結果で画面分岐
        if (!result) {
            // 削除失敗時
            request.setAttribute("error", "科目の削除に失敗しました。");
            return "subject_delete.jsp";

        }
        //削除完了画面へ
        return "subject_delete_done.jsp";

    }

}
 
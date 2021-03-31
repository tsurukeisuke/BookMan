import dao.UserShelfDAO;
import model.UserShelf;

public class singleTest {

    public static void main(String[] args) {
//        SearchBooksForApiLogic api = null;
//        try {
//            api = new SearchBooksForApiLogic("","Java");
//        } catch (ServletException e) {
//            // TODO 自動生成された catch ブロック
//            e.printStackTrace();
//        }
//        List<BookInfo> list = null;
//        try {
//            list = api.execute();
//        } catch (IOException e) {
//            // TODO 自動生成された catch ブロック
//            e.printStackTrace();
//        }
//        BookInfoDAO bDao = new BookInfoDAO();
//        for(BookInfo book: list) {
//            bDao.insertBookInfo(book);
//            System.out.println(book.getTitle());
//        }
//        AccountDAO dao = new AccountDAO();
//        List<Account> list =  dao.findAll();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        sdf.format(list.get(0).getBirthDay());
//        System.out.print(sdf.format(list.get(0).getBirthDay()));
//        Account act = list.get(0);
//        act.setGender(1);
//        dao.updateAccount(act, "aaa");
        UserShelfDAO usDao = new UserShelfDAO();
        UserShelf userShelf = usDao.searchUserShelfForUserIdAndIsbn("ああああ", "KoFZvgAACAAJ").get(0);
        System.out.println(userShelf.getUserId());
        userShelf.setComment("aaaa");
        System.out.println(usDao.updateUserShelf(userShelf) + "件更新");
    }

}

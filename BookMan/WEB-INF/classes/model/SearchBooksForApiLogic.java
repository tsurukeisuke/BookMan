package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchBooksForApiLogic extends HttpServlet {
    String GOOGLE_BOOKS_API_ISBN = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    String GOOGLE_BOOKS_API_TITLE = "https://www.googleapis.com/books/v1/volumes?q=intitle:";

    String J601_HTTPS_PROXY_ADDRESS = "172.16.61.20";
    String J601_HTTPS_PROXY_PORT = "3128";

    String mIsbn = null;
    String mTitle = null;

    public SearchBooksForApiLogic(String isbn, String title) throws ServletException {
        // proxyの設定
        System.setProperty("https.proxyHost", J601_HTTPS_PROXY_ADDRESS);
        System.setProperty("https.proxyPort", J601_HTTPS_PROXY_PORT);

        mIsbn = isbn;
        mTitle = title;
    }

    public List<BookInfo> execute() throws IOException {
        // gooleへ接続するため
        URL url = null;
        HttpURLConnection con = null;
        String requestUrl = null;

        // 検索結果データ格納用
        List<BookInfo> list;

        // isbnと書名の取得

        // ISBNが入力されていたらISBNで検索、入力されていなかったら書名等で検索
        if (mIsbn.equals("")) {
            // 書名等で検索
            //requestUrl = GOOGLE_BOOKS_API_TITLE + mTitle;
            requestUrl = GOOGLE_BOOKS_API_TITLE + URLEncoder.encode(mTitle, "UTF-8");
            System.out.println(requestUrl);

        } else {
            // ISBNで検索
            requestUrl = GOOGLE_BOOKS_API_ISBN + mIsbn;

        }

        // Google Books APIへの接続
        try {
            // URLConnectionの作成
            url = new URL(requestUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");// GETリクエスト
            con.setReadTimeout(10000); // 10秒
            con.setConnectTimeout(10000);// 10秒
        } catch (Exception e) {
            return null;
        }

        // レスポンスコードの確認
        int responseCode;

        responseCode = con.getResponseCode();

        if (responseCode != HttpURLConnection.HTTP_OK) {
            // 接続を切断する
            con.disconnect();

            // レスポンスコードが200以外の場合は、error.jspへフォワードする
            return null;
        }

        // 検索結果データ(レスポンス)の取得
        // ストリーム作成用
        BufferedReader responseReader = null;
        InputStreamReader isr = null;

        // レスポンス全データ取得用
        StringBuilder builder = new StringBuilder();

        // レスポンスを取得するためのストリームの作成
        isr = new InputStreamReader(con.getInputStream(), "UTF-8");
        responseReader = new BufferedReader(isr);

        // レスポンスデータを1行取得する
        String line = responseReader.readLine();

        // nullになるまでデータを１行取り出し、builderへ追加する
        while (line != null) {
            builder.append(line);
            line = responseReader.readLine();
        }

        // 取得したデータを文字列へ変換する
        String responseString = builder.toString();

        // 接続を切断する
        con.disconnect();

        // JSONオブジェクト作成用
        JSONObject jsonObject = null;

        try {
            // 取得した文字列からJSONオブジェクトを作成
            jsonObject = new JSONObject(responseString);

            // 検索データ数 totalItemsを検索結果数としています
            // 実際に検索して得られるデータは最大10個のようです
            int count = jsonObject.getInt("totalItems");

            // 検索結果0の場合、no_result.jspへフォワードする
            if (count == 0) {
                return null;
            }

            // JSON配列itemsの取得
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            // 検索結果データの格納
            list = new ArrayList<>();

            // 実際に得られるデータ数
            count = jsonArray.length();

            //
            for (int i = 0; i < count; i++) {
                // 各検索結果
                JSONObject item = jsonArray.getJSONObject(i);

                // volumeInfoに関するデータの取得
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");

                // idの取得
                String googleId = item.getString("id");

                // titleの取得
                String booktitle = volumeInfo.getString("title");

                // authorsの取得
                JSONArray authors = null;
                String firstAuthor = null;
                try {
                    authors = volumeInfo.getJSONArray("authors");
                    firstAuthor = authors.getString(0);
                } catch (JSONException e) {
                    firstAuthor = "未登録";
                }

                // publishedDateの取得
                String publishedDateStr = null;
                SimpleDateFormat sdf = null;
                try {
                    publishedDateStr = volumeInfo.getString("publishedDate");
                } catch (JSONException e) {
                    publishedDateStr = "未登録";
                }
                if(publishedDateStr.length() == 10) {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                } else if(publishedDateStr.length() == 7) {
                    sdf = new SimpleDateFormat("yyyy-MM");
                } else if(publishedDateStr.length() == 4) {
                    sdf = new SimpleDateFormat("yyyy");
                } else {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    publishedDateStr = "0001-01-01";
                }
                Date publishDate = sdf.parse(publishedDateStr);

                // publisherの取得
                String publisher = null;
                try {
                    publisher = volumeInfo.getString("publisher");

                } catch (JSONException e) {
                    publisher = "未登録";
                }

                // descriptionの取得
                String description = null;
                try {
                    description = volumeInfo.getString("description");

                } catch (JSONException e) {
                    description = "未登録";
                }

                // thumnailの取得
                String thumnail = null;
                String smallThumnail = null;
                try {
                    JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                    thumnail = imageLinks.getString("thumbnail");

                    // small thumnailの取得
                    smallThumnail = imageLinks.getString("smallThumbnail");

                }catch(JSONException e) {
                    thumnail = "";
                    smallThumnail = "";
                }

                // 検索結果データの追加
                list.add(new BookInfo(googleId, booktitle, 0, firstAuthor, publisher, publishDate, description, thumnail,
                        smallThumnail));
                //System.out.println(thumnail);
            }

        } catch (Exception e) {
            // 例外発生時、error.jspへフォワードする
            return null;
        }

        return list;
    }
}

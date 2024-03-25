package JsoupParse240325;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class DaumNewsTodaySeries {

    public static void main(String [] args) {

        //문제 1)jsoup을 사용하여 다음 뉴스의 오늘의 연재 부분을 파싱하여 화면에 출력하는 프로그램을 작성하세요
        //출력 형태 = 기사 제목, 기사 링크 2가지 내용을 출력하세요
        //실행 순서
        System.out.println("\n다음 뉴스의 오늘의 연재 출력하기\n");

        // 1.url 설정
        String url = "https://news.daum.net/?nil_profile=mini&nil_src=news";

        // 2.Document 객체 생성
        Document html = null;

        try {
            // 3.Connection.Response 객체 생성 및 Jsoup.conect()로 지정한 url에 접속
            Connection.Response res = Jsoup.connect(url).method(Connection.Method.GET).execute();
            // 4. 받아온 데이터를 Document 객체로 변환
            html = res.parse();

        } catch (IOException e) {
            System.out.println("Jsoup로 데이터 파싱 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        // 5. 가져올 데이터가 있는 태그 중 가장 가까운 조상 태그 가져오기
        Element list_todayseries = html.select(".list_todayseries").first();
        // 6. select()를 사용하여 원하는 태그 가져오기
        Elements link_txt = list_todayseries.select(".link_txt");

        for (int i = 0; i<link_txt.size(); i++) {
            Element item = link_txt.get(i);

            // 7. 마지막에 선택한 태그에서 기사 제목 및 기사 링크 가져오기
            Element todayAtag = item.select(".link_txt").first();
            String todayTitle = todayAtag.text();
            String todayLink = todayAtag.attr("href");
            System.out.println("기사 제목 :" + todayTitle);
            System.out.println("기사 링크 :" + todayLink);
            System.out.println("----------------\n");
        }





    }
}

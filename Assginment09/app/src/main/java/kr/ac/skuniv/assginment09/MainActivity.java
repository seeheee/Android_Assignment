package kr.ac.skuniv.assginment09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.data);

        String serviceUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
        String serviceKey = "uKyXwIs%2BR39F84YLNcFBPA7ilIBZCdN%2F7o9ZBuTrzsHPZOaAT3S8anDd20UuSo1250fl1YUZ08AauZ2F%2BG9bxA%3D%3D";
        String sidoName = "서울";
        String pageNo = "1";
        String numOfRows = "10";
        String ver = "1.3";
        String strUrl = serviceUrl+"?sidoName="+sidoName+"&ServiceKey="+serviceKey+"&pageNo="+pageNo+"&ver="+ver+"&numOfRows="+numOfRows;

        new DownloadWebpageTask().execute(strUrl);
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String)downloadUrl((String)urls[0]);
            } catch (IOException e) {
                return e.getMessage(); }}

        protected void onPostExecute(String result) {
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                String stationName = "";
                String pm10Value = "";
                String resultCode = "";
                boolean Set_stationName = false;
                boolean Set_pm10Value = false;
                boolean Set_resultCode =false;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if (eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("resultCode"))
                            Set_resultCode = true;
                        if (tag_name.equals("stationName"))
                            Set_stationName = true;
                        if (tag_name.equals("pm10Value"))
                            Set_pm10Value = true;
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (Set_resultCode) {
                            resultCode = xpp.getText();
                            tv.append("resultCode: " + resultCode + "\n");
                            Set_resultCode = false;
                        }
                        if (Set_stationName) {
                            stationName = xpp.getText();
                            tv.append("stationName: " + stationName + "\n");
                            Set_stationName = false;
                        }
                            if (Set_pm10Value) {
                                pm10Value = xpp.getText();
                                tv.append("pm10Value: " + pm10Value + "\n");
                                Set_pm10Value = false;
                            }
                    } else if (eventType == XmlPullParser.END_TAG) {
                            ;
                        }
                        eventType = xpp.next();
                    }
            }catch(Exception e){
                        tv.setText(e.getMessage());
            }
        }

        private String downloadUrl(String myurl) throws IOException {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while ((line = bufferedReader.readLine()) != null) {
                    page += line;
                }
                return page;
            } finally {
                conn.disconnect();
            }
        }
    }
}
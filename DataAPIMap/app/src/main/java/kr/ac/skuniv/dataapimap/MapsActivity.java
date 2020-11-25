package kr.ac.skuniv.dataapimap;

import androidx.fragment.app.FragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String serviceUrl = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureLIst";
        String serviceKey = "uKyXwIs%2BR39F84YLNcFBPA7ilIBZCdN%2F7o9ZBuTrzsHPZOaAT3S8anDd20UuSo1250fl1YUZ08AauZ2F%2BG9bxA%3D%3D";
        String itemCode = "PM10";
        String pageNo = "1";
        String numOfRows = "2";
        String dataGubun = "DAILY";
        String searchCondition = "MONTH";
        String strUrl = serviceUrl+"?itemCode="+itemCode+"&ServiceKey="+serviceKey+"&pageNo="+pageNo+"&dataGubun="+dataGubun+"&numOfRows="+numOfRows+"&searchCondition="+searchCondition;

        DownloadWebpageTask1 task1 = new DownloadWebpageTask1();
        task1.execute(strUrl);
    }
    private class DownloadWebpageTask1 extends AsyncTask<String, Void, String> {

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

                String seoul = "";
                String busan = "";

                boolean Set_seoul = false;
                boolean Set_busan = false;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if (eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("seoul"))
                            Set_seoul = true;
                        if (tag_name.equals("busan"))
                            Set_busan = true;
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (Set_seoul) {
                            seoul = xpp.getText();
                            Set_seoul = false;
                            displayseoul(seoul);
                        }
                        if (Set_busan) {
                                busan = xpp.getText();
                                Set_busan = false;
                                displaybusan(busan);
                        }

                    } else if (eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }
            }catch(Exception e){
            }
        }
        private void displayseoul(String seoul){
            LatLng latLng_seoul = new LatLng(37.56120720079536, 126.98267466165002);
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng_seoul)
                    .title(seoul)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng_seoul));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(7));
        }

        private void displaybusan(String busan){
            LatLng latLng_busan = new LatLng(35.16207942523138, 129.04670441285063);
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng_busan)
                    .title(busan)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng_busan));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(7));
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
package zz.itcast.mybaidumap;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;
    private BaiduMap baiduMap;

    private LocationClient mLocationClient;
    private Double mLatitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.bmapView);

        initMapType();
        initLocation();//初始化定位
    }

    private void initLocation() {
        mLocationClient=new LocationClient(this);
        myLocationListener=new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);

        LocationClientOption option=new LocationClientOption();
        option.setCoorType("bd0911");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //TODO inflate 填充菜单
        getMenuInflater().inflate(R.menu.main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_map_normal:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.id_map_satelite:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.id_map_traffic:
                if(baiduMap.isTrafficEnabled()) {

                    baiduMap.setTrafficEnabled(false);
                    item.setTitle("实时交通（off");
                }else{
                    baiduMap.setTrafficEnabled(true);
                    item.setTitle("实时交通（on）");
                }
                break;
            case R.id.id_map_location:
                moveToCurrentLocation();
                break;
        }
         return true;
    }
    private MyLocationListener myLocationListener;
    private boolean isFirstIn=true;
    class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData data=new MyLocationData.Builder()//
            .accuracy(bdLocation.getRadius())//
            .latitude(bdLocation.getLatitude())//
            .longitude(bdLocation.getLongitude())//
            .build();
            baiduMap.setMyLocationData(data);
            mLatitude = bdLocation.getLatitude();
            longitude = bdLocation.getLongitude();
            if(isFirstIn){
                moveToCurrentLocation();
                isFirstIn=false;
            }
        }
    }

    /**
     * 定位到当前位置
     */
    private void moveToCurrentLocation() {
        LatLng latlng=new LatLng(mLatitude, longitude);
        MapStatusUpdate msu= MapStatusUpdateFactory.newLatLng(latlng);
        baiduMap.animateMapStatus(msu);
    }

    private void initMapType() {

        baiduMap = mapView.getMap();
        MapStatusUpdate msu= MapStatusUpdateFactory.zoomTo(15.0f);
        baiduMap.setMapStatus(msu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        baiduMap.setMyLocationEnabled(true);
        if(!mLocationClient.isStarted()){
        mLocationClient.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        baiduMap.setMyLocationEnabled(false);
        if(mLocationClient.isStarted()){
        mLocationClient.stop();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
       mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    mapView.onPause();
    }
}

package zz.itcast.mybaidumap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class FourFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView tv=new TextView(getActivity());
        tv.setText("邮箱");
        tv.setTextSize(50);
        return tv;
    }
}

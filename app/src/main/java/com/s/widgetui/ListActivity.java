package com.s.widgetui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张垚杰 on 2018/2/11.
 */

public class ListActivity extends android.app.ListActivity{
    private static final String TAG = "ListActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String path = intent.getStringExtra("com.s.widgetui.Path");
        if(path == null){
            path = "";
        }
        Log.d(TAG, "onCreate path == "+path);

        setListAdapter(new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[] {"title"},
                new int[] {android.R.id.text1}));
        getListView().setTextFilterEnabled(true);

    }

    protected List<Map<String, Object>> getData(String prefix){

        List<Map<String, Object>> myData = new ArrayList<>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if(null == list){
            return myData;
        }

        String[] prefixPath;
        String prefixWithSlash = prefix;

        if(prefix.equals("")){
            prefixPath = null;
        }else {
            prefixPath = prefix.split("/");
            prefixWithSlash = prefix + "/";
        }

        int len = list.size();
        Map<String, Boolean> entries = new HashMap<>();

        for(int i = 0; i < len; i++){
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null ?
                    labelSeq.toString() : info.activityInfo.name;

            if(prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)){

                String[] labelPath = label.split("/");
                String nextLabel = prefixPath == null ?
                        labelPath[0] : labelPath[prefixPath.length];
                if((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1){

                    addItem(myData, nextLabel, activityIntent(
                            info.activityInfo.applicationInfo.packageName,
                            info.activityInfo.name));
                }else{
                    if(entries.get(nextLabel) == null){
                        addItem(myData, nextLabel, browseIntent(
                                prefix.equals("") ?
                                        nextLabel : prefix + "/" +nextLabel));
                        entries.put(nextLabel, true);
                    }
                }
            }
        }

        Collections.sort(myData, sDisplayNameComparator);
        return myData;
    }

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>(){

                private final Collator collator = Collator.getInstance();
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    return collator.compare(o1.get("title"), o2.get("title"));
                }
            };

    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }

    protected Intent browseIntent(String path){
        Intent result = new Intent();
        result.setClass(this, ListActivity.class);
        result.putExtra("com.s.widgetui.Path", path);
        return result;
    }

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent){
        Map<String, Object> temp = new HashMap<>();
        Log.d(TAG, String.format("name=%s, intent=%s ",name, intent));
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>)l.getItemAtPosition(position);

        Intent intent = new Intent((Intent) map.get("intent"));
        intent.addCategory(Intent.CATEGORY_SAMPLE_CODE);
        startActivity(intent);
    }
}

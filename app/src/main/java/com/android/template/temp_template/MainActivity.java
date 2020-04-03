package com.functionframework.demo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.template.temp_template.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));
        recyclerView.setAdapter(new TemplateFunctionAdapter(getTemplateFunctionData(), new TemplateFunctionAdapter.OnSimpleItemClickListener() {
            @Override
            public void onItemClick(Intent intent) {
                startActivity(intent);
            }
        }));
    }

    protected List<Map<String, Object>> getTemplateFunctionData() {
        List<Map<String, Object>> intentList = new ArrayList<>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory("android.intent.category.TEMPLATE");

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);
        if (null == list) return intentList;
        int len = list.size();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null ? labelSeq.toString() : info.activityInfo.name;

            String packageName = info.activityInfo.applicationInfo.packageName;
            if(packageName.equals(getPackageName())) {
                Map<String, Object> temp = new HashMap<>();
                temp.put("title", label);
                temp.put("intent", createActivityIntent(packageName, info.activityInfo.name));
                intentList.add(temp);
            }
        }

        return intentList;
    }

    protected Intent createActivityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }

    static class TemplateFunctionAdapter extends RecyclerView.Adapter<VH> {

        private interface OnSimpleItemClickListener {
            void onItemClick(Intent intent);
        }

        private OnSimpleItemClickListener mClickListener;

        private List<Map<String, Object>> mDataList = new ArrayList<>();

        TemplateFunctionAdapter(List<Map<String, Object>> dataList, OnSimpleItemClickListener clickListener) {
            mDataList = dataList;
            mClickListener = clickListener;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View itemView = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            return new VH(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull VH vh, int position) {
            final Map<String, Object> dataMap = mDataList.get(position);
            vh.titleView.setText((CharSequence) dataMap.get("title"));

            if (mClickListener != null) {
                vh.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onItemClick((Intent) dataMap.get("intent"));
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    }


    static class VH extends RecyclerView.ViewHolder {

        private TextView titleView;

        VH(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(android.R.id.text1);
        }
    }
}

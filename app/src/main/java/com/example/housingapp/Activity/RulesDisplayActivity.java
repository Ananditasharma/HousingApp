package com.example.housingapp.Activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.housingapp.R;

public class RulesDisplayActivity extends Activity implements View.OnClickListener {
    // Array of strings...
    String[] RulesArray = {"1.Every apartment owner is obliged to be a member of the apartment association which is formed for the welfare of the people. ",
            "2. If it is a joint ownership, the first person named in the title document will be the member.",
            "3. A resident should not be imposed with unlawful restrictions.",
            "4. A resident has the right to get attended or addressed about his complaints and grievances.",
            "5. A resident has the right to participate in all activities of the society.",
            "6. A resident has the right to attend and vote at general body meeting.",
            "7. A resident has the right to contest election.",
            "8. A resident has the right to seek information from the society."};
LinearLayout llBack1;
TextView tvtittle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_list);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, RulesArray);
         setToolBar();
        ListView listView = (ListView) findViewById(R.id.rule_list);
        listView.setAdapter(adapter);
    }
    private void setToolBar(){
        llBack1 = findViewById(R.id.llBack1);
        llBack1.setOnClickListener(this);
        tvtittle = findViewById(R.id.tvTitle);
        tvtittle.setText("Rules and Regulations");
    }
    @Override
    public void onClick(View v) {
        if(v== llBack1){
            onBackPressed();
        }
    }
}
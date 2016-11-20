package id.sch.smktelkom_mlg.project.xirpl105142332.dolanjatim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class DaerahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata);

        String post_key = getIntent().getExtras().getString("wisata_id");

        Toast.makeText(DaerahActivity.this, post_key, Toast.LENGTH_SHORT).show();
    }
}

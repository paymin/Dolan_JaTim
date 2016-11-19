package id.sch.smktelkom_mlg.project.xirpl105142332.dolanjatim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListKotaActivity extends AppCompatActivity implements ItemAddedHandler {

    DatabaseReference Ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference Refa = Ref.child("tb_list/nam_kota");

    RecyclerView recyclerView;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kota);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        image = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<String, MViewHolder> adapter = new FirebaseRecyclerAdapter<String, MViewHolder>(String.class, android.R.layout.two_line_list_item, MViewHolder.class, Refa) {
            @Override
            protected void populateViewHolder(MViewHolder viewHolder, String s, int position) {

                viewHolder.mTxet.setText(s);
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public static class MViewHolder extends RecyclerView.ViewHolder {
        TextView mTxet;

        public MViewHolder(View v) {
            super(v);
            mTxet = (TextView) v.findViewById(android.R.id.text1);

        }
    }
}

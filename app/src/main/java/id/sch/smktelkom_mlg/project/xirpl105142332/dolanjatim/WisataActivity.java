package id.sch.smktelkom_mlg.project.xirpl105142332.dolanjatim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class WisataActivity extends AppCompatActivity {

    //DatabaseReference Ref = FirebaseDatabase.getInstance().getReference().child("tb_kota");

    //DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("tb_kota/Kabupaten Ponorogo/deskripsi");
    //DatabaseReference mConditionRef = mRootRef.child("tb_kota/Kabupaten Pacitan/judul");
    //StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    //StorageReference riversRef = storageRef.child("images/rivers.jpg");
    private String mPost_key = null;
    private DatabaseReference mDatabase;
    private ImageView mBlogSingleImage;
    private TextView mBlogSingleTitle;
    private TextView mBlogSingleDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_wisata);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("tb_kota");
        mPost_key = getIntent().getExtras().getString("wisata_id");

        mBlogSingleDesc = (TextView) findViewById(R.id.place_detail_wisata);
        mBlogSingleImage = (ImageView) findViewById(R.id.imageFoto_wisata);

        mDatabase.child(mPost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String post_title = (String) dataSnapshot.child("judul").getValue();
                String post_desc = (String) dataSnapshot.child("deskripsi").getValue();
                String post_image = (String) dataSnapshot.child("logo").getValue();

                setTitle(post_title);
                mBlogSingleDesc.setText(post_desc);

                Picasso.with(WisataActivity.this).load(post_image).into(mBlogSingleImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
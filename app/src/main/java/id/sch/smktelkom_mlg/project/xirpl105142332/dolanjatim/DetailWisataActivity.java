package id.sch.smktelkom_mlg.project.xirpl105142332.dolanjatim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailWisataActivity extends AppCompatActivity {

    DatabaseReference Ref = FirebaseDatabase.getInstance().getReference().child("tb_kota");

    //DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("tb_kota/Kabupaten Ponorogo/deskripsi");
    //DatabaseReference mConditionRef = mRootRef.child("tb_kota/Kabupaten Pacitan/judul");
    //StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    //StorageReference riversRef = storageRef.child("images/rivers.jpg");
    TextView pd;
    private String mPost_key = null;
    private String wisata_key = null;
    private DatabaseReference mDatabase;
    private ImageView mBlogSingleImage;
    private TextView mBlogSingleDesc;
    private RecyclerView mBlogListw;
    private TextView lokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("tb_kota");
        mPost_key = getIntent().getExtras().getString("kota_id");
        wisata_key = getIntent().getExtras().getString("wisata_id");
        DatabaseReference kota = Ref.child(mPost_key);
        DatabaseReference wisata = kota.child("wisata");

        mBlogListw = (RecyclerView) findViewById(R.id.recyclerviewwisata);
        mBlogListw.setHasFixedSize(true);
        mBlogListw.setLayoutManager(new LinearLayoutManager(this));

        mBlogSingleDesc = (TextView) findViewById(R.id.wisata_detail);
        lokasi = (TextView) findViewById(R.id.wisata_location);
        mBlogSingleImage = (ImageView) findViewById(R.id.imageFotowisata);

        wisata.child(wisata_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String post_title = (String) dataSnapshot.child("nama_wisata").getValue();
                String post_desc = (String) dataSnapshot.child("detail").getValue();
                String post_image = (String) dataSnapshot.child("gambar").getValue();
                String post_location = (String) dataSnapshot.child("lokasi").getValue();

                setTitle(post_title);
                mBlogSingleDesc.setText(post_desc);
                lokasi.setText(post_location);

                Picasso.with(DetailWisataActivity.this).load(post_image).into(mBlogSingleImage);
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
    protected void onStart() {
        super.onStart();

    }
}

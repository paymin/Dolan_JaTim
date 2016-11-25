package id.sch.smktelkom_mlg.project.xirpl105142332.dolanjatim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TesActivity extends AppCompatActivity {

    DatabaseReference Air = FirebaseDatabase.getInstance().getReference().child("tb_kota/");
    private String mPost_key = null;
    private DatabaseReference mDatabase;
    private ImageView mBlogSingleImage;
    private TextView mBlogSingleTitle;
    private TextView mBlogSingleDesc;
    private RecyclerView mBlogListw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("tb_kota");
        mPost_key = getIntent().getExtras().getString("blog_id");


        mBlogListw = (RecyclerView) findViewById(R.id.recyclerviewwisata);
        mBlogListw.setHasFixedSize(true);
        mBlogListw.setLayoutManager(new LinearLayoutManager(this));

        mBlogSingleDesc = (TextView) findViewById(R.id.place_detail);
        mBlogSingleImage = (ImageView) findViewById(R.id.imageFoto);

        mDatabase.child(mPost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String post_title = (String) dataSnapshot.child("judul").getValue();
                String post_desc = (String) dataSnapshot.child("deskripsi").getValue();
                String post_image = (String) dataSnapshot.child("landmark").getValue();

                setTitle(post_title);
                mBlogSingleDesc.setText(post_desc);

                Picasso.with(TesActivity.this).load(post_image).into(mBlogSingleImage);
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
        DatabaseReference Zz = Air.child(mPost_key);
        DatabaseReference Wis = Zz.child("wisata");
        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class, R.layout.item_list_wisata, BlogViewHolder.class, Wis) {

            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.SetNama_wisata(model.getNama_wisata());
                viewHolder.setDetail(model.getDetail());
                viewHolder.setGambar(getApplicationContext(), model.getGambar());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleBlogIntent = new Intent(TesActivity.this, DetailWisataActivity.class);
                        singleBlogIntent.putExtra("kota_id", mPost_key);
                        singleBlogIntent.putExtra("wisata_id", post_key);
                        startActivity(singleBlogIntent);
                        Log.d("Zz: ", post_key);
                    }
                });
            }
        };
        mBlogListw.setAdapter(firebaseRecyclerAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mview;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
        }

        public void SetNama_wisata(String nama_wisata) {
            TextView post_title = (TextView) mview.findViewById(R.id.textViewJudulwisata);
            post_title.setText(nama_wisata);
        }

        public void setDetail(String Detail) {
            TextView post_title = (TextView) mview.findViewById(R.id.textViewDeskripsiwisata);
            post_title.setText(Detail);
        }

        public void setGambar(Context ctx, String gambar) {
            ImageView post_image = (ImageView) mview.findViewById(R.id.imageViewwisata);
            Picasso.with(ctx).load(gambar).into(post_image);
        }

    }
}

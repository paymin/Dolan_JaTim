package id.sch.smktelkom_mlg.project.xirpl105142332.dolanjatim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

    DatabaseReference Ref = FirebaseDatabase.getInstance().getReference().child("tb_kota");

    //DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("tb_kota/Kabupaten Ponorogo/deskripsi");
    //DatabaseReference mConditionRef = mRootRef.child("tb_kota/Kabupaten Pacitan/judul");
    //StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    //StorageReference riversRef = storageRef.child("images/rivers.jpg");
    TextView pd;
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

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class, R.layout.item_list_wisata, BlogViewHolder.class, Ref) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.setTitle(model.getJudul());
                viewHolder.setDesc(model.getDeskripsi());
                viewHolder.setImage(getApplicationContext(), model.getLogo());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleBlogIntent = new Intent(TesActivity.this, DetailWisataActivity.class);
                        singleBlogIntent.putExtra("blog_id", post_key);
                        startActivity(singleBlogIntent);
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

        public void setTitle(String title) {
            TextView post_title = (TextView) mview.findViewById(R.id.textViewJudulwisata);
            post_title.setText(title);
        }

        public void setDesc(String Desc) {
            TextView post_title = (TextView) mview.findViewById(R.id.textViewDeskripsiwisata);
            post_title.setText(Desc);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mview.findViewById(R.id.imageViewwisata);
            Picasso.with(ctx).load(image).into(post_image);
        }

    }
}

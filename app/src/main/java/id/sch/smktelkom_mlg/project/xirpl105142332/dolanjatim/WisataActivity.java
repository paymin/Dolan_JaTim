package id.sch.smktelkom_mlg.project.xirpl105142332.dolanjatim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class WisataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference Ref = FirebaseDatabase.getInstance().getReference().child("tb_wisata");

    private RecyclerView mBlogList;
    private String zz = null;
    private String nama = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbara);
        setSupportActionBar(toolbar);

        zz = getIntent().getExtras().getString("wisata_id");
        nama = getIntent().getExtras().getString("nama");

        mBlogList = (RecyclerView) findViewById(R.id.recyclerviewwisata);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layouta);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_viewa);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle(nama);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navAirTerjun) {
            if (!nama.equals("Air Terjun")) {
                Intent singleBlogIntent = new Intent(WisataActivity.this, WisataActivity.class);
                singleBlogIntent.putExtra("wisata_id", "air terjun");
                singleBlogIntent.putExtra("nama", "Air Terjun");
                startActivity(singleBlogIntent);
                this.finish();
            }


        } else if (id == R.id.navDanau) {
            if (!nama.equals("Danau")) {
                Intent singleBlogIntent = new Intent(WisataActivity.this, WisataActivity.class);
                singleBlogIntent.putExtra("wisata_id", "danau");
                singleBlogIntent.putExtra("nama", "Danau");
                startActivity(singleBlogIntent);
                this.finish();
            }

        } else if (id == R.id.navGunung) {
            if (!nama.equals("Gunung")) {
                Intent singleBlogIntent = new Intent(WisataActivity.this, WisataActivity.class);
                singleBlogIntent.putExtra("wisata_id", "gunung");
                singleBlogIntent.putExtra("nama", "Gunung");
                startActivity(singleBlogIntent);
                this.finish();
            }

        } else if (id == R.id.navPantai) {
            if (!nama.equals("Pantai")) {
                Intent singleBlogIntent = new Intent(WisataActivity.this, WisataActivity.class);
                singleBlogIntent.putExtra("wisata_id", "pantai");
                singleBlogIntent.putExtra("nama", "Pantai");
                startActivity(singleBlogIntent);
                this.finish();
            }

        } else if (id == R.id.navHome) {
            startActivity(new Intent(WisataActivity.this, MainActivity.class));
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layouta);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference wisata = Ref.child(zz);

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class, R.layout.listwisata_layout, BlogViewHolder.class, wisata) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String post_key = getRef(position).getKey();
                final String key_kota = model.getKey_kota();

                viewHolder.setTitle(model.getNama_wisata());
                viewHolder.setImage(getApplicationContext(), model.getGambar());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleBlogIntent = new Intent(WisataActivity.this, DetailWisataActivity.class);
                        singleBlogIntent.putExtra("wisata_id", post_key);
                        singleBlogIntent.putExtra("kota_id", key_kota);
                        startActivity(singleBlogIntent);
                    }
                });
            }
        };

        mBlogList.setAdapter(firebaseRecyclerAdapter);
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
            TextView post_title = (TextView) mview.findViewById(R.id.textViewJuduluu);
            post_title.setText(title);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mview.findViewById(R.id.imageViewuu);
            Picasso.with(ctx).load(image).into(post_image);
        }

    }
}

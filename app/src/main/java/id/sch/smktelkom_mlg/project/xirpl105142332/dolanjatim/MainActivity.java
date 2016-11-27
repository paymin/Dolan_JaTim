package id.sch.smktelkom_mlg.project.xirpl105142332.dolanjatim;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int REQUEST_CODE = 123;
    NavigationView navigationView = null;

    DatabaseReference Ref = FirebaseDatabase.getInstance().getReference().child("condition/kota");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*AirTerjunFragment fragment = new AirTerjunFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();*/



        RecyclerView mBlogListw = (RecyclerView) findViewById(R.id.recyclerviewmenu);
        mBlogListw.setHasFixedSize(true);
        mBlogListw.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));

        /*mBlogListv = (RecyclerView) findViewById(R.id.recyclerviewmenua);
        mBlogListv.setHasFixedSize(true);
        mBlogListv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));*/

        findViewById(R.id.tvViewMore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ListKotaActivity.class), REQUEST_CODE);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView imageView = (ImageView) findViewById(R.id.gambar);

        Picasso.with(this).load("https://firebasestorage.googleapis.com/v0/b/dolan-jatim-cc1f1.appspot.com/o/8U2yHxa.jpg?alt=media&token=a3b92756-ba2f-44a5-845e-c5c124d1c3c1").into(imageView);

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class, R.layout.item_list_menu, BlogViewHolder.class, Ref) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.setTitle(model.getJudul());
                viewHolder.setImage(getApplicationContext(), model.getLogo());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleBlogIntent = new Intent(MainActivity.this, TesActivity.class);
                        singleBlogIntent.putExtra("blog_id", post_key);
                        startActivity(singleBlogIntent);
                    }
                });
            }
        };
        mBlogListw.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        /*FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class, R.layout.item_list_menu, BlogViewHolder.class, Ref) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.setTitle(model.getJudul());
                viewHolder.setImage(getApplicationContext(), model.getLogo());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleBlogIntent = new Intent(MainActivity.this, TesActivity.class);
                        singleBlogIntent.putExtra("blog_id", post_key);
                        startActivity(singleBlogIntent);
                    }
                });
            }
        };
        mBlogListw.setAdapter(firebaseRecyclerAdapter);

        /*FirebaseRecyclerAdapter<Blog, BlogViewHoldera> firebaseRecyclerAdaptera = new FirebaseRecyclerAdapter<Blog, BlogViewHoldera>(
                Blog.class, R.layout.item_list_menua, BlogViewHoldera.class, Refa) {
            @Override
            protected void populateViewHolder(BlogViewHoldera viewHolder, Blog model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.setTitle(model.getJudul());
                viewHolder.setImage(getApplicationContext(), model.getLogo());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleBlogIntent = new Intent(MainActivity.this, DetailWisataActivity.class);
                        singleBlogIntent.putExtra("blog_id", post_key);
                        startActivity(singleBlogIntent);
                    }
                });
            }
        };
        mBlogListv.setAdapter(firebaseRecyclerAdaptera);*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navAirTerjun) {
            Intent singleBlogIntent = new Intent(MainActivity.this, WisataActivity.class);
            singleBlogIntent.putExtra("wisata_id", "air terjun");
            singleBlogIntent.putExtra("nama", "Air Terjun");
            startActivity(singleBlogIntent);
            this.finish();

        } else if (id == R.id.navDanau) {
            Intent singleBlogIntent = new Intent(MainActivity.this, WisataActivity.class);
            singleBlogIntent.putExtra("wisata_id", "danau");
            singleBlogIntent.putExtra("nama", "Danau");
            startActivity(singleBlogIntent);
            this.finish();

        } else if (id == R.id.navGunung) {
            Intent singleBlogIntent = new Intent(MainActivity.this, WisataActivity.class);
            singleBlogIntent.putExtra("wisata_id", "gunung");
            singleBlogIntent.putExtra("nama", "Gunung");
            startActivity(singleBlogIntent);
            this.finish();

        } else if (id == R.id.navPantai) {
            Intent singleBlogIntent = new Intent(MainActivity.this, WisataActivity.class);
            singleBlogIntent.putExtra("wisata_id", "pantai");
            singleBlogIntent.putExtra("nama", "Pantai");
            startActivity(singleBlogIntent);
            this.finish();

        } else if (id == R.id.navHome) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mview;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mview.findViewById(R.id.textViewJudulmenu);
            post_title.setText(title);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mview.findViewById(R.id.imageViewmenu);
            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.BLACK)
                    .borderWidthDp(0)
                    .cornerRadiusDp(260)
                    .oval(false)
                    .build();
            Picasso.with(ctx).load(image).transform(transformation).into(post_image);
        }

    }

    /*public static class BlogViewHoldera extends RecyclerView.ViewHolder {
        View mview;

        public BlogViewHoldera(View itemView) {
            super(itemView);
            mview = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mview.findViewById(R.id.textViewJudulmenua);
            post_title.setText(title);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mview.findViewById(R.id.imageViewmenua);
            Picasso.with(ctx).load(image).into(post_image);
        }

    }*/
}

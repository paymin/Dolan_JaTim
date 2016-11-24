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

public class ListKotaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference Ref = FirebaseDatabase.getInstance().getReference().child("tb_kota");

    private RecyclerView mBlogList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kota);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbara);
        setSupportActionBar(toolbar);

        mBlogList = (RecyclerView) findViewById(R.id.recyclerview);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layouta);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_viewa);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navAirTerjun) {

        } else if (id == R.id.navDanau) {

        } else if (id == R.id.navGunung) {

        } else if (id == R.id.navPantai) {

        } else if (id == R.id.navHome) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layouta);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class, R.layout.item_list_kota, BlogViewHolder.class, Ref) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.setTitle(model.getJudul());
                viewHolder.setImage(getApplicationContext(), model.getLandmark());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleBlogIntent = new Intent(ListKotaActivity.this, TesActivity.class);
                        singleBlogIntent.putExtra("blog_id", post_key);
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
            TextView post_title = (TextView) mview.findViewById(R.id.textViewJudul);
            post_title.setText(title);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mview.findViewById(R.id.imageView);
            Picasso.with(ctx).load(image).into(post_image);
        }

    }
}

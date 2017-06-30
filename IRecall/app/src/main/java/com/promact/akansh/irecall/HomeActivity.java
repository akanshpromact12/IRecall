package com.promact.akansh.irecall;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Akansh on 29-06-2017.
 */

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private FloatingActionMenu floatMenu;
    private FloatingActionButton floatActionCamera, floatActionGallery;
    private AlertDialog.Builder builder;
    private ListView dialogList;
    static final int CUSTOM_DIALOG_ID = 0;
    private String[] options = {"Take a Photo", "Take a Video"};
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        floatMenu = (FloatingActionMenu) findViewById(R.id.floating_action_menu);
        floatActionCamera = (FloatingActionButton) findViewById(R.id.menu_camera_option);
        floatActionGallery = (FloatingActionButton) findViewById(R.id.menu_gallery_option);
        floatActionGallery.setBackgroundColor(Color.WHITE);
        floatActionCamera.setBackgroundColor(Color.WHITE);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String photoUri = intent.getStringExtra("photoUri");
        TextView txt = (TextView) findViewById(R.id.txtView1);
        /*builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Select Photo / Video");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int option)
            {
                boolean result = Utility.checkPermission(getApplicationContext());

                if (options[option].equals("Take a Photo"))
                {
                    userCh
                }
            }
        });*/

        NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
        View newView = nav.getHeaderView(0); //Gets the header view from the header page, where all the widgets are kept.

        TextView txtUname = (TextView) newView.findViewById(R.id.usernm);
        TextView txtEmail = (TextView) newView.findViewById(R.id.emailNav);
        CircleImageView profilePic = (CircleImageView) newView.findViewById(R.id.imgProfile);
        imgPhoto = (ImageView) findViewById(R.id.imgPhoto);

        txtUname.setText(name);
        txtEmail.setText(email);
        Glide.with(getApplicationContext()).load(photoUri).into(profilePic);
        txt.setText("Welome " + name);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Toast.makeText(getApplicationContext(),
                txtEmail.getText().toString() + txtUname.getText().toString(),
                Toast.LENGTH_SHORT).show();*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        floatActionCamera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Selected camera option", Snackbar.LENGTH_LONG).setAction("Action Camera", null).show();
                showDialog(CUSTOM_DIALOG_ID);
            }
        });

        floatActionGallery.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Selected gallery option", Snackbar.LENGTH_LONG).setAction("Action Gallery", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        Dialog dialog = null;

        switch (id)
        {
            case CUSTOM_DIALOG_ID:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    dialog = new Dialog(this, android.R.style.Theme_Material_Dialog);
                }
                else
                {
                    dialog = new Dialog(this);
                }

                dialog.setContentView(R.layout.dialog_layout);
                dialog.setTitle("Select an option");

                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener()
                {
                    @Override
                    public void onCancel(DialogInterface dialog)
                    {
                        //Toast.makeText(HomeActivity.this, "OnCancelListener", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener()
                {
                    @Override
                    public void onDismiss(DialogInterface dialog)
                    {
                        //Toast.makeText(HomeActivity.this, "OnDismissListener", Toast.LENGTH_SHORT).show();
                    }
                });

                dialogList = (ListView) dialog.findViewById(R.id.dialogList);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);
                dialogList.setAdapter(adapter);
                dialogList.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        Toast.makeText(HomeActivity.this, parent.getItemAtPosition(position) + " clicked", Toast.LENGTH_SHORT).show();

                        if (parent.getItemAtPosition(position).equals("Take a Photo"))
                        {
                            Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (takePhotoIntent.resolveActivity(getPackageManager()) != null)
                            {
                                startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE);
                            }
                        }

                        dismissDialog(CUSTOM_DIALOG_ID);
                    }
                });

                break;
        }

        return dialog;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap) extras.get("data");
            imgPhoto.setImageBitmap(image);
        }
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog)
    {
        super.onPrepareDialog(id, dialog);

        switch (id)
        {
            case CUSTOM_DIALOG_ID:
                //
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            SharedPreferences.Editor editor = SaveSharedPref.getSharedPreferences(getApplicationContext()).edit();
            editor.clear();
            editor.commit();
            finish();

            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}

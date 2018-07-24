package com.example.rionaldo.xmppchatfirst;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.rionaldo.xmppchatfirst.Adapter.ContactAdapter;
import com.example.rionaldo.xmppchatfirst.Model.ContactModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactListActivity extends AppCompatActivity implements View.OnClickListener, ContactAdapter.OnItemClick{
    @BindView(R.id.recyclerView_contact) RecyclerView recyclerViewContact;
    @BindView(R.id.fab_add_contact) FloatingActionButton fabAddContact;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Friend List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_pink);

        adapter = new ContactAdapter(ContactModel.getInstance(this).getData());
        adapter.setOnItemClick(this);
        recyclerViewContact.setAdapter(adapter);
        recyclerViewContact.setHasFixedSize(false);
        recyclerViewContact.setLayoutManager(new LinearLayoutManager(this));

        fabAddContact.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fab_add_contact :
                showAddContactDialog();
                break;
        }
    }

    @Override
    public void onItemClick() {
        Intent intent = new Intent(this,ChatViewActivity.class);
        startActivity(intent);
    }

    public void showAddContactDialog(){
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(this);
        adBuilder.setTitle("Add New Contact");
        //set up the edit Text
        final EditText etAddContact = new EditText(this);
        etAddContact.setInputType(InputType.TYPE_CLASS_TEXT);
        adBuilder.setView(etAddContact);

        adBuilder.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        adBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        adBuilder.show();
    }
}

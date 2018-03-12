package com.skyfree.kinhnguyetmangthai.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.database.DatabaseHelper;
import com.skyfree.kinhnguyetmangthai.model.Acount;
import com.skyfree.kinhnguyetmangthai.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTvSetPass, mTvDeletePass;
    private Button mBtnSave;
    private DatabaseHelper mDb;
    private String mItemSpinner = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        mDb = new DatabaseHelper(this);
        initView();
    }

    private void initView() {
        mTvSetPass = (TextView) findViewById(R.id.tv_set_pass_pw);
        mTvDeletePass = (TextView) findViewById(R.id.tv_delete_pass_pw);

        mTvSetPass.setOnClickListener(this);
        mTvDeletePass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_set_pass_pw:
                showAlert();
                break;
            case R.id.tv_delete_pass_pw:
                mDb.deleteAllAcount();
                Toast.makeText(this, getString(R.string.you_just_delete_pass), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    private void showAlert() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_set_password, null);
        dialogBuilder.setView(dialogView);

        final EditText mEdtPass = (EditText) dialogView.findViewById(R.id.edt_password_dialog_set_pass);
        final EditText mEdtConfirmPass = (EditText) dialogView.findViewById(R.id.edt_confirm_password_dialog_set_pass);
        Spinner mSpin = (Spinner) dialogView.findViewById(R.id.spin_dialog_set_pass);
        final EditText mEdtQuestion = (EditText) dialogView.findViewById(R.id.edt_question_dialog_set_pass);
        final EditText mEdtAnswer = (EditText) dialogView.findViewById(R.id.edt_answer_dialog_set_pass);
        Button mBtnSave = (Button) dialogView.findViewById(R.id.btn_save_dialog_set_pass);

        List<String> categories = new ArrayList<String>();
        categories.add(getString(R.string.choose_security_question));
        categories.add(getString(R.string.your_phone_number));
        categories.add(getString(R.string.your_favorite_color));
        categories.add(getString(R.string.your_name));
        categories.add(getString(R.string.choose_my_own_question));

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpin.setAdapter(dataAdapter);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    mItemSpinner = "";
                }
                else if(position==4){
                    mEdtQuestion.setVisibility(View.VISIBLE);
                    mItemSpinner = mEdtQuestion.getText().toString();
                }else {
                    mItemSpinner = parent.getItemAtPosition(position).toString();
                    mEdtQuestion.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mItemSpinner = "";
            }
        });
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEdtPass.getText().toString().equals(mEdtConfirmPass.getText().toString()) && !mItemSpinner.equals("") && !mEdtPass.equals("") && !mEdtAnswer.equals("")){
                    mDb.deleteAllAcount();
                    mDb.addAcount(new Acount(mEdtPass.getText().toString(), mEdtConfirmPass.getText().toString(), mItemSpinner, mEdtAnswer.getText().toString()));
                    Toast.makeText(PasswordActivity.this, getString(R.string.done), Toast.LENGTH_SHORT).show();
                    alertStartDialog.cancel();
                    Utils.sendPassToMail(getApplicationContext(), getString(R.string.password) + " : " + mEdtPass.getText().toString() + "\n" + getString(R.string.answer) + " : " + mEdtAnswer.getText().toString());
                }else {
                    Toast.makeText(PasswordActivity.this, getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

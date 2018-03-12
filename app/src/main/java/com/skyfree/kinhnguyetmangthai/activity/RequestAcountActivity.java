package com.skyfree.kinhnguyetmangthai.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfree.kinhnguyetmangthai.R;
import com.skyfree.kinhnguyetmangthai.database.DatabaseHelper;

public class RequestAcountActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEdtPass;
    private TextView mTvLogin, mTvSecurityQuestion, mTvForgetPass;
    private DatabaseHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_acount);
        addEvent();
        initView();
    }

    private void addEvent() {
        mDb = new DatabaseHelper(this);
        if (!(mDb.getListAcount().size() > 0)){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void initView() {
        mEdtPass = (EditText) findViewById(R.id.edt_pass_ra);
        mTvLogin = (TextView) findViewById(R.id.tv_login_ra);
        mTvSecurityQuestion = (TextView) findViewById(R.id.tv_security_question_ra);
        mTvForgetPass = (TextView) findViewById(R.id.tv_forget_pass_ra);

        mTvLogin.setOnClickListener(this);
        mTvSecurityQuestion.setOnClickListener(this);
        mTvForgetPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login_ra:
                login();
                break;
            case R.id.tv_security_question_ra:
                askSecurityQuestion();
                break;
            case R.id.tv_forget_pass_ra:
                forgetPass();
                break;
        }
    }

    private void login() {
        if(mEdtPass.getText().toString().equals(mDb.getListAcount().get(0).getmPass())){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }else {
            Toast.makeText(this, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
        }
    }

    private void askSecurityQuestion(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_ask_security_question, null);
        dialogBuilder.setView(dialogView);

        EditText mEdtQuestion = (EditText) dialogView.findViewById(R.id.edt_question_dialog_ask_security_question);
        final EditText mEdtAnswer = (EditText) dialogView.findViewById(R.id.edt_answer_dialog_ask_security_question);
        Button mBtnCancel = (Button) dialogView.findViewById(R.id.btn_cancel_dialog_ask_security_question);
        Button mBtnOk = (Button) dialogView.findViewById(R.id.btn_ok_dialog_ask_security_question);

        final AlertDialog alertStartDialog = dialogBuilder.create();
        alertStartDialog.show();

        mEdtQuestion.setHint(mDb.getListAcount().get(0).getmQuestion());
        mEdtQuestion.setFocusable(false);

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertStartDialog.cancel();
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEdtAnswer.getText().toString().equals(mDb.getListAcount().get(0).getmAnswer())){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(RequestAcountActivity.this, getString(R.string.wrong_answer), Toast.LENGTH_SHORT).show();
                    alertStartDialog.cancel();
                }
            }
        });

    }

    private void forgetPass(){

    }
}

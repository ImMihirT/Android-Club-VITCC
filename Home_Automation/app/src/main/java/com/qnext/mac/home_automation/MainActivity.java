package com.qnext.mac.home_automation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private static final String TAG = "MyActivity";
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText uname;
    private EditText pass;
    private CardView cv;
   // private SignInButton mGoogleButton;
    private TextView SignUP;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=(EditText)findViewById(R.id.uname);
        pass=(EditText)findViewById(R.id.pass);
        cv = (CardView)findViewById(R.id.cv);
      //  mGoogleButton=(SignInButton)findViewById(R.id.gbtn);
        mAuth = FirebaseAuth.getInstance();
       cv.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);


    }
    private void userLogin(){
        String email=uname.getText().toString().trim();
        String password=pass.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"please enter Email id",Toast.LENGTH_LONG).show();
            return;

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"please enter Password",Toast.LENGTH_LONG).show();
            return;

        }
        progressDialog.setMessage("Logging In..");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            progressDialog.dismiss();

                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "Failed to auth",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //Start the Main App screen after login.
                            progressDialog.dismiss();
                            Intent Intent = new Intent(MainActivity.this,Control_Bar.class);
                            startActivity(Intent);


                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view==cv)
        {
            userLogin();
        }
    }
}

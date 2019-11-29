package com.itchii.cristian.argame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    String email, password;
    EditText edtxtEmail, edtxtPassword;
    LoginButton loginButtonFb;
    CallbackManager mCallbackManager;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton btnGoogle;
    Intent inLanzarHome;
    private final int RC_SIGN_IN = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtxtEmail = (EditText)findViewById(R.id.edtxtEmail);
        edtxtPassword = (EditText)findViewById(R.id.edtxtPassword);

        mAuth = FirebaseAuth.getInstance();

        //Facebook
        mCallbackManager = CallbackManager.Factory.create();
        loginButtonFb = (LoginButton)findViewById(R.id.login_buttonfb);

        //Google
        btnGoogle = (SignInButton)findViewById(R.id.login_buttonGoogle);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        inLanzarHome = new Intent(LoginActivity.this, HomeActivity.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Checar si hay un usuario logueado
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //Google
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    //Login con Facebook
    public void signInFb(View v){
        loginButtonFb.setReadPermissions("email", "public_profile");
        loginButtonFb.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("FACEBOOK", "facebook:omSuccess");
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("FACEBOOK", "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FACEBOOK", "facebook:onError");
            }
        });
    }

    //Firebase
    public void signIn(View v){
        if (edtxtEmail.getText().toString().isEmpty()||edtxtPassword.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this,"Ingrese los campos",Toast.LENGTH_SHORT).show();
        }else {
            email = edtxtEmail.getText().toString();
            password = edtxtPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(inLanzarHome);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this,"No se inicio sesion",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //Firebase
    @Override
    public void onClick(View view) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    //Toast.makeText(LoginActivity.this,"Se inicio sesion",Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(LoginActivity.this,"No se inicio sesion",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Facebook y Google
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("Google", "Google sign in failed", e);
                // ...
            }
        }else if (requestCode == 64206){
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }




    }

    //Facebook
    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information.
                            //Toast.makeText(LoginActivity.this,"Se inicio con Facebook",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(inLanzarHome);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this,"No se pudo iniciar sesion con Facebook",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //Google
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Toast.makeText(LoginActivity.this, "Se inciio sesion",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(inLanzarHome);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Error al iniciar sesion",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}

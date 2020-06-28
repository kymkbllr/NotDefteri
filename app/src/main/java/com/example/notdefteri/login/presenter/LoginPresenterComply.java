package com.example.notdefteri.login.presenter;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.example.notdefteri.login.view.LoginActivity;
import com.example.notdefteri.login.ILoginView;
import com.example.notdefteri.login.model.IUser;
import com.example.notdefteri.login.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginPresenterComply implements ILoginPresenter {
    ILoginView iLoginView;
    IUser user;
    FirebaseAuth auth ;
    public LoginPresenterComply(LoginActivity iLoginView) {
        this.iLoginView = iLoginView;
        initUser();
        auth= FirebaseAuth.getInstance();
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {


        final int code = user.checkUserValidity(name,passwd);
        if (code!=0) {
            iLoginView.onLoginResult(false);
          return;
        }


        auth.signInWithEmailAndPassword(name, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                iLoginView.onLoginResult(true);
                }
                else{
                    iLoginView.onLoginResult(false);
                }

            }
        });
       // iLoginView.onLoginResult(result, code);

    }

    @Override
    public void setProgressBarVisiblity(int visiblity){
        iLoginView.onSetProgressBarVisibility(visiblity);
    }

    private void initUser(){
        user = new UserModel("mvp","mvp");
    }
}

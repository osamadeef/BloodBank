package com.osama.daif.bloodbank.view.fragment.userCycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;

import com.osama.daif.bloodbank.R;
import com.osama.daif.bloodbank.view.activity.HomeCycleActivity;
import com.osama.daif.bloodbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.osama.daif.bloodbank.helper.HelperMethods.replaceFragment;

public class LoginFragment extends BaseFragment {
    public static final String TAG = "LoginFragment";

    @BindView(R.id.login_fragment_txt_phone_number)
    AppCompatEditText txt_phone_number;
    @BindView(R.id.login_fragment_txt_password)
    AppCompatEditText txt_password;
    @BindView(R.id.login_fragment_ckb_remember_me)
    CheckBox ckb_remember_me;
    @BindView(R.id.login_fragment_txt_forget_password)
    TextView txt_forget_password;
    @BindView(R.id.login_fragment_btn_enter)
    Button btn_enter;
    @BindView(R.id.login_fragment_btn_create_account)
    Button btn_create_account;
    private Unbinder unbinder = null;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        initFragment();
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick({R.id.login_fragment_ckb_remember_me, R.id.login_fragment_txt_forget_password, R.id.login_fragment_btn_enter, R.id.login_fragment_btn_create_account})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_fragment_ckb_remember_me:

                break;

            case R.id.login_fragment_txt_forget_password:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.fragment_user_container, new ForgetPasswordStep1Fragment());
                break;

            case R.id.login_fragment_btn_enter:
                startActivity(new Intent(baseActivity.getApplicationContext(), HomeCycleActivity.class));
                break;

            case R.id.login_fragment_btn_create_account:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.fragment_user_container, new RegisterAsUserFragment());
                break;
        }
    }
}
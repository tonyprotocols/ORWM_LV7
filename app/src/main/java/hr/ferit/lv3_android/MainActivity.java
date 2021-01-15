package hr.ferit.lv3_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ButtonClickListener {

    private  MessageFragment mMessageFragment;
    private  InputFragment mInputFragment;

    private FragmentManager mFragmentManger;

    private boolean mSwitch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManger = getSupportFragmentManager();
        setupFragments();
        setupListener();
    }

    private void setupListener() {
        findViewById(R.id.btSwitch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragments();
            }
        });
    }

    private void switchFragments() {
        mSwitch = !mSwitch;
        removeFragments();
        FragmentTransaction FragmentTransaction = mFragmentManger.beginTransaction();
        if(mSwitch){
            FragmentTransaction.replace(R.id.firstFragment, mInputFragment );
            FragmentTransaction.replace(R.id.secondFragment, mMessageFragment);}
        else{
            FragmentTransaction.replace(R.id.firstFragment, mMessageFragment);
            FragmentTransaction.replace(R.id.secondFragment, mInputFragment);
        }
        FragmentTransaction.commit();
    }

    private void removeFragments() {
        FragmentTransaction FragmentTransaction = mFragmentManger.beginTransaction();
        FragmentTransaction.remove(mMessageFragment);
        FragmentTransaction.remove(mInputFragment);
        FragmentTransaction.commit();
        mFragmentManger.executePendingTransactions();
    }

    private void setupFragments() {
     mMessageFragment = new MessageFragment();
     mInputFragment = new InputFragment();
        FragmentTransaction FragmentTransaction = mFragmentManger.beginTransaction();
        FragmentTransaction.add(R.id.firstFragment, mMessageFragment);
        FragmentTransaction.add(R.id.secondFragment, mInputFragment);

        FragmentTransaction.commit();
    }

    @Override
    public void onButtonClicked(String message) {
        mMessageFragment.displayMessage(message);
    }


}

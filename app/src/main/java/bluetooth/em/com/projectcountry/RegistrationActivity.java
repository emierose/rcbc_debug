package bluetooth.em.com.projectcountry;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import bluetooth.em.com.projectcountry.fragment.registration.RegistrationStepFour;
import bluetooth.em.com.projectcountry.fragment.registration.RegistrationStepOne;
import bluetooth.em.com.projectcountry.fragment.registration.RegistrationStepThree;
import bluetooth.em.com.projectcountry.fragment.registration.RegistrationStepTwo;
import bluetooth.em.com.projectcountry.view.RegistrationHolder;
import bluetooth.em.com.projectcountry.view.RegistrationInterface;
import bluetooth.em.com.projectcountry.view.Validate;

/**
 * Created by Em on 5/4/2016.
 */
public class RegistrationActivity extends AppCompatActivity  implements RegistrationInterface {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static  EditText username;
    public  static  EditText tpass;
    public  static  EditText re_tpass, firstname, middlename,lastname,bdate,bday_city, bday_state;
    public  static Spinner status;
    public  static EditText mothername,nationality,tin,sss,passport, acr,cadrno, ccv,p_add, p_country, p_state,s_add,s_country,s_state,t_add, t_country, t_state;
    public  static EditText residential, office,mobile, p_email, s_email, t_email,branch,code;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        submit = (Button)findViewById(R.id.btn_step4);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         initViewer();

        findViewById(R.id.btn_step4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public  boolean isFirstStepValid(){
        boolean result =true;
        if(Validate.isEmpty(username)){
//            showError("Registration",username.getHint().toString() +" Should not be empty.",null);
            result =false;
        }
    return result;
    }

    private void initViewer() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
           switch (position){
               case 0:
                   submit.setText("Proceed to Step 2");
                   break;
               case 1:
                   if(!isFirstStepValid()){
                       viewPager.setCurrentItem(0);
                   }
                   submit.setText("Proceed to Step 3");
                   break;
               case 2:
                   submit.setText("Proceed to Step 4");
                   break;
               case 3:
                   submit.setText("Register");
                   break;
           }


            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RegistrationStepOne(), "STEP 1");
        adapter.addFragment(new RegistrationStepTwo(), "STEP 2");
        adapter.addFragment(new RegistrationStepThree(), "STEP 3");
        adapter.addFragment(new RegistrationStepFour(), "STEP 4");
        viewPager.setAdapter(adapter);
    }

    @Override
    public RegistrationHolder getregistrationCredentials() {
        return null;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showError(String title, String message, DialogInterface.OnDismissListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AppCompatAlertDialogStyle);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
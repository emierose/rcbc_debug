package bluetooth.em.com.projectcountry.fragment.registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import bluetooth.em.com.projectcountry.R;
import bluetooth.em.com.projectcountry.RegistrationActivity;

/**
 * Created by Em on 5/5/2016.
 */
public class RegistrationStepOne extends Fragment    {
    View view;
    public RegistrationStepOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.registration_step_one, container, false);
        RegistrationActivity.username = (EditText)view.findViewById(R.id.et_username);
        RegistrationActivity.re_tpass = (EditText)view.findViewById(R.id.et_conf_password);
        RegistrationActivity.tpass = (EditText)view.findViewById(R.id.et_password);
//        RegistrationActivity.isFirstStepValid();
        return  view;
    }

}

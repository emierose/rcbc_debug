package bluetooth.em.com.projectcountry.fragment.registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import bluetooth.em.com.projectcountry.R;
import bluetooth.em.com.projectcountry.RegistrationActivity;

/**
 * Created by Em on 5/5/2016.
 */
public class RegistrationStepTwo extends Fragment {
    View view;

    public RegistrationStepTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.registration_step_two, container, false);
        RegistrationActivity.firstname = (EditText) view.findViewById(R.id.et_fname);
        RegistrationActivity.middlename = (EditText) view.findViewById(R.id.et_mname);
        RegistrationActivity.lastname = (EditText) view.findViewById(R.id.et_lname);
        RegistrationActivity.bdate = (EditText) view.findViewById(R.id.et_dob);
        RegistrationActivity.bday_city = (EditText) view.findViewById(R.id.et_dob_city);
        RegistrationActivity.bday_state = (EditText) view.findViewById(R.id.et_dob_state);
        RegistrationActivity.status = (Spinner) view.findViewById(R.id.sp_status);
        RegistrationActivity.nationality = (EditText) view.findViewById(R.id.et_nationality);
        RegistrationActivity.mothername = (EditText) view.findViewById(R.id.et_mothername);
        return view;
    }

}

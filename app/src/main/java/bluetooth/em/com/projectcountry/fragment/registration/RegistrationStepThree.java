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
public class RegistrationStepThree extends Fragment {
    View view;
    public RegistrationStepThree() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.registration_step_three, container, false);
        RegistrationActivity.residential = (EditText) view.findViewById(R.id.et_residential);
        RegistrationActivity.office = (EditText) view.findViewById(R.id.et_office);
        RegistrationActivity.mobile = (EditText) view.findViewById(R.id.et_mobile);
        RegistrationActivity.p_email = (EditText) view.findViewById(R.id.et_p_email);
        RegistrationActivity.s_email = (EditText) view.findViewById(R.id.et_s_email);
        RegistrationActivity.t_email = (EditText) view.findViewById(R.id.et_t_email);
        RegistrationActivity.branch = (EditText) view.findViewById(R.id.et_branch);
        RegistrationActivity.code = (EditText) view.findViewById(R.id.et_code);
        return view;
    }

}

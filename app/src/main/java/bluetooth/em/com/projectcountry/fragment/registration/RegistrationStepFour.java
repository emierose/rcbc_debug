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

public class RegistrationStepFour extends Fragment {
    View view;
    public RegistrationStepFour() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     view = inflater.inflate(R.layout.registration_step_four, container, false);
        RegistrationActivity.tin = (EditText) view.findViewById(R.id.et_tin_no);
        RegistrationActivity.sss = (EditText) view.findViewById(R.id.et_sss);
        RegistrationActivity.passport = (EditText) view.findViewById(R.id.et_passport);
        RegistrationActivity.cadrno = (EditText) view.findViewById(R.id.et_cardno);
        RegistrationActivity.ccv = (EditText) view.findViewById(R.id.et_ccv);
        RegistrationActivity.acr = (EditText) view.findViewById(R.id.et_acr);
        RegistrationActivity.p_add = (EditText) view.findViewById(R.id.et_p_address);
        RegistrationActivity.p_country = (EditText) view.findViewById(R.id.et_p_country);
        RegistrationActivity.p_state= (EditText) view.findViewById(R.id.et_p_state);
        RegistrationActivity.s_add = (EditText) view.findViewById(R.id.et_s_address);
        RegistrationActivity.s_country = (EditText) view.findViewById(R.id.et_s_country);
        RegistrationActivity.s_state = (EditText) view.findViewById(R.id.et_s_state);
        RegistrationActivity.t_add = (EditText) view.findViewById(R.id.et_t_address);
        RegistrationActivity.t_country = (EditText) view.findViewById(R.id.et_t_country);
        RegistrationActivity.t_state = (EditText) view.findViewById(R.id.et_t_state);
        return  view;
    }
}
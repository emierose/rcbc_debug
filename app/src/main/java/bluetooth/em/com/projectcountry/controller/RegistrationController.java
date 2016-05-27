package bluetooth.em.com.projectcountry.controller;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import UnlitechDevFramework.src.ud.framework.data.Response;
import UnlitechDevFramework.src.ud.framework.data.enums.Status;
import UnlitechDevFramework.src.ud.framework.webservice.data.WebServiceInfo;
import bluetooth.em.com.projectcountry.R;
import bluetooth.em.com.projectcountry.data.CountryObjects;
import bluetooth.em.com.projectcountry.data.JSONFlag;
import bluetooth.em.com.projectcountry.data.Message;
import bluetooth.em.com.projectcountry.data.Title;
import bluetooth.em.com.projectcountry.model.RegistrationModel;
import bluetooth.em.com.projectcountry.view.RegistrationHolder;
import bluetooth.em.com.projectcountry.view.RegistrationInterface;

/**
 * Created by Em on 5/11/2016.
 */
public class RegistrationController {
    RegistrationModel mModel;
    RegistrationInterface mView;
    public ArrayList<CountryObjects> countryObjects ;
    public ArrayList<CountryObjects> p_stateObjects ;
    public ArrayList<CountryObjects> s_stateObjects ;
    public ArrayList<CountryObjects> t_stateObjects ;
    public ArrayList<CountryObjects> bplace_stateObjects ;
    public static final int TYPE_P_COUNTRY = 2;
    public static final int TYPE_S_COUNTRY= 3;
    public static final int TYPE_T_COUNTRY= 4;
    public static final int TYPE_BPLACE_COUNTRY= 5;
    public RegistrationController(RegistrationInterface view, RegistrationModel model) {
        mModel = model;
        mView = view;
        countryObjects = new ArrayList<CountryObjects>();
        p_stateObjects = new ArrayList<CountryObjects>();
        s_stateObjects = new ArrayList<CountryObjects>();
        t_stateObjects = new ArrayList<CountryObjects>();
        bplace_stateObjects = new ArrayList<CountryObjects>();
    }

    public void requestCountry() {
        try{
        WebServiceInfo wsInfo = new WebServiceInfo("http://52.77.224.133:8089/ws_user/fetch_country_list");
        mModel.sendRequest(mView.getContext(), wsInfo, 1);
        } catch (Exception e) {
            RegistrationHolder holder = mView.getregistrationCredentials();
            holder.tv_p_country.setCompoundDrawables(mView.getContext().getResources().getDrawable(R.drawable.ic_reload),null,null,null);
            holder.tv_p_country.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_reload,0,0,0);
            System.out.println(e.getMessage());
        }
    }

    public void submit() {
        WebServiceInfo wsInfo = new WebServiceInfo("http://52.77.224.133:8089/ws_user/register_user");
        RegistrationHolder holder = mView.getregistrationCredentials();
        String p_country_code = countryObjects.get(holder.p_country.getSelectedItemPosition() - 1).COUNTRY_CODE;
        String s_country_code = countryObjects.get(holder.s_country.getSelectedItemPosition() - 1).COUNTRY_CODE;
        String t_country_code = countryObjects.get(holder.t_country.getSelectedItemPosition() - 1).COUNTRY_CODE;
        String bplace_country_code = countryObjects.get(holder.bday_country.getSelectedItemPosition() - 1).COUNTRY_CODE;
        wsInfo.addParam("username", holder.username.getText().toString());
        wsInfo.addParam("password",holder.username.getText().toString());
        wsInfo.addParam("fname",holder.username.getText().toString());
        wsInfo.addParam("mname",holder.username.getText().toString());
        wsInfo.addParam("lname",holder.username.getText().toString());
        wsInfo.addParam("dob",holder.username.getText().toString());
        wsInfo.addParam("dob_cty",bplace_country_code);
        wsInfo.addParam("dob_state", holder.bday_state.getSelectedItem().toString());
        wsInfo.addParam("marital",holder.username.getText().toString());
        wsInfo.addParam("mother_name",holder.username.getText().toString());
        wsInfo.addParam("nationality",holder.username.getText().toString());
        wsInfo.addParam("tin",holder.username.getText().toString());
        wsInfo.addParam("sss",holder.username.getText().toString());
        wsInfo.addParam("passport",holder.username.getText().toString());
        wsInfo.addParam("acr",holder.username.getText().toString());
        wsInfo.addParam("card_no",holder.username.getText().toString());
        wsInfo.addParam("ccv",holder.username.getText().toString());
        wsInfo.addParam("p_address",holder.username.getText().toString());
        wsInfo.addParam("p_country",p_country_code);
        wsInfo.addParam("p_state",holder.username.getText().toString());
        wsInfo.addParam("s_address",holder.username.getText().toString());
        wsInfo.addParam("s_country",s_country_code);
        wsInfo.addParam("s_state",holder.username.getText().toString());
        wsInfo.addParam("t_address",holder.username.getText().toString());
        wsInfo.addParam("t_country",t_country_code);
        wsInfo.addParam("t_state",holder.username.getText().toString());
        wsInfo.addParam("residential",holder.username.getText().toString());
        wsInfo.addParam("office",holder.username.getText().toString());
        wsInfo.addParam("mobile",holder.username.getText().toString());
        wsInfo.addParam("p_email",holder.username.getText().toString());
        wsInfo.addParam("s_email",holder.username.getText().toString());
        wsInfo.addParam("t_email",holder.username.getText().toString());
        wsInfo.addParam("b_code",holder.username.getText().toString());
        wsInfo.addParam("b_branch",holder.username.getText().toString());

        //Tells the model to send the personal info
        mModel.registrationRequest(mView.getContext(), wsInfo, 0);
    }
    public void processRegistrationResponse(Response response,int type) {
        System.out.println("RESPONSE:" + response.getResponse());
        try {
            if (response.getStatus() == Status.SUCCESS) {
                JSONObject jo = new JSONObject(response.getResponse());
                if (jo.getString(JSONFlag.STATUS).equals(JSONFlag.SUCCESS)) {
                    switch (type) {
                        case 0:
                            break;

                    }
                } else {
                    mView.showError(Title.REGISTRATION, jo.getString(JSONFlag.MESSAGE), null);
                }
            } else {
                mView.showError(Title.REGISTRATION, Message.ERROR_FETCHING_DATA, null);
            }
        } catch (RuntimeException e) {
            mView.showError("", Message.RUNTIME_ERROR, null);
            e.printStackTrace();
        } catch (JSONException e) {
            mView.showError("", Message.JSON_ERROR, null);
            e.printStackTrace();
        }
    }
    public void processResponse(Response response,int type) {
        try {
            if (response.getStatus() == Status.SUCCESS) {
                JSONObject jo = new JSONObject(response.getResponse());
                if (jo.getString(JSONFlag.STATUS).equals(JSONFlag.SUCCESS)) {
                    switch (type) {
                        case 0:
                            break;
                        case 1:
                                JSONArray array_data = jo.getJSONArray("result_data");
                                for (int i = 0; i < array_data.length(); i++) {
                                    JSONObject data = array_data.getJSONObject(i);
                                    CountryObjects ob = new CountryObjects();
                                    ob.COUNTRY_CODE = data.getString("country_code");
                                    ob.COUNTRY_DESC = data.getString("country_desc");
                                    countryObjects.add(ob);
                                }
                            loadCountryList();
                        break;
                        case TYPE_P_COUNTRY:
                            JSONArray array_data2 = jo.getJSONArray("result_data");
                            for (int i = 0; i < array_data2.length(); i++) {
                                JSONObject data = array_data2.getJSONObject(i);
                                CountryObjects ob = new CountryObjects();
                                System.out.println("STATE:" +  data.getString("state_code"));
                                ob.STATE_CODE = data.getString("state_code");
                                ob.STATE_DESC = data.getString("state_desc");
                                p_stateObjects.add(ob);

                            }
                            loadStateList(TYPE_P_COUNTRY);
                            break;
                        case TYPE_S_COUNTRY:
                            JSONArray array_data3 = jo.getJSONArray("result_data");
                            for (int i = 0; i < array_data3.length(); i++) {
                                JSONObject data = array_data3.getJSONObject(i);
                                CountryObjects ob = new CountryObjects();
                                ob.STATE_CODE = data.getString("state_code");
                                ob.STATE_DESC = data.getString("state_desc");
                                s_stateObjects.add(ob);
                            }
                            loadStateList(TYPE_S_COUNTRY);
                            break;
                        case TYPE_T_COUNTRY:
                            JSONArray array_data4 = jo.getJSONArray("result_data");
                            for (int i = 0; i < array_data4.length(); i++) {
                                JSONObject data = array_data4.getJSONObject(i);
                                CountryObjects ob = new CountryObjects();
                                ob.STATE_CODE = data.getString("state_code");
                                ob.STATE_DESC = data.getString("state_desc");
                               t_stateObjects.add(ob);
                            }
                            loadStateList(TYPE_T_COUNTRY);
                            break;
                        case TYPE_BPLACE_COUNTRY:
                            JSONArray array_data5 = jo.getJSONArray("result_data");
                            for (int i = 0; i < array_data5.length(); i++) {
                                JSONObject data = array_data5.getJSONObject(i);
                                CountryObjects ob = new CountryObjects();
                                ob.STATE_CODE = data.getString("state_code");
                                ob.STATE_DESC = data.getString("state_desc");
                                bplace_stateObjects.add(ob);
                            }
                            loadStateList(TYPE_BPLACE_COUNTRY);
                            break;
                    }
                } else {
                    mView.showError(Title.REGISTRATION, jo.getString(JSONFlag.MESSAGE), null);
                }
            } else {
                mView.showError(Title.REGISTRATION, Message.ERROR_FETCHING_DATA, null);
            }
        } catch (RuntimeException e) {
            mView.showError("", Message.RUNTIME_ERROR, null);
            e.printStackTrace();
        } catch (JSONException e) {
            mView.showError("", Message.JSON_ERROR, null);
            e.printStackTrace();
        }
        System.out.println("RESPONSE:" + response.getResponse());


    }

    private void loadStateList(int typePCountry) {
        RegistrationHolder holder = mView.getregistrationCredentials();
        switch (typePCountry){
            case TYPE_P_COUNTRY:
                ArrayList<String> forSpinner = new ArrayList<String>();
                for (int i = 0; i < p_stateObjects.size(); i++) {
                    forSpinner.add(p_stateObjects.get(i).STATE_DESC);
                }
                forSpinner.add(0, "-- Select State --");
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, forSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.p_state.setAdapter(adapter);
                break;
            case TYPE_S_COUNTRY:
                ArrayList<String> forSpinner2 = new ArrayList<String>();
                for (int i = 0; i < s_stateObjects.size(); i++) {
                    forSpinner2.add(s_stateObjects.get(i).STATE_DESC);
                }
                forSpinner2.add(0, "-- Select State --");
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, forSpinner2);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.s_state.setAdapter(adapter2);
                break;
            case TYPE_T_COUNTRY:
                ArrayList<String> forSpinner3 = new ArrayList<String>();
                for (int i = 0; i < t_stateObjects.size(); i++) {
                    forSpinner3.add(t_stateObjects.get(i).STATE_DESC);
                }
                forSpinner3.add(0, "-- Select State --");
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, forSpinner3);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.t_state.setAdapter(adapter3);
                break;
            case TYPE_BPLACE_COUNTRY:
                ArrayList<String> forSpinner4 = new ArrayList<String>();
                for (int i = 0; i < bplace_stateObjects.size(); i++) {
                    forSpinner4.add(bplace_stateObjects.get(i).STATE_DESC);
                }
                forSpinner4.add(0, "-- Select State --");
                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, forSpinner4);
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.bday_state.setAdapter(adapter4);
                break;
        }
    }

    private void loadCountryList() {
            RegistrationHolder holder = mView.getregistrationCredentials();
            holder.tv_p_country.clearAnimation();
            holder.tv_p_state.clearAnimation();
            holder.tv_s_country.clearAnimation();
            holder.tv_s_state.clearAnimation();
            holder.tv_t_country.clearAnimation();
            holder.tv_t_state.clearAnimation();
            holder.tv_place_country.clearAnimation();
            holder.tv_place_state.clearAnimation();
            holder.text.clearAnimation();
            ArrayList<String> forSpinner = new ArrayList<String>();
            for (int i = 0; i < countryObjects.size(); i++) {
                forSpinner.add(countryObjects.get(i).COUNTRY_DESC);
            }
            forSpinner.add(0, "-- Select Country --");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_spinner_item, forSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.p_country.setAdapter(adapter);
        holder.s_country.setAdapter(adapter);
        holder.t_country.setAdapter(adapter);
        holder.bday_country.setAdapter(adapter);
    }

    public AdapterView.OnItemSelectedListener on_p_country_select() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                searchState(position,TYPE_P_COUNTRY);}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
    public AdapterView.OnItemSelectedListener on_s_country_select() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    searchState(position,TYPE_S_COUNTRY);}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
    public AdapterView.OnItemSelectedListener on_t_country_select() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    searchState(position,TYPE_T_COUNTRY);}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
    public AdapterView.OnItemSelectedListener on_bplace_country_select() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    searchState(position,TYPE_BPLACE_COUNTRY);}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
    private void searchState(int position, int type) {
        System.out.println("COUNTRY CODE :" + countryObjects.get(position-1).COUNTRY_CODE+"COUNTRY POS :" + position);
        WebServiceInfo wsInfo = new WebServiceInfo("http://52.77.224.133:8089/ws_user/fetch_state_list");
        String code =  countryObjects.get(position-1).COUNTRY_CODE;
        wsInfo.addParam("c_code",code);
        //Tells the model to send the personal info
        mModel.sendRequest(mView.getContext(), wsInfo, type);
    }
}

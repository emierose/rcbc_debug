package bluetooth.em.com.projectcountry.activity.remittance;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;

import UnlitechDevFramework.src.ud.framework.data.Response;
import bluetooth.em.com.projectcountry.R;
import bluetooth.em.com.projectcountry.controller.PeraPadalaSendController;
import bluetooth.em.com.projectcountry.data.ClientObjects;
import bluetooth.em.com.projectcountry.data.Title;
import bluetooth.em.com.projectcountry.data.adapters.PadalaSearchAdapter;
import bluetooth.em.com.projectcountry.model.PeraPadalaModel;
import bluetooth.em.com.projectcountry.view.PeraPadalaInterface;

/**
 * Created by Em on 6/16/2016.
 */
public class PeraPadalaSend extends Fragment implements PeraPadalaInterface, PeraPadalaModel.PeraPadalaModelObserver {
View view,sender_data,bene_data;
    PeraPadalaModel mModel;
    PeraPadalaSendController mControler;
    private View dialogView;
    AlertDialog mAlertDialog,searchResultDialog;
    AlertDialog.Builder    builder;
    EditText sender_search,bene_search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.remittance_perapadala_send, container, false);
        sender_data = view.findViewById(R.id.include_sender_data);
        bene_data = view.findViewById(R.id.include_bene_data);
        TextView bene = (TextView)bene_data.findViewById(R.id.tv_title);
        bene.setText("Beneficiary");
        mModel = new PeraPadalaModel();
        mModel.registerObserver(this);
        mControler =  new PeraPadalaSendController(this,mModel);
        sender_search = (EditText)sender_data.findViewById(R.id.et_search);
        bene_search = (EditText)bene_data.findViewById(R.id.et_search);
        sender_search.addTextChangedListener(new TextWatcher() {
            private Timer timer=new Timer();
            private final long DELAY = 500; // milliseconds
            String key= "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if(s.length() > 5){
//                    key = s.toString();
//                    timer = new Timer();
//                    timer.schedule(
//                            new TimerTask() {
//                                @Override
//                                public void run() {
                                    // TODO: do what you need here (refresh list)
                                    // you will probably need to use runOnUiThread(Runnable action) for some specific actions
                                 System.out.println("KEY:" + s.toString());
//                                    mControler.searchUser(2, s.toString());
//                                }
//                            },
//                            DELAY
//                    );
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("KEY2:"+s.toString());
                // TODO Auto-generated method stub
            }
        });
        sender_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (sender_search.getRight() - sender_search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        mControler.searchUser(2, sender_search.getText().toString(), "SENDER");
                        return true;
                    }
                }
                return false;
            }
        });
        bene_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (bene_search.getRight() - bene_search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                        showRegistration();
                        mControler.searchUser(2, bene_search.getText().toString(),"BENEFICIARY");
                        return true;
                    }
                }
                return false;
            }
        });

        return view;
    }

    private void showRegistration() {
      builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyleBlue);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.remitrtance_client_registration, null);
        builder.setView(dialogView);
        mControler.registerUser(1);

        builder.setPositiveButton("REGISTER", null);
        builder.setNegativeButton("CANCEL", null);
         mAlertDialog = builder.create();
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                  mControler.registerUser(1);
                    }
                });
            }
        });
        mAlertDialog.show();
//        builder.setOnShowListener();
//        builder.show();
//        dialogView = new Dialog(getActivity());
//        dialogView.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialogView.setContentView(R.layout.remitrtance_client_registration);
//        dialogView.show();
    }

    @Override
    public PeraPadalaHolder getCredentials(int type) {
        PeraPadalaHolder holder = new PeraPadalaHolder();
        if (type == 2){
            holder.firstname = (EditText) dialogView.findViewById(R.id.et_firstname);
        holder.middlename = (EditText) dialogView.findViewById(R.id.et_middlename);
        holder.lastname = (EditText) dialogView.findViewById(R.id.et_lastname);
        holder.bdate = (EditText) dialogView.findViewById(R.id.et_bday);
        holder.mobile = (EditText) dialogView.findViewById(R.id.et_mobile);
        holder.email = (EditText) dialogView.findViewById(R.id.et_email);

        holder.tl_firstname = (TextInputLayout) dialogView.findViewById(R.id.tl_firstname);
        holder.tl_middlename = (TextInputLayout) dialogView.findViewById(R.id.tl_middlename);
        holder.tl_lastname = (TextInputLayout) dialogView.findViewById(R.id.tl_lastname);
        holder.tl_bdate = (TextInputLayout) dialogView.findViewById(R.id.tl_bday);
        holder.tl_mobile = (TextInputLayout) dialogView.findViewById(R.id.tl_mobile);
        holder.tl_email = (TextInputLayout) dialogView.findViewById(R.id.tl_email);
      }else {
            holder.builder = mAlertDialog;
            holder.searchDialog = searchResultDialog;
            holder.sender_data = view.findViewById(R.id.include_sender_data);
            holder.bene_data = view.findViewById(R.id.include_bene_data);

        }
        return holder;
    }

    @Override
    public void showRegistrationDialog(String searchtype) {
        builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyleBlue);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        dialogView = inflater.inflate(R.layout.remitrtance_client_registration, null);
        builder.setView(dialogView);
        builder.setPositiveButton("REGISTER", null);
        builder.setNegativeButton("CANCEL", null);
        mAlertDialog = builder.create();
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        mControler.registerUser(1);
                    }
                });
            }
        });
        mAlertDialog.show();
    }

    @Override
    public void showSearchResultDialo(ArrayList<ClientObjects> searchData,String type) {
        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyleBlue);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.remittance_client_result, null);
        builder.setView(view);
        RecyclerView recList = (RecyclerView)view. findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setAdapter(null);
        builder.setNegativeButton("DISMISS", null);
        searchResultDialog = builder.create();
        PadalaSearchAdapter adaper = new PadalaSearchAdapter(getActivity(),searchData,searchResultDialog,getCredentials(1),type);
        recList.setAdapter(adaper);
        searchResultDialog.show();



    }

    @Override
    public void showError(String title, String message, DialogInterface.OnDismissListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AppCompatAlertDialogStyle);
        builder.setTitle(Title.PERAPADALASEND);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.setOnDismissListener(listener);
        builder.show();
    }

    @Override
    public void secondResponseReceived(Response response, int type) {
        mControler.processResponse(response, type);

    }

    @Override
    public void errorOnRequest(Exception exception) {

    }

    @Override
    public void responseReceived(Response response, int type) {
        mControler.processResponse(response, type);
    }
}

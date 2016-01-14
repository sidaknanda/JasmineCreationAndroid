package com.android.jcandroid;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.jcandroid.Database.DAO;
import com.android.jcandroid.Model.CustomerModel;

public class CustomerAddUpdateFragment extends Fragment implements View.OnClickListener {

    private Button buttonSave;
    private EditText et_Name, et_Phone, et_Address, et_L, et_C, et_W, et_H, et_T, et_S, et_B, et_M, et_NF, et_NB, et_CHK, et_GHR, et_SLVR, et_P, et_TROUSER, et_THG, et_PAJAMI, et_PAJAMI1, et_PAJAMI2, et_PAJAMI3, et_R, et_BR, et_WR, et_RW, et_RH, et_ExtraComments;
    private String string_Name, string_Phone, string_Address, string_L, string_C, string_W, string_H, string_T, string_S, string_B, string_M, string_NF, string_NB, string_CHK, string_GHR, string_SLVR, string_P, string_TROUSER, string_THG, string_PAJAMI, string_PAJAMI1, string_PAJAMI2, string_PAJAMI3, string_R, string_BR, string_WR, string_RW, string_RH, string_ExtraComments;
    private CustomerModel customer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_update_fragment, container, false);

        buttonSave = (Button) view.findViewById(R.id.buttonAddNewSave);
        buttonSave.setOnClickListener(this);

        et_Name = (EditText) view.findViewById(R.id.editTextName);
        et_Phone = (EditText) view.findViewById(R.id.editTextPhone);
        et_Address = (EditText) view.findViewById(R.id.editTextAddress);
        et_L = (EditText) view.findViewById(R.id.editTextL);
        et_C = (EditText) view.findViewById(R.id.editTextC);
        et_W = (EditText) view.findViewById(R.id.editTextW);
        et_H = (EditText) view.findViewById(R.id.editTextH);
        et_T = (EditText) view.findViewById(R.id.editTextT);
        et_S = (EditText) view.findViewById(R.id.editTextS);
        et_B = (EditText) view.findViewById(R.id.editTextB);
        et_M = (EditText) view.findViewById(R.id.editTextM);
        et_NF = (EditText) view.findViewById(R.id.editTextNF);
        et_NB = (EditText) view.findViewById(R.id.editTextNB);
        et_CHK = (EditText) view.findViewById(R.id.editTextCHK);
        et_GHR = (EditText) view.findViewById(R.id.editTextGHR);
        et_SLVR = (EditText) view.findViewById(R.id.editTextSLVR);
        et_P = (EditText) view.findViewById(R.id.editTextP);
        et_TROUSER = (EditText) view.findViewById(R.id.editTextTROUSER);
        et_THG = (EditText) view.findViewById(R.id.editTextTHG);
        et_PAJAMI = (EditText) view.findViewById(R.id.editTextPAJAMI);
        et_PAJAMI1 = (EditText) view.findViewById(R.id.editTextPAJAMI1);
        et_PAJAMI2 = (EditText) view.findViewById(R.id.editTextPAJAMI2);
        et_PAJAMI3 = (EditText) view.findViewById(R.id.editTextPAJAMI3);
        et_R = (EditText) view.findViewById(R.id.editTextR);
        et_BR = (EditText) view.findViewById(R.id.editTextBR);
        et_WR = (EditText) view.findViewById(R.id.editTextWR);
        et_RW = (EditText) view.findViewById(R.id.editTextRW);
        et_RH = (EditText) view.findViewById(R.id.editTextRH);
        et_ExtraComments = (EditText) view.findViewById(R.id.editTextExtra);

        try {
            customer = getArguments().getParcelable(Utility.SELECTED_CUSTOMER);
        } catch (Exception e) {
            e.printStackTrace();
            customer = null;
        }

        if (customer != null) {
            setCustomerToViews();
        }

        return view;
    }

    private void setCustomerToViews() {
        et_Name.setText(customer.getName());
        et_Phone.setText(customer.getPhone());
        et_Address.setText(customer.getAddress());
        et_L.setText(customer.getL());
        et_C.setText(customer.getC());
        et_W.setText(customer.getW());
        et_H.setText(customer.getH());
        et_T.setText(customer.getT());
        et_S.setText(customer.getS());
        et_B.setText(customer.getB());
        et_M.setText(customer.getM());
        et_NF.setText(customer.getNf());
        et_NB.setText(customer.getNb());
        et_CHK.setText(customer.getChk());
        et_GHR.setText(customer.getGhr());
        et_SLVR.setText(customer.getSlvr());
        et_P.setText(customer.getP());
        et_TROUSER.setText(customer.getTrouser());
        et_THG.setText(customer.getThg());
        et_PAJAMI.setText(customer.getPajami());
        et_PAJAMI1.setText(customer.getPajami1());
        et_PAJAMI2.setText(customer.getPajami2());
        et_PAJAMI3.setText(customer.getPajami3());
        et_R.setText(customer.getR());
        et_BR.setText(customer.getBr());
        et_WR.setText(customer.getWr());
        et_RW.setText(customer.getRw());
        et_RH.setText(customer.getRh());
        et_ExtraComments.setText(customer.getExtraComments());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonAddNewSave) {
            string_Name = et_Name.getText().toString();
            string_Phone = et_Phone.getText().toString();
            string_Address = et_Address.getText().toString();
            string_L = et_L.getText().toString();
            string_C = et_C.getText().toString();
            string_W = et_W.getText().toString();
            string_H = et_H.getText().toString();
            string_T = et_T.getText().toString();
            string_S = et_S.getText().toString();
            string_B = et_B.getText().toString();
            string_M = et_M.getText().toString();
            string_NF = et_NF.getText().toString();
            string_NB = et_NB.getText().toString();
            string_CHK = et_CHK.getText().toString();
            string_GHR = et_GHR.getText().toString();
            string_SLVR = et_SLVR.getText().toString();
            string_P = et_P.getText().toString();
            string_TROUSER = et_TROUSER.getText().toString();
            string_THG = et_THG.getText().toString();
            string_PAJAMI = et_PAJAMI.getText().toString();
            string_PAJAMI1 = et_PAJAMI1.getText().toString();
            string_PAJAMI2 = et_PAJAMI2.getText().toString();
            string_PAJAMI3 = et_PAJAMI3.getText().toString();
            string_R = et_R.getText().toString();
            string_BR = et_BR.getText().toString();
            string_WR = et_WR.getText().toString();
            string_RW = et_RW.getText().toString();
            string_RH = et_RH.getText().toString();
            string_ExtraComments = et_ExtraComments.getText().toString();

            if (!string_Name.isEmpty() && !string_Phone.isEmpty()) {
                CustomerModel model = new CustomerModel();
                model.setName(string_Name);
                model.setPhone(string_Phone);
                model.setAddress(string_Address);
                model.setL(string_L);
                model.setC(string_C);
                model.setW(string_W);
                model.setH(string_H);
                model.setT(string_T);
                model.setS(string_S);
                model.setB(string_B);
                model.setM(string_M);
                model.setNf(string_NF);
                model.setNb(string_NB);
                model.setChk(string_CHK);
                model.setGhr(string_GHR);
                model.setSlvr(string_SLVR);
                model.setP(string_P);
                model.setTrouser(string_TROUSER);
                model.setThg(string_THG);
                model.setPajami(string_PAJAMI);
                model.setPajami1(string_PAJAMI1);
                model.setPajami2(string_PAJAMI2);
                model.setPajami3(string_PAJAMI3);
                model.setR(string_R);
                model.setBr(string_BR);
                model.setWr(string_WR);
                model.setRw(string_RW);
                model.setRh(string_RH);
                model.setExtraComments(string_ExtraComments);

                boolean status;
                if (customer != null) {
                    status = DAO.updateCustomer(getActivity(), model, DAO.getRowId(getActivity(), customer));
                } else {
                    status = DAO.createCustomer(getActivity(), model);
                }

                if (status) {
                    Utility.showToast(getActivity(), "Successful");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                } else {
                    Utility.showToast(getActivity(), "Error: Phone number already in USE");
                }
            } else {
                Utility.showToast(getActivity(), "Name and Phone cannot be BLANK !!!");
            }
        }
    }

    public CustomerModel getSelectedCustomer() {
        return customer;
    }
}

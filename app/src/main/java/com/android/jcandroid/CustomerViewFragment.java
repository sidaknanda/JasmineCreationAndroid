package com.android.jcandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.jcandroid.Model.CustomerModel;

public class CustomerViewFragment extends Fragment {

    private CustomerModel customer;
    private TextView tv_Name, tv_Phone, tv_Address, tv_L, tv_C, tv_W, tv_H, tv_T, tv_S, tv_B, tv_M, tv_NF, tv_NB, tv_CHK, tv_GHR, tv_SLVR, tv_P, tv_TROUSER, tv_THG, tv_PAJAMI, tv_PAJAMI1, tv_PAJAMI2, tv_PAJAMI3, tv_R, tv_BR, tv_WR, tv_RW, tv_RH, tv_ExtraComments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_view_fragment, container, false);

        tv_Name = (TextView) view.findViewById(R.id.textViewName);
        tv_Phone = (TextView) view.findViewById(R.id.textViewPhone);
        tv_Address = (TextView) view.findViewById(R.id.textViewAddress);
        tv_L = (TextView) view.findViewById(R.id.textViewL);
        tv_C = (TextView) view.findViewById(R.id.textViewC);
        tv_W = (TextView) view.findViewById(R.id.textViewW);
        tv_H = (TextView) view.findViewById(R.id.textViewH);
        tv_T = (TextView) view.findViewById(R.id.textViewT);
        tv_S = (TextView) view.findViewById(R.id.textViewS);
        tv_B = (TextView) view.findViewById(R.id.textViewB);
        tv_M = (TextView) view.findViewById(R.id.textViewM);
        tv_NF = (TextView) view.findViewById(R.id.textViewNF);
        tv_NB = (TextView) view.findViewById(R.id.textViewNB);
        tv_CHK = (TextView) view.findViewById(R.id.textViewCHK);
        tv_GHR = (TextView) view.findViewById(R.id.textViewGHR);
        tv_SLVR = (TextView) view.findViewById(R.id.textViewSLVR);
        tv_P = (TextView) view.findViewById(R.id.textViewP);
        tv_TROUSER = (TextView) view.findViewById(R.id.textViewTROUSER);
        tv_THG = (TextView) view.findViewById(R.id.textViewTHG);
        tv_PAJAMI = (TextView) view.findViewById(R.id.textViewPAJAMI);
        tv_PAJAMI1 = (TextView) view.findViewById(R.id.textViewPAJAMI1);
        tv_PAJAMI2 = (TextView) view.findViewById(R.id.textViewPAJAMI2);
        tv_PAJAMI3 = (TextView) view.findViewById(R.id.textViewPAJAMI3);
        tv_R = (TextView) view.findViewById(R.id.textViewR);
        tv_BR = (TextView) view.findViewById(R.id.textViewBR);
        tv_WR = (TextView) view.findViewById(R.id.textViewWR);
        tv_RW = (TextView) view.findViewById(R.id.textViewRW);
        tv_RH = (TextView) view.findViewById(R.id.textViewRH);
        tv_ExtraComments = (TextView) view.findViewById(R.id.textViewExtra);

        customer = getArguments().getParcelable(Utility.SELECTED_CUSTOMER);

        setToViews();

        return view;
    }

    private void setToViews() {
        tv_Name.setText(customer.getName());
        tv_Phone.setText(customer.getPhone());
        tv_Address.setText(customer.getAddress());
        tv_L.setText(customer.getL());
        tv_C.setText(customer.getC());
        tv_W.setText(customer.getW());
        tv_H.setText(customer.getH());
        tv_T.setText(customer.getT());
        tv_S.setText(customer.getS());
        tv_B.setText(customer.getB());
        tv_M.setText(customer.getM());
        tv_NF.setText(customer.getNf());
        tv_NB.setText(customer.getNb());
        tv_CHK.setText(customer.getChk());
        tv_GHR.setText(customer.getGhr());
        tv_SLVR.setText(customer.getSlvr());
        tv_P.setText(customer.getP());
        tv_TROUSER.setText(customer.getTrouser());
        tv_THG.setText(customer.getThg());
        tv_PAJAMI.setText(customer.getPajami());
        tv_PAJAMI1.setText(customer.getPajami1());
        tv_PAJAMI2.setText(customer.getPajami2());
        tv_PAJAMI3.setText(customer.getPajami3());
        tv_R.setText(customer.getR());
        tv_BR.setText(customer.getBr());
        tv_WR.setText(customer.getWr());
        tv_RW.setText(customer.getRw());
        tv_RH.setText(customer.getRh());
        tv_ExtraComments.setText(customer.getExtraComments());
    }

    public CustomerModel getSelectedCustomer() {
        return customer;
    }
}
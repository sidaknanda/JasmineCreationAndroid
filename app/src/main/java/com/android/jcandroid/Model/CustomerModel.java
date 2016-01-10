package com.android.jcandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class CustomerModel implements Parcelable {

    public String name;
    public String phone;
    public String address;
    public String l;
    public String c;
    public String w;
    public String h;
    public String t;
    public String s;
    public String b;
    public String m;
    public String nf;
    public String nb;
    public String chk;
    public String ghr;
    public String slvr;
    public String p;
    public String trouser;
    public String thg;
    public String pajami;
    public String pajami1;
    public String pajami2;
    public String pajami3;
    public String r;
    public String br;
    public String wr;
    public String rw;
    public String rh;
    public String extraComments;

    public CustomerModel() {
    }

    public String getPajami3() {
        return pajami3;
    }

    public void setPajami3(String pajami3) {
        this.pajami3 = pajami3;
    }

    public String getPajami1() {
        return pajami1;
    }

    public void setPajami1(String pajami1) {
        this.pajami1 = pajami1;
    }

    public String getPajami2() {
        return pajami2;
    }

    public void setPajami2(String pajami2) {
        this.pajami2 = pajami2;
    }

    public String getExtraComments() {
        return extraComments;
    }

    public void setExtraComments(String extraComments) {
        this.extraComments = extraComments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public String getNb() {
        return nb;
    }

    public void setNb(String nb) {
        this.nb = nb;
    }

    public String getChk() {
        return chk;
    }

    public void setChk(String chk) {
        this.chk = chk;
    }

    public String getGhr() {
        return ghr;
    }

    public void setGhr(String ghr) {
        this.ghr = ghr;
    }

    public String getSlvr() {
        return slvr;
    }

    public void setSlvr(String slvr) {
        this.slvr = slvr;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getTrouser() {
        return trouser;
    }

    public void setTrouser(String trouser) {
        this.trouser = trouser;
    }

    public String getThg() {
        return thg;
    }

    public void setThg(String thg) {
        this.thg = thg;
    }

    public String getPajami() {
        return pajami;
    }

    public void setPajami(String pajami) {
        this.pajami = pajami;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public String getWr() {
        return wr;
    }

    public void setWr(String wr) {
        this.wr = wr;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    protected CustomerModel(Parcel in) {
        name = in.readString();
        phone = in.readString();
        address = in.readString();
        l = in.readString();
        c = in.readString();
        w = in.readString();
        h = in.readString();
        t = in.readString();
        s = in.readString();
        b = in.readString();
        m = in.readString();
        nf = in.readString();
        nb = in.readString();
        chk = in.readString();
        ghr = in.readString();
        slvr = in.readString();
        p = in.readString();
        trouser = in.readString();
        thg = in.readString();
        pajami = in.readString();
        pajami1 = in.readString();
        pajami2 = in.readString();
        pajami3 = in.readString();
        r = in.readString();
        br = in.readString();
        wr = in.readString();
        rw = in.readString();
        rh = in.readString();
        extraComments = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(l);
        dest.writeString(c);
        dest.writeString(w);
        dest.writeString(h);
        dest.writeString(t);
        dest.writeString(s);
        dest.writeString(b);
        dest.writeString(m);
        dest.writeString(nf);
        dest.writeString(nb);
        dest.writeString(chk);
        dest.writeString(ghr);
        dest.writeString(slvr);
        dest.writeString(p);
        dest.writeString(trouser);
        dest.writeString(thg);
        dest.writeString(pajami);
        dest.writeString(pajami1);
        dest.writeString(pajami2);
        dest.writeString(pajami3);
        dest.writeString(r);
        dest.writeString(br);
        dest.writeString(wr);
        dest.writeString(rw);
        dest.writeString(rh);
        dest.writeString(extraComments);
    }

    @SuppressWarnings("unused")
    public static final Creator<CustomerModel> CREATOR = new Creator<CustomerModel>() {
        @Override
        public CustomerModel createFromParcel(Parcel in) {
            return new CustomerModel(in);
        }

        @Override
        public CustomerModel[] newArray(int size) {
            return new CustomerModel[size];
        }
    };

    public static Comparator<CustomerModel> customerNameComparator = new Comparator<CustomerModel>() {

        public int compare(CustomerModel s1, CustomerModel s2) {
            String StudentName1 = s1.getName().toUpperCase();
            String StudentName2 = s2.getName().toUpperCase();
            return StudentName1.compareTo(StudentName2);
        }
    };
}
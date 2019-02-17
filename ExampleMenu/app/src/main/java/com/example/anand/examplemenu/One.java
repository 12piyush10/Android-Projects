package com.example.anand.examplemenu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class One extends Fragment {
TextView abouttxt;
    View view;
    String at;
    public One() {
       at="Courses Offered By GRC" +
                "\nClasses For XI & XII Comm. Students" +
                "\n            For CBSE" +
                "\n->ACCOUNTS" +
                "\n->ECONOMICS" +
                "\n->BUSINESS STUDIES" +
                "\n->MATHS" +
                "\n->CS" +
                "\n->IP" +
                "\n            For RBSE" +
                "\n->ACCOUNTS" +
                "\n->ECONOMICS" +
                "\n->BUSINESS STUDIES" +
                "\n->MATHS" +
                "\n     " +
                "\n           COLLEGE LEVEL" +
                "\n->Classes for B.Comm." +
                "\n->ACCOUNTANCY" +
                "\n->STATISTICS" +
                "\n->INCOME TAX" +
                "\n->COSTING" +
                "\n->FINANCIAL MANAGEMENT" +
                "\n->BUDGETING" +
                "\n" +
                "\n->CPT PREPRATION & CS FOUNDATION" +
                "\n->IBPS(Coming soon)" +
                "\n->CAREER COUNCELLING(FREE FOR ALL)" +
                "\n" +
                "\n                BOOK BANK" +
                "\nThe GRC is running a small BOOK BANK for XI & XII Commerce" +
                "\nstudents for those who cannot afford expensive refresher books." +
                "\nAny student can contact the admin for this FREE service." +
                "\n";
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_one, container, false);

        abouttxt=(TextView)view.findViewById(R.id.aboutTxt);
        abouttxt.setText(at);
        return view;
    }

}

package com.example.logan.plusminusgpacalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class CalculatorInputActivity extends AppCompatActivity {

    final double A_Plus = 4.3;
    final double A = 4;
    final double A_Minus = 3.7;
    final double B_Plus = 3.3;
    final double B = 3;
    final double B_Minus = 2.7;
    final double C_Plus = 2.3;
    final double C = 2;
    final double C_Minus = 1.7;
    final double D_Plus = 1.3;
    final double D = 1;
    final double D_Minus = .7;
    final double F = 0;

    Button btnAddRow;
    Button btnCalculateGPA;

    EditText etCH1;
    EditText etCH2;
    EditText etCH3;
    EditText etCH4;
    EditText etCH5;

    Spinner spnLG1;
    Spinner spnLG2;
    Spinner spnLG3;
    Spinner spnLG4;
    Spinner spnLG5;

    TextView tvTotalCredDynamic;
    String tempBefore;
    String tempAfter;

    ArrayList<EditText> creditListAdded;
    ArrayList<Spinner> gradeListAdded;
    String[] gradesArray = {"Select A Grade","A+","A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F"};
    ArrayList<String> possibleGrades = new ArrayList<>(Arrays.asList(gradesArray));


    int classTotal;
    double gradeTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_input);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        tvTotalCredDynamic = (TextView) findViewById(R.id.totalCreditsDynamicTextView);
        tvTotalCredDynamic.setText("0");
        classTotal = 0;
        gradeTotal = 0;

        final TextWatcher inputTextWatcher = new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    tempAfter = s.toString();
                    if (tempBefore !=null)
                        classTotal = classTotal - Integer.valueOf(tempBefore);
                    classTotal = classTotal + Integer.valueOf(tempAfter);
                    tvTotalCredDynamic.setText(Integer.toString(classTotal));
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                if (!s.toString().equals("") && s.toString() != null)
                    tempBefore = s.toString();
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        };

        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,possibleGrades);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        etCH1 = (EditText) findViewById(R.id.etCred1);
        etCH2 = (EditText) findViewById(R.id.etCred2);
        etCH3 = (EditText) findViewById(R.id.etCred3);
        etCH4 = (EditText) findViewById(R.id.etCred4);
        etCH5 = (EditText) findViewById(R.id.etCred5);

        spnLG1 = (Spinner) findViewById(R.id.spnLG1);
        spnLG2 = (Spinner) findViewById(R.id.spnLG2);
        spnLG3 = (Spinner) findViewById(R.id.spnLG3);
        spnLG4 = (Spinner) findViewById(R.id.spnLG4);
        spnLG5 = (Spinner) findViewById(R.id.spnLG5);

        spnLG1.setAdapter(spinnerAdapter);
        spnLG2.setAdapter(spinnerAdapter);
        spnLG3.setAdapter(spinnerAdapter);
        spnLG4.setAdapter(spinnerAdapter);
        spnLG5.setAdapter(spinnerAdapter);

        etCH1.addTextChangedListener(inputTextWatcher);
        etCH2.addTextChangedListener(inputTextWatcher);
        etCH3.addTextChangedListener(inputTextWatcher);
        etCH4.addTextChangedListener(inputTextWatcher);
        etCH5.addTextChangedListener(inputTextWatcher);

        creditListAdded = new ArrayList<>();
        gradeListAdded = new ArrayList<>();

        btnAddRow = (Button) findViewById(R.id.addRowButton);
        btnCalculateGPA = (Button) findViewById(R.id.calculateGPAButton);

        btnCalculateGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classTotal = 0;
                gradeTotal = 0;

                String CH1 = etCH1.getText().toString();
                String CH2 = etCH2.getText().toString();
                String CH3 = etCH3.getText().toString();
                String CH4 = etCH4.getText().toString();
                String CH5 = etCH5.getText().toString();

                String LG1 = spnLG1.getSelectedItem().toString();
                String LG2 = spnLG2.getSelectedItem().toString();
                String LG3 = spnLG3.getSelectedItem().toString();
                String LG4 = spnLG4.getSelectedItem().toString();
                String LG5 = spnLG5.getSelectedItem().toString();

                if (isNotEmpty(CH1) && !LG1.equals("Select A Grade")) {
                    gradeTotal = gradeTotal + (Integer.valueOf(CH1)* correctValues(LG1));
                    classTotal = classTotal + Integer.valueOf(CH1);
                }
                if (isNotEmpty(CH2) && !LG2.equals("Select A Grade")) {
                    gradeTotal = gradeTotal + (Integer.valueOf(CH2)* correctValues(LG2));
                    classTotal = classTotal + Integer.valueOf(CH2);
                }
                if (isNotEmpty(CH3) && !LG3.equals("Select A Grade")) {
                    gradeTotal = gradeTotal + (Integer.valueOf(CH3)* correctValues(LG3));
                    classTotal = classTotal + Integer.valueOf(CH3);
                }
                if (isNotEmpty(CH4) && !LG4.equals("Select A Grade")) {
                    gradeTotal = gradeTotal + (Integer.valueOf(CH4)* correctValues(LG4));
                    classTotal = classTotal + Integer.valueOf(CH4);
                }
                if (isNotEmpty(CH5) && !LG5.equals("Select A Grade")) {
                    gradeTotal = gradeTotal + (Integer.valueOf(CH5)* correctValues(LG5));
                    classTotal = classTotal + Integer.valueOf(CH5);
                }
                if (gradeListAdded != null && creditListAdded != null && gradeListAdded.size() == creditListAdded.size()) {
                    for (int i = 0; i < gradeListAdded.size(); i++) {
                        if (isNotEmpty(creditListAdded.get(i).getText().toString()) && !gradeListAdded.get(i).getSelectedItem().toString().equals("Select A Grade")) {
                            gradeTotal = gradeTotal + (Integer.valueOf(creditListAdded.get(i).getText().toString()) * correctValues(gradeListAdded.get(i).getSelectedItem().toString()));
                            classTotal = classTotal + Integer.valueOf(creditListAdded.get(i).getText().toString());
                        }
                    }
                }
                if (classTotal == 0)
                    Toast.makeText(CalculatorInputActivity.this, "Fill in more data", Toast.LENGTH_LONG).show();
                else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(CalculatorInputActivity.this);
                    dialog.setCancelable(false);
                    dialog.setTitle("Plus Minus GPA");
                    dialog.setMessage(String.format("Your current GPA is %.2f. Would you like to reset the screen or edit something?",gradeTotal/classTotal));
                    dialog.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(CalculatorInputActivity.this,CalculatorInputActivity.class));
                            finish();

                        }
                    })
                            .setNegativeButton("Edit ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();;
                                }
                            });

                    final AlertDialog alert = dialog.create();
                    alert.show();
                }
            }
        });
        btnAddRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout tableLayout = (TableLayout) findViewById(R.id.tb);
                TableRow tr = new TableRow(CalculatorInputActivity.this);
                tr.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                EditText etCred = new EditText(CalculatorInputActivity.this);
                creditListAdded.add(etCred);
                Spinner spnLG = new Spinner(CalculatorInputActivity.this);
                gradeListAdded.add(spnLG);
                Space space = new Space(CalculatorInputActivity.this);
                TableRow.LayoutParams spaceParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,.03f);
                space.setLayoutParams(spaceParams);

                TableRow.LayoutParams txtParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,1);
                txtParams.setMargins(3,3,3,3);
                etCred.setEms(10);
                etCred.setRawInputType(InputType.TYPE_CLASS_NUMBER);
                etCred.setTextSize(24);
                etCred.setLayoutParams(txtParams);
                etCred.addTextChangedListener(inputTextWatcher);
                tr.addView(etCred);
                tr.addView(space);
                txtParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT,1);

                spnLG.setLayoutParams(txtParams);
                spnLG.setAdapter(spinnerAdapter);
                tr.addView(spnLG);

                tableLayout.addView(tr);
            }
        });
    }
    public boolean isNotEmpty(String value) {
        if (value.equals(""))
            return false;
        return true;
    }
    public double correctValues(String lg) {
        lg = lg.toUpperCase();
        if (lg.length() == 1){
            if (lg.charAt(0) == 'A')
                return A;
            else if (lg.charAt(0) == 'B')
                return B;
            else if (lg.charAt(0) == 'C')
                return C;
            else if (lg.charAt(0) == 'D')
                return D;
            else
                return F;
        }
        else  {
            if (lg.charAt(0) == 'A') {
                if (lg.charAt(1) == '+')
                    return A_Plus;
                else
                    return A_Minus;
            }
            else if (lg.charAt(0) == 'B') {
                if (lg.charAt(1) == '+')
                    return B_Plus;
                else
                    return B_Minus;
            }
            else if (lg.charAt(0) == 'C') {
                if (lg.charAt(1) == '+')
                    return C_Plus;
                else
                    return C_Minus;
            }
            else {
                if (lg.charAt(1) == '+')
                    return D_Plus;
                else
                    return D_Minus;
            }
        }
    }
}


package com.example.hemah.grocerystore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double Amount = 0.0;
    private double taxpercent = 0.10;
    private double itemprice1 = 0.0;
    private double itemprice2 = 0.0;
    private double itemprice3 = 0.0;
    private double weight1 = 0.0;
    private double weight2 = 0.0;
    private double weight3 = 0.0;

    private TextView amountTextView;
    private TextView Taxpercenttextview;
    private TextView Item1textview;
    private TextView Weight1numtextview;
    private TextView Item2textview;
    private TextView Weight2numtextview;
    private TextView Item3textview;
    private TextView Wght3numtextview;
    private TextView Totalamounttextview;
    private TextView TotTaxtextview;
    private TextView Changetextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        amountTextView = (TextView) findViewById(R.id.AmounttextView);
        Taxpercenttextview = (TextView) findViewById(R.id.taxpercenttextView);
        Item1textview = (TextView) findViewById(R.id.item1textView);
        Weight1numtextview = (TextView)  findViewById(R.id.weight1numtextView);
        Item2textview = (TextView) findViewById(R.id.item2textView);
        Weight2numtextview = (TextView) findViewById(R.id.weight2numtextView);
        Item3textview = (TextView) findViewById(R.id.itm3textView);
        Wght3numtextview = (TextView) findViewById(R.id.wght3numtextView);
        Totalamounttextview = (TextView) findViewById(R.id.totalamounttextView);
        TotTaxtextview = (TextView) findViewById(R.id.totTaxtextView);
        Changetextview = (TextView) findViewById(R.id.changetextView);


        Totalamounttextview.setText(currencyFormat.format(0));
        TotTaxtextview.setText(currencyFormat.format(0));
        Changetextview.setText(currencyFormat.format(0));

// set amountEditText's TextWatcher
        EditText moneyEditText =
                (EditText) findViewById(R.id.MoneyeditText);
        moneyEditText.addTextChangedListener(moneyEditTextWatcher);

        EditText Item1EditText =
                (EditText) findViewById(R.id.item1edittext);
        Item1EditText.addTextChangedListener(Item1EditTextWatcher);

        EditText Item2EditText =
                (EditText) findViewById(R.id.item2editText);
        Item2EditText.addTextChangedListener(Item2EditTextWatcher);

        EditText Item3EditText =
                (EditText) findViewById(R.id.item3editText);
        Item3EditText.addTextChangedListener(Item3EditTextWatcher);


        SeekBar TaxSeekBar =
                (SeekBar) findViewById(R.id.taxseekBar);
        TaxSeekBar.setOnSeekBarChangeListener(TaxseekBarListener);
        SeekBar Weight1SeekBar =
                (SeekBar) findViewById(R.id.weight1seekBar);
        Weight1SeekBar.setOnSeekBarChangeListener(Weight1seekBarListener);
        SeekBar Weight2SeekBar =
                (SeekBar) findViewById(R.id.weight2seekBar);
        Weight2SeekBar.setOnSeekBarChangeListener(Weight2seekBarListener);

        SeekBar Weight3SeekBar =
                (SeekBar) findViewById(R.id.weight3seekBar);
        Weight3SeekBar.setOnSeekBarChangeListener(Weight3seekBarListener);

    }




    private final OnSeekBarChangeListener TaxseekBarListener =
            new OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    taxpercent = progress / 100.0;
                    calculate();
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            };

    private final OnSeekBarChangeListener Weight1seekBarListener =
            new OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    weight1 = progress;
                    calculate();
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            };

    private final OnSeekBarChangeListener Weight2seekBarListener =
            new OnSeekBarChangeListener() {

                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    weight2 = progress;
                    calculate();
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            };
    private final OnSeekBarChangeListener Weight3seekBarListener =
            new OnSeekBarChangeListener() {

                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    weight3 = progress;
                    calculate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }
                @Override

                public void onStopTrackingTouch(SeekBar seekBar) { }
            };


    private final TextWatcher moneyEditTextWatcher = new TextWatcher() {


        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try{
                Amount = Double.parseDouble(s.toString()) / 100.0;
                amountTextView.setText(currencyFormat.format(Amount));
            }
            catch (NumberFormatException e) {
                amountTextView.setText("");
                Amount = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
    private final TextWatcher Item1EditTextWatcher = new TextWatcher() {


        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try{
                itemprice1 = Double.parseDouble(s.toString()) / 100.0;
                Item1textview.setText(currencyFormat.format(itemprice1));
            }
            catch (NumberFormatException e) {
                Item1textview.setText("");
                itemprice1 = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }
        @Override

        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    private final TextWatcher Item2EditTextWatcher = new TextWatcher() {


        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try{
                itemprice2 = Double.parseDouble(s.toString()) / 100.0;
                Item2textview.setText(currencyFormat.format(itemprice2));
            }
            catch (NumberFormatException e) {
                Item2textview.setText("");
                itemprice2 = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
    private final TextWatcher Item3EditTextWatcher = new TextWatcher() {


        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try{
                itemprice3 = Double.parseDouble(s.toString()) / 100.0;
                Item3textview.setText(currencyFormat.format(itemprice3));
            }
            catch (NumberFormatException e) {
                Item3textview.setText("");
                itemprice3 = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
    private void calculate() {

        Taxpercenttextview.setText(percentFormat.format(taxpercent));
        String wt1=Double.toString(weight1);
        Weight1numtextview.setText(wt1);
        String wt2=Double.toString(weight2);
        Weight2numtextview.setText(wt2);
        String wt3=Double.toString(weight3);
        Wght3numtextview.setText(wt3);

        double total = (weight1*itemprice1)+(weight2*itemprice2)+(weight3*itemprice3);
        double tax = total * taxpercent;
        double change =Amount-(total+tax);

        Totalamounttextview.setText(currencyFormat.format(total));
        TotTaxtextview.setText(currencyFormat.format(tax));
        Changetextview.setText(currencyFormat.format(change));
    }




}



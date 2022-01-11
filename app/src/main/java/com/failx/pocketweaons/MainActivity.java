package com.failx.pocketweaons;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String MATERIAL1 = "countmaterial1";
    public static final String MATERIAL2 = "countmaterial2";
    public static final String MATERIAL3 = "countmaterial3";
    public static final String MONEY = "countmaterial4";
    public static final String LEVEL = "level";

    private TextView material1;
    private TextView material2;
    private TextView material3;
    private TextView material4;
    private TextView material1num;
    private TextView material2num;
    private TextView material3num;
    private TextView material4num;
    private TextView actionBox1;
    private TextView actionBox2;
    private TextView actionBox3;
    private TextView bar_percent;

    private TextView messageBox;
    private TextView plus_text1;
    private TextView plus_text2;
    private TextView plus_text3;
    private TextView minus_text1;
    private TextView minus_text2;
    private TextView minus_text3;
    private int plus_count = 1;
    private int minus_count = 1;
    private boolean swiped = false;

    private ImageView batarang;
    private ImageView batsuit;
    private ImageView batarang_sell;

    private SeekBar batarang_bar;
    private Switch sw_batarang;
    private Switch sw_batarang_sell;

    private int countmaterial1 = 0;
    private int countmaterial2 = 0;
    private int countmaterial3 = 0;
    private float countmaterial4 = (float) 0.00;

    final int state1 = 10;
    final int state2 = 20;
    final int state3 = 30;
    final int state4 = 40;
    final int state5 = 50;
    final int state6 = 60;
    final int state7 = 70;
    final int state8 = 100;

    final int money1 = 10;
    final int money2 = 20;
    final int money3 = 30;
    final int money4 = 40;
    final int money5 = 50;
    final int money6 = 60;
    final int money7 = 70;
    final int money8 = 80;
    final int money9 = 90;
    final int money10 = 100;
    final int money11 = 110;
    final int money12 = 120;
    final int money13 = 130;
    final int money14 = 140;

    private int current_level = 1;
    private int next_level = 1;
    private float percent = (float) 0.50;

    boolean action1_box_clicked = false;
    boolean action2_box_clicked = false;
    boolean action3_box_clicked = false;

    private int window_height;
    private int window_width;
    CountDownTimer batarangs;
    CountDownTimer sell_batarangs;
    int delay_time_bats_sell = 1000;
    final int delay_time_bats = 500;
    int marketing = 100;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        //LoadData();

        material1 = findViewById(R.id.material1);
        material2 = findViewById(R.id.material2);
        material3 = findViewById(R.id.material3);
        material4 = findViewById(R.id.material4);
        material1num = findViewById(R.id.material1num);
        material2num = findViewById(R.id.material2num);
        material3num = findViewById(R.id.material3num);
        material4num = findViewById(R.id.material4num);
        actionBox1 = findViewById(R.id.actionBox1);
        actionBox2 = findViewById(R.id.actionBox2);
        actionBox3 = findViewById(R.id.actionBox3);
        bar_percent = findViewById(R.id.bar_percent);
        updateResources();

        messageBox = findViewById(R.id.messageBox);
        plus_text1 = findViewById(R.id.plus_text1);
        plus_text2 = findViewById(R.id.plus_text2);
        plus_text3 = findViewById(R.id.plus_text3);
        minus_text1 = findViewById(R.id.minus_text1);
        minus_text2 = findViewById(R.id.minus_text2);
        minus_text3 = findViewById(R.id.minus_text3);

        batarang = findViewById(R.id.batarang);
        batsuit = findViewById(R.id.batsuit);
        batarang_sell = findViewById(R.id.batarang_sell);
        batarang_bar = findViewById(R.id.batarang_bar);
        sw_batarang = findViewById(R.id.sw_batarang);
        sw_batarang_sell = findViewById(R.id.sw_batarang_sell);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        window_height = displayMetrics.heightPixels;
        window_width = displayMetrics.widthPixels;

        batarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BatarangAdd();
            }
        });

        batsuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                batsuit.startAnimation(bounce);
                countmaterial2++;                                   //count up the clicked material
                updateResources();                                  //show new count and gets new Actions
            }
        });

        batarang_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BatarangSell();
            }
        });

        actionBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                actionBox1.setAnimation(bounce);
                actionBox1.setVisibility(View.GONE);
                action1_box_clicked = true;
                ActionEvents();                                     //looking for new events
            }
        });

        actionBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                actionBox2.setAnimation(bounce);
                actionBox2.setVisibility(View.GONE);
                action2_box_clicked = true;
            }
        });

        actionBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
                actionBox3.setAnimation(bounce);
                actionBox3.setVisibility(View.GONE);
                action3_box_clicked = true;
            }
        });

        sw_batarang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sw_batarang.getThumbDrawable().setTint(getColor(R.color.batman));
                    sw_batarang.getTrackDrawable().setTint(getColor(R.color.money));
                    sw_batarang.setText(R.string.switch_auto);
                    batarangs.start();
                } else {
                    sw_batarang.getThumbDrawable().setTint(getColor(R.color.batman));
                    sw_batarang.getTrackDrawable().setTint(getColor(R.color.red));
                    sw_batarang.setText(R.string.switch_self);
                    batarangs.cancel();
                }
            }
        });

        sw_batarang_sell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sw_batarang_sell.getThumbDrawable().setTint(getColor(R.color.batman));
                    sw_batarang_sell.getTrackDrawable().setTint(getColor(R.color.money));
                    sw_batarang_sell.setText(R.string.switch_auto);
                    sell_batarangs.start();

                } else {
                    sw_batarang_sell.getThumbDrawable().setTint(getColor(R.color.batman));
                    sw_batarang_sell.getTrackDrawable().setTint(getColor(R.color.red));
                    sw_batarang_sell.setText(R.string.switch_self);
                    sell_batarangs.cancel();
                }
            }
        });

        batarang_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            Animation bar_zoom_in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomin);
            Animation bar_zoom_out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoomout);

            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 55) {
                    bar_percent.setTextColor(getColor(R.color.money));
                } else if (progress < 45) {
                    bar_percent.setTextColor(getColor(R.color.green));
                } else {
                    bar_percent.setTextColor(getColor(R.color.white));
                }
                bar_percent.setText(progress + "%");
                percent = (float) (progress / 100.00);                         //price of selling
                delay_time_bats_sell = progress * marketing;                   //speed of automation
                if (sw_batarang_sell.isChecked()) {
                    sell_batarangs.cancel();
                    sell_batarangs.start();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                bar_percent.startAnimation(bar_zoom_in);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                bar_percent.startAnimation(bar_zoom_out);

            }
        });

        IntializeTimers();
    }

    void BatarangAdd() {
        Animation bounce;
        if (swiped) {
            bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce_swiped);
        } else {
            bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
        }
        batarang.startAnimation(bounce);
        if (current_level == 1 && countmaterial1 == 0) {    //showing entrance Level
            countmaterial1++;                               //count up the clicked material
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
            batarangPlusAnimation("1");
        } else if (current_level <= 4) {
            countmaterial1++;                               //count up the clicked material
            batarangPlusAnimation("1");
        } else if (current_level <= 8) {
            countmaterial1 += 5;                     //count up the clicked material higher
            batarangPlusAnimation("5");
        } else if (current_level <= 12) {
            countmaterial1 += 10;                     //count up the clicked material higher
            batarangPlusAnimation("10");
        } else if (current_level <= 16) {
            countmaterial1 += 15;                     //count up the clicked material higher
            batarangPlusAnimation("15");
        } else if (current_level <= 20) {
            countmaterial1 += 40;                     //count up the clicked material higher
            batarangPlusAnimation("40");
        } else {
            countmaterial1 += 50;                     //count up the clicked material higher
            batarangPlusAnimation("50");
        }
        updateResources();                             //show new count and gets new Actions
    }

    void BatarangSell() {                                             //converts material1 in money
        Animation bounce;
        if (swiped) {
            bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce_swiped);
        } else {
            bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
        }
        batarang_sell.startAnimation(bounce);
        float add_show = 0;
        if (current_level <= 8) {
            if (countmaterial1 <= 0) {                      //for not going under 0 materials
                add_show = countmaterial1 * percent;
                countmaterial4 += add_show;
                countmaterial4 = (float) (Math.round(countmaterial4 * 100) / 100.0);
                countmaterial1 = 0;
            } else {                                           //converts material1 in money
                countmaterial1--;
                add_show = 1 * percent;
                countmaterial4 += add_show;
                countmaterial4 = (float) (Math.round(countmaterial4 * 100) / 100.0);
            }
        } else if (current_level <= 16) {                      //for not going under 0 materials
            if (countmaterial1 <= 10) {
                add_show = countmaterial1 * percent;
                countmaterial4 += add_show;
                countmaterial4 = (float) (Math.round(countmaterial4 * 100) / 100.0);
                countmaterial1 = 0;
            } else {                                           //converts material1 in money
                countmaterial1 -= 10;
                add_show = 10 * percent;
                countmaterial4 += add_show;
                countmaterial4 = (float) (Math.round(countmaterial4 * 100) / 100.0);
            }
        }
        add_show = (float) (Math.round(add_show * 100) / 100.0);
        batarangSellAnimation("" + add_show);
        updateResources();
    }

    void GameEvents() {
        if (next_level < current_level) {                         //For no repeated statements
            Animation bounce_mat1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
            Animation bounce_mat2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
            Animation bounce_mat3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
            Animation bounce_mat4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
            switch (current_level) {
                case 2: {
                    //Material 1 Introduction
                    messageBoxIn("You are working very resolute Master Wayne!");
                    material1.setTextColor(getColor(R.color.green));
                    material1num.setTextColor(getColor(R.color.green));
                    material1num.startAnimation(bounce_mat1);
                    ActionBoxAppear(actionBox1, getString(R.string.actionBox));
                    break;
                }
                case 3: {
                    messageBoxIn("How about sell them for Wayne Enterprises?");

                    ActionBoxEnable(actionBox1, getString(R.string.actionBoxEvent1));
                    break;
                }
                case 4: {
                    messageBoxIn("You stored " + countmaterial1 + " Batarangs");
                    material1num.startAnimation(bounce_mat1);
                    break;
                }
                case 5: {
                    messageBoxIn("You shouldn't work so hard Master Wayne. Let me Help you.");
                    sw_batarang.setVisibility(View.VISIBLE);
                    sw_batarang_sell.setVisibility(View.VISIBLE);
                    batarang_bar.setVisibility(View.VISIBLE);
                    break;
                }
                case 6: {
                    messageBoxIn("You stored " + countmaterial1 + " Batarangs");
                    material1num.startAnimation(bounce_mat1);
                    messageBoxIn("You earned " + countmaterial4 + " $");
                    material4num.startAnimation(bounce_mat4);
                    break;
                }
                case 7: {
                    break;
                }
                case 8: {
                    //Material 2 Introduction
                    moveButtons();
                    messageBoxIn("You need a suite Master Wayne.");
                    material2.setVisibility(View.VISIBLE);
                    material2num.setVisibility(View.VISIBLE);
                    material2num.startAnimation(bounce_mat2);
                    batsuit.setVisibility(View.VISIBLE);
                    break;
                }
                case 9: {
                    //Material 2
                    messageBoxIn("You stored " + countmaterial2 + " Batsuits");
                    material2.setTextColor(getColor(R.color.green));
                    material2num.setTextColor(getColor(R.color.green));
                    material2num.startAnimation(bounce_mat2);
                    break;
                }
                case 10: {
                    break;
                }
                case 11: {
                    break;
                }
                case 12: {
                    break;
                }
                case 13: {
                    break;
                }
                case 14: {
                    break;
                }
                case 15: {
                    break;
                }
                case 16: {
                    //Material 2 Introduction
                    messageBoxIn("How about a vehicle Master Wayne?");
                    material3.setVisibility(View.VISIBLE);
                    material3num.setVisibility(View.VISIBLE);
                    material3num.startAnimation(bounce_mat3);
                    break;
                }
                case 17: {
                    messageBoxIn("You stored " + countmaterial3 + " Batmobils");
                    material3.setTextColor(getColor(R.color.green));
                    material3num.setTextColor(getColor(R.color.green));
                    material3num.startAnimation(bounce_mat3);
                    break;
                }
                case 18: {
                    break;
                }
                case 19: {
                    break;
                }
                case 20: {
                    break;
                }
                case 21: {
                    break;
                }
                case 22: {
                    break;
                }
                case 23: {
                    break;
                }
                case 24: {
                    break;
                }
                case 25: {
                    break;
                }
                default: {
                    messageBoxIn("Sorry a problem occurred.");
                }
            }
            next_level = current_level;
        }
    }

    void ActionEvents() {
        Animation bounce_mat1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
        Animation bounce_mat2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
        Animation bounce_mat3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
        Animation bounce_mat4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
        switch (current_level) {
            case 2: {
                break;
            }
            case 3: {
                if (action1_box_clicked) {
                    batarang_sell.setVisibility(View.VISIBLE);
                    material1num.startAnimation(bounce_mat1);
                    action1_box_clicked = false;
                }
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
            case 6: {
                break;
            }
            case 7: {
                break;
            }
            case 8: {
                if (action1_box_clicked) {          //muss noch festlegen wann welche actionBox
                    material2.setVisibility(View.VISIBLE);
                    material2num.setVisibility(View.VISIBLE);
                }
                break;
            }
            case 9: {
                break;
            }
            case 10: {
                break;
            }
            case 11: {
                break;
            }
            case 12: {
                break;
            }
            case 13: {
                break;
            }
            case 14: {
                break;
            }
            case 15: {
                break;
            }
            case 16: {
                if (action1_box_clicked) {          //muss noch festlegen wann welche actionBox
                    material3.setVisibility(View.VISIBLE);
                    material3num.setVisibility(View.VISIBLE);
                }
                break;
            }
            case 17: {
                break;
            }
            case 18: {
                break;
            }
            case 19: {
                break;
            }
            case 20: {
                break;
            }
            case 21: {
                break;
            }
            case 22: {
                break;
            }
            case 23: {
                break;
            }
            case 24: {
                break;
            }
            case 25: {
                break;
            }
            default: {
                messageBoxIn("Sorry a problem occurred from the ActionEvents");
            }
        }
    }

    void ActionBoxAppear(TextView actionBox, String string_of_actionbox) {
        actionBox.setText(string_of_actionbox);
        actionBox.setVisibility(View.VISIBLE);
        actionBox.setBackgroundColor(getColor(R.color.action_box_disabled));
        actionBox.setTextColor(getColor(R.color.bat_screen));
        actionBox.setClickable(false);
    }

    void ActionBoxEnable(TextView actionBox, String string_of_actionbox) {
        actionBox.setText(string_of_actionbox);
        actionBox.setBackgroundColor(getColor(R.color.action_box_enabled));
        actionBox.setTextColor(getColor(R.color.white));
        actionBox.setClickable(true);
    }

    @SuppressLint("SetTextI18n")
    void batarangPlusAnimation(String counter) {
        Animation plus1;
        Animation plus2;
        Animation plus3;
        if (swiped) {
            plus1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus_swiped);
            plus2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus_swiped);
            plus3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus_swiped);
        } else {
            plus1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus);
            plus2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus);
            plus3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus);
        }
        switch (plus_count) {
            case 1: {
                plus_text1.setText("+" + counter);
                plus_text1.setVisibility(View.VISIBLE);
                plus_text1.startAnimation(plus1);
                plus_count = 2;
                break;
            }
            case 2: {
                plus_text2.setText("+" + counter);
                plus_text2.setVisibility(View.VISIBLE);
                plus_text2.startAnimation(plus2);
                plus_count = 3;
                break;
            }
            case 3: {
                plus_text3.setText("+" + counter);
                plus_text3.setVisibility(View.VISIBLE);
                plus_text3.startAnimation(plus3);
                plus_count = 1;
                break;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    void batarangSellAnimation(String counter) {
        Animation plus1;
        Animation plus2;
        Animation plus3;
        if (swiped) {
            plus1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus_swiped);
            plus2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus_swiped);
            plus3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus_swiped);
        } else {
            plus1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus);
            plus2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus);
            plus3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.plus);
        }
        switch (minus_count) {
            case 1: {
                minus_text1.setText("+" + counter);
                minus_text1.setVisibility(View.VISIBLE);
                minus_text1.startAnimation(plus1);
                minus_count = 2;
                break;
            }
            case 2: {
                minus_text2.setText("+" + counter);
                minus_text2.setVisibility(View.VISIBLE);
                minus_text2.startAnimation(plus2);
                minus_count = 3;
                break;
            }
            case 3: {
                minus_text3.setText("+" + counter);
                minus_text3.setVisibility(View.VISIBLE);
                minus_text3.startAnimation(plus3);
                minus_count = 1;
                break;
            }
        }
    }

    public void IntializeTimers() {

        batarangs = new CountDownTimer(10000, delay_time_bats) {
            @Override
            public void onTick(long millisUntilFinished) {
                BatarangAdd();
            }

            @Override
            public void onFinish() {
                this.start();
            }
        };
        sell_batarangs = new CountDownTimer(10000, delay_time_bats_sell) {
            @Override
            public void onTick(long millisUntilFinished) {
                BatarangSell();
            }

            @Override
            public void onFinish() {
                onTick(delay_time_bats_sell);
                this.start();
            }
        };
    }

    @SuppressLint("SetTextI18n")
    void updateResources() {
        material1num.setText("" + countmaterial1);
        material2num.setText("" + countmaterial2);
        material3num.setText("" + countmaterial3);
        material4num.setText("" + countmaterial4 + " $");
        Level();
        GameEvents();
    }

    void messageBoxIn(String message) {
        messageBox.append("\n-" + message);
        messageBox.setMovementMethod(new ScrollingMovementMethod());
    }

    void Level() {
        if (countmaterial1 >= state8 && countmaterial2 >= state8 && countmaterial3 >= state8 && countmaterial4 >= money14 && current_level == 24) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial1 >= state5 && countmaterial3 >= state7 && countmaterial4 >= money13 && current_level == 23) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial3 >= state6 && countmaterial4 >= money12 && current_level == 22) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial2 >= state7 && countmaterial3 >= state5 && current_level == 21) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial3 >= state4 && current_level == 20) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial3 >= state3 && countmaterial4 >= money11 && current_level == 19) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial3 >= state2 && countmaterial4 >= money10 && current_level == 18) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial3 >= state1 && current_level == 17) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial4 >= money9 && current_level == 16) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial1 >= state8 && countmaterial2 >= state7 && current_level == 15) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial2 >= state6 && countmaterial4 >= money8 && current_level == 14) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial2 >= state5 && countmaterial4 >= money7 && current_level == 13) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial2 >= state4 && current_level == 12) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial2 >= state3 && countmaterial4 >= money6 && current_level == 11) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial2 >= state2 && current_level == 10) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial2 >= state1 && current_level == 9) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial4 >= money5 && current_level == 8) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial1 >= state7 && countmaterial4 >= money4 && current_level == 7) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial1 >= state6 && countmaterial4 >= money3 && current_level == 6) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial1 >= state5 && current_level == 5) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial1 >= state4 && countmaterial4 >= money2 && current_level == 4) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial4 >= money1 && current_level == 3) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial1 >= state2 && current_level == 2) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        } else if (countmaterial1 >= state1 && current_level == 1) {
            current_level++;
            Toast.makeText(MainActivity.this, "Level: " + current_level, Toast.LENGTH_SHORT).show();
        }
        SaveData();
    }

    public void SaveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(MATERIAL1, countmaterial1);
        editor.putInt(MATERIAL2, countmaterial2);
        editor.putInt(MATERIAL3, countmaterial3);
        editor.putFloat(MONEY, countmaterial4);
        editor.putInt(LEVEL, current_level);
        editor.apply();
    }

    public void LoadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        countmaterial1 = sharedPreferences.getInt(MATERIAL1, 0);
        countmaterial2 = sharedPreferences.getInt(MATERIAL2, 0);
        countmaterial3 = sharedPreferences.getInt(MATERIAL3, 0);
        countmaterial4 = sharedPreferences.getFloat(MONEY, 0);
        current_level = sharedPreferences.getInt(LEVEL, 0);
    }

    void moveButtons() {
        ObjectAnimator batarang_a = ObjectAnimator.ofFloat(batarang, "translationX", -((float) (window_width * 0.3)));
        ObjectAnimator batarang_sell_a = ObjectAnimator.ofFloat(batarang_sell, "translationX", -((float) (window_width * 0.3)));
        ObjectAnimator batarang_bar_a = ObjectAnimator.ofFloat(batarang_bar, "translationX", -((float) (window_width * 0.3)));
        ObjectAnimator sw_batarang_a = ObjectAnimator.ofFloat(sw_batarang, "translationX", -((float) (window_width * 0.3)));
        ObjectAnimator sw_batarang_sell_a = ObjectAnimator.ofFloat(sw_batarang_sell, "translationX", -((float) (window_width * 0.3)));
        ObjectAnimator plus_text1a = ObjectAnimator.ofFloat(plus_text1, "translationX", -((float) (window_width * 0.3)));
        ObjectAnimator plus_text2a = ObjectAnimator.ofFloat(plus_text2, "translationX", -((float) (window_width * 0.3)));
        ObjectAnimator plus_text3a = ObjectAnimator.ofFloat(plus_text3, "translationX", -((float) (window_width * 0.3)));
        ObjectAnimator minus_text1a = ObjectAnimator.ofFloat(minus_text1, "translationX", -((float) (window_width * 0.3)));
        ObjectAnimator minus_text2a = ObjectAnimator.ofFloat(minus_text2, "translationX", -((float) (window_width * 0.3)));
        ObjectAnimator minus_text3a = ObjectAnimator.ofFloat(minus_text3, "translationX", -((float) (window_width * 0.3)));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(batarang_a, batarang_sell_a, batarang_bar_a, sw_batarang_a,
                sw_batarang_sell_a, plus_text1a, plus_text2a, plus_text3a, minus_text1a, minus_text2a, minus_text3a);
        animatorSet.setDuration(800);
        animatorSet.start();
        swiped = true;
    }
}
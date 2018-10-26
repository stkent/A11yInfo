package com.stkent.a11yinfoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.stkent.a11yinfo.A11yInfo;
import com.stkent.a11yinfo.A11yService;

public final class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final A11yInfo a11yInfo = new A11yInfo(this);

        // Enabled accessibility services:
        final StringBuilder infoStringBuilder = new StringBuilder()
                .append("Enabled a11y services: ")
                .append("\n");

        for (final A11yService a11yService : a11yInfo.getEnabledA11yServices()) {
            infoStringBuilder
                    .append(a11yService.toString())
                    .append("\n");
        }

        // Font scale:
        infoStringBuilder
                .append("\n")
                .append("Font scale: ")
                .append("\n")
                .append(a11yInfo.getFontScale());

        // Display scale:
        infoStringBuilder
                .append("\n\n")
                .append("Display scale: ")
                .append("\n")
                .append(a11yInfo.getDisplayScale());

        // Display inversion:
        infoStringBuilder
                .append("\n\n")
                .append("Display inversion enabled: ")
                .append("\n")
                .append(a11yInfo.isDisplayInversionEnabled());

        textView.setText(infoStringBuilder.toString());
    }

}

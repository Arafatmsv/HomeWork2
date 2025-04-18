package com.ara.homework2;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        String emailText = email.getText().toString().trim();
//        String passwordText = password.getText().toString().trim();
//        if (emailText.isEmpty() && passwordText.isEmpty()) {
//            button.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.gray));
//        } else {
//            button.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.orange));
//        }
        TextView email = findViewById(R.id.email);
        TextView password = findViewById(R.id.password);
        Button button = findViewById(R.id.button);
        Context context = this;

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();

                if (emailText.isEmpty() || passwordText.isEmpty()) {
                    button.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.gray));
                } else {
                    button.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.orange));
                }
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        };

        email.addTextChangedListener(watcher);
        password.addTextChangedListener(watcher);

        button.setOnClickListener(view -> {
            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            ConstraintLayout main = findViewById(R.id.main);

            if (emailText.equals("admin") && passwordText.equals("admin")) {
                LinearLayout container_form = findViewById(R.id.container_form);
                TextView entry_text = findViewById(R.id.entry_text);
                TextView other_text1 = findViewById(R.id.other_text1);
                TextView other_text2 = findViewById(R.id.other_text2);

                container_form.setVisibility(view.GONE);
                entry_text.setVisibility(view.GONE);
                other_text1.setVisibility(view.GONE);
                other_text2.setVisibility(view.GONE);

                Snackbar.make(main, "Вы успешно зарегистрировались", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(main, "Неправильный логин и пароль", Snackbar.LENGTH_SHORT).show();
            }
        });

    }
}
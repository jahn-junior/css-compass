package edu.tacoma.uw.mockprototype;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        findViewById(R.id.set_button).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetProgramActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.study_button).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), StudySkillsActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.resources_button).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ResourcesActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.about_button).setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
        });

        // This will add the listener to the logout button, setting the activity as the loginActivity
        findViewById(R.id.logout_button).setOnClickListener(v -> {
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
                sharedPreferences.edit().putBoolean(getString(R.string.LOGGEDIN), false).commit();

                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
        });
    }
}
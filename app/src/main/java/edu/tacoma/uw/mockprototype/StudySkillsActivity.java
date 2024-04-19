package edu.tacoma.uw.mockprototype;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudySkillsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_study_skills);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.advising_button).setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/set/advising");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        findViewById(R.id.workshop_button).setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/set/students/390-workshops");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        findViewById(R.id.leetcode_button).setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://leetcode.com/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        findViewById(R.id.tlc_button).setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/tlc");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
    }
}
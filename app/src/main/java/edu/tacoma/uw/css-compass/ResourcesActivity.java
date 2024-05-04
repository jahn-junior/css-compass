package edu.tacoma.uw.mockprototype;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resources);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.pantry_button).setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/equity-center/pantry");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        findViewById(R.id.study_space_button).setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/uuf/campus-study-spaces");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        findViewById(R.id.dubnet_button).setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/involvement/dubnet");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        findViewById(R.id.parking_button).setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.tacoma.uw.edu/fa/facilities/transportation");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });
    }
}
package cassule.braulio.com.animatedvectorexample;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    CustomSnackbar customSnackbar;
    ImageView snackImage;
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackbar();
            }
        });
    }

    private void showSnackbar() {
        View v = findViewById(R.id.main_activity_layout);

        if (customSnackbar == null){
            customSnackbar = CustomSnackbar.Builder(MainActivity.this)
                    .layout(R.layout.snackbar_layout)
                    .duration(CustomSnackbar.LENGTH.LONG)
                    .swipe(false)
                    .build(v);
            customSnackbar.show();
            snackImage = customSnackbar.getContentView().findViewById(R.id.animated_snack_image);
            drawable = snackImage.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
        } else {
            customSnackbar.show();
            snackImage = customSnackbar.getContentView().findViewById(R.id.animated_snack_image);
            drawable = snackImage.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
        }
    }
}

package tutorials.hackbulgaria.com.week0_layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity implements View.OnClickListener {

    private View topView;
    private View middleView;
    private View bottomView;

    private int[] colorsList;
    private int currentColorPosition;
    private int currentTopColor;
    private int currentMiddleColor;
    private int currentBottomColor;
    private boolean isVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topView = findViewById(R.id.topView);
        middleView = findViewById(R.id.middleView);
        bottomView = findViewById(R.id.bottomView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        colorsList = getResources().getIntArray(R.array.colors_list);

        topView.setOnClickListener(this);
        middleView.setOnClickListener(this);
        bottomView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.topView:
                currentTopColor = changeColor(view);
                break;

            case R.id.middleView:
                currentMiddleColor = changeColor(view);
                break;

            case R.id.bottomView:
                currentBottomColor = changeColor(view);
                break;

            default:
                break;
        }
    }


    private int changeColor(View view) {

        int selectedColor = 0;

        if (view != null) {

            if (currentColorPosition < colorsList.length) {

                selectedColor = colorsList[currentColorPosition];
                view.setBackgroundColor(selectedColor);

                currentColorPosition++;
            } else {
                currentColorPosition = 0;
            }
        }

        return selectedColor;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = null;

        switch (item.getItemId()) {

            case R.id.action_rotate_flag:

                if (isVertical) {
                    setContentView(R.layout.activity_main);
                    isVertical = false;
                } else {
                    setContentView(R.layout.layout_vertical_flags);
                    isVertical = true;
                }

                topView.setBackgroundColor(currentTopColor);
                middleView.setBackgroundColor(currentMiddleColor);
                bottomView.setBackgroundColor(currentBottomColor);
                break;

            case R.id.action_middle:
                intent = new Intent(this, SecondScreen.class);
                startActivity(intent);
                break;

            case R.id.action_hard:
                intent = new Intent(this, HardFlagScreen.class);
                startActivity(intent);
                break;

            case R.id.action_image:
                intent = new Intent(this, ImageActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }

        return true;
    }
}

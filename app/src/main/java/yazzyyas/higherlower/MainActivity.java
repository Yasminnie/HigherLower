package yazzyyas.higherlower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView showScores;
    private ArrayAdapter<String> scoreAdapter;
    private List<String> scores;
    private List numbers;
    private TextView score;
    private TextView highscore;
    private int[] diceImages;
    private ImageView diceImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showScores = findViewById(R.id.list_view);
        score = findViewById(R.id.scoreVar);
        numbers = new ArrayList<>();
        scores = new ArrayList<>();
        diceImageView = findViewById(R.id.diceImage);

        diceImages = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

        FloatingActionButton downFab = (FloatingActionButton) findViewById(R.id.downFab);
        downFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        FloatingActionButton upFab = (FloatingActionButton) findViewById(R.id.upFab);
        upFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }

    private void updateUI() {
        // If the list adapter is null, a new one will be instantiated and set on our list view.
        if (scoreAdapter == null) {
            // We can use ‘this’ for the context argument because an Activity is a subclass of the Context class.
            // The ‘android.R.layout.simple_list_item_1’ argument refers to the simple_list_item_1 layout of the Android layout directory.
            scoreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scores);
            showScores.setAdapter(scoreAdapter);
        } else {
            // When the adapter is not null, it has to know what to do when our dataset changes, when a change happens we need to call this method on the adapter so that it will update internally.
            scoreAdapter.notifyDataSetChanged();
        }
    }

    private void addItem() {
        Random random = new Random();
        int randomnumber = random.nextInt(6) + 1;
        numbers.add(randomnumber);

        String newItem = "Throw is " + randomnumber;
        scores.add(newItem);
        diceImageView.setImageResource(diceImages[randomnumber - 1]);
        updateUI();
        Toast.makeText(this, "Add item.", Toast.LENGTH_SHORT).show();

//        int previousNumber = (int) numbers.get(numbers.size() - 2);
//        System.out.println("previousnumber " + previousNumber);
//        if (randomnumber <= previousNumber) {
//            Toast.makeText(this, "You lost.", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "You won.", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

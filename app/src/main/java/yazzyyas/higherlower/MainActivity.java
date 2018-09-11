package yazzyyas.higherlower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private int scoreText = 0;
    private int highscoreText = 0;
    int previousNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showScores = findViewById(R.id.list_view);
        score = findViewById(R.id.scoreText);
        highscore = findViewById(R.id.highscoreText);
        numbers = new ArrayList<>();
        scores = new ArrayList<>();
        diceImageView = findViewById(R.id.diceImage);
        diceImages = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

        int randomnumber = generateNumber();
        String newItem = "Throw is " + previousNumber;
        scores.add(newItem);
        diceImageView.setImageResource(diceImages[previousNumber - 1]);
        updateUI();

        FloatingActionButton downFab = (FloatingActionButton) findViewById(R.id.downFab);
        downFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downFab();
            }
        });

        FloatingActionButton upFab = (FloatingActionButton) findViewById(R.id.upFab);
        upFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upFab();
            }
        });
    }

    private void updateUI() {
        if (scoreAdapter == null) {
            scoreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scores);
            showScores.setAdapter(scoreAdapter);
        } else {
            scoreAdapter.notifyDataSetChanged();
        }
    }

    private int generateNumber() {
        Random random = new Random();
        int randomnummer = random.nextInt(6) + 1;
        return randomnummer;
    }

    private void downFab() {
        int randomnumber = generateNumber();
        numbers.add(randomnumber);

        Log.i("previousnumber ", "previousnumber " + previousNumber);
        String newItem = "Throw is " + randomnumber;
        scores.add(newItem);
        diceImageView.setImageResource(diceImages[randomnumber - 1]);
        updateUI();

        if (randomnumber >= previousNumber) {
            lost();
            Toast.makeText(this, "You lost.", Toast.LENGTH_SHORT).show();
        } else {
            updateScore();
            Toast.makeText(this, "You won.", Toast.LENGTH_SHORT).show();
        }
        previousNumber = randomnumber;
    }

    private void upFab() {
        int randomnumber = generateNumber();
        numbers.add(randomnumber);

        Log.i("previousnumber ", "previousnumber " + previousNumber);
        String newItem = "Throw is " + randomnumber;
        scores.add(newItem);
        diceImageView.setImageResource(diceImages[randomnumber - 1]);
        updateUI();

        if (randomnumber <= previousNumber) {
            lost();
            Toast.makeText(this, "You lost.", Toast.LENGTH_SHORT).show();
        } else {
            updateScore();
            Toast.makeText(this, "You won.", Toast.LENGTH_SHORT).show();
        }
        previousNumber = randomnumber;
    }

    private void updateScore() {
        ++scoreText;
        score.setText("Score: " + scoreText);

        if (scoreText > highscoreText) {
            highscoreText = scoreText;
            highscore.setText("Highscore: " + highscoreText);
        }
    }

    private void lost() {
        scoreText = 0;
        score.setText("Score: " + scoreText);
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

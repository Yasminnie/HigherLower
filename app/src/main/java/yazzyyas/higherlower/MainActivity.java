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
    private int scoreText = 0;
    private int highscoreText = 0;

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
        addItem();
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
        if (scoreAdapter == null) {
            scoreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scores);
            showScores.setAdapter(scoreAdapter);
        } else {
            scoreAdapter.notifyDataSetChanged();
        }
    }

    private void addItem() {
        Random random = new Random();
        int randomnumber = random.nextInt(6) + 1;
        numbers.add(randomnumber);
        int previousNumber = (int) numbers.get(numbers.size() - 1);

        String newItem = "Throw is " + randomnumber;
        scores.add(newItem);
        diceImageView.setImageResource(diceImages[randomnumber - 1]);
        updateUI();

        System.out.println("previousnumber " + previousNumber);
        if (randomnumber <= previousNumber) {
            lost();
            Toast.makeText(this, "You lost.", Toast.LENGTH_SHORT).show();
        } else {
            updateScore();
            Toast.makeText(this, "You won.", Toast.LENGTH_SHORT).show();
        }
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

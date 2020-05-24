package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String TAG = "Recycler Main";
    ArrayList<Task> data = new ArrayList<>();
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = findViewById(R.id.Name);
        addButton = findViewById(R.id.Submit);

        data.add(new Task("Get milk"));
        data.add(new Task("Get Oreos"));

        //Create RecyclerView in the layout and bind the data, ViewHolder and Adapter to the it
        final RecyclerView recyclerview = findViewById(R.id.recyclerView);
        //Create adapter and pass in the data
        Adapter mAdapter = new Adapter(data);
        //Layout manager tells recyclerview how to draw the list
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //pass in layout, animation and adapter.
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(mAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = getName(text);
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter the name of your task!", Toast.LENGTH_SHORT).show();
                } else {
                    data.add(new Task(name));
                    showNewEntry(recyclerview, data);
                }
            }
        });


    }

    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, ArrayList data) {
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }

    public String getName(TextView textView){
        return textView.getText().toString();
    }



}

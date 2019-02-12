package ca.nbcc.shoppinglist;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    private List<TextView> textViewList;
    private GroceryItem[] groceryList;
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for(int i = 0; i < 10; i++){
            if(groceryList[i] == null){
                break;
            }
            outState.putInt("count" + (i+1), groceryList[i].getCount());
            outState.putString("textView" + (i+1), groceryList[i].getItemName());
        }
    }
    public void btnClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View layout = findViewById(R.id.info);
        textViewList = new ArrayList<>();
        groceryList = new GroceryItem[10];
        for(int i = 0; i < 10; i++){
            TextView textView = new TextView(this);
            textView.setId(i+1);
            textView.setTextColor(Color.WHITE);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textViewList.add(textView);
            ((LinearLayout)layout).addView(textView);
        }
        if (savedInstanceState != null) {
            for(int i = 0; i < 10; i++){
                if(savedInstanceState.getString("textView" + (i + 1)) == null){
                    break;
                }
                groceryList[i] = new GroceryItem(
                        savedInstanceState.getString("textView" + (i + 1)),
                        savedInstanceState.getInt("count" + (i + 1))
                );
                String out = groceryList[i].getCount() + " " + groceryList[i].getItemName();
                textViewList.get(i).setText(out);
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA);
                for(int i = 0; i < 10; i++){
                    if(groceryList[i] == null){
                        groceryList[i] = new GroceryItem(
                                reply,
                                1
                        );
                    }else if(groceryList[i].getItemName().equals(reply)){
                        groceryList[i].addCount();
                    }
                    if(groceryList[i].getItemName().equals(reply)){
                        String out = groceryList[i].getCount() + " " + groceryList[i].getItemName();
                        textViewList.get(i).setText(out);
                        break;
                    }
                }
            }
        }
    }
}
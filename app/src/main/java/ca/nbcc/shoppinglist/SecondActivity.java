package ca.nbcc.shoppinglist;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA = "ca.nbcc.shoppingList.extra.REPLY";
    public void btnClick(View view) {
        String groceryItem = (String)((Button)view).getText();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA, groceryItem);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
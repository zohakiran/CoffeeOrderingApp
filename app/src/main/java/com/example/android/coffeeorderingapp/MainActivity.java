
package com.example.android.coffeeorderingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void inc (View view) {
        if(quantity==100){
            Toast.makeText(this,"You cannot have morethan 100 coffees",Toast.LENGTH_SHORT).show();
            return;

        }
        quantity = quantity + 1;
        displayQuantity(quantity); }

    public void dec(View view) {
        if(quantity==1){
            Toast.makeText(this,"You cannot have lessthan 1 coffee",Toast.LENGTH_SHORT).show();

            return;

        }
        quantity = quantity - 1;
        displayQuantity(quantity); }
    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        ImageView imageView = (ImageView) findViewById(R.id.android_coffee_image_view);
        imageView.setImageResource(R.drawable.after);

        CheckBox whippedcream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean haswhippedcream = whippedcream.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean haschocolate = chocolate.isChecked();
        int price = calculatePrice(haswhippedcream,haschocolate);
        EditText nameField= (EditText) findViewById(R.id.name_field);
        String hasname= nameField.getText().toString();
        String priceMessage = createOrderSummary(hasname, price,haswhippedcream,haschocolate);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for:" + hasname);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }

    }        private int calculatePrice(boolean addwhippedcream, boolean addchocolate) {

        int baseprice= 5;
        if(addwhippedcream){
            baseprice=baseprice+1;
        } if(addchocolate)
        {
            baseprice=baseprice+2;

        }
        return  quantity*baseprice;
    }
    private String createOrderSummary(String name,int price,boolean addwhippedcream, boolean addchocolate){
        String priceMessage="Name: "+name;
        priceMessage= priceMessage+"\nQuantity: "+quantity;
        priceMessage+="\nAdd Whipped Cream?"+ addwhippedcream;
        priceMessage += "\nAdd chocolate? " + addchocolate;

        priceMessage= priceMessage+ "\nTotal Price:$ " + price;
        priceMessage= priceMessage+"\nThankyou!";

        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */


    /**
     * This method displays the given text on the screen.
     */

}

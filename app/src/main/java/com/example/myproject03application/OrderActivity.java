package com.example.myproject03application;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import model.Order;
import java.util.ArrayList;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {
    private ArrayList<Order> orderList = new ArrayList<>();
    private AutoCompleteTextView autoCompleteTextView;
    private AutoCompleteTextView autoCompleteTextView2;
    private AutoCompleteTextView autoCompleteTextView3;
    private AutoCompleteTextView autoCompleteTextView4;

    private String[] processors = {"I5 10400F", "I7-10700F", "I9-14900KF"};
    private String[] graphicsCards = {"GTX 1650", "RTX 3050", "RX 7900XT"};
    private String[] ramOptions = {"4Gb", "8Gb", "16Gb", "32Gb", "64Gb", "128Gb"};
    private String[] storageOptions = {"258Gb", "512Gb", "1Tb", "2Tb", "4Tb"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize AutoCompleteTextViews
        setupAutoCompleteTextViews();

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrderActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Check button functionality
        Button checkButton = findViewById(R.id.AddmoreOrder_button);
        checkButton.setOnClickListener(v -> {
            TextInputEditText nameEditText = findViewById(R.id.text_user_name);
            String userName = Objects.requireNonNull(nameEditText.getText()).toString().trim();

            String selectedProcessor = autoCompleteTextView.getText().toString();
            String selectedGraphicsCard = autoCompleteTextView2.getText().toString();
            String selectedRam = autoCompleteTextView3.getText().toString();
            String selectedStorage = autoCompleteTextView4.getText().toString();

            // Validate input
            if (userName.isEmpty() || selectedProcessor.isEmpty() || selectedGraphicsCard.isEmpty() || selectedRam.isEmpty() || selectedStorage.isEmpty()) {
                Toast.makeText(OrderActivity.this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                return; // Exit the method to prevent further processing
            }

            String processorImage = "";
            String vgaImage = "";

            // Check processor selection
            switch (selectedProcessor) {
                case "I5 10400F":
                    processorImage = "https://img.advice.co.th/images_nas/pic_product4/A0148187/A0148187OK_BIG_1.jpg";
                    break;
                case "I7-10700F":
                    processorImage = "https://img.advice.co.th/images_nas/pic_product4/A0135575/A0135575OK_BIG_1.jpg"; // Add image URL
                    break;
                case "I9-14900KF":
                    processorImage = "https://img.advice.co.th/images_nas/pic_product4/A0154878/A0154878OK_BIG_1.jpg"; // Add image URL
                    break;
                default:
                    Toast.makeText(OrderActivity.this, "Incorrect processor selection", Toast.LENGTH_SHORT).show();
                    return;
            }

            // Check graphics card selection
            switch (selectedGraphicsCard) {
                case "GTX 1650":
                    vgaImage = "https://img.advice.co.th/images_nas/pic_product4/A0154639/A0154639OK_BIG_1.jpg";
                    break;
                case "RTX 3050":
                    vgaImage = "https://img.advice.co.th/images_nas/pic_product4/A0158302/A0158302OK_BIG_1.jpg"; // Add image URL
                    break;
                case "RX 7900XT":
                    vgaImage = "https://img.advice.co.th/images_nas/pic_product4/A0148399/A0148399OK_BIG_1.jpg"; // Add image URL
                    break;
                default:
                    Toast.makeText(OrderActivity.this, "Incorrect graphics card selection", Toast.LENGTH_SHORT).show();
                    return;
            }

            int id = orderList.size() + 1; // Set the order ID
            Order order = new Order(id, userName, selectedProcessor, processorImage, selectedGraphicsCard, vgaImage, selectedRam, selectedStorage);
            orderList.add(order);

            Toast.makeText(OrderActivity.this, "Order added: " + orderList.size(), Toast.LENGTH_SHORT).show();
        });

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
            if (!orderList.isEmpty()) {
                // หากมีข้อมูลใน orderList ให้เปลี่ยนหน้า
                Intent intent = new Intent(OrderActivity.this, OrderListActivity.class);
                intent.putParcelableArrayListExtra("orderList", new ArrayList<>(orderList));
                startActivity(intent);
                finish();
                Toast.makeText(OrderActivity.this, "Go to Order List", Toast.LENGTH_SHORT).show();
            } else {
                // หาก orderList ว่างเปล่า จะแสดงข้อความแจ้งเตือน
                Toast.makeText(OrderActivity.this, "No orders available to display", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupAutoCompleteTextViews() {
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        autoCompleteTextView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, processors));

        autoCompleteTextView2 = findViewById(R.id.auto_complete_txt2);
        autoCompleteTextView2.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, graphicsCards));

        autoCompleteTextView3 = findViewById(R.id.auto_complete_txt3);
        autoCompleteTextView3.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ramOptions));

        autoCompleteTextView4 = findViewById(R.id.auto_complete_txt4);
        autoCompleteTextView4.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, storageOptions));

        autoCompleteTextView.setOnItemClickListener(this::onItemSelected);
        autoCompleteTextView2.setOnItemClickListener(this::onItemSelected);
        autoCompleteTextView3.setOnItemClickListener(this::onItemSelected);
        autoCompleteTextView4.setOnItemClickListener(this::onItemSelected);
    }

    private void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String selectedItem = adapterView.getItemAtPosition(position).toString();
        Log.d("OrderActivity", "Selected item: " + selectedItem);
    }
}

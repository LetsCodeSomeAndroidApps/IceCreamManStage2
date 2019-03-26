package com.example.com.icecreamman2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Ice_Cream_Main extends AppCompatActivity {


    private RecyclerView iceCreamRecyclerView;

    private Context mContext;

    private ArrayList<String> mFlavorImageLinks;
    private ArrayList<String> mFlavorImageNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice__cream__main);
        iceCreamRecyclerView = findViewById(R.id.iceCreamRecycler);
        mContext = getApplicationContext();
        getDataFireBase();


    }


    public void updateIceCreamRecycler(){
        if(iceCreamRecyclerView != null && mFlavorImageNames.size()>0 &&
                mFlavorImageLinks.size()>0 ){
            IceCreamAdapter iceCreamAdapter = new IceCreamAdapter(mContext,
                    mFlavorImageLinks, mFlavorImageNames);
            iceCreamRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            iceCreamRecyclerView.setAdapter(iceCreamAdapter);
        }
    }


//    public ArrayList<String> generateIceCreamImageLinks(){
//        String vanillaImageLink = "https://firebasestorage.googleapis.com/v0/b/gymapp-8e0cc.appspot.com/o/vanilla.png?alt=media&token=f705daf8-d136-4a0b-acc1-d65582df64f8";
//        String mintChocolateImageLink = "https://firebasestorage.googleapis.com/v0/b/gymapp-8e0cc.appspot.com/o/mintchocolate.png?alt=media&token=88bd61c2-2545-4e9b-b926-ff66b62190f6";
//        String cottonCandyImageLink = "https://firebasestorage.googleapis.com/v0/b/gymapp-8e0cc.appspot.com/o/cottoncandy.png?alt=media&token=5e21b66b-b634-4343-8997-5496b811a573";
//        String blueberryImageLink = "https://firebasestorage.googleapis.com/v0/b/gymapp-8e0cc.appspot.com/o/blueberry.png?alt=media&token=11600647-f0d3-41a9-b12c-485ced07c37c";
//        String raspberryImageLink = "https://firebasestorage.googleapis.com/v0/b/gymapp-8e0cc.appspot.com/o/rasberry.png?alt=media&token=32215b66-430b-4fd1-819e-6ffc063e438d";
//        String sherbertImageLink = "https://firebasestorage.googleapis.com/v0/b/gymapp-8e0cc.appspot.com/o/sherbert.png?alt=media&token=a11bebcc-6292-4e46-b92c-a31745c9f955";
//
//        ArrayList<String> iceCreamImageLinks = new ArrayList<>();
//        iceCreamImageLinks.add(vanillaImageLink);
//        iceCreamImageLinks.add(mintChocolateImageLink);
//        iceCreamImageLinks.add(cottonCandyImageLink);
//        iceCreamImageLinks.add(blueberryImageLink);
//        iceCreamImageLinks.add(raspberryImageLink);
//        iceCreamImageLinks.add(sherbertImageLink);
//        iceCreamImageLinks.add(blueberryImageLink);
//        iceCreamImageLinks.add(raspberryImageLink);
//        iceCreamImageLinks.add(sherbertImageLink);
//
//        return iceCreamImageLinks;
//
//    }

//    public ArrayList<String> generateIceCreamNames(){
//        String vanillaName = "Vanilla";
//        String mintChocolateName = "Mint Chocolate";
//        String cottonCandyName = "Cotton Candy";
//        String blueberry = "Blueberry";
//        String raspberry = "Raspberry";
//        String sherbert = "Sherbert";
//
//        ArrayList<String> iceCreamImageNames = new ArrayList<>();
//        iceCreamImageNames.add(vanillaName);
//        iceCreamImageNames.add(mintChocolateName);
//        iceCreamImageNames.add(cottonCandyName);
//        iceCreamImageNames.add(blueberry);
//        iceCreamImageNames.add(raspberry);
//        iceCreamImageNames.add(sherbert);
//        iceCreamImageNames.add(blueberry);
//        iceCreamImageNames.add(raspberry);
//        iceCreamImageNames.add(sherbert);
//        return iceCreamImageNames;
//
//    }


    public void getDataFireBase(){
        mFlavorImageLinks = new ArrayList<>();
        mFlavorImageNames = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("Flavors").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String flavorName = childSnapshot.getKey();
                    flavorName = flavorName.replace("-", " ");
                    String flavorPicture = childSnapshot.getValue().toString();
                    mFlavorImageNames.add(flavorName);
                    mFlavorImageLinks.add(flavorPicture);
                    Log.d("FlavorAndLink", flavorName+flavorPicture);
                }


                updateIceCreamRecycler();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }










}

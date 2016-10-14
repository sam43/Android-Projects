package com.edimaudo.lightsout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

  private ImageView image1,image2,image3, image4,
  image5,image6,image7,image8,image9,image10,image11,
  image12,image13,image14,image15,image16;

  int[] lightArray = new int[16];
  int[] imageViewArray = {
          R.id.image1, R.id.image2,R.id.image3,R.id.image4,
          R.id.image5,R.id.image6,R.id.image7,R.id.image8,
          R.id.image9,R.id.image10,R.id.image11,R.id.image12,
          R.id.image13,R.id.image14,R.id.image15,R.id.image16};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    image1 = (ImageView) findViewById(R.id.image1);
    image2 = (ImageView) findViewById(R.id.image2);
    image3 = (ImageView) findViewById(R.id.image3);
    image4 = (ImageView) findViewById(R.id.image4);
    image5 = (ImageView) findViewById(R.id.image5);
    image6 = (ImageView) findViewById(R.id.image6);
    image7 = (ImageView) findViewById(R.id.image7);
    image8 = (ImageView) findViewById(R.id.image8);
    image9 = (ImageView) findViewById(R.id.image9);
    image10 = (ImageView) findViewById(R.id.image10);
    image11 = (ImageView) findViewById(R.id.image11);
    image12 = (ImageView) findViewById(R.id.image12);
    image13 = (ImageView) findViewById(R.id.image13);
    image14 = (ImageView) findViewById(R.id.image14);
    image15 = (ImageView) findViewById(R.id.image15);
    image16 = (ImageView) findViewById(R.id.image16);

    image1.setOnClickListener(this);
    image2.setOnClickListener(this);
    image3.setOnClickListener(this);
    image4.setOnClickListener(this);
    image5.setOnClickListener(this);
    image6.setOnClickListener(this);
    image7.setOnClickListener(this);
    image8.setOnClickListener(this);
    image9.setOnClickListener(this);
    image10.setOnClickListener(this);
    image11.setOnClickListener(this);
    image12.setOnClickListener(this);
    image13.setOnClickListener(this);
    image14.setOnClickListener(this);
    image15.setOnClickListener(this);
    image16.setOnClickListener(this);

    //assignLight();
    //checkEndGame();

  }

  //check if the game should end
  public void checkEndGame(){
      if(arraySum(lightArray) == 0){
        AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
        winBuild.setTitle("Yay!");
        winBuild.setMessage("Congrats! you won!");
        winBuild.setPositiveButton("Play Again?",
                new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                    resetGame();
                  }
                });
        winBuild.setNegativeButton("Exit",
                new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                    MainActivity.this.finish();
                  }
                });
        winBuild.show();
      }
    }




  //set all light array values to zero
  public void resetGame(){
    for (int i = 0; i < lightArray.length; i++) {
      lightArray[i] = 0;
    }
    assignLight();
  }

  // sum light array
  public int arraySum(int [] arrayInfo){
    int output = 0;
    for (int i = 0; i < arrayInfo.length; i++){
      output+=arrayInfo[i];
    }
    return output;
  }

  //assign light colors randomly
  public void assignLight(){
    Random rand = new Random();
    for (int i = 0; i < lightArray.length; i++){
      lightArray[i] = rand.nextInt(2);
      if (lightArray[i] == 1){
        ImageView img = (ImageView) findViewById(imageViewArray[i]);
        img.setImageResource(R.drawable.circle_change);
      }
    }

  }



  //list.indexOf('e')
  public void updateColor(ArrayList<Integer> arrayList){
    for (int i = 0; i < arrayList.size(); i++){
      ImageView imageView = (ImageView) findViewById(arrayList.get(i));

      if(lightArray[i] == 0){
        imageView.setImageResource(R.drawable.circle_change);
        lightArray[i] = 1;
      } else {
        imageView.setImageResource(R.drawable.circle);
        lightArray[i] = 0;
      }
    }


  }



  @Override
  public void onClick(View view) {

    switch(view.getId()){
      case R.id.image1:

        break;
      case R.id.image2:

        break;
      case R.id.image3:

        break;
      case R.id.image4:

        break;
      case R.id.image5:

        break;
      case R.id.image6:

        break;
      case R.id.image7:

        break;
      case R.id.image8:

        break;
      case R.id.image9:

        break;
      case R.id.image10:

        break;
      case R.id.image11:

        break;
      case R.id.image12:

        break;
      case R.id.image13:

        break;
      case R.id.image14:

        break;
      case R.id.image15:

        break;
      case R.id.image16:

        break;
    }

      //updateColor(output);
    //changeColor(view.getId(),output,lightArray[output]);
  }

  public int[] generateArray(int value){
    int[] outputArray;
    if (value == 1 || value == 4 || value == 13 || value == 16){
      outputArray = new int[3];
    } else if (value == 6 || value == 7 || value == 10 || value == 11) {
      outputArray = new int[5];
    } else {
      outputArray = new int[4];
    }

    switch (value){
      case 1:
        outputArray[0] = R.id.image1;
        outputArray[1] = R.id.image2;
        outputArray[2] = R.id.image5;
        break;
    }


    return outputArray;
  }


}

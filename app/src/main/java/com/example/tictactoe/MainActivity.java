package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    int activePlayer = 1;
    int moves = 0;
    boolean matchFinished = false;
    ArrayList<Integer> player1 = new ArrayList<>();
    ArrayList<Integer> player2 = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Boolean> marked = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.newGame) {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClick(View view){
        if(matchFinished){
            Toast.makeText(this, "Match over", Toast.LENGTH_SHORT).show();
            return;
        }
        Button button = (Button)view;
        int cell = 0;
        switch(button.getId()){
            case R.id.button1:
                cell = 1;
                break;
            case R.id.button2:
                cell = 2;
                break;
            case R.id.button3:
                cell = 3;
                break;
            case R.id.button4:
                cell = 4;
                break;
            case R.id.button5:
                cell = 5;
                break;
            case R.id.button6:
                cell = 6;
                break;

            case R.id.button7:
                cell = 7;
                break;
            case R.id.button8:
                cell = 8;
                break;
            case R.id.button9:
                cell = 9;
        }

        play(cell, button);
    }

    public void play(int cell, Button button){
        if((marked.get(cell))!=null){
            Toast.makeText(this, "Invalid Move", Toast.LENGTH_SHORT).show();
            return;
        }
        TextView textView = findViewById(R.id.textView);
        if(activePlayer == 1){
            button.setText("X");
            button.setTextSize(30);
            map.put(cell, button.getId());
            player1.add(cell);
            marked.put(cell, true);
            if(checkWinner(cell, activePlayer)){
                matchFinished = true;
                String msg = "Match over";
                textView.setText(msg);
                Toast.makeText(this, "Player 1 wins", Toast.LENGTH_LONG).show();
                return ;
            }
            String msg = "Player 2 move";
            textView.setText(msg);
            activePlayer = 2;
        }
        else if(activePlayer == 2){
            button.setText("O");
            button.setTextSize(30);
            map.put(cell, button.getId());
            player2.add(cell);
            marked.put(cell, true);
            if(checkWinner(cell, activePlayer)){
                matchFinished = true;
                String msg = "Match over";
                textView.setText(msg);
                Toast.makeText(this, "Player 2 wins", Toast.LENGTH_LONG).show();
                return ;
            }
            String msg = "Player 1 move";
            textView.setText(msg);
            activePlayer = 1;
        }
        moves += 1;
        if(moves >= 9){
            Toast.makeText(this, "Match draw", Toast.LENGTH_LONG).show();
            String msg = "Match draw";
            textView.setText(msg);
        }
    }

    public boolean checkWinner(int cell, int player){
        ArrayList<Integer> playerList = null;
        if(player == 1){
            playerList = player1;
        }
        else if(player == 2){
            playerList = player2;
        }
        assert playerList != null;
        if(playerList.size()<=2){
            return false;
        }
        switch (cell){
            case 1:
                if((playerList.contains(2) && playerList.contains(3)) ||
                        (playerList.contains(4) && playerList.contains(7)) ||
                        (playerList.contains(5) && playerList.contains(9))){
                    return true;
                }
                break;
            case 2:
                if((playerList.contains(1) && playerList.contains(3)) ||
                        (playerList.contains(8) && playerList.contains(5))){
                    return true;
                }
                break;
            case 3:
                if((playerList.contains(2) && playerList.contains(1)) ||
                        (playerList.contains(6) && playerList.contains(9)) ||
                        (playerList.contains(5) && playerList.contains(7))){
                    return true;
                }
                break;
            case 4:
                if((playerList.contains(5) && playerList.contains(6)) ||
                        (playerList.contains(1) && playerList.contains(7))){
                    return true;
                }
                break;
            case 5:
                if((playerList.contains(2) && playerList.contains(8)) ||
                        (playerList.contains(4) && playerList.contains(6))||
                        (playerList.contains(1) && playerList.contains(9))||
                        (playerList.contains(3) && playerList.contains(7))){
                    return true;
                }
                break;
            case 6:
                if((playerList.contains(4) && playerList.contains(5)) ||
                        (playerList.contains(3) && playerList.contains(9))){
                    return true;
                }
                break;
            case 7:
                if(playerList.contains(8) && playerList.contains(9) ||
                        (playerList.contains(4) && playerList.contains(1)) ||
                        (playerList.contains(5) && playerList.contains(3))){
                    return true;
                }
                break;
            case 8:
                if((playerList.contains(9) && playerList.contains(7)) ||
                        (playerList.contains(2) && playerList.contains(5))){
                    return true;
                }
                break;
            case 9:
                if((playerList.contains(7) && playerList.contains(8)) ||
                        (playerList.contains(3) && playerList.contains(6)) ||
                        (playerList.contains(5) && playerList.contains(1))){
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}

package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    ListView ChatList;
    EditText ChatInput;
    Button button6;
    ArrayList<String> List;
    ChatAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        ChatList = findViewById(R.id.ChatList);
        ChatInput = findViewById(R.id.ChatInput);
        button6 = findViewById(R.id.button6);
        List = new ArrayList<>();
        messageAdapter = new ChatAdapter(this);
        ChatList.setAdapter(messageAdapter);
        button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                List.add(ChatInput.getText().toString());
                Log.i("Send button clicked", "Message is " + List.get(List.size() - 1));
                messageAdapter.notifyDataSetChanged();
                Log.i("Send button clicked","Data changed");
                ChatInput.setText("");
                Log.i("Send Button clicked", "Message field is now empty");
            }
        });


    }
    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx){
            super(ctx, 0);
        }
        public int getCount(){
            return(List.size());
        }
        public String getItem(int position){
            return(List.get(position));
        }
        public View getView(int position, View convertView, ViewGroup parent){
            Log.i("Transmitting","Starting transmission");
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;

            if(position%2 == 0){
                result = inflater.inflate(R.layout.chat_row_incoming, null);
                Log.i("Transmitting","Incoming Transmission");
            }



            else{
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
                Log.i("Transmitting", "Outgoing Transmission");
            }
            TextView message = result.findViewById(R.id.message_text);
            Log.i("Transmitting", message.toString());
            message.setText(getItem(position));
            Log.i("Transmitting","Message " + getItem(position) + "where" + position);
            return result;





        }
    }
}
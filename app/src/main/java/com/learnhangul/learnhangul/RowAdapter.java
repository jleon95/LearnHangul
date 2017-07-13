package com.learnhangul.learnhangul;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class RowAdapter extends ArrayAdapter<Character> {

    Context context;
    Character [] data;
    private static LayoutInflater inflater = null;

    public RowAdapter(Context context, ArrayList<Character> data) {

        super(context,0,data);
        this.context = context;
        this.data = data.toArray(new Character[data.size()]);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Character getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null)

            view = inflater.inflate(R.layout.list_row,null);

        TextView character = (TextView) view.findViewById(R.id.row_layout_character);
        character.setText(data[i].getCharacter());
        int learnRating = data[i].getLearnRating();

        if(!data[i].isActive())

            character.setTextColor(ContextCompat.getColor(context,R.color.grey_not_selected));

        else if(learnRating < 0)

            character.setTextColor(ContextCompat.getColor(context,R.color.red_difficult));

        else if(learnRating == 0)

            character.setTextColor(ContextCompat.getColor(context,R.color.orange_just_started));

        else if(learnRating > 0 && learnRating < 4)

            character.setTextColor(ContextCompat.getColor(context,R.color.yellow_halfway));

        else

            character.setTextColor(ContextCompat.getColor(context,R.color.green_learned));

        TextView pronunciation = (TextView) view.findViewById(R.id.row_layout_pronunciation);
        pronunciation.setText(data[i].getPronunciation());
        return view;

    }
}

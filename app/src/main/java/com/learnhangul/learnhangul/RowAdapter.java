package com.learnhangul.learnhangul;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class RowAdapter extends ArrayAdapter<Character> {

    private Context context;
    private Character [] data;
    private static LayoutInflater inflater = null;

    RowAdapter(Context context, ArrayList<Character> data) {

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
        selectColor(data[i],character);
        TextView pronunciation = (TextView) view.findViewById(R.id.row_layout_pronunciation);
        pronunciation.setText(data[i].getPronunciation());
        return view;

    }

    private void selectColor(Character c, TextView text){

        int learnRating = c.getLearnRating();

        if(!c.isActive()) { // If you don't want to review the character, it is colored darker.

            if(learnRating < 0)

                text.setTextColor(ContextCompat.getColor(context,R.color.not_selected_red_difficult));

            else if(learnRating == 0)

                text.setTextColor(ContextCompat.getColor(context,R.color.never_selected_grey));

            else if(learnRating > 0 && learnRating < 4)

                text.setTextColor(ContextCompat.getColor(context,R.color.not_selected_yellow_halfway));

            else

                text.setTextColor(ContextCompat.getColor(context,R.color.not_selected_green_learned));

        }

        else { // If you select the character for reviewing, it is colored brighter.

            if (learnRating < 0)

                text.setTextColor(ContextCompat.getColor(context, R.color.selected_red_difficult));

            else if (learnRating == 0)

                text.setTextColor(ContextCompat.getColor(context, R.color.selected_orange_just_started));

            else if (learnRating > 0 && learnRating < 4)

                text.setTextColor(ContextCompat.getColor(context, R.color.selected_yellow_halfway));

            else

                text.setTextColor(ContextCompat.getColor(context, R.color.selected_green_learned));
        }
    }
}

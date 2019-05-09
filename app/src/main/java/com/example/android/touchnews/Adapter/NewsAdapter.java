package com.example.android.touchnews.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.touchnews.Activities.MainActivity;
import com.example.android.touchnews.Activities.details_activity;
import com.example.android.touchnews.Object.News;
import com.example.android.touchnews.R;
import com.squareup.picasso.Picasso;



import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;


public class NewsAdapter extends ArrayAdapter<News> {

    public static News current;

    String url21 ;

    View view;

    List<News> mNews;

    public NewsAdapter(Context context, List<News> News) {
        super(context, 0, News);

        mNews = News;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        view = convertView;

        current = mNews.get(position);


        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(current.getTitle());

        TextView descreption = (TextView) view.findViewById(R.id.details);
        descreption.setText(current.getDesc()
        );
        TextView author = (TextView) view.findViewById(R.id.author);
        author.setText(current.getSource()
        );
        TextView time = (TextView) view.findViewById(R.id.time);
        time.setText(current.getTime());

        ImageView img = (ImageView) view.findViewById(R.id.img);


        Picasso.with(getContext()).load(current.getImg())
                .resize(300, 200)
                .into(img);


         url21=current.getUrl_link();

       view.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               Intent heloo = new Intent(view.getContext(), details_activity.class);
               heloo.putExtra("url",mNews.get(position).getUrl_link());
               getContext().startActivity(heloo);

               Log.v(NewsAdapter.class.getSimpleName(),"ID= helolololol  "+current);


           }

       });


        return view;


    }

}
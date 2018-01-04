package com.example.user.android_assignment_8_4;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAdapter adp=new MyAdapter(MainActivity.this);
        gridView=(GridView)findViewById(R.id.mygridview);
        gridView.setAdapter(adp);
    }
    class MyAdapter extends BaseAdapter {
        //Arraylist
        ArrayList<MyClass> list;
        Context context;

        MyAdapter(Context context){
            this.context=context;
            list= new ArrayList<MyClass>();
            Resources res= context.getResources();
            String[] tempversion=  res.getStringArray(R.array.versions);
            int[] images={R.drawable.gingerbread,R.drawable.honeycomb,R.drawable.icecream,R.drawable.jelly,
                    R.drawable.kitkat,R.drawable.lollipop};
            //Now putting it in main arraylist using for loop
            for(int i=0;i<6;i++){
                //creating the object for images and version
                MyClass myClass= new MyClass(images[i],tempversion[i]);
                list.add(myClass);//add the list  of the myclass
            }

        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        class ViewHolder{
            //A ViewHolder object stores each of the component views inside the tag field of the Layout, so you can
            // immediately access them without the need to look them up repeatedly.

            ImageView myimage;
            TextView mytext;
            ViewHolder(View view){
                //creating ids for image and text
                myimage= (ImageView) view.findViewById(R.id.view);
                mytext= (TextView) view.findViewById(R.id.textView);
            }
        }
        public class MyClass {
            int versionImage;
            String versionName;
            //creating the variables image and version name in a class and pointing them to the current value
            public MyClass(int versionImage, String versionName) {
                this.versionImage = versionImage;
                this.versionName = versionName;
            }


        }

        //this method is responsible for creating views
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View row= view;
            ViewHolder holder=null;
            //A ViewHolder object stores each of the component views inside the tag field of the Layout, so you can
            // immediately access them without the need to look them up repeatedly.

            if(row==null) {
                LayoutInflater inflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                //Instantiates a layout XML file into its corresponding View objects
                row= inflater.inflate(R.layout.view,parent,false);
                //inflater.inflate is used to create the view from our xml file
                holder =new ViewHolder(row);
                //here holder will holds the  values in the row
                row.setTag(holder);// store the holder with the view.

            }else{
                // we've just avoided calling findViewById() on resource everytime

                // just use the viewHolder

                holder= (ViewHolder) row.getTag();

            }
            MyClass temp= list.get(position);
            MyClass temp1=list.get(position);
            //if we want to change image from any place in our code where we do not have context to call getResources() method on
            // it. e.g in Adapters. No need to pass context through constructor just for this funcionality

            holder.myimage.setImageResource(temp.versionImage);
            holder.mytext.setText(temp.versionName);
            return row;
        }
    }
}




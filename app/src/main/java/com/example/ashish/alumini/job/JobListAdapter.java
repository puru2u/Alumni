package com.example.ashish.alumini.job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashish.alumini.R;
import com.example.ashish.alumini.network.pojo.Job;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashish on 11/3/16.
 */
public class JobListAdapter extends ArrayAdapter<Job> {
    List<Job> mListJobs;

    @Bind(R.id.imageView_logo)
    ImageView mImageView;
    @Bind(R.id.textView_companyName) TextView mTextViewCompanyName;
    @Bind(R.id.textView_joblocation) TextView mTextViewJobLocation;
    @Bind(R.id.textView_jobPosition) TextView mTextViewJobPosition;




    public JobListAdapter(Context context, int resource, List<Job> objects) {
        super(context, resource, objects);

        mListJobs =objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            convertView = vi.inflate(R.layout.list_layout_job, null);
        }
        //Butterknife Injections
        ButterKnife.bind(this,convertView);

        Job item = getItem(position);




        mTextViewCompanyName.setText(item.getName());
        mTextViewJobLocation.setText(item.getLocation());
        mTextViewJobPosition.setText(item.getRole());


        return convertView;
    }

    @Override
    public Job getItem(int position) {
        return mListJobs.get(position);
    }

}
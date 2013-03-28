/**
 * Copyright (C) 2009 Alistair Rutherford, Glasgow, Scotland, UK, www.netthreads.co.uk
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.netthreads.android.bulletml;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import jp.gr.java_conf.abagames.bulletml_demo.noiz.Sample;


/**
 * Bullet ML List adapter.
 * 
 * This adapts our list of objects into view elements.
 *
 */
public class SampleMLListAdapter extends BaseAdapter
{
    private Sample[] list = null;

    /**
    * Remember our context so we can use it when constructing views.
    */
    private Context context = null;

    public SampleMLListAdapter(Context context, Sample[] samples)
    {
        this.context = context;
        
        this.list = samples;
    }

    /**
     * The number of items in the list.
     *
     * @see android.widget.ListAdapter#getCount()
     */
    public int getCount()
    {
        return list.length;
    }

    /**
     * Return item index.
     */
    public Object getItem(int position)
    {
        return list[position];
    }

    /**
     * Use the array index as a unique id.
     *
     * @see android.widget.ListAdapter#getItemId(int)
     */
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * Make a view to hold each row.
     *
     * @see android.widget.ListAdapter#getView(int, android.view.View,
     *      android.view.ViewGroup)
     */
    public View getView(int position, View convertView, ViewGroup parent)
    {
        SampleMLView sv;

        Sample sample = list[position];

        String description = getDescription(sample);

        if (convertView == null)
        {
            sv = new SampleMLView(context, description);
        }
        else
        {
            sv = (SampleMLView)convertView;
            sv.setTitle(description);
        }

        return sv;
    }

    public Sample[] getList()
    {
        return list;
    }

    public void setList(Sample[] samples)
    {
        this.list = samples;
    }

    /**
     * Build description string for address.
     *
     * @param address
     *
     * @return String description.
     */
    public String getDescription(Sample sample)
    {
        String description = "";

        description = sample.name;
        
        return description;
    }

    /**
     * We will use a LocationView to display each item.
     *
     */
    private class SampleMLView extends LinearLayout
    {
        private TextView locationText;

        public SampleMLView(Context context, String description)
        {
            super(context);

            // Assign our xml layout
            addView(inflate(context, R.layout.ml_row, null));

            this.setOrientation(HORIZONTAL);

            locationText = (TextView)this.findViewById(R.id.name);
            locationText.setText(description);
        }

        /**
         * Convenience method to set the title of a SpeechView
         */
        public void setTitle(String title)
        {
            locationText.setText(title);
        }
    }
}

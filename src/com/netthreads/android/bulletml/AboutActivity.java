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

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

/**
 * About view with version number.
 *
 */
public class AboutActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about);

        TextView textView = (TextView)this.findViewById(R.id.app_version);

        String version;

        try
        {
            PackageInfo pi = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);

            version = pi.versionName;
        }
        catch (NameNotFoundException e)
        {
            version = getString(R.string.unknown);
        }

        textView.setText(version);
        

        TextView linkView = (TextView)this.findViewById(R.id.link_text_view);

        linkView.setText(R.string.link_text);
    }
}

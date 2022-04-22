package com.gropse.epub.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.folioreader.Config;
import com.folioreader.FolioReader;
import com.folioreader.util.AppUtil;
import com.macreel.epauthi.R;

import java.io.FileOutputStream;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        FolioReader folioReader = FolioReader.get();

        Config config = AppUtil.getSavedConfig(getApplicationContext());
        if (config == null)
            config = new Config();
        config.setShowTts(true);
         config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL);

        folioReader.setConfig(config, true);
        folioReader.openBook(R.raw.sherlock);








    }
}

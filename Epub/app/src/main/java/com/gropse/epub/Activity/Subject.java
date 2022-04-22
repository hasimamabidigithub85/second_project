package com.gropse.epub.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.downloader.Status;
import com.folioreader.Config;
import com.folioreader.FolioReader;
import com.folioreader.model.HighLight;
import com.folioreader.model.ReadPosition;
import com.folioreader.ui.base.OnSaveHighlight;
import com.folioreader.util.AppUtil;
import com.folioreader.util.OnHighlightListener;
import com.folioreader.util.ReadPositionListener;
import com.gropse.epub.Activity.Adapter.BooksAdapter;
import com.gropse.epub.Activity.Model.BookModel;
import com.gropse.epub.Activity.Network.APIClient;
import com.gropse.epub.Activity.Network.APIInterface;
import com.gropse.epub.Activity.Utils.Prefs;
import com.gropse.epub.Activity.Utils.Utils;
import com.gropse.epub.Activity.recycler.RecyclerTouchListener;
import com.macreel.epauthi.R;
import com.payumoney.sdkui.ui.fragments.AddEmiCardFragment;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.Locale.UK;

public class Subject extends AppCompatActivity implements OnHighlightListener, ReadPositionListener, FolioReader.OnClosedListener {
    ArrayList<BookModel> arraylist;
    String viewType;
    RecyclerView recyclerView_book;
    BooksAdapter booksAdapter;
    String classValueStr, Str_Url, mediumStr;
    String classname;
    ProgressDialog pd, pd_view,pd_view1;
    int downloadId, downloadIdOne;
    String Url = "";
    RadioGroup radioGroup;
    String title = "";
    TextToSpeech mTextToSpeech;
    String StrUrl = "";
    Button btn_view, btn_download,buttonCancelOne, buttonOne;
    TextView textViewProgressOne;
    String dirPath,dirPath1,Strdirpath1;
    ArrayList<BookModel> arraylist_StrUrl;
    ArrayList<BookModel> titleStr;
    RelativeLayout rl_dd;
    int position1;
    private FolioReader folioReader;
    ImageView view_img, download_img;
    ProgressBar progressbar;
    Dialog dialog, SelectToSpeach_dialog;
    ProgressDialog progressDialog;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        folioReader = FolioReader.get()
                .setOnHighlightListener(this)
                .setReadPositionListener(this)
                //.setReadLocatorListener(this)

                .setOnClosedListener(this);
        pd = new ProgressDialog(Subject.this);




        Prefs.instanceOf(getApplicationContext()).setSelectToSpeach("0");
        dirPath1 = getFilesDir().getAbsolutePath() + "/epothi";
        Log.e("dirpath1", dirPath1);

        classValueStr = Prefs.instanceOf(getApplicationContext()).getValue();
        mediumStr = Prefs.instanceOf(getApplicationContext()).getMedium();

//initialize
        PRDownloader.initialize(getApplicationContext());
//Pr downloader
        final PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);

        // Setting timeout globally for the download network requests:
        PRDownloaderConfig config1 = PRDownloaderConfig.newBuilder()
                .setReadTimeout(30_000)
                .setConnectTimeout(30_000)
                .build();
        PRDownloader.initialize(getApplicationContext(), config1);
        recyclerView_book = findViewById(R.id.recyclerView_book);


        if (AppUtility.isNetworkAvailable(getApplicationContext(),true)) {
            downloadApi();
        }
        recyclerView_book.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView_book, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View v, final int position) {

                position1 = position;
                //btn_view = v.findViewById(R.id.btn_view);
                view_img = v.findViewById(R.id.view_img);
                //btn_download = v.findViewById(R.id.btn_download);
                download_img = v.findViewById(R.id.download_img);
                // progressBarOne = v.findViewById(R.id.progressBar);
                textViewProgressOne = v.findViewById(R.id.textViewProgressOne);
                buttonOne = v.findViewById(R.id.buttonOne);
                buttonCancelOne = v.findViewById(R.id.buttonCancelOne);
                rl_dd = v.findViewById(R.id.rl_dd);
                buttonOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                download_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String path = "http://gathbandhan.co.in" + arraylist.get(position).url.substring(1);
                        Uri uri = Uri.parse(path); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }

                });
                view_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //dialog ppopup
                        progressBarPoppup();
                        pd_view = new ProgressDialog(Subject.this);
                        Strdirpath1 = dirPath1 + "/" + arraylist.get(position).tittle + ".epub";

                        File file = new File(Strdirpath1);
                        if (file.exists()) {

                            Config config = AppUtil.getSavedConfig(getApplicationContext());
                            if (config == null)
                                config = new Config();
                            config.setShowTts(true);

                            config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL);
                            folioReader.setConfig(config, true)
                                    .openBook(Strdirpath1);
                          //  setTextToSpeech(getApplicationContext());
                           // ReadText();


                            progressbar.setIndeterminate(false);
                            dialog.dismiss();
                        }
                        else {
                            progressbar.setIndeterminate(true);
                            progressbar.getIndeterminateDrawable().setColorFilter(
                                    Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);

                            if (Status.RUNNING == PRDownloader.getStatus(downloadIdOne)) {
                                PRDownloader.pause(downloadIdOne);
                                return;
                            }
                            buttonOne.setEnabled(false);
                            progressbar.setIndeterminate(true);
                            progressbar.getIndeterminateDrawable().setColorFilter(
                                    Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);

                            if (Status.PAUSED == PRDownloader.getStatus(downloadIdOne)) {
                                PRDownloader.resume(downloadIdOne);
                                return;
                            }
                            downloadIdOne = PRDownloader.download("http://gathbandhan.co.in" + arraylist.get(position).url.substring(1), dirPath1, arraylist.get(position).tittle + ".epub")
                                    .build()
                                    .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                                        @Override
                                        public void onStartOrResume() {
                                            progressbar.setIndeterminate(false);
                                            buttonOne.setEnabled(true);
                                            buttonOne.setText("pause");

                                            buttonCancelOne.setEnabled(true);
                                        }
                                    })
                                    .setOnPauseListener(new OnPauseListener() {
                                        @Override
                                        public void onPause() {
                                            buttonOne.setText("resume");
                                            rl_dd.setVisibility(View.GONE);
                                        }
                                    })
                                    .setOnCancelListener(new OnCancelListener() {
                                        @Override
                                        public void onCancel() {
                                            buttonOne.setText("start");
                                            buttonCancelOne.setEnabled(false);
                                            progressbar.setProgress(0);
                                            textViewProgressOne.setText("");
                                            downloadIdOne = 0;
                                            progressbar.setIndeterminate(false);
                                            rl_dd.setVisibility(View.GONE);
                                        }
                                    })
                                    .setOnProgressListener(new OnProgressListener() {
                                        @Override
                                        public void onProgress(Progress progress) {
                                            long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                            progressbar.setProgress((int) progressPercent);
                                            textViewProgressOne.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                                            progressbar.setIndeterminate(false);
                                        }
                                    })
                                    .start(new OnDownloadListener() {
                                        @Override
                                        public void onDownloadComplete() {
                                            buttonOne.setEnabled(false);
                                            buttonCancelOne.setEnabled(false);
                                            buttonOne.setText("completed");
                                            pd_view.dismiss();
                                            rl_dd.setVisibility(View.GONE);
                                            view();
                                        }

                                        @Override
                                        public void onError(Error error) {
                                            buttonOne.setText("start");
                                            Toast.makeText(getApplicationContext(), "some_error_occurred" + " " + "1", Toast.LENGTH_SHORT).show();
                                            textViewProgressOne.setText("");
                                            progressbar.setProgress(0);
                                            downloadIdOne = 0;
                                            buttonCancelOne.setEnabled(false);
                                            progressbar.setIndeterminate(false);
                                            buttonOne.setEnabled(true);
                                        }
                                    });
                        }
                    }
                });
                buttonCancelOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PRDownloader.cancel(downloadIdOne);
                        rl_dd.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    /*public void setTextToSpeech(final Context context) {
        mTextToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(Locale.UK);
                    mTextToSpeech.setSpeechRate(0.70f);
                }
                mTextToSpeech.setOnUtteranceCompletedListener(
                        new TextToSpeech.OnUtteranceCompletedListener() {
                            @Override
                            public void onUtteranceCompleted(String utteranceId) {
                                ((AppCompatActivity) context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (mIsSpeaking) {
                                            callbacks.highLightTTS();
                                        }
                                    }
                                });
                            }
                        });
            }
        });
    }*/
    public void view() {

         Config config = AppUtil.getSavedConfig(getApplicationContext());

      //  ReadPosition readPosition = (ReadPosition) getLastNonConfigurationInstance();
         if (config == null)
            config = new Config();
         config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL);

     //   folioReader.setReadPosition(readPosition);
         folioReader.setConfig(config, true)
                .openBook(Strdirpath1);
         config.setShowTts(true);
         config.describeContents();

       // setTextToSpeech(Subject.this);
        //  ReadText();

        pd.cancel();
        progressbar.setIndeterminate(false);
        dialog.dismiss();
        //    }
    }
    public void downloadApi() {
        pd = new ProgressDialog(Subject.this);
        pd.setMessage("Loading");
        pd.show();
        pd.setCancelable(false);


        final APIInterface apiService = APIClient.getClient().create(APIInterface.class);
        Call<ArrayList<BookModel>> call = apiService.createView(classValueStr, mediumStr);
        call.enqueue(new Callback<ArrayList<BookModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BookModel>> call, Response<ArrayList<BookModel>> response) {
                arraylist = new ArrayList<>();
                arraylist = response.body();
                Log.e("arr", String.valueOf(arraylist));
                if (response.body() != null) {
                    pd.cancel();
                    for (int i = 0; i < arraylist.size(); i++) {
                        String sms = response.body().get(i).message;
                        if (sms.equals("0")) {
                            StrUrl = StrUrl + arraylist.get(i).url;
                            viewType = viewType + arraylist.get(i).viewType;
                            title = title + arraylist.get(i).tittle;
                            Url = "http://gathbandhan.co.in" + StrUrl.substring(1);
                            setAdapter();
                        } else {
                            pd.cancel();
                            Toast.makeText(Subject.this, "No Data Found ", Toast.LENGTH_SHORT).show();
                            noDataFound();
                        }
                    }
                    Log.e("StrUrl", StrUrl);
                } else {
                    pd.cancel();
                    Toast.makeText(Subject.this, "No Data Found !!", Toast.LENGTH_SHORT).show();
                    noDataFound();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<BookModel>> call, Throwable t) {
                Log.e("ok", t.getMessage());
            }
        });
    }
    public void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView_book.setLayoutManager(linearLayoutManager);
        BooksAdapter adapter = new BooksAdapter(getApplicationContext(), arraylist);
        recyclerView_book.setAdapter(adapter);
    }
    //Poppup no data found
    public void noDataFound() {
        final Dialog dialog = new Dialog(Subject.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.no_data_poppup);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);


        Button country = dialog.findViewById(R.id.logoutYesbtn);
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Subject.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        dialog.show();
    }

    public void progressBarPoppup() {
        dialog = new Dialog(Subject.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progres_poppup);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);


        progressbar = dialog.findViewById(R.id.progress_bar);
        ImageView cancel = dialog.findViewById(R.id.cancel_progressbar);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }

  /*  public void EnableSelectToSpeach(View view) {

        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);

    }*/
    @Override
    public void onFolioReaderClosed() {
        Log.v("LOG_TAG", "-> onFolioReaderClosed");
    }

    @Override
    public void onHighlight(HighLight highlight, HighLight.HighLightAction type) {

        Toast.makeText(this,
                "highlight id = " + highlight.getUUID() + " type = " + type,
                Toast.LENGTH_SHORT).show();
    }

    /*@Override
    public void saveReadLocator(ReadLocator readLocator) {
        Log.i("LOG_TAG", "-> saveReadLocator -> " + readLocator.toJson());

    }*/


    @Override
    public void saveReadPosition(ReadPosition readPosition) {


    }
}

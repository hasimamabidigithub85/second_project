package com.gropse.epub.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.downloader.Status;
import com.folioreader.Config;
import com.folioreader.FolioReader;
import com.folioreader.model.HighLight;
import com.folioreader.model.ReadPosition;
import com.folioreader.util.AppUtil;
import com.folioreader.util.OnHighlightListener;
import com.folioreader.util.ReadPositionListener;
import com.gropse.epub.Activity.Adapter.BooksAdapter;
import com.gropse.epub.Activity.Adapter.TeacherTrainingAdapter;
import com.gropse.epub.Activity.Model.BookModel;
import com.gropse.epub.Activity.Model.GetTitleModel;
import com.gropse.epub.Activity.Network.APIClient;
import com.gropse.epub.Activity.Network.APIInterface;
import com.gropse.epub.Activity.Utils.Prefs;
import com.gropse.epub.Activity.Utils.Utils;
import com.gropse.epub.Activity.recycler.RecyclerTouchListener;
import com.macreel.epauthi.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherTrainingMatrial extends AppCompatActivity implements OnHighlightListener, ReadPositionListener, FolioReader.OnClosedListener {
    ArrayList<GetTitleModel> arraylist_title;
    String StrUrl = "";
    String title = "";
    String Url = "";
    int position1;
    ProgressDialog pd;
    ImageView view_img_teacher, download_img_teacher;
    RecyclerView recyView_teacher_training;
    ProgressBar progressBar_teacher, progressBar;
    TextView textViewProgressOne_teacher;
    Button buttonOne_teacher, buttonCancelOne_teacher;
    RelativeLayout rl_dd_teacher;
    String dirPath1, extension, ext;
    int downloadIdOne;
    Dialog dialog;
    String ext_url;
    String Strdirpath1 = "";
    String Strdirpath1pdf = "";
    WebView webview;
    private FolioReader folioReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_training_matrial);

        recyView_teacher_training = findViewById(R.id.recyView_teacher_training);


        folioReader = FolioReader.get()
                .setOnHighlightListener(this)
              //  .setReadLocatorListener(this)
                .setReadPositionListener(this)
                .setOnClosedListener(this);

        dirPath1 = getFilesDir().getAbsolutePath() + "/teacher";


        if (AppUtility.isNetworkAvailable(getApplicationContext(), true)) {
            teacherTrainingApi();
        }

        //recyclerView touch listener


        recyView_teacher_training.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyView_teacher_training, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View v, final int position) {


                position1 = position;
                //btn_view = v.findViewById(R.id.btn_view);
                view_img_teacher = v.findViewById(R.id.view_img_teacher);
                //btn_download = v.findViewById(R.id.btn_download);
                download_img_teacher = v.findViewById(R.id.download_img_teacher);
                progressBar_teacher = v.findViewById(R.id.progressBar_teacher_tr);
                textViewProgressOne_teacher = v.findViewById(R.id.textViewProgressOne_teacher);
                buttonOne_teacher = v.findViewById(R.id.buttonOne_teacher);
                buttonCancelOne_teacher = v.findViewById(R.id.buttonCancelOne_teacher);
                rl_dd_teacher = v.findViewById(R.id.rl_dd_teacher);


                download_img_teacher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String path = "http://gathbandhan.co.in" + arraylist_title.get(position).url.substring(1);

                        Uri uri = Uri.parse(path); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                     /*   Strdirpath1 = dirPath1 + "/" + arraylist_title.get(position).tittle + ".epub";
                        File file = new File(Strdirpath1);
                        if (file.exists()) {
                            // openFolder();
                            Toast.makeText(getApplicationContext(), "File Already Downloaded", Toast.LENGTH_SHORT).show();

                        } else {
                            rl_dd_teacher.setVisibility(View.VISIBLE);
                        }*/
                    }

                });
                view_img_teacher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        ext_url = "http://gathbandhan.co.in" + arraylist_title.get(position).url.substring(1);

                        ext = ext_url.substring(ext_url.lastIndexOf("."));

                        if (ext.equals(".pdf")) {
                                Log.e("Url",ext_url);
                                    Intent i=new Intent(getApplicationContext(),WebView_page.class);
                                    Prefs.instanceOf(getApplicationContext()).setUrl(ext_url);
                                    startActivity(i);
                        } else {
                            progressBarPoppup();
                            Strdirpath1 = dirPath1 + "/" + arraylist_title.get(position).tittle + ".epub";
                            File file = new File(Strdirpath1);

                            if (file.exists()) {

                                Config config = AppUtil.getSavedConfig(getApplicationContext());
                                if (config == null)
                                    config = new Config();
                                config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL);
                                config.setShowTts(true);

                                folioReader.setConfig(config, true)
                                        .openBook(Strdirpath1);
                                progressBar.setIndeterminate(false);
                                dialog.dismiss();
                            } else {

                                //  downloadFile();
                                progressBar.setIndeterminate(true);
                                progressBar.getIndeterminateDrawable().setColorFilter(
                                        Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                                if(Status.RUNNING == PRDownloader.getStatus(downloadIdOne)) {
                                    PRDownloader.pause(downloadIdOne);
                                    return;
                                }
                                buttonOne_teacher.setEnabled(false);
                                progressBar.setIndeterminate(true);
                                progressBar.getIndeterminateDrawable().setColorFilter(
                                        Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                                if (Status.PAUSED == PRDownloader.getStatus(downloadIdOne)) {
                                    PRDownloader.resume(downloadIdOne);
                                    return;
                                }
                                downloadIdOne = PRDownloader.download("http://gathbandhan.co.in" + arraylist_title.get(position).url.substring(1), dirPath1, arraylist_title.get(position).tittle + ".epub")

                                        .build()
                                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                                            @Override
                                            public void onStartOrResume() {
                                                progressBar.setIndeterminate(false);
                                                buttonOne_teacher.setEnabled(true);
                                                buttonOne_teacher.setText("pause");

                                                buttonCancelOne_teacher.setEnabled(true);
                                            }
                                        })
                                        .setOnPauseListener(new OnPauseListener() {
                                            @Override
                                            public void onPause() {
                                                buttonOne_teacher.setText("resume");
                                                rl_dd_teacher.setVisibility(View.GONE);
                                            }
                                        })
                                        .setOnCancelListener(new OnCancelListener() {
                                            @Override
                                            public void onCancel() {
                                                buttonOne_teacher.setText("start");
                                                buttonCancelOne_teacher.setEnabled(false);
                                                progressBar.setProgress(0);
                                                textViewProgressOne_teacher.setText("");
                                                downloadIdOne = 0;
                                                progressBar.setIndeterminate(false);
                                                rl_dd_teacher.setVisibility(View.GONE);
                                            }
                                        })
                                        .setOnProgressListener(new OnProgressListener() {
                                            @Override
                                            public void onProgress(Progress progress) {
                                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                                progressBar.setProgress((int) progressPercent);
                                                textViewProgressOne_teacher.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                                                progressBar.setIndeterminate(false);
                                            }
                                        })
                                        .start(new OnDownloadListener() {
                                            @Override
                                            public void onDownloadComplete() {
                                                buttonOne_teacher.setEnabled(false);
                                                buttonCancelOne_teacher.setEnabled(false);
                                                buttonOne_teacher.setText("completed");
                                                //       pd_view1.dismiss();
                                                rl_dd_teacher.setVisibility(View.GONE);

                                                //view metthod
                                                view();
                                            }

                                            @Override
                                            public void onError(Error error) {
                                                buttonOne_teacher.setText("start");
                                                Toast.makeText(getApplicationContext(), "some_error_occurred" + " " + "1", Toast.LENGTH_SHORT).show();
                                                textViewProgressOne_teacher.setText("");
                                                progressBar.setProgress(0);
                                                downloadIdOne = 0;
                                                buttonCancelOne_teacher.setEnabled(false);
                                                progressBar.setIndeterminate(false);
                                                buttonOne_teacher.setEnabled(true);
                                                dialog.dismiss();
                                            }
                                        });


                            }


                        }

                    }
                });
               /* buttonCancelOne_teacher.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        PRDownloader.cancel(downloadIdOne);
                        rl_dd_teacher.setVisibility(View.GONE);
                    }
                });*/
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public void teacherTrainingApi() {
        pd = new ProgressDialog(TeacherTrainingMatrial.this);
        pd.setMessage("Loading");
        pd.setCancelable(false);
        pd.show();
        final APIInterface apiService = APIClient.getClient().create(APIInterface.class);

        Call<ArrayList<GetTitleModel>> call = apiService.getTeacherTraining();
        call.enqueue(new Callback<ArrayList<GetTitleModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetTitleModel>> call, Response<ArrayList<GetTitleModel>> response) {
                arraylist_title = new ArrayList<>();
                arraylist_title = response.body();

                if (response.body() != null) {

                    for (int i = 0; i < arraylist_title.size(); i++) {

                        String sms = response.body().get(i).message;
                        if (sms.equals("0")) {
                            pd.dismiss();
                            StrUrl = StrUrl + arraylist_title.get(i).url;
                            //viewType = viewType + arraylist.get(i).viewType;
                            title = title + arraylist_title.get(i).tittle;
                            Url = "http://gathbandhan.co.in" + StrUrl.substring(1);


                            //booksAdapter.addList(arraylist);
                            setAdapter();
                        } else {
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(), "No Data Found ", Toast.LENGTH_SHORT).show();
                            noDataFound();
                        }
                    }
                    Log.e("StrUrl", StrUrl);
                } else {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "No Data Found !!", Toast.LENGTH_SHORT).show();
                    noDataFound();
                }


            }

            @Override
            public void onFailure(Call<ArrayList<GetTitleModel>> call, Throwable t) {
                Log.e("ok", t.getMessage());
            }
        });

    }


    public void noDataFound() {
        final Dialog dialog = new Dialog(getApplicationContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.no_data_poppup);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);

        Button country = dialog.findViewById(R.id.logoutYesbtn);
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        dialog.show();
    }
    public void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyView_teacher_training.setLayoutManager(linearLayoutManager);
        TeacherTrainingAdapter adapter = new TeacherTrainingAdapter(getApplicationContext(), arraylist_title);
        recyView_teacher_training.setAdapter(adapter);
    }
    public void view() {

        Config config = AppUtil.getSavedConfig(getApplicationContext());
        if (config == null)
            config = new Config();
        config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL);
        config.setShowTts(true);
        folioReader.setConfig(config, true).openBook(Strdirpath1);

        pd.dismiss();
        progressBar_teacher.setIndeterminate(false);
        dialog.dismiss();
    }

    public void progressBarPoppup() {

        dialog = new Dialog(TeacherTrainingMatrial.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progres_poppup);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);


        progressBar = dialog.findViewById(R.id.progress_bar);
        ImageView cancel = dialog.findViewById(R.id.cancel_progressbar);
        progressBar_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Intent i = new Intent(Subject.this, MainActivity.class);
//                startActivity(i);
//                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        dialog.show();
    }


    @Override
    public void onFolioReaderClosed() {

    }
    @Override
    public void onHighlight(HighLight highlight, HighLight.HighLightAction type) {

    }

   /* @Override
    public void saveReadLocator(ReadLocator readLocator) {

    }*/

    @Override
    public void saveReadPosition(ReadPosition readPosition) {

    }

   /* public void EnableSelectToSpeachTeacher(View view) {

        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);

    }*/



}

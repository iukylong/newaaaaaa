package com.example.longlongtran.compehtml;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String MY_URL = "https://www.facebook.com/pg/devpro.vn/posts/?ref=page_internal";

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.Recycler_view);
        configRecyclerView();
        new DownloadTask().execute(MY_URL);
    }

    private void configRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.canScrollVertically();
        recycler.hasFixedSize();
        recycler.setLayoutManager(layoutManager);
    }

    //Download HTML bằng AsynTask
    @SuppressLint("StaticFieldLeak")
    private class DownloadTask extends AsyncTask<String, Void, ArrayList<Article>> {

        @Override
        protected ArrayList<Article> doInBackground(String... strings) {
            Document document;
            ArrayList<Article> listArticle = new ArrayList<>();
            try {
                document = Jsoup.connect(strings[0]).get();
                if (document != null) {
                        Elements elements = document.select("div._427x > div._4-u2 > div._3ccb > div._5pcr > div._1dwg");
                        if (elements != null) {
                            for (Element element1 : elements) {
                                Article article = new Article();
                                Element titleSubject = element1.getElementsByTag("p").first();
                                Element linkSubject = element1.getElementsByTag("a").first();
                                Element descrip = element1.getElementsByTag("span").first();
                                //Parse to model
                                if (titleSubject != null) {
                                    String title = titleSubject.text();
                                    article.setTitle(title);
                                }
                                if (linkSubject != null) {
                                    String link = linkSubject.attr("href");
                                    article.setUrl(link);
                                }
                                if (descrip != null) {
                                    String des = descrip.text();
                                    article.setDecription(des);
                                }
                                listArticle.add(article);
                            }

                        }

                        //Add to list

                    }
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return listArticle;
        }

        @Override
        protected void onPostExecute(ArrayList<Article> articles) {
            super.onPostExecute(articles);
            //Setup data recyclerView
            ArticleAdapter articleAdapter = new ArticleAdapter(MainActivity.this, articles);
            recycler.setAdapter(articleAdapter);
        }
    }
}

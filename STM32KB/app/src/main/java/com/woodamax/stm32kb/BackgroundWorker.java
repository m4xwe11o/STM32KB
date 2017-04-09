package com.woodamax.stm32kb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by maxim on 07.04.2017.
 * This class is used to handle the DB connection for Login and Register
 */

public class BackgroundWorker extends AsyncTask<String, Void, String>{
    Context context;
    Toast toast;
    BackgroundWorker(Context ctx){
        context = ctx;
    }
    DatabaseHelper myDBH;

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://m4xwe11o.ddns.net/MAD-Test/login.php";
        String register_url = "http://m4xwe11o.ddns.net/MAD-Test/register.php";
        String fetch_article_url = "http://m4xwe11o.ddns.net/MAD-Test/fetch_article.php";
        if(type.equals("Login")){
            try{
                String username = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("Register")) {
            try {
                String surname = params[1];
                String firstname = params[2];
                String address = params[3];
                String email = params[4];
                String password = params[5];
                String confpassword = params[6];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("surname", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8") + "&"
                        + URLEncoder.encode("firstname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8") + "&"
                        + URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(address, "UTF-8") + "&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                        + URLEncoder.encode("confpassword", "UTF-8") + "=" + URLEncoder.encode(confpassword, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("FetchArticleDescription")){
            try {
                String article = params[1];
                URL url = new URL(fetch_article_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("article", "UTF-8") + "=" + URLEncoder.encode(article, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        //toast.makeText(context.getApplicationContext(),"Login Status",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String result) {
        //Capture app crash
        if (result == null){result = "None";}
        //toast.makeText(context.getApplicationContext(),result.toString(),Toast.LENGTH_SHORT).show();

        if(result.toString() == ""){
            toast.makeText(context.getApplicationContext(),"DB query not sucessfull",Toast.LENGTH_SHORT).show();
        }
        if(result.contains("Query")){
            toast.makeText(context.getApplicationContext(),"Query sucessfull",Toast.LENGTH_SHORT).show();
            getNewArticles(result);
        }
        if(result.contains("OK")){
            toast.makeText(context.getApplicationContext(),result.toString(),Toast.LENGTH_SHORT).show();
            if(result.contains("Yes")){
                Intent intent = new Intent (context.getApplicationContext(), ArticleScreen.class);
                intent.putExtra(ArticleScreen.EXTRA_MESSAGE,result.toString());
                context.startActivity(intent);
            }else{
                toast.makeText(context.getApplicationContext(),"Not allowed to login",Toast.LENGTH_SHORT).show();
            }
        }else if (result.contains("Insert")){
            toast.makeText(context.getApplicationContext(),result.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    //Creepy little function - it makes all the magic needed to parse the values correct
    //Wollmice said DON'T touch it !
    private void getNewArticles(String query) {
        //myDBH = new DatabaseHelper(context);
        query=query.replaceAll("]", "X\n");
        toast.makeText(context.getApplicationContext(),query,Toast.LENGTH_SHORT).show();
        Scanner scanner = new Scanner(query);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String title = line.substring(line.indexOf("{")+1,line.indexOf("}"));
            toast.makeText(context,title,Toast.LENGTH_SHORT).show();
            String desc = line.substring(line.indexOf("(")+1,line.indexOf(")"));
            toast.makeText(context,desc,Toast.LENGTH_SHORT).show();
            //myDBH.insertData(title,desc," "," ");
            insertIntoDatabase(title,desc);
        }
    }

    //Creepy little function too - it makes all the checks needed to insert data only if its not there
    private void insertIntoDatabase(String title, String desc) {
        myDBH = new DatabaseHelper(context);
        Cursor res = myDBH.getArticleDescription();
        if(res.getCount() == 0) {
            // show message
            toast.makeText(context,"Inserting values",Toast.LENGTH_SHORT).show();
            myDBH.insertData(title,desc," "," ");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            if(title.equals(res.getString(1)) || desc.equals(res.getString(2))){
                toast.makeText(context,title,Toast.LENGTH_SHORT).show();
                toast.makeText(context," Title value allready exists",Toast.LENGTH_SHORT).show();
                toast.makeText(context,desc,Toast.LENGTH_SHORT).show();
                toast.makeText(context," Desc value allready exists",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        toast.makeText(context,"Inserting values",Toast.LENGTH_SHORT).show();
        myDBH.insertData(title,desc," "," ");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

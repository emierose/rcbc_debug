package bluetooth.em.com.projectcountry;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
    }

    private void init() {
//        URL url = null;
//        try {
//            url = new URL("http://some-server");
//
//            HttpURLConnection conn = null;
//            conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//// read the response
//        System.out.println("Response Code: " + conn.getResponseCode());
//        InputStream in = new BufferedInputStream(conn.getInputStream());
//            String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
//        System.out.println(response);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        new AsyncTask<Void, Void, String>(){
//            protected String doInBackground(Void[] params) {
//                String response="";
//                try {
//                    response=new HttpRequest("https://mobilereports.globalpinoyremittance.com/portal/intl_airlines").preparePost().sendAndReadString();
//                } catch (Exception e) {
//                    response=e.getMessage();
//                }
//                System.out.println("RESPONSE:"+response);
//                return response;
//
//            }
//            protected void onPostExecute(String result) {
//                //do something with response
//            }
//        }.execute();
//        StringBuffer chaine = new StringBuffer("");
//        try{
//            URL url = new URL("https://mobilereports.globalpinoyremittance.com/portal/intl_airlines");
//            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
////            connection.setRequestProperty("User-Agent", "");
//            connection.setRequestMethod("POST");
//            connection.setDoInput(true);
//            connection.connect();
//
//            InputStream inputStream = connection.getInputStream();
//
//            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                chaine.append(line);
//            }
//            System.out.println(chaine);
//
//        } catch (IOException e) {
//            // writing exception to log
//            e.printStackTrace();
//        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

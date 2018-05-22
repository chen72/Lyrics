package sg.edu.rp.webservices.lyrics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText etName,etSong;
    TextView tvShow;
    Button btnGet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.etName);
        etSong = (EditText)findViewById(R.id.etSong);
        tvShow = (TextView)findViewById(R.id.tvShow);
        btnGet = (Button)findViewById(R.id.btnGet);



        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Code for step 1 start
                HttpRequest request = new HttpRequest
                        ("https://orion.apiseeds.com/api/music/lyric/"+etName.getText().toString().trim()+"/"+etSong.getText().toString().trim()+"?apikey=vVMdYKrAJbAQ0Az1jwiwSMLDp1XO01ZzmoquOUMmF2dfB0HcygYUAkayYEvDEquh ");
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("GET");
                request.execute();
                // Code for step 1 end

            }
        });






    }
    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here

                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        JSONObject resultObj = (JSONObject) jsonObject.get("result");

                        JSONObject artist = (JSONObject) resultObj.get("artist");
                        String name = (String) artist.get("name");

                        JSONObject track = (JSONObject) resultObj.get("track");
                        String trackname = (String)track.get("name") ;
                        String text = (String)track.get("text");
                        tvShow.setText("Artist : "+name+"\nSong: "+trackname+"\nLyrics: "+text);

                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }


                }
            };
    // Code for step 2 end


}

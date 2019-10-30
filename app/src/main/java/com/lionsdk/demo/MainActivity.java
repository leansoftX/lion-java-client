package com.lionsdk.demo;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lion.client.sdk.LionClient;
import com.lion.client.sdk.LionUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.transition.Visibility;

import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.hc.core5.http.ParseException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private  String currentUser = "未登陆";
    private  TextView tvLoginUser;

    public void onClick(View view) {
        TextView tvAPIUrl = findViewById(R.id.tbAPIUrl);
        String apiUrl = tvAPIUrl.getText().toString();

        TextView tvSDKKey = findViewById(R.id.tbSDKKey);
        String sdkKey = tvSDKKey.getText().toString();

        TextView tvUser = findViewById(R.id.tvLoginUser);
        currentUser = tvUser.getText().toString();

        switch(view.getId()) {
            case R.id.btnAllEnable:
                Toast.makeText(this,"btnAllEnable",Toast.LENGTH_SHORT).show();
                try {
                    boolean isEnable = GetAllUserEnableFeatureFlag(apiUrl,sdkKey);
                    Button btn = findViewById(R.id.btn_test_fuction1);
                    if(isEnable)
                    {
                        btn.setVisibility(View.VISIBLE);
                    }
                    else
                        btn.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.btnLogin:
                Toast.makeText(this,"btnLogin",Toast.LENGTH_SHORT).show();
                EditText etUser = findViewById(R.id.tbUserEmail);
                String newUser = etUser.getText().toString();
                if(!newUser.equals("请输入要登陆的用户邮箱")) {
                    currentUser = newUser;
                    this.SetLoginStatus(tvUser, currentUser);
                }
                break;
            case R.id.btnLoginOff:
                Toast.makeText(this,"btnLogin",Toast.LENGTH_SHORT).show();
                currentUser = "未登陆";
                this.SetLoginStatus(tvUser,currentUser);
                break;
            case R.id.btnLxmDisable:
                Toast.makeText(this,"btnLxmDisable",Toast.LENGTH_SHORT).show();
                currentUser = ((Button)findViewById(R.id.btnLxmDisable)).getText().toString();
                this.SetLoginStatus(tvUser, currentUser);

                try {
                    boolean isEnable = this.GetTestFuction1UserFeatureFlag(currentUser,apiUrl,sdkKey);
                    Button btn = findViewById(R.id.btn_test_fuction2);
                    if(isEnable)
                    {
                        btn.setVisibility(View.VISIBLE);
                    }
                    else
                        btn.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.btnLxmEnable:
                Toast.makeText(this,"btnLxmEnable",Toast.LENGTH_SHORT).show();
                currentUser = ((Button)findViewById(R.id.btnLxmEnable)).getText().toString();
                this.SetLoginStatus(tvUser, currentUser);
                Toast.makeText(this,currentUser,Toast.LENGTH_SHORT).show();

                try {
                    boolean isEnable = this.GetTestFuction1UserFeatureFlag(currentUser,apiUrl,sdkKey);
                    Button btn = findViewById(R.id.btn_test_fuction2);
                    if(isEnable)
                    {
                        btn.setVisibility(View.VISIBLE);
                    }
                    else
                        btn.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            default:
                Toast.makeText(this,String.valueOf(view.getId()),Toast.LENGTH_SHORT).show();
        }
    };

    protected  void SetLoginStatus(TextView tvUser,String userName)
    {
        EditText etUser = findViewById(R.id.tbUserEmail);
        currentUser = userName;
        tvUser.setText(currentUser);

        Button btn = findViewById(R.id.btn_test_fuction1);
        btn.setVisibility(View.INVISIBLE);

        btn = findViewById(R.id.btn_test_fuction2);
        btn.setVisibility(View.INVISIBLE);

        btn = findViewById(R.id.btn_test_fuction4);
        btn.setVisibility(View.INVISIBLE);

        btn = findViewById(R.id.btn_test_fuction3);
        btn.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnAllEnable);
        btn.setOnClickListener(this);

        btn = findViewById(R.id.btnLogin);
        btn.setOnClickListener(this);

        btn = findViewById(R.id.btnLoginOff);
        btn.setOnClickListener(this);

        btn = findViewById(R.id.btnLxmDisable);
        btn.setOnClickListener(this);

        btn = findViewById(R.id.btnLxmEnable);
        btn.setOnClickListener(this);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public boolean GetAllUserEnableFeatureFlag(String apiUrl,String sdkKey) throws IOException, ParseException {
        LionClient client = new LionClient(sdkKey,apiUrl);//"sdk-fd36d100-a98c-42b8-9e2d-af535e06acb2"
        final boolean result = client.BoolVariation("test-fuction1");
        return result;
    }

    public boolean GetTestFuction1UserFeatureFlag(String userKey,String apiUrl,String sdkKey) throws IOException, ParseException {
        LionUser lionUser = new LionUser(userKey);
        lionUser.setName(userKey);
        lionUser.setEmail(userKey);
        HashMap<String, String> map = lionUser.getCustom();
        map.put("Gender", "male");
        LionClient client = new LionClient(apiUrl,sdkKey);
        final boolean result = client.BoolVariation("test-fuction2", lionUser);
        return  result;
    }
}

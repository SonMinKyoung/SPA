package com.example.spa.Request;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
public class SignupRequest extends StringRequest{

    //서버 URL 설정
    final static private String URL= "http://alsrud107.dothome.cl.kr/Register.php";
    private Map<String, String> map;

    public SignupRequest(String userID, String userPassword, String userName, String userPhone,
                         String userEmail, String userCarnumber, String listener){
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword",userPassword);
        map.put("userName",userName);
        map.put("userPhone",userPhone);
        map.put("userEmail",userEmail);
        map.put("userCarnumber",userCarnumber);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
